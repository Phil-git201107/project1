package com.chiczu.project1.dao.impl;

import com.chiczu.project1.dao.BaseDao;
import com.chiczu.project1.dao.ItemDao;
import com.chiczu.project1.pojo.Item;

import java.awt.print.Book;
import java.util.List;

/**
 * @User: Nick
 * @Date: 2020-11-08 20:35
 */
public class ItemDaoImpl extends BaseDao implements ItemDao {
    @Override
    public int addItem(Item item) {
        String sql = "INSERT INTO commodity (category,itemno,`name`,purchaseprice,retailprice,photo) " +
                "VALUES( ?,?,?,?,?,?)";
        return update(sql,item.getCategory(),item.getItemno(),item.getName(),item.getPurchaseprice(),
                item.getRetailprice(),item.getImgPath());
    }

    @Override
    public int addItemCount(Item item, Integer addcount) {
        String sql = "update `storage` set inventory = ? where id = ?";
        return update(sql,item.getInventory()+addcount,item.getId());
    }

    @Override
    public List<Item> queryItems() {
        String sql = "SELECT id,category,c.itemno,c.`name`,purchaseprice,retailprice,s.`sales` ,s.`inventory` \n" +
                "FROM commodity c\n" +
                "INNER JOIN `storage` s\n" +
                "ON c.`itemno`=s.`itemno`";
        return queryForList(Item.class,sql);
    }
}
