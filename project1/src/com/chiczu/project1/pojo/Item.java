package com.chiczu.project1.pojo;

/**
 * @User: Nick
 * @Date: 2020-11-08 20:20
 */
public class Item {

    private Integer id;
    private String category;
    private String itemno;
    private String name;
    private Integer purchaseprice;
    private Integer retailprice;
    private String imgPath="static/img/default.jpg";
    private Integer sales;
    private Integer inventory;


    public Item() {
    }

    public Item(Integer id, String category, String itemno, String name, Integer purchaseprice, Integer retailprice, String imgPath) {
        this.id = id;
        this.category = category;
        this.itemno = itemno;
        this.name = name;
        this.purchaseprice = purchaseprice;
        this.retailprice = retailprice;
        //要求圖書封面圖片路徑不得為空
        if(imgPath != null && !"".equals(imgPath)) {
            this.imgPath = imgPath;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getItemno() {
        return itemno;
    }

    public void setItemno(String itemno) {
        this.itemno = itemno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPurchaseprice() {
        return purchaseprice;
    }

    public void setPurchaseprice(Integer purchaseprice) {
        this.purchaseprice = purchaseprice;
    }

    public Integer getRetailprice() {
        return retailprice;
    }

    public void setRetailprice(Integer retailprice) {
        this.retailprice = retailprice;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", itemno='" + itemno + '\'' +
                ", name='" + name + '\'' +
                ", purchaseprice=" + purchaseprice +
                ", retailprice=" + retailprice +
                ", imgPath='" + imgPath + '\'' +
                ", sales=" + sales +
                ", inventory=" + inventory +
                '}';
    }
}
