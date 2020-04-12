package com.itheima.baseservlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 *
 */
@WebServlet(urlPatterns="/user")
public class UserServlet extends BaseServlet {

    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("UserServlet.register：注册功能");
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("UserServlet.login：登录功能");
    }

}
