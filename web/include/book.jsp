
    <div class="row col-md-5">
        <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/do">
            <input type="hidden" name="action" value="userRequest">
            <div class="form-group">
                <span class="centring"><fmt:message key="make.a.room.reservation"/></span>
            </div>
            <div class="form-group">
                <label for="inputAmountOfPlaces" class="col-sm-3 control-label"><fmt:message key="place.number"/></label>
                <div class="col-sm-9">
                    <input type="number" class="form-control" name="amountOfPlaces" id="inputAmountOfPlaces" placeholder="Number" min="1" max="5">
                </div>
            </div>
            <div class="form-group">
                <label for="inputDateFrom" class="col-sm-3 control-label"><fmt:message key="date.from"/></label>
                <div class="col-sm-9">
                    <input type="date" class="form-control" name="dateFrom" id="inputDateFrom" placeholder="From">
                </div>
            </div>
            <div class="form-group">
                <label for="inputDateTo" class="col-sm-3 control-label"><fmt:message key="date.to"/></label>
                <div class="col-sm-9">
                    <input type="date" class="form-control" name="dateTo" id="inputDateTo" placeholder="To">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-9">
                    <button type="submit" class="btn btn-success"><fmt:message key="book"/></button>
                </div>
            </div>
        </form>
    </div>
