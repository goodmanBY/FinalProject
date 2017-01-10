<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${not empty sessionScope.language ? sessionScope.language  : 'en'}"/>
<fmt:setBundle basename="com.savko.i18n.text"/>
<html lang="en">
<head>
    <%@ include file="include/head.jsp" %>
    <title>Royal Hostel</title>
</head>
<body>

<%@ include file="include/navigation.jsp" %>

<%@ include file="include/carousel.jsp" %>

<div class="container">
    <%@ include file="include/book.jsp" %>
    <%@ include file="include/about.jsp" %>
</div>

<%@ include file="include/footer.jsp" %>

<%@ include file="include/script.jsp" %>

</body>
</html>