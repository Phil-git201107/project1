package com.chiczu.project1.service.impl;

import com.chiczu.project1.dao.ItemDao;
import com.chiczu.project1.dao.impl.ItemDaoImpl;
import com.chiczu.project1.pojo.Item;
import com.chiczu.project1.service.ItemService;

import java.util.List;

/**
 * @User: Nick
 * @Date: 2020-11-08 22:24
 */
public class ItemServiceImpl implements ItemService {

    private ItemDao itemDao = new ItemDaoImpl();

    @Override
    public List<Item> queryItems() {
        return itemDao.queryItems();
    }

    @Override
    public int addItemSale(Item item,Integer quantity) {
        return itemDao.addItemSale(item,quantity);
    }

    @Override
    public int addItemStock(Item item, Integer amount) {
        return itemDao.addItemStock(item,amount);
    }

    @Override
    public Item queryItemById(Integer id) {
        return itemDao.queryItemById(id);
    }
}
