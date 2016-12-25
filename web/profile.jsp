<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${not empty sessionScope.language ? sessionScope.language  : 'en'}"/>
<fmt:setBundle basename="com.savko.i18n.text"/>
<html>
<head>
    <%@ include file="include/head.jsp"%>
    <title>Profile</title>
</head>
<body>

<%@ include file="include/navigation.jsp"%>

<div style="height: 500px">
${}
</div>

<%@ include file="include/footer.jsp"%>

<%@ include file="include/script.jsp"%>

</body>
</html>
