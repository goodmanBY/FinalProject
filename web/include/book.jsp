<div class="col-md-6">
    <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/do">
        <input type="hidden" name="action" value="userRequest">
        <div class="form-group">
            <span class="col-sm-offset-3 col-sm-9"><fmt:message key="make.a.room.reservation"/></span>
        </div>
        <c:if test="${empty sessionScope.user}">
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-9 error-message">
                    <span>You should sign in before booking</span>
                </div>
            </div>
        </c:if>
        <c:if test="${sessionScope.user.banned == 1}">
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-9 error-message">
                    <span>You have been banned. You can't book requests</span>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-9 error-message">
                    <span>Cause: ${sessionScope.blockDescription}</span>
                </div>
            </div>
        </c:if>
        <c:if test="${empty sessionScope.user || sessionScope.user.banned == 0}">
            <div class="form-group">
                <label for="inputAmountOfPlaces" class="col-sm-3 control-label"><fmt:message
                        key="place.number"/></label>
                <div class="col-sm-9">
                    <input type="number" class="form-control" name="amountOfPlaces"
                           id="inputAmountOfPlaces" placeholder="Number" min="1" max="5"
                           <c:if test="${empty sessionScope.user}">readonly</c:if>>
                </div>
            </div>
            <div class="form-group">
                <label for="inputDateFrom" class="col-sm-3 control-label"><fmt:message key="date.from"/></label>
                <div class="col-sm-9">
                    <input type="date" class="form-control" name="dateFrom"
                           id="inputDateFrom" placeholder="From"
                           <c:if test="${empty sessionScope.user}">readonly</c:if>>
                </div>
            </div>
            <div class="form-group">
                <label for="inputDateTo" class="col-sm-3 control-label"><fmt:message key="date.to"/></label>
                <div class="col-sm-9">
                    <input type="date" class="form-control" name="dateTo"
                           id="inputDateTo" placeholder="To"
                           <c:if test="${empty sessionScope.user}">readonly</c:if>>
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
        <%@ include file="/include/message.jsp" %>
    </form>
</div>
