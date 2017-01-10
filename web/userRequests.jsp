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
        <c:forEach items="${requests}" var="request">
            <tr>
                <th <c:if test="${empty admin}">hidden</c:if>>${request.requestId}</th>
                <th>${request.amountOfPlaces}</th>
                <th>${request.dateFrom}</th>
                <th>${request.dateTo}</th>
                <th>$${request.cost}</th>
                <th>${request.confirmed}</th>
                <th>${request.paid}</th>
                <th>${request.approvedBy}</th>
                <th>
                    <a href="${pageContext.request.contextPath}/do?action=preparePayRequest&requestId=${request.requestId}">Pay</a>
                </th>
            </tr>
        </c:forEach>
        </c:if>
    </table>
</div>

<%@ include file="include/footer.jsp" %>

<%@ include file="include/script.jsp" %>

</body>
</html>
