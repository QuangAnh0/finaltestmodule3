<%--
  Created by IntelliJ IDEA.
  User: quang
  Date: 9/7/2022
  Time: 10:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
</head>
<body>
<center>
    <h1></h1>
    <h2>
        <a href="/users?action=create"></a>
    </h2>
</center>
<div align="center">
    <table border="1" bgcolor="blue">
        <caption><h2></h2></caption>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>DateOfBirth</th>
            <th>address</th>
            <th>phoneNumber</th>
            <th>email</th>
            <th>classroom</th>
        </tr>
        <c:forEach var="students" items="${students}">
            <tr>
                <td> ${students.id}</td>
                <td> ${students.name}</td>
                <td> ${students.DateOfBirth}</td>
                <td> ${students.address}</td>
                <td> ${students.phoneNumber}</td>
                <td> ${students.email}</td>
                <td> ${students.classroom}</td>

                <td>
                    <a href="/Students?action=edit&id=${students.id}">Edit</a>
                    <a href="/Students?action=delete&id=${students.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>


