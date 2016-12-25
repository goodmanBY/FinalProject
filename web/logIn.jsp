<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${not empty sessionScope.language ? sessionScope.language  : 'en'}"/>
<fmt:setBundle basename="com.savko.i18n.text"/>
<html lang="en">
<head>
    <%@ include file="include/head.jsp" %>
    <title>Log in</title>
</head>
<body>

<%@ include file="include/navigation.jsp" %>

<div class="container containerTopPadding">
    <div class="row">
        <div class="col-md-6 col-md-offset-2">
            <form class="form-horizontal" method="get" action="${pageContext.request.contextPath}/do">
                <input type="hidden" name="action" value="userLogIn">
                <div class="form-group">
                    <span class="centring"><fmt:message key="log.in"/></span>
                </div>
                <div class="form-group">
                    <label for="inputLogin" class="col-sm-2 control-label"><fmt:message key="login"/></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="login" id="inputLogin" placeholder="Login">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword" class="col-sm-2 control-label"><fmt:message key="password"/></label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" name="password" id="inputPassword"
                               placeholder="Password">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-success"><fmt:message key="log.in"/></button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<%@ include file="include/footer.jsp" %>

<%@ include file="include/script.jsp"%>

</body>
</html>