<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${not empty sessionScope.language ? sessionScope.language  : 'en'}"/>
<fmt:setBundle basename="com.savko.i18n.text"/>
<html>
<head>
    <%@ include file="/include/head.jsp" %>
    <title>All booking requests</title>
</head>
<body>

<table class="table table-hover">
    <tr>
        <th>Request ID</th>
        <th>User ID</th>
        <th>Amount of places</th>
        <th>Date from</th>
        <th>Date to</th>
        <th>Cost, $</th>
        <th>Confirmed</th>
        <th>Paid</th>
        <th>Approved byd</th>
    </tr>
    <c:forEach items="${bookingRequests}" var="bookingRequests">
        <tr>
            <th>${bookingRequests.requestId}</th>
            <th>${bookingRequests.userId}</th>
            <th>${bookingRequests.amountOfPlaces}</th>
            <th>${bookingRequests.dateFrom}</th>
            <th>${bookingRequests.dateTo}</th>
            <th>${bookingRequests.cost}, $</th>
            <th>${bookingRequests.confirmed}</th>
            <th>${bookingRequests.paid}</th>
            <th>${bookingRequests.approvedBy}</th>
            <c:choose>
                <c:when test="${bookingRequests.confirmed == 0}">
                    <th>
                        <a href="${pageContext.request.contextPath}/do?action=confirmBookingRequest&requestId=${bookingRequests.requestId}">Confirm</a>
                    </th>
                </c:when>
                <c:otherwise>
                    <th>
                        <a href="${pageContext.request.contextPath}/do?action=cancelConfirmation&requestId=${bookingRequests.requestId}">Cancel
                            confirmation</a>
                    </th>
                </c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>
</table>

<%@ include file="/include/script.jsp" %>

</body>
</html>
