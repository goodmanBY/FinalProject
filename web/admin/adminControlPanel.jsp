<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${not empty sessionScope.language ? sessionScope.language  : 'en'}"/>
<fmt:setBundle basename="com.savko.i18n.text"/>
<html>
<head>
    <%@ include file="/include/head.jsp" %>
    <title>Control panel</title>
</head>
<body>
<c:if test="${not empty sessionScope.admin}">
    <div class="col-md-12 centring">Cheers, ${sessionScope.admin.login}!</div>

    <div class="col-md-6 centring">
        <a href="${pageContext.request.contextPath}/do?action=allUsers"><h2>Users</h2></a>
    </div>
    <div class="col-md-6 centring">
        <a href="${pageContext.request.contextPath}/do?action=allBookingRequests"><h2>Booking requests</h2></a>
    </div>

    <div class="col-md-12 centring"><a href="${pageContext.request.contextPath}/do?action=adminLogOut"><fmt:message
            key="log.out"/></a></div>
</c:if>

<%@ include file="/include/script.jsp" %>

</body>
</html>
