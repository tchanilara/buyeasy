<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="/pub/css/styles.css" rel="stylesheet" />
    <link href="/pub/css/sign-in.css" rel="stylesheet">
</head>

<body>

    <!-- Bootstrap core JS-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

    <!-- Navigation-->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container px-4 px-lg-5">
            <a class="navbar-brand" href="#!">
                <img src="/pub/images/logo.jpg" width="80" height="80" alt=""></a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse gap-1" id="navbarSupportedContent" >
                <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                    <li class="nav-item"><a class="nav-link" href="/about"><b>About</b></a></li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" role="button"
                        data-bs-toggle="dropdown" aria-expanded="false" href="#!"><b>Savings</b></a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="#!">Weekly ad</a></li>
                            <li><a class="dropdown-item" href="#!">All deals</a></li>
                            <li><a class="dropdown-item" href="#!"> Upcoming deals</a></li>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button"
                            data-bs-toggle="dropdown" aria-expanded="false"><b>Products</b></a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="/">All Products</a></li>
                            <li>
                                <hr class="dropdown-divider" />
                            </li>
                            <li><a class="dropdown-item" href="#!">Popular Items</a></li>
                            <li><a class="dropdown-item" href="#!">New Arrivals</a></li>
                        </ul>
                    </li>
                    <sec:authorize access="hasAnyAuthority('ADMIN')">
                                        <li class="nav-item">
                                            <a class="nav-link" href="/admin/product"><b>Add Product</b></a>
                                        </li>
                                    </sec:authorize>
                </ul>
                <form class="d-flex " action="/product/search">
                    <input class="form-control  bg-opacity-10 border-dark" type="search" id="search" name="search" placeholder="Search" aria-label="Search">

                    <button  class="btn  bg-opacity-10 border-0 ms-n5" type="submit"><i class="bi bi-search"></i></button>
                  </form>
                  <sec:authorize access="!isAuthenticated()">
                <form class="d-flex" action="/auth/login">
                    <button type="submit" class="btn btn-primary">Sign in</button>
                </form>
                <form class="d-flex" action="/auth/register">
                    <button type="submit" class="btn btn-secondary">Register</button>
                </form>
                </sec:authorize>

                <sec:authorize access="isAuthenticated()">

                <form class="d-flex" action="/order/viewcart">
                                    <button class="btn btn-outline-dark" type="submit">
                                        <i class="bi-cart-fill me-1"></i>
                                        Cart
                                        <span class="badge bg-dark text-white ms-1 rounded-pill">${size}</span>
                                    </button>
                                </form>
                                <sec:authorize access="isAuthenticated()">
                                <div class="dropdown">
                                  <button class="btn btn-outline-dark rounded-pill dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                                    <i class="bi bi-person"></i>
                                  </button>
                                  <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                    <li><a class="dropdown-item" href=""><sec:authentication property="principal.username" /></a></li>
                                    <li><a class="dropdown-item" href="#">Profile</a></li>
                                    <li><a class="dropdown-item" href="#"><hr class="dropdown-divider" /></a></li>
                                    <li><a class="dropdown-item" href="/order/history">History</a></li>
                                  </ul>
                                </div>
                                </sec:authorize>

                                <form class="d-flex" action="/auth/logout">
                                    <button type="submit" class="btn btn-outline-dark rounded-pill" title="Log Out"><i class="bi bi-box-arrow-right"></i></button>
                                </form>
                </sec:authorize>

            </div>
        </div>
    </nav>