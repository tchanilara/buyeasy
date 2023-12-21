<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en" data-bs-theme="auto">

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Register</title>
<!-- Bootstrap icons-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
    integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">


<style>
    .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
    }

    @media (min-width: 768px) {
        .bd-placeholder-img-lg {
            font-size: 3.5rem;
        }
    }

    .b-example-divider {
        width: 100%;
        height: 3rem;
        background-color: rgba(0, 0, 0, .1);
        border: solid rgba(0, 0, 0, .15);
        border-width: 1px 0;
        box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
    }

    .b-example-vr {
        flex-shrink: 0;
        width: 1.5rem;
        height: 100vh;
    }

    .bi {
        vertical-align: -.125em;
        fill: currentColor;
    }



    .btn-bd-primary {
        --bd-violet-bg: #712cf9;
        --bd-violet-rgb: 112.520718, 44.062154, 249.437846;

        --bs-btn-font-weight: 600;
        --bs-btn-color: var(--bs-white);
        --bs-btn-bg: var(--bd-violet-bg);
        --bs-btn-border-color: var(--bd-violet-bg);
        --bs-btn-hover-color: var(--bs-white);
        --bs-btn-hover-bg: #6528e0;
        --bs-btn-hover-border-color: #6528e0;
        --bs-btn-focus-shadow-rgb: var(--bd-violet-rgb);
        --bs-btn-active-color: var(--bs-btn-hover-color);
        --bs-btn-active-bg: #5a23c8;
        --bs-btn-active-border-color: #5a23c8;
    }

    .bd-mode-toggle {
        z-index: 1500;
    }

    .bd-mode-toggle .dropdown-menu .active .bi {
        display: block !important;
    }
</style>


<!-- Custom styles for this template -->
<link href="/pub/css/sign-in.css" rel="stylesheet">
</head>

<body class="d-flex align-items-center py-6 bg-body-tertiary">

    <!-- Bootstrap core JS-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <div class="container d-flex justify-content-center">
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
                <input type="text" class="form-control" id="description" name="description" value="${form.description}"
                    placeholder="description" required>
                <div class="error">
                </div>
            </div>
            <div class="form-check text-start my-3">
            </div>
            <button class="btn btn-primary w-100 py-2 " type="submit">Save</button>

        </form>
    </div>
</body>

</html>