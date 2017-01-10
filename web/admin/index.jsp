<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${not empty sessionScope.language ? sessionScope.language  : 'en'}"/>
<fmt:setBundle basename="com.savko.i18n.text"/>
<html>
<head>
    <%@ include file="/include/head.jsp" %>
    <title>Admin</title>
</head>
<body>

<div class="container container-top-padding">
    <div class="row">
        <div class="col-md-6 col-md-offset-2">
            <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/do">
                <input type="hidden" name="action" value="adminLogIn">
                <div class="form-group">
                    <span class="centring"><fmt:message key="log.in"/></span>
                </div>
                <div class="form-group">
                    <label for="inputLogin" class="col-md-2 control-label"><fmt:message key="login"/></label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="login" id="inputLogin" placeholder="Login">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword" class="col-md-2 control-label"><fmt:message key="password"/></label>
                    <div class="col-md-10">
                        <input type="password" class="form-control" name="password" id="inputPassword"
                               placeholder="Password">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-2 col-md-10">
                        <button type="submit" class="btn btn-success"><fmt:message key="log.in"/></button>
                    </div>
                </div>
            </form>
            <%@ include file="/include/message.jsp" %>
        </div>
    </div>
</div>

<%@ include file="/include/script.jsp" %>

</body>
</html>
