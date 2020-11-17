<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Phil
  Date: 2020/11/8
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品管理頁</title>
    <%@ include file="/pages/common/head.jsp"%>
</head>
<body>
    <table id="container">
        <thead>
            <tr>
                <td colspan="9" align="center">商品總表</td>
            </tr>
            <tr>
                <td>工作項目</td>
                <td id="function_frame" colspan="8"></td>
            </tr>
            <tr id="columnName">
                <th>類別</th>
                <th>商品編號</th>
                <th>品名</th>
                <th>進價</th>
                <th>零售價</th>
                <th><button id="sales_btn">總銷量</button></th>
                <th><button id="stock_btn" value="庫存" >庫存</button>
                </th>
                <th colspan="2"></th>
            </tr>
        </thead>
        
        <tbody id="focolumn">
            <c:forEach items="${sessionScope.page}" var="item">
            <tr>
                <td id="item_category">${item.category}</td>
                <td>${item.itemno}</td>
                <td>${item.name}</td>
                <td>${item.purchaseprice}</td>
                <td>${item.retailprice}</td>
                <td>${item.sales}</td>
                <td>${item.inventory}</td>
                <td><button itemId="${item.id}" class="addToSales">銷售</button></td>
                <td><button itemId="${item.id}" class="addToStock">進貨</button></td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
    
