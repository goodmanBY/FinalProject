<%@ include file="/include/adminTags.jsp" %>

<html>
<head>
    <%@ include file="/include/head.jsp" %>
    <title>Admin</title>
</head>
<body>

<div class="container container-top-padding">
    <div class="row">
        <div class="col-md-6 col-md-offset-2">
            <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/do" onkeyup="return validateLogInForm()">
                <input type="hidden" name="action" value="adminLogIn">
                <div class="form-group">
                    <span class="centring">Log in</span>
                </div>
                <div class="form-group">
                    <label for="login" class="col-md-2 control-label">Login</label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="login" id="login" placeholder="Login">
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-md-2 control-label">Password</label>
                    <div class="col-md-10">
                        <input type="password" class="form-control" name="password" id="password"
                               placeholder="Password">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-2 col-md-10">
                        <button type="submit" class="btn btn-success">Log in</button>
                    </div>
                </div>
            </form>
            <span id="validation-error" class="error-message centring"></span>
            <%@ include file="/include/message.jsp" %>
        </div>
    </div>
</div>

<%@ include file="/include/script.jsp" %>

</body>
</html>
