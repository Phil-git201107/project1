package com.chiczu.project1.test;

import com.chiczu.project1.dao.ItemDao;
import com.chiczu.project1.dao.impl.ItemDaoImpl;
import com.chiczu.project1.pojo.Item;
import org.junit.Test;

import java.util.List;

/**
 * @User: Nick
 * @Date: 2020-11-08 21:39
 */
public class ItemDaoTest {

    @Test
    public void testQueryItems(){
        ItemDao itemDao = new ItemDaoImpl();
        List<Item> list = itemDao.queryItems();
        for(Item i:list){
            System.out.println(i);
        }

    }
}
