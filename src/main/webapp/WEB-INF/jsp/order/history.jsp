<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <jsp:include page="../include/header.jsp" />

  <link href="/pub/css/cart.css" rel="stylesheet">
  <script>
    $(document).ready(function () {
      $("#history").DataTable();
    });
  </script>

  <section class="h-100 h-custom" style="background-color: #eee;">
    <div id="history" class="container py-5 h-100">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <h5 class="mb-3"><a href="/" class="text-body"><i class="bi bi-arrow-left me-2"></i>Continue
                            shopping</a></h5>
        <hr>
        <c:if test="${not empty cartVar}">
          <table class="searchable sortable table">
            <thead>
              <tr>
                <th scope="col">Order No</th>
                <th scope="col">Status</th>
                <th scope="col">Created</th>
                <th scope="col">Number</th>
                <th scope="col">Total</th>
                <th scope="col">Details</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach items="${cartVar}" var="order">
                <tr>
                  <th scope="row"><a href="/order/detail?orderid=${order.id}">${order.id}</a></th>
                  <th>${order.status}</th>
                  <td>${order.orderDate}</td>
                  <td>${order.getNumberOfProducts()}</td>
                  <td>$${order.getTotalOrderPrice()}</td>
                  <td><a href="/order/detail?orderid=${order.id}">...</a></td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </c:if>
      </div>
    </div>
  </section>
  <jsp:include page="../include/footer.jsp" />