<%@ include file="include/userTags.jsp" %>

<html>
<head>
    <%@ include file="include/head.jsp" %>
    <title><fmt:message key="request.info"/></title>
</head>
<body>

<%@ include file="include/navigation.jsp" %>

<div class="col-md-5 col-md-offset-3 container-top-padding">
    <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/do">
        <input type="hidden" name="action" value="userRequestInfo">
        <div class="form-group centring">
            <span class="centring"><fmt:message key="your.reservation"/></span>
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
            <label for="Discount" class="col-sm-3 control-label"><fmt:message key="discount"/></label>
            <div class="col-sm-9">
                <input type="text" class="form-control" name="Discount" id="Discount"
                       value="${sessionScope.user.discount}%" readonly>
            </div>
        </div>
        <div class="form-group">
            <label for="amountOfPlaces" class="col-sm-3 control-label"><fmt:message key="amount.of.places"/></label>
            <div class="col-sm-9">
                <input type="text" class="form-control" name="amountOfPlaces" id="amountOfPlaces"
                       value="${requestScope.amountOfPlaces}" readonly>
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
            <label for="cost" class="col-sm-3 control-label"><fmt:message key="cost"/>, $</label>
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