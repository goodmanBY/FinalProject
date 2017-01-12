<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${not empty sessionScope.language ? sessionScope.language  : 'en'}"/>
<fmt:setBundle basename="com.savko.i18n.text"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="include/head.jsp" %>
    <title>Request</title>
</head>
<body>

<%@ include file="include/navigation.jsp" %>

<div class="col-md-6 col-md-offset-2 container-top-padding">
    <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/do">
        <input type="hidden" name="action" value="userRequestInfo">
        <div class="form-group centring">
            <span class="centring">Your reservation</span>
        </div>
        <div class="form-group">
            <div class="col-sm-9" hidden>
                <input type="text" class="form-control" name="userId" id="userId" value="${sessionScope.user.id}"
                       readonly>
            </div>
        </div>
        <div class="form-group">
            <label for="name" class="col-sm-3 control-label"><fmt:message key="name"/></label>
            <div class="col-sm-9">
                <input type="text" class="form-control" name="name" id="name" value="${sessionScope.user.name}"
                       readonly>
            </div>
        </div>
        <div class="form-group">
            <label for="lastName" class="col-sm-3 control-label"><fmt:message key="last.name"/></label>
            <div class="col-sm-9">
                <input type="text" class="form-control" name="lastName" id="lastName"
                       value="${sessionScope.user.lastName}" readonly>
            </div>
        </div>
        <div class="form-group">
            <label for="amountOfPlaces" class="col-sm-3 control-label">Amount of places</label>
            <div class="col-sm-9">
                <input type="text" class="form-control" name="amountOfPlaces" id="amountOfPlaces"
                       value="${requestScope.amountOfPlaces}" readonly>
            </div>
        </div>
        <div class="form-group">
            <label for="amountOfDays" class="col-sm-3 control-label">Amount of days</label>
            <div class="col-sm-9">
                <input type="text" class="form-control" name="amountOfDays" id="amountOfDays"
                       value="${requestScope.amountOfDays}" readonly>
            </div>
        </div>
        <div class="form-group">
            <label for="dateFrom" class="col-sm-3 control-label"><fmt:message key="date.from"/></label>
            <div class="col-sm-9">
                <input type="text" class="form-control" name="dateFrom" id="dateFrom" value="${requestScope.dateFrom}"
                       readonly>
            </div>
        </div>
        <div class="form-group">
            <label for="dateTo" class="col-sm-3 control-label"><fmt:message key="date.to"/></label>
            <div class="col-sm-9">
                <input type="text" class="form-control" name="dateTo" id="dateTo" value="${requestScope.dateTo}"
                       readonly>
            </div>
        </div>
        <div class="form-group">
            <label for="cost" class="col-sm-3 control-label">Cost, $</label>
            <div class="col-sm-9">
                <input type="text" class="form-control" name="cost" id="cost" value="${requestScope.cost}" readonly>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-3 col-sm-9">
                <button type="submit" class="btn btn-success"><fmt:message key="book"/></button>
            </div>
        </div>
    </form>
</div>

<%@ include file="include/footer.jsp" %>

<%@ include file="include/script.jsp" %>

</body>
</html>
