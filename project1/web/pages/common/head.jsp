<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String basePath = request.getScheme()
            +"://"
            + request.getServerName()
            +":"
            + request.getServerPort()
            + request.getContextPath()
            +"/";
    pageContext.setAttribute("basePath",basePath);
%>

<!--寫base標籤,永遠固定相對路徑跳轉的結果-->
<base href="<%=basePath%>">
<script type="text/javascript" src="static/jquery-1.7.2.js"></script>