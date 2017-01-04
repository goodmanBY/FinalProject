<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${not empty sessionScope.language ? sessionScope.language  : 'en'}"/>
<fmt:setBundle basename="com.savko.i18n.text"/>
<html>
<head>
    <%@ include file="include/head.jsp" %>
    <title>Control Panel</title>
</head>
<body>
Cheers, ${sessionScope.adminLogin}!
<li><a href="${pageContext.request.contextPath}/do?action=adminLogOut"><fmt:message
        key="log.out"/></a></li>
</body>
</html>
