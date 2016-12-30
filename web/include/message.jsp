<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty error}">
    <span class="error-message centring">${error}</span>
</c:if>

<c:if test="${not empty registered}">
    <span class="registered centring">${registered}</span>
</c:if>

