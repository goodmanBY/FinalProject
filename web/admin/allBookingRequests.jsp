<%@ include file="/include/adminTags.jsp" %>

<html>
<head>
    <%@ include file="/include/head.jsp" %>
    <title>All booking requests</title>
</head>
<body>

<c:if test="${not empty sessionScope.admin}">
    <div class="col-md-12 centring">
        <h2>All booking requests</h2>
    </div>
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
            <tr ${(bookingRequests.paid == 1 && bookingRequests.confirmed == 1) ? 'class="success"' : ''}>
                <th>${bookingRequests.requestId}</th>
                <th>
                    <a href="${pageContext.request.contextPath}/do?action=userProfile&userId=${bookingRequests.userId}">${bookingRequests.userId}</a>
                </th>
                <th>${bookingRequests.amountOfPlaces}</th>
                <th>${bookingRequests.dateFrom}</th>
                <th>${bookingRequests.dateTo}</th>
                <th>${bookingRequests.cost}</th>
                <c:choose>
                    <c:when test="${bookingRequests.confirmed == 0}">
                        <th>Not confirmed</th>
                    </c:when>
                    <c:otherwise>
                        <th>Confirmed</th>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${bookingRequests.paid == 0}">
                        <th>Not paid</th>
                    </c:when>
                    <c:otherwise>
                        <th>Paid</th>
                    </c:otherwise>
                </c:choose>
                <th>
                        ${bookingRequests.approvedBy}
                </th>
                <c:choose>
                    <c:when test="${bookingRequests.confirmed == 0}">
                        <th>
                            <a href="${pageContext.request.contextPath}/do?action=confirmBookingRequest&requestId=${bookingRequests.requestId}">Confirm</a>
                        </th>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${bookingRequests.paid == 0}">
                                <th>
                                    <a href="${pageContext.request.contextPath}/do?action=cancelConfirmation&requestId=${bookingRequests.requestId}">Cancel
                                        confirmation</a>
                                </th>
                            </c:when>
                            <c:otherwise>
                                <th>
                                    Unable to cancel paid order
                                </th>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
    </table>

    <%@ include file="/include/toControlPanel.jsp" %>

</c:if>

<%@ include file="/include/script.jsp" %>

</body>
</html>
