<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Add Contacts</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="js/bootstrap.js"></script>
    <link href="css/bootstrap.css" rel="stylesheet">
    <script src="js/jquery-1.11.3.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<div class="container">
    <form action="<c:url value="/doAddContacts" />" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <p><label>Выберите файл</label></p>
            <input type="file" class="form-control" placeholder="Choose file" accept=".text/csv" name="file"
                   id="uploadFile">
        </div>
        <input type="submit" class="btn btn-default" name="addContacts" value="Add Contacts"/></p>
    </form>
</div>
<script src="js/bootstrap.js"></script>
</body>
</html>
