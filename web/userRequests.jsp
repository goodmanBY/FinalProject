<%@ include file="include/tags.jsp" %>

<html>
<head>
    <%@ include file="include/head.jsp" %>
    <title><fmt:message key="your.booking.requests"/></title>
</head>
<body>

<%@ include file="include/navigation.jsp" %>

<div class="container-top-padding">
    <c:choose>
        <c:when test="${not empty requests}">
            <div class="col-md-12 centring">
                <h2><fmt:message key="your.booking.requests"/></h2>
            </div>
            <table class="table table-hover">
                <tr>
                    <th ${(empty admin) ? 'hidden' : ''}>Request ID</th>
                    <th><fmt:message key="amount.of.places"/></th>
                    <th><fmt:message key="date.from"/></th>
                    <th><fmt:message key="date.to"/></th>
                    <th><fmt:message key="cost"/></th>
                    <th><fmt:message key="confirmed"/></th>
                    <th><fmt:message key="paid"/></th>
                    <th><fmt:message key="approved.by"/></th>
                    <th><fmt:message key="status"/></th>
                </tr>
                <c:forEach items="${requests}" var="bookingRequest">
                    <tr ${(bookingRequest.paid == 1 && bookingRequest.confirmed == 1) ? 'class="success"' : ''}>
                        <th ${(empty admin) ? 'hidden' : ''}>${bookingRequest.requestId}</th>
                        <th>${bookingRequest.amountOfPlaces}</th>
                        <th>${bookingRequest.dateFrom}</th>
                        <th>${bookingRequest.dateTo}</th>
                        <th>$${bookingRequest.cost}</th>
                        <c:choose>
                            <c:when test="${bookingRequest.confirmed == 0}">
                                <th><fmt:message key="not.confirmed"/></th>
                            </c:when>
                            <c:otherwise>
                                <th><fmt:message key="confirmed"/></th>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${bookingRequest.paid == 0}">
                                <th><fmt:message key="not.paid"/></th>
                            </c:when>
                            <c:otherwise>
                                <th><fmt:message key="paid"/></th>
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
                                            <a href="${pageContext.request.contextPath}/do?action=preparePayRequest&requestId=${bookingRequest.requestId}">
                                                <fmt:message key="pay"/>
                                            </a>
                                        </th>
                                    </c:when>
                                    <c:otherwise>
                                        <th>
                                            <fmt:message key="paid"/>
                                        </th>
                                    </c:otherwise>
                                </c:choose>
                            </c:when>
                            <c:otherwise>
                                <th><fmt:message key="wait.confirmation"/></th>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <div class="row">
                <div class="col-md-12 centring">
                    <h2><fmt:message key="empty.requests.message"/></h2>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<%@ include file="include/footer.jsp" %>

<%@ include file="include/script.jsp" %>

</body>
</html>
