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
@WebServlet(urlPatterns="/linkMan")
public class LinkManServlet extends BaseServlet {
    public void delete(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("LinkManServlet.delete方法：删除联系人");
    }

    public void queryAll(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("LinkManServlet.queryAll：查询所有联系人");
    }

}