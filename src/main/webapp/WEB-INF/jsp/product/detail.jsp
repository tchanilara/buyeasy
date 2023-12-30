<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp"/>


<section>
    <div class="bg-light2 pt-5 pb-5">
        <div class="row">
            <div class="col-12 text-center">
                <h1 class="m-0">Product Detail</h1>
            </div>
        </div>
    </div>
</section>

<section>
 <div class="container d-flex justify-content-center">
        <div class="card" style="width: 18rem;">
            <img class="card-img-top" style="max-height:200px" src="${product.imageUrl}" alt="Card image cap">
            <div class="card-body">
                <h5 class="card-title"><a href="/product/detail?id=${product.id}">${product.name}</a></h5>
                <p class="card-text">${product.description}</p>
                <p class="card-text">$${product.price}</p>
                <div class="input-group">
                    <span class="input-group-btn mr-3">Quantity
                    </span>
                    <input type="text" name="quantity" size="3" class="form-control input-number" value="1" min="1" max="30">
                    </div>

                <div class="text-center mt-3"><a class="btn btn-outline-dark mt-auto" href="/order/viewcart?id=${product.id}"><i class="bi bi-cart-plus">
                                            </i>
                                             Add to Cart </a>
            </div>
        </div>
    </div>
</section>




<jsp:include page="../include/footer.jsp"/>