<%@ page import="com.springapp.mvc.model.Bucket" %>
<%@ page import="com.springapp.mvc.model.Product" %>
<%@ page import="java.util.List" %>
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
    <title>All Products</title>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="/resources/libs/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <jsp:include page="nav.jsp">
        <jsp:param name="active" value="main"/>
    </jsp:include>

    <div class="row">
        <% int i =0; %>
        <c:forEach items="${products}" var="product" varStatus="status">
            <c:if test="${status.count % 4 == 0}">
                <div class="row">
            </c:if>
            <div class="col-md-3 thumbnail">
                <img class="img-responsive" src="${product.photo}">
                <a href="/product/${product.id}"><h4>${product.name}</h4></a>
                <span><b>Price: </b>${product.price} <b>Left: </b> ${product.amount}</span>
                <%
                    Product product = ((List<Product>) request.getAttribute("products")).get(i);
                    Bucket bucket = (Bucket) session.getAttribute("bucket");
                    if (bucket != null) {
                        if (bucket.isInBucket(product)){
                            %>
                                <a href="/bucket/delete/${product.id}"><button class="btn btn-info btn-block">Remove</button></a>
                            <%
                        } else {
                %>
                <a href="/bucket/add/${product.id}"><button type="button" class="btn btn-primary btn-block">Add</button></a>
                <%
                        }
                    }
                %>

            </div>
            <c:if test="${status.count % 4 == 3}">
                </div>
            </c:if>
            <% i++; %>
        </c:forEach>
        <c:if test="${fn:length(products) % 4 != 3}">
    </div>
    </c:if>
</div>
</body>
</html>
