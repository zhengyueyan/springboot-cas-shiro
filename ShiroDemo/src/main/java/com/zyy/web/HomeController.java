package com.zyy.web;

import com.zyy.config.shiroCas.ShiroCasConfiguration;
import com.zyy.entity.UserInfo;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;

/**
 * @Description:
 * @Author: 郑玥延
 * @Date: 13:40 2018/6/7
 */
@Controller
public class HomeController {

    @RequestMapping({"/","/index"})
    public String index(){
        return"/index";
    }

    @RequestMapping("/403")
    public String unauthorizedRole(){
        System.out.println("------没有权限-------");
        return "403";
    }

    @RequestMapping(value="/login",method= RequestMethod.GET)
    public String loginForm(Model model){
        model.addAttribute("user", new UserInfo());
        return "redirect:" + ShiroCasConfiguration.loginUrl;
    }


    @RequestMapping(value = "logout", method = { RequestMethod.GET, RequestMethod.POST })
    public String loginout(HttpSession session){
        return "redirect:"+ShiroCasConfiguration.logoutUrl;
    }

}
