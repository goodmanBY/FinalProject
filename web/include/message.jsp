<span id="validation-error" class="error-message centring"></span>

<c:if test="${not empty error}">
    <span class="error-message centring">${error}</span>
</c:if>

<c:if test="${not empty registered}">
    <span class="registered centring">${registered}</span>
</c:if>
