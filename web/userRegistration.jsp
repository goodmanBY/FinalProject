<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${not empty sessionScope.language ? sessionScope.language  : 'en'}"/>
<fmt:setBundle basename="com.savko.i18n.text"/>
<html lang="en">
<head>
    <%@ include file="include/head.jsp" %>
    <title>Registration</title>
</head>
<body>

<%@ include file="include/navigation.jsp" %>

<div class="container container-top-padding">
    <div class="row">
        <div class="col-md-6 col-md-offset-2">
            <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/do">
                <input type="hidden" name="action" value="userRegistration">
                <div class="form-group">
                    <span class="centring">Register</span>
                </div>
                <div class="form-group">
                    <label for="name" class="col-md-2 control-label"><fmt:message key="name"/></label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="name" id="name" placeholder="Name">
                    </div>
                </div>
                <div class="form-group">
                    <label for="lastName" class="col-md-2 control-label"><fmt:message key="last.name"/></label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="lastName" id="lastName" placeholder="Last Name">
                    </div>
                </div>
                <div class="form-group">
                    <label for="login" class="col-md-2 control-label"><fmt:message key="login"/></label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="login" id="login" placeholder="Login">
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-md-2 control-label"><fmt:message key="password"/></label>
                    <div class="col-md-10">
                        <input type="password" class="form-control" name="password" id="password"
                               placeholder="Password">
                    </div>
                </div>
                <div class="form-group">
                    <label for="confirmPassword" class="col-md-2 control-label"><fmt:message key="confirm"/></label>
                    <div class="col-md-10">
                        <input type="password" class="form-control" name="confirmPassword" id="confirmPassword"
                               placeholder="Password">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-success"><fmt:message key="registration"/></button>
                    </div>
                </div>
            </form>
            <span id="validation-error" class="error-message centring"></span>
            <%@ include file="include/message.jsp" %>
        </div>
    </div>
</div>

<%@ include file="include/footer.jsp" %>

<%@ include file="include/script.jsp" %>

</body>
</html>