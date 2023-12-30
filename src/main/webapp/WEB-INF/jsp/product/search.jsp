<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp"/>


<section>
   <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
               <c:forEach items="${productVar}" var="product">
                   <div class="col mb-2">
                       <div class="card h-100">
                           <!-- Product image-->
                           <img class="card-img-top" src="${product.imageUrl}" alt="..." />
                           <!-- Product details-->
                           <div class="card-body p-4">
                               <div class="text-center">
                                   <!-- Product name-->
                                   <h5 class="fw-bolder">${product.name}</h5>
                                   <!-- Product price-->
                                   $${product.price}
                               </div>
                           </div>
                           <!-- Product actions-->
                           <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                               <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="/product/detail?id=${product.id}">View Details</a>
                               </div>
                               <div class="text-center mt-2"><a class="btn btn-outline-dark mt-auto" href="/order/add?id=${product.id}&qty=1"><i class="bi bi-cart-plus">
                               </i>
                                Add to Cart </a>
                               </div>
                           </div>
                       </div>
                   </div>
               </c:forEach>
    </div>
</section>




<jsp:include page="../include/footer.jsp"/>