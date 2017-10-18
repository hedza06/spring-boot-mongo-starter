package com.springbootmongo.starter.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class TokenProvider {

    private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    @Value("${jwt.authKey}")
    private String authoritiesKey;

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long tokenValidityInSeconds;

    @Value("${jwt.rememberMeExpiration}")
    private long rememberMeTokenValidityInSeconds;

    /**
     * Method for creating JWT token
     *
     * @param authentication - spring core authentication object
     * @param rememberMe - flag to remember user.
     * @return Token string
     */
    public String createToken(Authentication authentication, Boolean rememberMe)
    {
        String authorities = authentication.getAuthorities()
                .stream()
                .map(authority -> authority.getAuthority())
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenValidityInSeconds * 1000);
        if (rememberMe) {
            validity = new Date(now + this.rememberMeTokenValidityInSeconds * 1000);
        }

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(authoritiesKey, authorities)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .setExpiration(validity)
                .compact();
    }

    /**
     * Method for getting authentication details from provided token
     *
     * @param token - token to be parsed.
     * @return Authentication object.
     */
    Authentication getAuthentication(String token)
    {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        String principal = claims.getSubject();
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(authoritiesKey).toString().split(","))
                .map(authority -> new SimpleGrantedAuthority(authority))
                .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    /**
     * Method for validating token.
     *
     * @param token - provided token string
     * @return true | false
     */
    boolean validateToken(String token)
    {
        try
        {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        }
        catch (SignatureException e) {
            logger.error("TOKEN NOT VALID: " + e.getMessage());
            return false;
        }
    }
}
