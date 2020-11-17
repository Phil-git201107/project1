package com.chiczu.project1.service;

import com.chiczu.project1.pojo.Item;

import java.awt.print.Book;
import java.util.List;

/**
 * @User: Nick
 * @Date: 2020-11-08 21:23
 */
public interface ItemService {

    public List<Item> queryItems();

    public int addItemSale(Item item,Integer quantity);

    public int addItemStock(Item item,Integer count);

    public Item queryItemById(Integer id);
}
