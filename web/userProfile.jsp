<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${not empty sessionScope.language ? sessionScope.language  : 'en'}"/>
<fmt:setBundle basename="com.savko.i18n.text"/>
<html>
<head>
    <%@ include file="include/head.jsp" %>
    <title>Profile</title>
</head>
<body>

<c:if test="${not empty sessionScope.user}">

    <%@ include file="include/navigation.jsp" %>

    <div class="container container-top-padding">
        <div class="row">
            <div>
                <span class="centring"><h2>Welcome, ${sessionScope.user.login}!</h2></span>
            </div>
            <div class="col-md-6 col-md-offset-2">
                <div hidden>
                    <input name="userId" id="userId" value="${sessionScope.user.id}">
                </div>
                <div class="form-group">
                    <label class="col-md-offset-6 col-md-2 control-label"><fmt:message key="name"/></label>
                    <span>${sessionScope.user.name}</span>
                </div>
                <div class="form-group">
                    <label class="col-md-offset-6 col-md-2 control-label"><fmt:message key="last.name"/></label>
                    <span>${sessionScope.user.lastName}</span>
                </div>
                <div class="form-group">
                    <label class="col-md-offset-6 col-md-2 control-label"><fmt:message key="login"/></label>
                    <span>${sessionScope.user.login}</span>
                </div>
            </div>
            <div class="col-md-12 centring">
                <a href="${pageContext.request.contextPath}/do?action=userAllRequestsInfo"><h2>My requests</h2></a>
            </div>
        </div>
    </div>

    <%@ include file="include/footer.jsp" %>

    <%@ include file="include/script.jsp" %>

</c:if>
</body>
</html>
