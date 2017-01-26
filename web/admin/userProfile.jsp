<%@ include file="/include/adminTags.jsp" %>

<html>
<head>
    <%@ include file="/include/head.jsp" %>
    <title>User profile</title>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-md-12 centring">
            <h2>Full information about user</h2>
        </div>
        <div class="col-md-6 col-md-offset-2">
            <div class="form-group">
                <label for="userId" class="col-md-offset-2 col-sm-2 control-label">User ID</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" name="userId" id="userId" value="${requestScope.user.id}"
                           readonly>
                </div>
            </div>
            <div class="form-group">
                <label for="name" class="col-md-offset-2 col-sm-2 control-label">Name</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" name="name" id="name" value="${requestScope.user.name}"
                           readonly>
                </div>
            </div>
            <div class="form-group">
                <label for="lastName" class="col-md-offset-2 col-sm-2 control-label">Last name</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" name="lastName" id="lastName"
                           value="${requestScope.user.lastName}"
                           readonly>
                </div>
            </div>
            <div class="form-group">
                <label for="login" class="col-md-offset-2 col-sm-2 control-label">Login</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" name="login" id="login"
                           value="${requestScope.user.login}" readonly>
                </div>
            </div>
            <div class="form-group">
                <label for="banned" class="col-md-offset-2 col-sm-2 control-label">Banned</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" name="banned" id="banned"
                           value="${(requestScope.user.banned == 0) ? 'Not banned' : 'Banned'}" readonly>
                </div>
            </div>
            <div class="form-group">
                <label for="discount" class="col-md-offset-2 col-sm-2 control-label">Discount</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" name="discount" id="discount"
                           value="${requestScope.user.discount}%" readonly>
                </div>
            </div>
        </div>
    </div>
</div>

<table class="table table-hover">
    <div class="col-md-12 centring">
        <h2>All user's booking requests</h2>
    </div>
    <tr>
        <th>Request ID</th>
        <th>Amount of places</th>
        <th>Date from</th>
        <th>Date to</th>
        <th>Cost, $</th>
        <th>Confirmed</th>
        <th>Declined</th>
        <th>Paid</th>
        <th>Approved by</th>
        <th>Confirm/Cancel</th>
        <th>Decline/Cancel</th>
    </tr>
    <c:forEach items="${requestScope.bookingRequests}" var="bookingRequests">
        <tr ${bookingRequests.paid == 1 && bookingRequests.confirmed == 1 ? 'class="success"' : ''}>
            <th>${bookingRequests.requestId}</th>
            <th>${bookingRequests.amountOfPlaces}</th>
            <th>${bookingRequests.dateFrom}</th>
            <th>${bookingRequests.dateTo}</th>
            <th>${bookingRequests.cost}</th>
            <c:choose>
                <c:when test="${bookingRequests.confirmed == 0}">
                    <th>Not confirmed</th>
                </c:when>
                <c:otherwise>
                    <th ${(bookingRequests.confirmed == 1 && bookingRequests.paid == 0) ? 'class="confirmed"' : ''}>
                        Confirmed
                    </th>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${bookingRequests.declined == 0}">
                    <th>Not declined</th>
                </c:when>
                <c:otherwise>
                    <th ${(bookingRequests.declined == 1 ) ? 'class="declined"' : ''}>
                        Declined
                    </th>
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
                        <a href="${pageContext.request.contextPath}/admin/do?action=confirmBookingRequest&requestId=${bookingRequests.requestId}">Confirm</a>
                    </th>
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${bookingRequests.paid == 0}">
                            <th>
                                <a href="${pageContext.request.contextPath}/admin/do?action=cancelConfirmation&requestId=${bookingRequests.requestId}">Cancel
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
            <c:choose>
                <c:when test="${bookingRequests.declined == 0}">
                    <th>
                        <a href="${pageContext.request.contextPath}/admin/do?action=declineBookingRequest&requestId=${bookingRequests.requestId}">Decline
                            request</a>
                    </th>
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${bookingRequests.paid == 0}">
                            <th>
                                <a href="${pageContext.request.contextPath}/admin/do?action=cancelDeclination&requestId=${bookingRequests.requestId}">Cancel
                                    confirmation</a>
                            </th>
                        </c:when>
                        <c:otherwise>
                            <th>
                                Unable to decline paid order
                            </th>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>
</table>

<%@ include file="/include/toControlPanel.jsp" %>

<%@ include file="/include/script.jsp" %>

</body>
</html>
