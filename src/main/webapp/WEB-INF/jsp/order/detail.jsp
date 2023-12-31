<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <jsp:include page="../include/header.jsp" />

  <link href="/pub/css/cart.css" rel="stylesheet">

  <section class="h-100 h-custom" style="background-color: #eee;">
    <div class="container py-5 h-100">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col">
          <div class="card">
            <div class="card-body p-4">

                <div class="col-lg-12">
                  <h5 class="mb-3"><a href="/" class="text-body"><i class="bi bi-arrow-left me-2"></i>Continue
                      shopping</a></h5>
                  <hr>

                  <div class="d-flex justify-content-between align-items-center mb-4">
                    <div>
                      <p class="mb-1">Order No</p>
                      <p class="mb-0">${orderid} </p>
                    </div>
                  </div>

                  <c:forEach items="${cartVar}" var="cart">
                    <div class="card mb-3">
                      <div class="card-body">
                        <div class="d-flex justify-content-between">
                          <div class="d-flex flex-row align-items-center">
                            <div>
                              <img src="${cart.product.imageUrl}" class="img-fluid rounded-3" alt="Shopping item"
                                style="width: 65px;">
                            </div>
                            <div class="ms-3">
                              <h5>${cart.product.name}</h5>
                              <p class="small mb-0">${cart.product.description}</p>
                            </div>
                          </div>
                          <div class="d-flex flex-row align-items-center">
                            <div style="width: 50px;">
                                <h5 >${cart.quantityOrdered}</h5>
                            </div>
                            <div style="width: 80px;">
                              <h5 class="mb-0 ml-2">$${cart.product.price}</h5>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </c:forEach>

              </div>

            </div>
          </div>
        </div>
      </div>
    </div>
  </section>