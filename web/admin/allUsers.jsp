<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${not empty sessionScope.language ? sessionScope.language  : 'en'}"/>
<fmt:setBundle basename="com.savko.i18n.text"/>
<html>
<head>
    <%@ include file="/include/head.jsp" %>
    <title>Users</title>
</head>
<body>

<c:if test="${not empty sessionScope.admin}">
<table class="table table-hover">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Last Name</th>
        <th>Login</th>
        <th>Banned</th>
        <th>Discount</th>
    </tr>
    <c:forEach items="${users}" var="users">
        <tr>
            <th>${users.id}</th>
            <th>${users.name}</th>
            <th>${users.lastName}</th>
            <th>${users.login}</th>
            <c:choose>
                <c:when test="${users.banned == 0}">
                    <th>Not banned</th>
                </c:when>
                <c:otherwise>
                    <th>Banned</th>
                </c:otherwise>
            </c:choose>
            <th>${users.discountId}</th>
            <c:choose>
                <c:when test="${users.banned == 0}">
                    <th><a href="${pageContext.request.contextPath}/do?action=blockUser&userId=${users.id}">Block</a>
                    </th>
                </c:when>
                <c:otherwise>
                    <th>
                        <a href="${pageContext.request.contextPath}/do?action=unblockUser&userId=${users.id}">Unblock</a>
                    </th>
                </c:otherwise>
            </c:choose>
            <th><a href="${pageContext.request.contextPath}/do?action=userProfile&userId=${users.id}">Profile</a></th>
        </tr>
    </c:forEach>
    </c:if>
</table>

<div class="col-md-12 centring"><a href="${pageContext.request.contextPath}/admin/adminControlPanel.jsp">Back</a>
</div>

<%@ include file="/include/script.jsp"%>

</body>
</html>
