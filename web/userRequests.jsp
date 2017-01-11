<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${not empty sessionScope.language ? sessionScope.language  : 'en'}"/>
<fmt:setBundle basename="com.savko.i18n.text"/>
<html>
<head>
    <%@ include file="include/head.jsp" %>
    <title>Requests</title>
</head>
<body>

<%@ include file="include/navigation.jsp" %>

<c:if test="${not empty requests}">
<div class="container-top-padding">
    <table class="table table-hover">
        <tr>
            <th <c:if test="${empty admin}">hidden</c:if>>Request ID</th>
            <th>Amount of places</th>
            <th>Date from</th>
            <th>Date to</th>
            <th>Cost</th>
            <th>Confirmed</th>
            <th>Paid</th>
            <th>Approved by</th>
        </tr>
        <c:forEach items="${requests}" var="bookingRequest">
            <tr>
                <th <c:if test="${empty admin}">hidden</c:if>>${bookingRequest.requestId}</th>
                <th>${bookingRequest.amountOfPlaces}</th>
                <th>${bookingRequest.dateFrom}</th>
                <th>${bookingRequest.dateTo}</th>
                <th>$${bookingRequest.cost}</th>
                <th>${bookingRequest.confirmed}</th>
                <th>${bookingRequest.paid}</th>
                <th>${bookingRequest.approvedBy}</th>
                <c:choose>
                    <c:when test="${bookingRequest.confirmed == 1}">
                        <th>
                            <a href="${pageContext.request.contextPath}/do?action=preparePayRequest&requestId=${bookingRequest.requestId}">Pay</a>
                        </th>
                    </c:when>
                    <c:otherwise>
                        <th>Wait confirmation</th>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
        </c:if>
    </table>
</div>

<%@ include file="include/footer.jsp" %>

<%@ include file="include/script.jsp" %>

</body>
</html>
