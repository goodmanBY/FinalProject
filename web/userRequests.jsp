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

<div class="container-top-padding">
    <c:choose>
        <c:when test="${not empty requests}">
            <div class="col-md-12 centring">
                <h2>Your booking requests</h2>
            </div>
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
                    <th>Status</th>
                </tr>
                <c:forEach items="${requests}" var="bookingRequest">
                    <tr <c:if test="${bookingRequest.paid == 1 && bookingRequest.confirmed == 1}">class="success"</c:if>>
                        <th <c:if test="${empty admin}">hidden</c:if>>${bookingRequest.requestId}</th>
                        <th>${bookingRequest.amountOfPlaces}</th>
                        <th>${bookingRequest.dateFrom}</th>
                        <th>${bookingRequest.dateTo}</th>
                        <th>$${bookingRequest.cost}</th>
                        <c:choose>
                            <c:when test="${bookingRequest.confirmed == 0}">
                                <th>Not confirmed</th>
                            </c:when>
                            <c:otherwise>
                                <th>Confirmed</th>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${bookingRequest.paid == 0}">
                                <th>Not paid</th>
                            </c:when>
                            <c:otherwise>
                                <th>Paid</th>
                            </c:otherwise>
                        </c:choose>
                        <th>
                            ${bookingRequest.approvedBy}
                        </th>
                        <c:choose>
                            <c:when test="${bookingRequest.confirmed == 1}">
                                <c:choose>
                                    <c:when test="${bookingRequest.paid == 0}">
                                        <th>
                                            <a href="${pageContext.request.contextPath}/do?action=preparePayRequest&requestId=${bookingRequest.requestId}">Pay</a>
                                            <a>Cash</a>
                                        </th>
                                    </c:when>
                                    <c:otherwise>
                                        <th>
                                            Paid
                                        </th>
                                    </c:otherwise>
                                </c:choose>
                            </c:when>
                            <c:otherwise>
                                <th>Wait confirmation</th>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <div class="col-md-12 centring">
                <h2>You do not have any booking requests</h2>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<%@ include file="include/footer.jsp" %>

<%@ include file="include/script.jsp" %>

</body>
</html>
