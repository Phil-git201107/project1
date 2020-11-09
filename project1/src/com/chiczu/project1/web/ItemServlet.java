package com.chiczu.project1.web;

import com.chiczu.project1.pojo.Item;
import com.chiczu.project1.service.ItemService;
import com.chiczu.project1.service.impl.ItemServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @User: Nick
 * @Date: 2020-11-08 21:19
 */
public class ItemServlet extends BaseServlet{
    ItemService itemService =new ItemServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Item> itemList = itemService.queryItems();

        req.setAttribute("page",itemList);
        req.getRequestDispatcher("pages/inventory.jsp").forward(req,resp);
    }
}
