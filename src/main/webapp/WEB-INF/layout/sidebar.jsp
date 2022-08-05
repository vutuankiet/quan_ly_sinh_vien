<%--
  Created by IntelliJ IDEA.
  User: vutua
  Date: 7/16/2022
  Time: 12:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="sidebar-main sidebar-menu-one sidebar-expand-md sidebar-color">
    <div class="mobile-sidebar-header d-md-none d-flex justify-content-between">
        <div class="header-logo">
            <a class="d-flex align-items-center" href="${pageContext.request.contextPath}/dashboard">
                <img class="border rounded-circle bg-white" src="${pageContext.request.contextPath}/Assets/img/favicon.png" style="width: 35px; height: 35px;" alt="logo">
                <div class="ml-3 text-white">
                    <p class="m-0 p-0 text-white">Students</p>
                    <h5 class="m-0 p-0 font-bold text-white">Managers</h5>
                </div>
            </a>
        </div>
        <button style="padding: 0 20px 0 20px; color: white;" type="button" class="navbar-toggler sidebar-toggle-mobile">
            <i class="fas fa-bars"></i>
        </button>

    </div>
    <div class="sidebar-menu-content">
        <ul class="nav nav-sidebar-menu sidebar-toggle-view">
            <li class="nav-item sidebar-nav-item">
                <a href="#" class="nav-link"><i class="flaticon-dashboard"></i><span>Dashboard</span></a>
                <ul class="nav sub-group-menu">
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/dashboard" class="nav-link"><i class="fas fa-angle-right"></i>Admin</a>
                    </li>
                </ul>
            </li>
            <li class="nav-item sidebar-nav-item">
                <a href="#" class="nav-link"><i class="flaticon-classmates"></i><span>Students</span></a>
                <ul class="nav sub-group-menu">
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/students?page=1" class="nav-link"><i class="fas fa-angle-right"></i>All
                            Students</a>
                    </li>
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/students-add" class="nav-link"><i
                                class="fas fa-angle-right"></i>Add Student</a>
                    </li>
                </ul>
            </li>
            <li class="nav-item sidebar-nav-item">
                <a href="#" class="nav-link"><i
                        class="flaticon-maths-class-materials-cross-of-a-pencil-and-a-ruler"></i><span>Class</span></a>
                <ul class="nav sub-group-menu">
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/classes?page=1" class="nav-link"><i class="fas fa-angle-right"></i>All
                            Classes</a>
                    </li>
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/classes-add" class="nav-link"><i class="fas fa-angle-right"></i>Add New
                            Class</a>
                    </li>
                </ul>
            </li>
            <li class="nav-item sidebar-nav-item">
                <a href="#" class="nav-link"><i
                        class="flaticon-open-book"></i><span>Subjects</span></a>
                <ul class="nav sub-group-menu">
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/subjects?page=1" class="nav-link"><i class="fas fa-angle-right"></i>All
                            Subjects</a>
                    </li>
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/subjects-add" class="nav-link"><i class="fas fa-angle-right"></i>Add New
                            Subject</a>
                    </li>
                </ul>
            </li>
            <li class="nav-item sidebar-nav-item">
                <a href="#" class="nav-link"><i class="flaticon-shopping-list"></i><span>Scores</span></a>
                <ul class="nav sub-group-menu">
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/scores?page=1" class="nav-link"><i class="fas fa-angle-right"></i>All Scores</a>
                    </li>
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/scores-add" class="nav-link"><i class="fas fa-angle-right"></i>Add
                            Score</a>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</div>

