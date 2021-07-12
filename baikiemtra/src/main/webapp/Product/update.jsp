<%--
  Created by IntelliJ IDEA.
  User: Nguyen Viet Tien
  Date: 07/12/2021
  Time: 10:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post">
    <h3>Update product</h3>
    <label>Name</label>
    <br>
    <input type="text" name="name" value="${product.name}">
    <br>
    <label>Price</label>
    <br>
    <input type="text" name="price" value="${product.price}">
    <br>
    <label>Quantity</label>
    <br>
    <input type="text" name="quantity" value="${product.quantity}">
    <br>
    <label>Color</label>
    <br>
    <input type="text" name="color" value="${product.color}">
    <br>
    <label>Description</label>
    <br>
    <input type="text" name="description" value="${product.description}">
    <br>
    <label>Category</label>
    <br>
    <select name="category">
        <br>
        <c:forEach items="${categories}" var="category">
            <option value="${category.name}">${category.name}</option>
        </c:forEach>
    </select>
    <br>
    <button>Update</button>
    |<a href="/products">Back</a>
</form>
</body>
</html>
