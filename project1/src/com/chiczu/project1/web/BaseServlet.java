package com.chiczu.project1.web;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @User: Nick
 * @Date: 2020-10-26 16:05
 */
public abstract class BaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 解决post請求中文亂碼問題
        // 一定要在獲取請求參數之前調用才有效
        req.setCharacterEncoding("UTF-8");
        //解決響應中文亂碼問題
        resp.setContentType("text/html;charset=UTF-8");

        String action = req.getParameter("action");
        try {
            //通過action來辨識字串,獲取相應的業務 方法的代理物件
            Method method = this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);

            //調用目標業務 的方法
            method.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
//            throw new RuntimeException(e);//把異常拋給filter過濾器
        }
    }
}
