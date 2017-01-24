<%@ include file="include/userTags.jsp" %>

<html>
<head>

    <link href="${pageContext.request.contextPath}/css/information.css" rel="stylesheet">
    <%@ include file="include/head.jsp" %>
    <title>Congratulations</title>
</head>
<body>

<%@ include file="include/navigation.jsp" %>

<div class="page-container page-container-responsive">
    <div class="row space-top-8 space-8 row-table">
        <div class="col-5 col-middle">
            <h2>Your request has been successfully booked!</h2>
            <h3>Please wait confirmation!</h3>
            <ul class="list-unstyled">
                <li><a href="${pageContext.request.contextPath}/do?action=userAllRequestsInfo"><fmt:message
                        key="my.requests"/></a></li>
            </ul>
        </div>
        <div class="col-5 col-middle text-center">
            <img src="${pageContext.request.contextPath}/img/congratulations.png"
                 alt="Balloons." height="520" width="600">
        </div>
    </div>
</div>

<%@ include file="include/footer.jsp" %>

<%@ include file="include/script.jsp" %>

</body>
</html>
