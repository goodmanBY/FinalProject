<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${not empty sessionScope.language ? sessionScope.language  : 'en'}"/>
<fmt:setBundle basename="com.savko.i18n.text"/>
<html>
<head>
    <%@ include file="include/head.jsp" %>
    <title>Users</title>
</head>
<body>
<c:if test="${not empty sessionScope.adminLogin}">
<table class="table table-hover">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Last Name</th>
        <th>Login</th>
        <th>Password</th>
        <th>Banned</th>
        <th>Discount ID</th>
    </tr>
        <c:forEach items="${users}" var="users">
            <tr>
                <th>${users.id}</th>
                <th>${users.name}</th>
                <th>${users.lastName}</th>
                <th>${users.login}</th>
                <th>${users.password}</th>
                <th>${users.banned}</th>
                <th>${users.discountId}</th>
            </tr>
        </c:forEach>
    </c:if>
</table>

<%@ include file="include/script.jsp"%>

</body>
</html>
