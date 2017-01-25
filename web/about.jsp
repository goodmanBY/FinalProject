<%@ include file="include/userTags.jsp" %>

<html>
<head>
    <%@ include file="include/head.jsp" %>
    <title><fmt:message key="about"/></title>
</head>
<body>

<%@ include file="include/navigation.jsp" %>

<div class="container about-top-padding">
    <div class="row">
        <%@ include file="include/about.jsp" %>
        <div class="col-md-6">
            <h2 class="col-md-offset-1"><fmt:message key="info"/></h2>
            <ul>
                <li><fmt:message key="our.rooms.are.similar"/></li>
                <li><fmt:message key="cost.of.our.room"/> $${requestScope.roomCost}.</li>
                <li><fmt:message key="if.you.want.to.stay"/></li>
                <li><fmt:message key="more.the.better"/></li>
            </ul>
            <h4 class="col-md-offset-1"><fmt:message key="enjoy"/>!</h4>
        </div>
    </div>
</div>

<%@ include file="include/footer.jsp" %>

<%@ include file="include/script.jsp" %>

</body>
</html>
