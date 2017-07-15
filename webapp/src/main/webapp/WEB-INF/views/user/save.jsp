<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title></title>
</head>
<body>
<h1>User Save</h1>

    <c:if test="${not empty message}">
        <h1 style="color:greenyellow">${message}</h1>
    </c:if>

    <form action="/user/save" method="post">
        <input type="text" name="userName">
        <input type="text" name="address">
        <input type="text" name="zipcode">
        <button>保存</button>

    </form>

    <%--文件上传表单--%>
    <form method="post" action="/user/upload" enctype="multipart/form-data" >

        <h1>文件上传</h1>
        <input type="file" name="name">
        <button>上传</button>

    </form>




</body>
</html>