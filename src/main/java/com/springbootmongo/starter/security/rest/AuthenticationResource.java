package com.springbootmongo.starter.security.rest;

import com.springbootmongo.starter.security.jwt.JwtToken;
import com.springbootmongo.starter.security.jwt.TokenProvider;
import com.springbootmongo.starter.web.dto.LoginDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationResource {

    private final Logger logger = LoggerFactory.getLogger(AuthenticationResource.class);

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Method for authenticating user.
     *
     * @param loginDTO - login transfer object.
     * @param httpServletResponse - http response object
     * @return Response entity object. String response.
     */
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authorize(@Valid @RequestBody LoginDTO loginDTO,
                                       HttpServletResponse httpServletResponse)
    {
        logger.debug("Credentials: {}", loginDTO.toString());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());

        try
        {
            Authentication authentication = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            boolean rememberMe = (loginDTO.getRememberMe() == null) ? false : loginDTO.getRememberMe();

            String token = tokenProvider.createToken(authentication, rememberMe);
            httpServletResponse.addHeader("Authorization", "Bearer " + token);

            return ResponseEntity.ok(new JwtToken(token));
        }
        catch (AuthenticationException e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}
