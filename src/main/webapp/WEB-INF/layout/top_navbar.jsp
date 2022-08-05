<div class="navbar navbar-expand-md header-menu-one bg-light">
    <div class="nav-bar-header-one">
        <div class="header-logo">
            <a class="d-flex align-items-center" href="${pageContext.request.contextPath}/dashboard">
                <img class="border rounded-circle bg-white" src="${pageContext.request.contextPath}/Assets/img/favicon.png" alt="logo">
                <div class="ml-3 text-white">
                    <p class="m-0 p-0">Students</p>
                    <h5 class="m-0 p-0 font-bold">Managers</h5>
                </div>
            </a>
        </div>
        <div class="toggle-button sidebar-toggle">
            <button type="button" class="item-link">
                <span class="btn-icon-wrap">
                    <span></span>
                    <span></span>
                    <span></span>
                </span>
            </button>
        </div>
    </div>
    <div class="d-md-none mobile-nav-bar">
        <button class="navbar-toggler pulse-animation" type="button" data-toggle="collapse"
                data-target="#mobile-navbar" aria-expanded="false">
            <i class="far fa-arrow-alt-circle-down"></i>
        </button>
        <button type="button" class="navbar-toggler sidebar-toggle-mobile">
            <i class="fas fa-bars"></i>
        </button>
    </div>
    <div class="header-main-menu collapse navbar-collapse" id="mobile-navbar">
        <ul class="navbar-nav">
        </ul>
        <ul class="navbar-nav">
            <li class="navbar-item dropdown header-admin">
                <a class="navbar-nav-link" href="#" role="button" data-toggle="dropdown"
                   aria-expanded="false">
                    <div class="admin-title">
                        <h5 class="item-title">dangkhanh.dev@gmail.com</h5>
                        <span>Admin</span>
                    </div>
                    <div class="admin-img">
                        <img src="${pageContext.request.contextPath}/Assets/img/figure/admin.jpg" alt="Admin">
                    </div>
                </a>
                <div class="dropdown-menu dropdown-menu-right">
                    <div class="item-header">
                        <h6 class="item-title">dangkhanh.dev@gmail.com</h6>
                    </div>
                    <div class="item-content">
                        <ul class="settings-list">
                            <li><a href="${pageContext.request.contextPath}/logout"><i class="flaticon-turn-off"></i>Log Out</a></li>
                        </ul>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</div>
