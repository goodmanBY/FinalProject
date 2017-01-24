<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${not empty sessionScope.language ? sessionScope.language  : 'en'}"/>
<fmt:setBundle basename="com.savko.i18n.text"/>

<html>
<head>
    <link href="${pageContext.request.contextPath}/css/information.css" rel="stylesheet">
    <title>Error ${code}</title>
</head>
<body>

<div class="page-container page-container-responsive">
    <div class="row space-top-8 space-8 row-table">
        <div class="col-5 col-middle">
            <h1 class="text-jumbo text-ginormous"><fmt:message key="oops"/>!</h1>
            <h2><fmt:message key="something.went.wrong"/></h2>
            <h6><fmt:message key="error.code"/>: ${code}</h6>
            <ul class="list-unstyled">
                <li><fmt:message key="return.to.the.main.page"/></li>
                <li><a href="${pageContext.request.contextPath}/index.jsp"><fmt:message key="home"/></a></li>
            </ul>
        </div>
        <div class="col-5 col-middle text-center">
            <img src="${pageContext.request.contextPath}/img/errorPicture.gif"
                 alt="Girl has dropped her ice cream.">
        </div>
    </div>
</div>

</body>
</html>
