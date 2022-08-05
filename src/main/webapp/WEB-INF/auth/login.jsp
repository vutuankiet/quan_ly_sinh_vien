<!doctype html>
<html class="no-js" lang="">
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>${requestScope.web_title}</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Favicon -->
    <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/Assets/img/favicon.png">
    <!-- Normalize CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Assets/css/normalize.css">
    <!-- Main CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Assets/css/main.css">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Assets/css/bootstrap.min.css">
    <!-- Fontawesome CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Assets/css/all.min.css">
    <!-- Flaticon CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Assets/fonts/flaticon.css">
    <!-- Animate CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Assets/css/animate.min.css">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Assets/css/style.css">
    <!-- Modernize js -->
    <script src="${pageContext.request.contextPath}/Assets/js/modernizr-3.6.0.min.js"></script>
</head>

<body>
<!-- Preloader Start Here -->
<div id="preloader"></div>
<!-- Preloader End Here -->
<!-- Login Page Start Here -->
<div class="login-page-wrap">
    <div class="login-page-content">
        <div class="login-box">
            <div class="item-logo">
                <a class="d-flex align-items-center justify-content-center" href="${pageContext.request.contextPath}/dashboard">
                    <img class="border rounded-circle bg-white" src="${pageContext.request.contextPath}/Assets/img/favicon.png" alt="logo">
                    <div class="ml-3 text-white">
                        <p class="m-0 p-0">Students</p>
                        <h5 class="m-0 p-0 font-bold">Managers</h5>
                    </div>
                </a>
            </div>
            <form action="${pageContext.request.contextPath}/login" method="post" class="login-form">
                <div class="form-group">
                    <label>Email</label>
                    <input type="email" name="txt_email" placeholder="E-MAIL" class="form-control" required>
                    <i class="far fa-envelope"></i>
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <input type="password" name="txt_password" placeholder="PASSWORD" class="form-control" required>
                    <i class="fas fa-lock"></i>
                </div>
                <div class="form-group d-flex align-items-center justify-content-between">
                    <div class="form-check">
                        <input type="checkbox" name="txt_remember" class="form-check-input" id="remember-me">
                        <label for="remember-me" class="form-check-label">Remember Me</label>
                    </div>
                </div>
                <div class="form-group">
                    <button type="submit" class="login-btn">Login</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Login Page End Here -->
<!-- jquery-->
<script src="${pageContext.request.contextPath}/Assets/js/jquery-3.3.1.min.js"></script>
<!-- Plugins js -->
<script src="${pageContext.request.contextPath}/Assets/js/plugins.js"></script>
<!-- Popper js -->
<script src="${pageContext.request.contextPath}/Assets/js/popper.min.js"></script>
<!-- Bootstrap js -->
<script src="${pageContext.request.contextPath}/Assets/js/bootstrap.min.js"></script>
<!-- Scroll Up Js -->
<script src="${pageContext.request.contextPath}/Assets/js/jquery.scrollUp.min.js"></script>
<!-- Custom Js -->
<script src="${pageContext.request.contextPath}/Assets/js/main.js"></script>
<%
    String req_msg = (String) request.getAttribute("err_msg");
%>
<% if (req_msg != null) { %>
<script>alert('<%=req_msg%>')</script>
<%}%>

</body>
</html>