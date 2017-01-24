<%@ include file="/include/adminTags.jsp" %>

<html>
<head>
    <%@ include file="/include/head.jsp" %>
    <title>Title</title>
</head>
<body>

<div class="row">
    <div class="col-md-12 centring">
        <h2>Change room cost and discount values</h2>
    </div>

    <div class="col-md-6 centring">
        <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/admin/do">
            <input type="hidden" name="action" value="changeRoomCost">
            <div class="form-group">
                <div class="col-md-12 centring">
                    <h4>Current room cost is $${requestScope.roomCost}</h4>
                </div>
                <label for="roomCost" class="col-md-4 control-label">Change</label>
                <div class="col-md-4">
                    <input type="number" class="form-control" name="roomCost" id="roomCost" min="1">
                </div>
                <div class="col-md-4">
                    <button type="submit" class="btn btn-success">Submit</button>
                </div>

            </div>
        </form>
    </div>

    <div class="col-md-6 centring">
        <table class="table table-hover">
            <tr>
                <th>Discount ID</th>
                <th>Discount value</th>
                <th>Change discount</th>
                <th></th>
            </tr>
            <c:forEach items="${requestScope.discounts}" var="discounts">
                <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/admin/do">
                    <input type="hidden" name="action" value="changeDiscountValue">
                    <tr>
                        <input type="hidden" class="form-control" name="discountId" value="${discounts.discountId}">

                        <th class="col-md-3">${discounts.discountId}</th>

                        <th class="col-md-3">${discounts.discount}%</th>

                        <th class="col-md-3">
                            <input type="number" class="form-control" name="discountValue" id="discountValue" min="1"
                                   max="20">
                        </th>

                        <th class="col-md-3">
                            <button type="submit" class="btn btn-success">Submit</button>
                        </th>
                    </tr>
                </form>
            </c:forEach>
        </table>
    </div>
</div>

<%@ include file="/include/toControlPanel.jsp" %>

<%@ include file="/include/script.jsp" %>

</body>
</html>
