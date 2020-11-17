package com.chiczu.project1.web;

import com.chiczu.project1.pojo.Item;
import com.chiczu.project1.service.ItemService;
import com.chiczu.project1.service.impl.ItemServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @User: Nick
 * @Date: 2020-11-08 21:19
 */
public class ItemServlet extends BaseServlet{
    ItemService itemService =new ItemServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Item> itemList = itemService.queryItems();

        req.getSession().setAttribute("page",itemList);
        req.getRequestDispatcher("/pages/inventory.jsp").forward(req,resp);
    }
    protected void updateSales(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        Integer quantity = Integer.parseInt(req.getParameter("quantity"));

        Item queryItem = itemService.queryItemById(id);
        int addItemSale = itemService.addItemSale(queryItem, quantity);

        HashMap<String, Object> resultMap = new HashMap<>();
        //因銷售量已更新,故需重新獲取queryItem
        Item queryItemNew = itemService.queryItemById(id);
        resultMap.put("itemname",queryItemNew.getName());
        resultMap.put("quantity",quantity);
        resultMap.put("sales",queryItemNew.getSales());
        resultMap.put("stock",queryItemNew.getInventory());
        Gson gson = new Gson();
        String toJson = gson.toJson(resultMap);

        resp.getWriter().write(toJson);
    }
    protected void updateStock(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        Integer amount = Integer.parseInt(req.getParameter("quantity"));

        Item queryItem = itemService.queryItemById(id);
        int addItemStock = itemService.addItemStock(queryItem, amount);

        HashMap<String, Object> resultMap = new HashMap<>();
        //因庫存量已更新,故需重新獲取queryItem
        Item queryItemNew = itemService.queryItemById(id);
        resultMap.put("itemname",queryItemNew.getName());
        resultMap.put("quantity",amount);
        resultMap.put("stock",queryItemNew.getInventory());

        Gson gson = new Gson();
        String toJson = gson.toJson(resultMap);
        resp.getWriter().write(toJson);
    }
}
