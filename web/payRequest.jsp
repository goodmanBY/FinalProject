<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${not empty sessionScope.language ? sessionScope.language  : 'en'}"/>
<fmt:setBundle basename="com.savko.i18n.text"/>
<html>
<head>
    <%@ include file="include/head.jsp" %>
    <title>Pay</title>
</head>
<body>

<%@ include file="include/navigation.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-2 container-top-padding">
            <div class="form-group centring">
                <span class="centring">Your booking request</span>
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
                           value="${sessionScope.user.lastName}"
                           readonly>
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
                <label for="dateFrom" class="col-sm-3 control-label"><fmt:message key="date.from"/></label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" name="dateFrom" id="dateFrom"
                           value="${requestScope.dateFrom}"
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
        </div>

        <div class="col-md-6 col-md-offset-2">
            <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/do">
                <input type="hidden" name="action" value="payRequest">
                <div hidden>
                    <input type="text" class="form-control" name="requestId" id="requestId"
                           value="${requestScope.requestId}">
                </div>
                <div class="form-group centring">
                    <span class="centring">Card info</span>
                </div>
                <div class="form-group">
                    <label for="cardNumber" class="col-md-2 control-label">Card number</label>
                    <div class="col-md-10">
                        <input type="number" class="form-control" name="cardNumber" id="cardNumber"
                               placeholder="0000 0000 0000 0000">
                    </div>
                </div>
                <div class="form-group">
                    <label for="month" class="col-md-2 control-label">Month</label>
                    <div class="col-md-10">
                        <input type="number" class="form-control" name="month" id="month" min="1" max="12">
                    </div>
                </div>
                <div class="form-group">
                    <label for="year" class="col-md-2 control-label">Year</label>
                    <div class="col-md-10">
                        <input type="number" class="form-control" name="year" id="year">
                    </div>
                </div>
                <div class="form-group">
                    <label for="owner" class="col-md-2 control-label">Owner's name</label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="owner" id="owner">
                    </div>
                </div>
                <div class="form-group">
                    <label for="securityCode" class="col-md-2 control-label">Security code (CVC2/CVV2)</label>
                    <div class="col-md-10">
                        <input type="number" class="form-control" name="securityCode" id="securityCode">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-2 col-md-10">
                        <button type="submit" class="btn btn-success">Pay</button>
                    </div>
                </div>
            </form>
            <%@ include file="include/message.jsp" %>
        </div>
    </div>
</div>

<%@ include file="include/footer.jsp" %>

<%@ include file="include/script.jsp" %>


</body>
</html>