<%@ include file="include/userTags.jsp" %>

<html>
<head>
    <%@ include file="include/head.jsp" %>
    <title><fmt:message key="registration"/></title>
</head>
<body>

<%@ include file="include/navigation.jsp" %>

<div class="container container-top-padding">
    <div class="row">
        <div class="form-group">
            <span class="centring"><fmt:message key="registration"/></span>
        </div>
        <div class="col-md-5 col-md-offset-3">
            <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/do"
                  onkeyup="return validateUserRegistrationForm()">
                <input type="hidden" name="action" value="userRegistration">
                <div class="form-group">
                    <label for="name" class="col-md-2 control-label"><fmt:message key="name"/></label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="name" id="name"
                               placeholder="<fmt:message key="name"/>">
                    </div>
                </div>
                <div class="form-group">
                    <label for="lastName" class="col-md-2 control-label"><fmt:message key="last.name"/></label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="lastName" id="lastName"
                               placeholder="<fmt:message key="last.name"/>">
                    </div>
                </div>
                <div class="form-group">
                    <label for="login" class="col-md-2 control-label"><fmt:message key="login"/></label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="login" id="login"
                               placeholder="<fmt:message key="login"/>">
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
                    <label for="confirmPassword" class="col-md-2 control-label"><fmt:message key="confirm"/></label>
                    <div class="col-md-10">
                        <input type="password" class="form-control" name="confirmPassword" id="confirmPassword"
                               placeholder="<fmt:message key="confirm"/>">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-success"><fmt:message key="registration"/></button>
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