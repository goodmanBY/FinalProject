<%@ include file="/include/adminTags.jsp" %>

<html>
<head>
    <%@ include file="/include/head.jsp" %>
    <title>Title</title>
</head>
<body>

<div class="container">
    <div class="row">
        <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/do"
              onkeyup="return validateAddBlockDescriptionForm()">
            <input type="hidden" name="action" value="addBlockDescription">
            <div hidden>
                <label for="userId"></label><input name="userId" id="userId" value="${requestScope.userId}">
            </div>
            <div>
                <span class="col-md-12 centring"><h2>Add description</h2></span>
            </div>
                <textarea class="form-control custom-text-area" name="blockDescription" rows="3"></textarea>
            <div class="form-group">
                <div class="col-md-12 centring">
                    <button type="submit" class="btn btn-success">Block user</button>
                </div>
            </div>
        </form>
        <%@ include file="/include/message.jsp" %>
    </div>
</div>

<%@ include file="/include/toControlPanel.jsp" %>

<%@ include file="/include/script.jsp" %>

</body>
</html>
