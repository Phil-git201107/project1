package com.chiczu.project1.web;

import com.chiczu.project1.pojo.Item;
import com.chiczu.project1.pojo.User;
import com.chiczu.project1.service.ItemService;
import com.chiczu.project1.service.UserService;
import com.chiczu.project1.service.impl.ItemServiceImpl;
import com.chiczu.project1.service.impl.UserServiceImpl;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @User: Nick
 * @Date: 2020-11-08 15:49
 */
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String code = req.getParameter("code");
        String token = (String)req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        User loginuser = userService.login(new User(null, username, password));
        if(token != null && token.equalsIgnoreCase(code)){
            if(loginuser == null){
                req.setAttribute("msg","登入的帳號或密碼錯誤");
                req.setAttribute("username",username);
                req.getRequestDispatcher("index.jsp").forward(req,resp);
            }else{
                req.getSession().setAttribute("user",loginuser);
                ItemService itemService =new ItemServiceImpl();
                List<Item> itemList = itemService.queryItems();
                req.setAttribute("page",itemList);
                //跳轉到庫存表
                req.getRequestDispatcher("/pages/inventory.jsp").forward(req,resp);

            }
        }else{
            req.setAttribute("msg","驗證碼錯誤");
            req.setAttribute("username",username);
            req.getRequestDispatcher("index.jsp").forward(req,resp);
        }


    }
}
