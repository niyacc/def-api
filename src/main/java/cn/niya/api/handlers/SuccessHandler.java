package cn.niya.api.handlers;

import cn.niya.api.util.JWTTokenUtil;
import cn.niya.api.util.JsonResult;
import cn.niya.api.util.ResponseUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class SuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // sent token
        String name = authentication.getName();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        StringBuilder stringBuilder = new StringBuilder();
        for (GrantedAuthority authority : authorities) {
            stringBuilder.append(authority);
            stringBuilder.append(",");
        }
        StringBuilder stringBuilder1 = stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
        String token = JWTTokenUtil.createToken(name, stringBuilder1.toString());
        response.setHeader("Authorization" , "Bearer " + token);
        ResponseUtil.responseOut(response, JsonResult.ok(authentication));
    }
}
