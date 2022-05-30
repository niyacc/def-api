package cn.niya.api.handlers;

import cn.niya.api.util.JsonResult;
import cn.niya.api.util.ResponseUtil;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationNotLoginHandler implements org.springframework.security.web.AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseUtil.responseOut(response , JsonResult.unAuth());
    }
}
