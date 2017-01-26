<%@ include file="include/userTags.jsp" %>
<html>
<head>
    <%@ include file="include/head.jsp" %>
    <title>Payment info</title>
</head>
<body>

<%@ include file="include/navigation.jsp" %>

<c:if test="${not empty requestScope.paymentInfo}">

    <div class="container container-top-padding">
        <div class="row">
            <h3 class="centring"><fmt:message key="payment.info"/></h3>
            <div class="col-md-6 col-md-offset-2">
                <div class="form-group">
                    <label for="lastFourDigits" class="col-md-offset-2 col-md-2 control-label"><fmt:message
                            key="last.digits"/></label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" name="lastFourDigits" id="lastFourDigits"
                               value="**** **** **** ${requestScope.paymentInfo.lastFourDigits}" readonly>
                    </div>
                </div>
                <div class="form-group">
                    <label for="cost" class="col-md-offset-2 col-md-2 control-label"><fmt:message
                            key="cost"/>, $</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" name="cost" id="cost"
                               value="${requestScope.paymentInfo.cost}" readonly>
                    </div>
                </div>
                <div class="form-group">
                    <label for="dateTime" class="col-md-offset-2 col-md-2 control-label"><fmt:message
                            key="date.and.time"/></label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" name="dateTime" id="dateTime"
                               value="${requestScope.paymentInfo.dateAndTime}" readonly></div>
                </div>
            </div>
        </div>
    </div>

</c:if>

<%@ include file="include/footer.jsp" %>

<%@ include file="include/script.jsp" %>

</body>
</html>
