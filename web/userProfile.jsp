<%@ include file="include/userTags.jsp" %>

<html>
<head>
    <%@ include file="include/head.jsp" %>
    <title><fmt:message key="profile"/></title>
</head>
<body>

<c:if test="${not empty sessionScope.user}">

    <%@ include file="include/navigation.jsp" %>

    <div class="container container-top-padding">
        <div class="row">
            <div>
                <span class="col-md-12 centring"><h2><fmt:message
                        key="welcome"/>, ${sessionScope.user.login}!</h2></span>
            </div>
            <div class="col-md-5 col-md-offset-3">
                <div hidden>
                    <input name="userId" id="userId" value="${sessionScope.user.id}">
                </div>
                <div class="form-group">
                    <label for="name" class="col-md-offset-2 col-md-2 control-label"><fmt:message key="name"/></label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" name="name" id="name"
                               value="${sessionScope.user.name}" readonly>
                    </div>
                </div>
                <div class="form-group">
                    <label for="lastName" class="col-md-offset-2 col-md-2 control-label"><fmt:message
                            key="last.name"/></label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" name="lastName" id="lastName"
                               value="${sessionScope.user.lastName}" readonly>
                    </div>
                </div>
                <div class="form-group">
                    <label for="login" class="col-md-offset-2 col-md-2 control-label"><fmt:message key="login"/></label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" name="login" id="login"
                               value="${sessionScope.user.login}" readonly>
                    </div>
                </div>
            </div>
            <div class="col-md-12 centring">
                <a href="${pageContext.request.contextPath}/do?action=userAllRequestsInfo"><h2><fmt:message
                        key="my.requests"/></h2></a>
            </div>
        </div>
    </div>

    <%@ include file="include/footer.jsp" %>

    <%@ include file="include/script.jsp" %>

</c:if>

</body>
</html>
