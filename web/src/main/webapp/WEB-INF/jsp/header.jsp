<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 06.11.2015
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<div class="container-fluid">
    <div class="navbar navbar-static-top">
        <ul class="nav navbar-nav">
            <li class="active"><a href="/">Home</a></li>
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Contacts <span
                    class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="add">Add contacts</a></li>
                    <li><a href="displayContacts">Display Contacts</a></li>
                </ul>
            </li>
        </ul>
    </div>
</div>
</body>
</html>
