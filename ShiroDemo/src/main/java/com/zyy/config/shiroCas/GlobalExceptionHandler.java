package com.zyy.config.shiroCas;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * @package: com.zyy.config.shiroCas
 * @description:
 * @author: zhengyueyan
 * @date: 16:55 2018/7/18
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = UnauthorizedException.class)
    public String unauthorizedHandler(HttpServletRequest request, Exception exception) throws Exception {
        return "403";
    }

    @ExceptionHandler(value = Exception.class)
    public String allExceptionHandler(HttpServletRequest request, Exception exception) throws Exception {
        exception.printStackTrace();
        return "404";
    }

}