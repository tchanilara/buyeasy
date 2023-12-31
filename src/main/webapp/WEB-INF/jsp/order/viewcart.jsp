<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <jsp:include page="../include/header.jsp" />

  <link href="/pub/css/cart.css" rel="stylesheet">

  <section class="h-100 h-custom" style="background-color: #eee;">
    <div class="container py-5 h-100">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col">
          <div class="card">
            <div class="card-body p-4">

              <div class="row">

                <div class="col-lg-8">
                  <h5 class="mb-3"><a href="/" class="text-body"><i class="bi bi-arrow-left me-2"></i>Continue
                      shopping</a></h5>
                  <hr>

                  <div class="d-flex justify-content-between align-items-center mb-4">
                    <div>
                      <p class="mb-1">Shopping cart</p>
                      <p class="mb-0">You have ${size} items in your cart</p>
                    </div>
                    <div>
                      <p class="mb-0"><span class="text-muted">Sort by:</span> <a href="#!" class="text-body">price <i
                            class="bi bi-chevron-down mt-1"></i></a></p>
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
                              <input type="text" name="quantity" size="3" class="form-control input-number"
                                value="${cart.quantityOrdered}" min="1" max="30">
                            </div>
                            <div style="width: 80px;">
                              <h5 class="mb-0 ml-2">$${cart.product.price}</h5>
                            </div>
                            <div style="width: 50px;">
                              <a href="/order/remove?id=${cart.product.id}" class="text-body"><i
                                  class="bi bi-trash me-2"></i></a>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </c:forEach>

                </div>

                <c:if test="${size > 0}">
                  <div class="col-lg-4">

                    <div class="card bg-primary text-white rounded-3">
                      <div class="card-body">
                        <div class="d-flex justify-content-between align-items-center mb-4">
                          <h5 class="mb-0">Card details</h5>
                          <img src="/pub/images/card.png" class="img-fluid rounded-3" style="width: 45px;" alt="Avatar">
                        </div>

                        <p class="small">Card type</p>
                        <a href="#!" type="submit" class="text-white"><img src="/pub/images/visa.png"
                            class="img-fluid rounded-3" style="width: 20px;"></a>
                        <a href="#!" type="submit" class="text-white"><img src="/pub/images/mastercard.png"
                            class="img-fluid rounded-3" style="width: 20px;"></a>
                        <a href="#!" type="submit" class="text-white"><img src="/pub/images/american.png"
                            class="img-fluid rounded-3" style="width: 20px;"></a>
                        <a href="#!" type="submit" class="text-white"><img src="/pub/images/paypal.png"
                            class="img-fluid rounded-3" style="width: 20px;"></a>

                        <form class="mt-4">
                          <div class="form-outline form-white mb-4">
                            <input type="text" id="typeName" class="form-control form-control-lg" siez="17"
                              placeholder="Cardholder's Name" />
                            <label class="form-label" for="typeName">Cardholder's Name</label>
                          </div>

                          <div class="form-outline form-white mb-4">
                            <input type="text" id="typeText" class="form-control form-control-lg" siez="17"
                              placeholder="1234 5678 9012 3457" minlength="19" maxlength="19" />
                            <label class="form-label" for="typeText">Card Number</label>
                          </div>

                          <div class="row mb-4">
                            <div class="col-md-6">
                              <div class="form-outline form-white">
                                <input type="text" id="typeExp" class="form-control form-control-lg"
                                  placeholder="MM/YYYY" size="7" id="exp" minlength="7" maxlength="7" />
                                <label class="form-label" for="typeExp">Expiration</label>
                              </div>
                            </div>
                            <div class="col-md-6">
                              <div class="form-outline form-white">
                                <input type="password" id="typeText" class="form-control form-control-lg"
                                  placeholder="&#9679;&#9679;&#9679;" size="1" minlength="3" maxlength="3" />
                                <label class="form-label" for="typeText">Cvv</label>
                              </div>
                            </div>
                          </div>

                        </form>

                        <hr class="my-4">

                        <div class="d-flex justify-content-between">
                          <p class="mb-2">Subtotal</p>
                          <p class="mb-2">$${subtotal}</p>
                        </div>

                        <div class="d-flex justify-content-between">
                          <p class="mb-2">Shipping</p>
                          <p class="mb-2">$${shipping}</p>
                        </div>

                        <div class="d-flex justify-content-between">
                          <p class="mb-2">Taxes</p>
                          <p class="mb-2">$${taxes}</p>
                        </div>

                        <div class="d-flex justify-content-between mb-4">
                          <p class="mb-2">Total(Incl. taxes)</p>
                          <p class="mb-2">$${total}</p>
                        </div>

                        <form class="d-flex " action="/order/checkout">
                          <button type="submit" class="btn btn-info btn-block btn-lg">
                            <div class="d-flex justify-content-between">
                              <span>$${total}</span>
                              <span>Checkout <i class="bi bi-arrow-right ms-2"></i></span>
                            </div>
                          </button>
                        </form>

                      </div>
                    </div>

                  </div>
                </c:if>

              </div>

            </div>
          </div>
        </div>
      </div>
    </div>
  </section>