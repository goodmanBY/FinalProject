<%@ include file="/include/adminTags.jsp" %>

<html>
<head>
    <%@ include file="/include/head.jsp" %>
    <title>Control panel</title>
</head>
<body>

<div class="row">
    <div class="col-md-12 centring">${sessionScope.admin.login}</div>

    <div class="col-md-6 centring">
        <a href="${pageContext.request.contextPath}/do?action=allUsers"><h2>Users</h2></a>
    </div>
    <div class="col-md-6 centring">
        <a href="${pageContext.request.contextPath}/do?action=allBookingRequests"><h2>Booking requests</h2></a>
    </div>

    <div class="col-md-12 centring"><a href="${pageContext.request.contextPath}/do?action=adminLogOut">Log out</a>
    </div>
</div>

<%@ include file="/include/script.jsp" %>

</body>
</html>
