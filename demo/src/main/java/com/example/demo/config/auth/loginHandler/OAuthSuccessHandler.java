package com.example.demo.config.auth.loginHandler;

import com.example.demo.config.auth.jwt.JwtProperties;
import com.example.demo.config.auth.jwt.JwtTokenProvider;
import com.example.demo.config.auth.jwt.TokenInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

    JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //----------------------------------------------------------------
        //JWT Token
       //----------------------------------------------------------------

        System.out.println("OAuthSuccessHandler Authentication : " + authentication);
        TokenInfo tokenInfo =  jwtTokenProvider.generateToken(authentication);
        System.out.println("TOKEN : " + tokenInfo);
        Cookie cookie = new Cookie(JwtProperties.COOKIE_NAME,tokenInfo.getAccessToken());
        cookie.setMaxAge(JwtProperties.EXPIRATION_TIME);
        cookie.setPath("/");
        response.addCookie(cookie);


        response.sendRedirect("/");
    }
}
