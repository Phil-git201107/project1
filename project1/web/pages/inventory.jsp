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
    <link type="text/css" rel="stylesheet" href="static/css/style.css"/>
    
</head>
<body>
    <form action="itemServlet" method="get">
        <table>
            <tr>
                <td>類別</td>
                <td>商品編號</td>
                <td>品名</td>
                <td>進價</td>
                <td>零售價</td>
                <td>銷量</td>
                <td>庫存</td>
            </tr>
            <c:forEach items="${requestScope.page}" var="item">
                <tr>
                    <td>${item.category}</td>
                    <td>${item.itemno}</td>
                    <td>${item.name}</td>
                    <td>${item.purchaseprice}</td>
                    <td>${item.retailprice}</td>
                    <td>${item.sales}</td>
                    <td>${item.inventory}</td>
                </tr>
            </c:forEach>
        </table>
    </form>
</body>
</html>
