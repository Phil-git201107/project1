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
    /*
    增加品項
     */
    @Override
    public int addItem(Item item) {
        String sql = "INSERT INTO commodity (category,itemno,`name`,purchaseprice,retailprice,photo) " +
                "VALUES( ?,?,?,?,?,?)";
        return update(sql,item.getCategory(),item.getItemno(),item.getName(),item.getPurchaseprice(),
                item.getRetailprice(),item.getImgPath());
    }
    /*
    進貨,增加庫存
     */
    @Override
    public int addItemStock(Item item, Integer amount) {
        String sql = "update `storage` set inventory = ? where itemno = ?";
        return update(sql,item.getInventory()+amount,item.getItemno());
    }
    /*
    將單品銷售,加入總銷售
     */
    public int addItemSale(Item item,Integer quantity){
        String sql = "update `storage` set sales = ?,inventory = ? where itemno = ?";
        return update(sql,item.getSales()+quantity,item.getInventory()-quantity,item.getItemno());
    }
    /*
    獲得商品總表
     */
    @Override
    public List<Item> queryItems() {
        String sql = "select id,category,c.itemno,c.`name`,purchaseprice,retailprice,s.`sales` ,s.`inventory` \n" +
                "FROM commodity c\n" +
                "INNER JOIN `storage` s\n" +
                "ON c.`itemno`=s.`itemno`";
        return queryForList(Item.class,sql);
    }

    @Override
    public Item queryItemById(Integer id) {
        String sql = "SELECT id,category,c.itemno,c.`name`,purchaseprice,retailprice,s.`sales` ,s.`inventory` " +
                "FROM commodity c INNER JOIN `storage` s " +
                "ON c.`itemno`=s.`itemno` WHERE id = ?";
        return queryForOne(Item.class,sql,id);
    }
}
