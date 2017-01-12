<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${not empty sessionScope.language ? sessionScope.language  : 'en'}"/>
<fmt:setBundle basename="com.savko.i18n.text"/>

<html>
<head>
    <%@ include file="/include/head.jsp" %>
    <title>User profile</title>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-md-12 centring">
            <h2>Information about user</h2>
        </div>
        <div class="col-md-6 col-md-offset-2">
            <div class="form-group">
                <label for="userId" class="col-md-offset-2 col-sm-2 control-label"><fmt:message key="name"/></label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" name="userId" id="userId" value="${requestScope.user.id}"
                           readonly>
                </div>
            </div>
            <div class="form-group">
                <label for="name" class="col-md-offset-2 col-sm-2 control-label"><fmt:message key="name"/></label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" name="name" id="name" value="${requestScope.user.name}"
                           readonly>
                </div>
            </div>
            <div class="form-group">
                <label for="lastName" class="col-md-offset-2 col-sm-2 control-label"><fmt:message
                        key="last.name"/></label>
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
                           value="<c:choose><c:when test="${requestScope.user.banned == 0}">Not blocked</c:when><c:otherwise>
                            Blocked</c:otherwise></c:choose>" readonly>
                </div>
            </div>
            <div class="form-group">
                <label for="discount" class="col-md-offset-2 col-sm-2 control-label">Discount</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" name="discount" id="discount"
                           value="${requestScope.user.discountId}" readonly>
                </div>
            </div>
        </div>
    </div>
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
        <tr <c:if test="${bookingRequests.paid == 1 && bookingRequests.confirmed == 1}">class="success"</c:if>>
            <th>${bookingRequests.requestId}</th>
            <th>${bookingRequests.userId}</th>
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

<div class="col-md-12 centring"><a href="${pageContext.request.contextPath}/admin/adminControlPanel.jsp">Back</a>
</div>

<%@ include file="/include/script.jsp" %>

</body>
</html>
