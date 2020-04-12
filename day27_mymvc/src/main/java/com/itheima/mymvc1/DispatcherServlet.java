package com.itheima.mymvc1;

import com.itheima.util.ClassScannerUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 *
 */
//@WebServlet(urlPatterns="*.d")
public class DispatcherServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //获取客户端请求路径
            String uri = request.getRequestURI();
            String contextPath = request.getContextPath();
            String requestPath = uri.substring(contextPath.length(), uri.lastIndexOf("."));

            //找到@RequestMapping配置值,和requestPath相等的方法,然后调用它
            List<Class<?>> classList = ClassScannerUtils.getClasssFromPackage("com.itheima.web");
            for (Class<?> clazz : classList) {
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    boolean isMappingMethod = method.isAnnotationPresent(RequestMapping.class);
                    if (isMappingMethod){
                        RequestMapping mapping = method.getAnnotation(RequestMapping.class);
                        String mappingPath = mapping.value();
                        if (requestPath.equals(mappingPath)){
                            method.invoke(clazz.newInstance(),request,response);
                            return;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
