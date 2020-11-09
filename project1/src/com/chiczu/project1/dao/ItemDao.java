package com.chiczu.project1.dao;

import com.chiczu.project1.pojo.Item;

import java.awt.print.Book;
import java.util.List;

/**
 * @User: Nick
 * @Date: 2020-11-08 20:29
 */
public interface ItemDao {

    public int addItem(Item item);

    public int addItemCount(Item item, Integer addcount);

    public List<Item> queryItems();

}
