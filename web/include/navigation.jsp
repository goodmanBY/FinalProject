<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="navbar-wrapper">
    <div class="container">
        <nav class="navbar navbar-inverse navbar-static-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                            aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="index.jsp">Royal Hostel</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="index.jsp"><fmt:message key="home"/></a></li>
                        <li><a href="${pageContext.request.contextPath}/do?action=about"><fmt:message key="about"/></a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                               aria-haspopup="true" aria-expanded="false"><fmt:message key="language"/><span
                                    class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="${pageContext.request.contextPath}/do?action=changeLanguage&language=en_EN">English</a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/do?action=changeLanguage&language=ru_RU">Русский</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                    <c:choose>
                        <c:when test="${empty sessionScope.user}">
                            <ul class="nav navbar-nav navbar-right">
                                <li><a href="userLogIn.jsp"><fmt:message key="log.in"/></a></li>
                                <li><a href="userRegistration.jsp"><fmt:message key="registration"/></a></li>
                            </ul>
                        </c:when>
                        <c:otherwise>
                            <ul class="nav navbar-nav navbar-right">
                                <li><a href="userProfile.jsp"><fmt:message key="profile"/></a></li>
                                <li><a href="${pageContext.request.contextPath}/do?action=userLogOut"><fmt:message
                                        key="log.out"/></a></li>
                            </ul>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </nav>
    </div>
</div>
