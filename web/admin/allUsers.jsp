<%@ include file="/include/adminTags.jsp" %>

<html>
<head>
    <%@ include file="/include/head.jsp" %>
    <title>Users</title>
</head>
<body>

<div class="col-md-12 centring">
    <h2>All users</h2>
</div>
<table class="table table-hover">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Last Name</th>
        <th>Login</th>
        <th>Banned</th>
        <th>Discount</th>
        <th>Block/Unblock</th>
        <th></th>
    </tr>
    <c:forEach items="${requestScope.users}" var="users">
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
                    <th class="banned">Banned</th>
                </c:otherwise>
            </c:choose>
            <th>
                <div class="btn-group">
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"
                            aria-haspopup="true" aria-expanded="false">
                            ${users.discount}%<span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/admin/do?action=setDiscountValue&userId=${users.id}&discountValue=5">5%</a></li>
                        <li><a href="${pageContext.request.contextPath}/admin/do?action=setDiscountValue&userId=${users.id}&discountValue=10">10%</a></li>
                        <li><a href="${pageContext.request.contextPath}/admin/do?action=setDiscountValue&userId=${users.id}&discountValue=15">15%</a></li>
                    </ul>
                </div>
            </th>
            <c:choose>
                <c:when test="${users.banned == 0}">
                    <th>
                        <a href="${pageContext.request.contextPath}/admin/do?action=blockUser&userId=${users.id}">Block</a>
                    </th>
                </c:when>
                <c:otherwise>
                    <th>
                        <a href="${pageContext.request.contextPath}/admin/do?action=unblockUser&userId=${users.id}">Unblock</a>
                    </th>
                </c:otherwise>
            </c:choose>
            <th><a href="${pageContext.request.contextPath}/admin/do?action=userProfile&userId=${users.id}">Profile</a></th>
        </tr>
    </c:forEach>
</table>

<%@ include file="/include/toControlPanel.jsp" %>

<%@ include file="/include/script.jsp" %>

</body>
</html>
