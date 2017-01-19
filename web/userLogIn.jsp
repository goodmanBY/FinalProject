<%@ include file="include/userTags.jsp" %>

<html>
<head>
    <%@ include file="include/head.jsp" %>
    <title><fmt:message key="log.in"/></title>
</head>
<body>

<%@ include file="include/navigation.jsp" %>

<div class="container container-top-padding">
    <div class="row">
        <div class="col-md-6 col-md-offset-2">
            <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/do" onkeyup="return validateLogInForm()">
                <input type="hidden" name="action" value="userLogIn">
                <div class="form-group">
                    <span class="centring"><fmt:message key="log.in"/></span>
                </div>
                <div class="form-group">
                    <label for="login" class="col-md-2 control-label"><fmt:message key="login"/></label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="login" id="login" placeholder="<fmt:message key="login"/>">
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-md-2 control-label"><fmt:message key="password"/></label>
                    <div class="col-md-10">
                        <input type="password" class="form-control" name="password" id="password"
                               placeholder="<fmt:message key="password"/>">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-2 col-md-10">
                        <button type="submit" class="btn btn-success"><fmt:message key="log.in"/></button>
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