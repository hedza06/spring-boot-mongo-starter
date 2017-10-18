package com.springbootmongo.starter.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {

    private final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    private TokenProvider tokenProvider;

    JwtFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException
    {
        try
        {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            String token = this.resolveToken(httpServletRequest);
            if (StringUtils.hasText(token))
            {
                if (this.tokenProvider.validateToken(token))
                {
                    Authentication authentication = this.tokenProvider.getAuthentication(token);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }
        catch (ExpiredJwtException e) {
            logger.error("Security exception for user {} - {}", e.getClaims().getSubject(), e.getMessage());
            ((HttpServletResponse) servletResponse).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    /**
     * Method for resolving token - check if token exists in authorization.
     *
     * @param httpServletRequest - http request
     * @return resolved token | null
     */
    private String resolveToken(HttpServletRequest httpServletRequest)
    {
        String bearerToken = httpServletRequest.getHeader(JwtConfigurer.AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        String token = httpServletRequest.getParameter(JwtConfigurer.AUTHORIZATION_TOKEN);
        if (StringUtils.hasText(token)) {
            return token;
        }
        return null;
    }
}
