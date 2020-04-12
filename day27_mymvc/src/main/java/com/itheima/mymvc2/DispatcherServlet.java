package com.itheima.mymvc2;

import com.itheima.mymvc1.RequestMapping;
import com.itheima.util.ClassScannerUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@WebServlet(urlPatterns = "*.do",loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
    private Map<String, MvcMethod> map = new HashMap<>();

    @Override
    public void init() throws ServletException {
        try {
            List<Class<?>> classList = ClassScannerUtils.getClasssFromPackage("com.itheima.web");
            for (Class<?> clazz : classList) {
                boolean isController = clazz.isAnnotationPresent(Controller.class);
                if (!isController) {
                    continue;
                }
                Object obj = clazz.newInstance();

                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    boolean isMappingMethod = method.isAnnotationPresent(RequestMapping.class);
                    if (isMappingMethod) {
                        RequestMapping mapping = method.getAnnotation(RequestMapping.class);
                        String mappingPath = mapping.value();
                        MvcMethod mvcMethod = new MvcMethod(method, obj);
                        map.put(mappingPath, mvcMethod);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String uri = request.getRequestURI();
            String contextPath = request.getContextPath();
            String requestPath = uri.substring(contextPath.length(), uri.lastIndexOf("."));
            MvcMethod mvcMethod = map.get(requestPath);
            Method method = mvcMethod.getMethod();
            Object obj = mvcMethod.getObj();
            method.invoke(obj, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
