<%--
  Created by IntelliJ IDEA.
  User: adel
  Date: 27.05.14
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${product.name}</title>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="/resources/libs/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <jsp:include page="nav.jsp">
            <jsp:param name="active" value="product" />
        </jsp:include>

        <div class="jumbotron row">
            <div class="col-md-4">
                <img class="img-responsive" src="${product.photo}"/>
            </div>
            <div class="col-md-2">
                <h2>${product.name}</h2>
                <p>Price: ${product.price}</p>
                <p>Amount: ${product.amount}</p>
            </div>
            <div class="col-md-6">
                <p>${product.description}</p>
            </div>
        </div>
    </div>
</body>
</html>
