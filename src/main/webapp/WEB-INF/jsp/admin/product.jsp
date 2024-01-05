<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <jsp:include page="../include/header.jsp" />
    <title>Add New Product</title>

    <section>
        <div class="container d-flex justify-content-center mt-3">
            <form method="get" id="form" action="/admin/productSubmit">
                <div class="text-center">
                    <h1 class="h3 mb-3 fw-normal">Add New Product!!!</h1>
                </div>
                <c:if test="${not empty success}">
                    <div class="text-center">
                        <div class="row justify-content-center">
                            <div class="alert alert-success" role="alert">
                                ${success}
                            </div>
                        </div>
                    </div>
                </c:if>

                <div class="input-group">
                    <div class="form-floating mr-3">
                        <label for="code">Code</label>
                        <input type="text" class="form-control" id="code" name="code" value="${form.code}"
                            placeholder="Code" required>
                        <div class="error">
                        </div>
                    </div>

                    <div class="form-floating">
                        <label for="name">Name</label>
                        <input type="text" class="form-control" id="name" name="name" value="${form.name}"
                            placeholder="Name" required>
                        <div class="error">
                        </div>
                    </div>
                </div>
                <div class="input-group">
                    <div class="form-floating mr-3">
                        <label for="price">Price</label>
                        <input type="text" class="form-control" id="price" name="price" value="${form.price}"
                            placeholder="Price">
                    </div>
                    <div class="form-floating">
                        <label for="imageUrl">Image Url</label>
                        <input type="text" class="form-control" id="imageUrl" name="imageUrl" value="${form.imageUrl}"
                            placeholder="Image Url">
                    </div>
                </div>
                <div class="form-floating">
                    <label for="description">Description</label>
                    <input type="text" class="form-control" id="description" name="description"
                        value="${form.description}" placeholder="description" required>
                    <div class="error">
                    </div>
                </div>
                <div class="form-check text-start my-3">
                </div>
                <button class="btn btn-primary w-100 py-2 " type="submit">Save</button>

            </form>
        </div>
    </section>