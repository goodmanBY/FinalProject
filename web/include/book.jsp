<div class="col-md-6">
    <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/do">
        <input type="hidden" name="action" value="userRequest">
        <c:if test="${empty sessionScope.user || sessionScope.user.banned == 0}">
            <div class="form-group">
                <span class="col-sm-offset-3 col-sm-9"><fmt:message key="make.room.reservation"/></span>
            </div>
        </c:if>
        <c:if test="${empty sessionScope.user}">
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-9 error-message">
                    <span><fmt:message key="empty.user.message"/></span>
                </div>
            </div>
        </c:if>
        <c:if test="${sessionScope.user.banned == 1}">
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-9 error-message">
                    <span><fmt:message key="banned.user.message"/></span>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-9 error-message">
                    <span><fmt:message key="cause"/>: ${sessionScope.blockDescription}</span>
                </div>
            </div>
        </c:if>
        <c:if test="${empty sessionScope.user || sessionScope.user.banned == 0}">
            <div class="form-group">
                <label for="amountOfPlaces" class="col-sm-3 control-label"><fmt:message
                        key="amount.of.places"/></label>
                <div class="col-sm-9">
                    <input type="number" class="form-control" name="amountOfPlaces"
                           id="amountOfPlaces" placeholder="<fmt:message key="amount.of.places"/>"
                        ${not empty sessionScope.user ? 'value = \"1\"' : ''} min="1" max="5"
                        ${empty sessionScope.user ? 'readonly' : ''}>
                </div>
            </div>
            <div class="form-group">
                <label for="dateFrom" class="col-sm-3 control-label"><fmt:message key="date.from"/></label>
                <div class="col-sm-9">
                    <input type="date" class="form-control" name="dateFrom"
                           id="dateFrom" placeholder="From"
                        ${empty sessionScope.user ? 'readonly' : ''}>
                </div>
            </div>
            <div class="form-group">
                <label for="dateTo" class="col-sm-3 control-label"><fmt:message key="date.to"/></label>
                <div class="col-sm-9">
                    <input type="date" class="form-control" name="dateTo"
                           id="dateTo" placeholder="To"
                        ${empty sessionScope.user ? 'readonly' : ''}>
                </div>
            </div>
        </c:if>
        <c:if test="${not empty sessionScope.user && sessionScope.user.banned == 0}">
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-9">
                    <button type="submit" class="btn btn-success"><fmt:message key="book"/></button>
                </div>
            </div>
        </c:if>
    </form>
    <%@ include file="/include/message.jsp" %>
</div>
