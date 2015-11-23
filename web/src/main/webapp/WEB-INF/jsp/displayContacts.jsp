<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 06.11.2015
  Time: 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Contacts</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="js/bootstrap.js"></script>
    <link href="css/bootstrap.css" rel="stylesheet">
    <script src="js/jquery-1.11.3.js"></script>
    <script type="text/javascript" src="js/jquery.tablesorter.js"></script>
    <script>
        $(function () {
            $("#contactsTable").tablesorter({
                theme: 'bootstrap',
                headerTemplate: '{content} {icon}',
                widgets: ['zebra', 'columns'],
                debug: false
            });
        });
    </script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<div class="container-fl">
    <table class="table table-striped" id="contactsTable">
        <thead>
        <tr>
            <th>First Name&nbsp&nbsp<span class="glyphicon glyphicon-sort"></span></th>
            <th>Last Name&nbsp&nbsp<span class="glyphicon glyphicon-sort"></span></th>
            <th>Login&nbsp&nbsp<span class="glyphicon glyphicon-sort"></span></th>
            <th>Email&nbsp&nbsp<span class="glyphicon glyphicon-sort"></span></th>
            <th>Phone Number&nbsp&nbsp<i class="glyphicon glyphicon-sort"></span></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="contact" items="${contacts}">
            <tr>
                <td>${contact.firstName}</td>
                <td>${contact.lastName}</td>
                <td>${contact.login}</td>
                <td>${contact.email}</td>
                <td>${contact.phoneNumber}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%--For displaying Page numbers.
The when condition does not display a link for the current page--%>

<div class="container col-md-offset-5 navbar-fixed-bottom">
    <ul class="pagination">
        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <li><a href="#">${i}</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="displayContacts?page=${i}">${i}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </ul>
</div>

<script src="js/bootstrap.js"></script>
</body>
</html>
