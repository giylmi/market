<%@ page import="com.springapp.mvc.model.Bucket" %>
<%@ page import="com.springapp.mvc.model.Product" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: adel
  Date: 24.05.14
  Time: 21:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bucket</title>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="/resources/libs/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <jsp:include page="nav.jsp">
        <jsp:param name="active" value="bucket"/>
    </jsp:include>

    <div class="row">
        <c:forEach items="${sessionScope.bucket.products}" var="product" varStatus="status">
            <c:if test="${status.count % 4 == 0}">
                <div class="row">
            </c:if>
            <div class="col-md-3 thumbnail">
                <img class="img-responsive" src="${product.photo}">
                <a href="/product/${product.id}"><h4>${product.name}</h4></a>
                <span><b>Price: </b>${product.price} <b>Left: </b> ${product.amount}</span>
                <a href="/bucket/delete/${product.id}"><button class="btn btn-info btn-block">Remove</button></a>
            </div>
            <c:if test="${status.count % 4 == 3}">
                </div>
            </c:if>
        </c:forEach>
        <c:if test="${fn:length(products) % 4 != 3}">
    </div>
    </c:if>
</div>
</body>
</html>
