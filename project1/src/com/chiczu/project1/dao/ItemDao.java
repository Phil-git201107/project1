package com.chiczu.project1.dao;

import com.chiczu.project1.pojo.Item;

import java.awt.print.Book;
import java.util.List;

/**
 * @User: Nick
 * @Date: 2020-11-08 20:29
 */
public interface ItemDao {
    /*
    增加品項
     */
    public int addItem(Item item);
    /*
    將單品銷售,加入總銷售
     */
    public int addItemSale(Item item,Integer quantity);
    /*
    進貨,增加庫存
     */
    public int addItemStock(Item item, Integer addcount);
    /*
    獲得商品總表
     */
    public List<Item> queryItems();

    public Item queryItemById(Integer id);

}
