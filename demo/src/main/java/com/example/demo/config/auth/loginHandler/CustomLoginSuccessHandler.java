package com.example.demo.config.auth.loginHandler;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.config.auth.jwt.JwtProperties;
import com.example.demo.config.auth.jwt.JwtTokenProvider;
import com.example.demo.config.auth.jwt.TokenInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomLoginSuccessHandler implements  AuthenticationSuccessHandler{


	JwtTokenProvider jwtTokenProvider;
	public CustomLoginSuccessHandler(){
		jwtTokenProvider = new JwtTokenProvider();
	}


	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		//----------------------------------------------------------------
		//JWT Token
		//----------------------------------------------------------------
		TokenInfo tokenInfo =  jwtTokenProvider.generateToken(authentication);
		Cookie cookie = new Cookie(JwtProperties.COOKIE_NAME,tokenInfo.getAccessToken());
		cookie.setMaxAge(JwtProperties.EXPIRATION_TIME);
		cookie.setPath("/");
		response.addCookie(cookie);






		System.out.println("CustomLoginSuccessHandler's onAuthenticationSuccess! ");
		Collection<? extends GrantedAuthority> collection =   authentication.getAuthorities();
		
		
			collection.forEach((role)->{	
				try {	
					System.out.println("role : " + role.getAuthority());
					String role_str = role.getAuthority();
					if(role_str.equals("ROLE_USER")) {
						
						System.out.println("USER 페이지로 이동!");
						response.sendRedirect(request.getContextPath()+"/user");
						return ;
					}else if(role_str.equals("ROLE_MEMBER")){
						System.out.println("MEMBER 페이지로 이동!");
						response.sendRedirect(request.getContextPath()+"/member");
						return ;
					}
					else if(role_str.equals("ROLE_ADMIN")) {
						System.out.println("ADMIN 페이지로 이동!");	
						response.sendRedirect(request.getContextPath()+"/admin");
						return ;
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			
			} );
		
		
		
	}

}
