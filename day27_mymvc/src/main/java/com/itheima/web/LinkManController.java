package com.itheima.web;

import com.itheima.mymvc1.RequestMapping;
import com.itheima.mymvc2.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LinkManController {

    @RequestMapping("/linkMan/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("LinkManController.delete：删除联系人");
    }

    @RequestMapping("/linkMan/queryAll")
    public void queryAll(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("LinkManController.queryAll：查询所有联系人");
    }
}