</body>
<link type="text/css" rel="stylesheet" href="static/css/style.css"/>
<script type="text/javascript">
    $(function() {
        //給銷售按鈕綁定單擊事件
        $(".addToSales").click(function(){
            var itemName = $(this).parent().parent().find("td:eq(2)").text();
            if(confirm("確定要輸入【"+itemName+"】的銷售量?")){
                var quantity = prompt("請輸入【"+itemName+"】銷售量");
                if(quantity != null && quantity != ""){
                    // $(this)在ajax 的回調函數中無效,以賦值給一個變量,
                    // 運用這個變量在ajax 的回調函數中使用,就能運作了
                    var thisDom = $(this);
                    /**
                     * 在事件響應的function函數中,有一個this物件,這個this物件,是當前響應事件的dom物件
                     * @type {jQuery}
                     */
                    var itemId = $(this).attr("itemId");
                    //運用ajax,進行異步請求,添加商品到購物車
                    $.getJSON("http://localhost:8080/project1/itemServlet","action=updateSales&id="+itemId+
                        "&quantity="+quantity,function (data) {
                        $("#function_frame").text("您為【"+data.itemname+"】登錄了【"+data.quantity+"】件銷售,目前總銷售量是:"+data.sales);
                        thisDom.parent().parent().find("td:eq(5)").text(data.sales);
                        thisDom.parent().parent().find("td:eq(6)").text(data.stock);
                    })
                }else{
                    alert("您取消輸入動作了");
                }
            }else{
                alert("您取消了輸入【"+itemName+"】銷售量的計畫");
            }
        });
        //給進貨按鈕綁定單擊事件
        $(".addToStock").click(function(){
            var itemName = $(this).parent().parent().find("td:eq(2)").text();

            if(confirm("確定要為【"+itemName+"】做進貨登錄?")){
                var amount = prompt("請輸入【"+itemName+"】進貨量");
                if(amount != null && amount != ""){
                    // $(this)在ajax 的回調函數中無效,以賦值給一個變量,
                    // 運用這個變量在ajax 的回調函數中使用,就能運作了
                    var addStockDom = $(this);
                    /**
                     * 在事件響應的function函數中,有一個this物件,這個this物件,是當前響應事件的dom物件
                     * @type {jQuery}
                     */
                    var itemId = $(this).attr("itemId");
                    //運用ajax,進行異步請求,添加商品到購物車
                    $.getJSON("http://localhost:8080/project1/itemServlet","action=updateStock&id="+itemId+
                        "&quantity="+amount,function (data) {
                        $("#function_frame").text("您為【"+data.itemname+"】登錄了【"+data.quantity+"】件進貨,目前庫存量是:"+data.stock);
                        addStockDom.parent().parent().find("td:eq(6)").text(data.stock);
                    })
                }else{
                    alert("您取消輸入動作了");
                }
            }else{
                alert("您取消了登錄【"+itemName+"】進貨量的計畫");
            }
        });
        //總銷量按鈕排序
        $("#sales_btn").click(function () {
            // var table = $("#container > *").find($("tbody tr:gt(2)"));
            // var rows = table.toArray().sort(function(a,b){
            //         return (a.cells[5].innerHTML)-(b.cells[5].innerHTML);
            //     });
            var table = $(this).parents('table').eq(0);
            var rows = table.find('tr:gt(2)').toArray().sort(function(a,b){
                return (a.cells[5].innerHTML)-(b.cells[5].innerHTML);
            });
            this.asc = !this.asc;
            if(!this.asc){ rows = rows.reverse();}
            table.children('tbody').empty().html(rows);

            //給銷售按鈕綁定單擊事件
            $(".addToSales").click(function(){
                var itemName = $(this).parent().parent().find("td:eq(2)").text();
                if(confirm("確定要輸入【"+itemName+"】的銷售量?")){
                    var quantity = prompt("請輸入【"+itemName+"】銷售量");
                    if(quantity != null && quantity != ""){
                        // $(this)在ajax 的回調函數中無效,以賦值給一個變量,
                        // 運用這個變量在ajax 的回調函數中使用,就能運作了
                        var thisDom = $(this);
                        /**
                         * 在事件響應的function函數中,有一個this物件,這個this物件,是當前響應事件的dom物件
                         * @type {jQuery}
                         */
                        var itemId = $(this).attr("itemId");
                        //運用ajax,進行異步請求,添加商品到購物車
                        $.getJSON("http://localhost:8080/project1/itemServlet","action=updateSales&id="+itemId+
                            "&quantity="+quantity,function (data) {
                            $("#function_frame").text("您為【"+data.itemname+"】登錄了【"+data.quantity+"】件銷售,目前總銷售量是:"+data.sales);
                            thisDom.parent().parent().find("td:eq(5)").text(data.sales);
                            thisDom.parent().parent().find("td:eq(6)").text(data.stock);
                        })
                    }else{
                        alert("您取消輸入動作了");
                    }
                }else{
                    alert("您取消了輸入【"+itemName+"】銷售量的計畫");
                }
            });
            //給進貨按鈕綁定單擊事件
            $(".addToStock").click(function(){
                var itemName = $(this).parent().parent().find("td:eq(2)").text();

                if(confirm("確定要為【"+itemName+"】做進貨登錄?")){
                    var amount = prompt("請輸入【"+itemName+"】進貨量");
                    if(amount != null && amount != ""){
                        // $(this)在ajax 的回調函數中無效,以賦值給一個變量,
                        // 運用這個變量在ajax 的回調函數中使用,就能運作了
                        var addStockDom = $(this);
                        /**
                         * 在事件響應的function函數中,有一個this物件,這個this物件,是當前響應事件的dom物件
                         * @type {jQuery}
                         */
                        var itemId = $(this).attr("itemId");
                        //運用ajax,進行異步請求,添加商品到購物車
                        $.getJSON("http://localhost:8080/project1/itemServlet","action=updateStock&id="+itemId+
                            "&quantity="+amount,function (data) {
                            $("#function_frame").text("您為【"+data.itemname+"】登錄了【"+data.quantity+"】件進貨,目前庫存量是:"+data.stock);
                            addStockDom.parent().parent().find("td:eq(6)").text(data.stock);
                        })
                    }else{
                        alert("您取消輸入動作了");
                    }
                }else{
                    alert("您取消了登錄【"+itemName+"】進貨量的計畫");
                }
            });

        });
        //庫存按鈕排序
        $("#stock_btn").click(function(){
            var table = $(this).parents('table').eq(0);
            var rows = table.find('tr:gt(2)').toArray().sort(function(a,b){
                return (a.cells[6].innerHTML)-(b.cells[6].innerHTML);
            });
            this.asc = !this.asc;
            if(!this.asc){ rows = rows.reverse();}
            table.children('tbody').empty().html(rows);

            //給銷售按鈕綁定單擊事件
            $(".addToSales").click(function(){
                var itemName = $(this).parent().parent().find("td:eq(2)").text();
                if(confirm("確定要輸入【"+itemName+"】的銷售量?")){
                    var quantity = prompt("請輸入【"+itemName+"】銷售量");
                    if(quantity != null && quantity != ""){
                        // $(this)在ajax 的回調函數中無效,以賦值給一個變量,
                        // 運用這個變量在ajax 的回調函數中使用,就能運作了
                        var thisDom = $(this);
                        /**
                         * 在事件響應的function函數中,有一個this物件,這個this物件,是當前響應事件的dom物件
                         * @type {jQuery}
                         */
                        var itemId = $(this).attr("itemId");
                        //運用ajax,進行異步請求,添加商品到購物車
                        $.getJSON("http://localhost:8080/project1/itemServlet","action=updateSales&id="+itemId+
                            "&quantity="+quantity,function (data) {
                            $("#function_frame").text("您為【"+data.itemname+"】登錄了【"+data.quantity+"】件銷售,目前總銷售量是:"+data.sales);
                            thisDom.parent().parent().find("td:eq(5)").text(data.sales);
                            thisDom.parent().parent().find("td:eq(6)").text(data.stock);
                        })
                    }else{
                        alert("您取消輸入動作了");
                    }
                }else{
                    alert("您取消了輸入【"+itemName+"】銷售量的計畫");
                }
            });
            //給進貨按鈕綁定單擊事件
            $(".addToStock").click(function(){
                var itemName = $(this).parent().parent().find("td:eq(2)").text();

                if(confirm("確定要為【"+itemName+"】做進貨登錄?")){
                    var amount = prompt("請輸入【"+itemName+"】進貨量");
                    if(amount != null && amount != ""){
                        // $(this)在ajax 的回調函數中無效,以賦值給一個變量,
                        // 運用這個變量在ajax 的回調函數中使用,就能運作了
                        var addStockDom = $(this);
                        /**
                         * 在事件響應的function函數中,有一個this物件,這個this物件,是當前響應事件的dom物件
                         * @type {jQuery}
                         */
                        var itemId = $(this).attr("itemId");
                        //運用ajax,進行異步請求,添加商品到購物車
                        $.getJSON("http://localhost:8080/project1/itemServlet","action=updateStock&id="+itemId+
                            "&quantity="+amount,function (data) {
                            $("#function_frame").text("您為【"+data.itemname+"】登錄了【"+data.quantity+"】件進貨,目前庫存量是:"+data.stock);
                            addStockDom.parent().parent().find("td:eq(6)").text(data.stock);
                        })
                    }else{
                        alert("您取消輸入動作了");
                    }
                }else{
                    alert("您取消了登錄【"+itemName+"】進貨量的計畫");
                }
            });
        });

    });
</script>

</html>
