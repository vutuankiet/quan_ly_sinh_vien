<%--
  Created by IntelliJ IDEA.
  User: vutua
  Date: 7/16/2022
  Time: 11:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
    #classes_table td {
        vertical-align: middle !important;
    }
</style>
<!-- Breadcubs Area Start Here -->
<div class="breadcrumbs-area">
    <h3>Students</h3>
    <ul>
        <li>
            <a href="${pageContext.request.contextPath}/dashboard">Home</a>
        </li>
        <li>All Students</li>
    </ul>
</div>
<!-- Breadcubs Area End Here -->
<!-- Student Table Area Start Here -->
<div class="card height-auto">
    <div class="card-body">
        <div class="heading-layout1">
            <div class="item-title">
                <h3>All Students Data</h3>
            </div>
        </div>
        <form class="mg-b-20" method="post" action="${pageContext.request.contextPath}/students-search?page=1">
            <div class="row gutters-8">
                <div class="col-4-xxxl col-xl-4 col-lg-3 col-12 form-group">
                    <input type="text" name="st_name" maxlength="30" placeholder="Search by Student ..." class="form-control">
                </div>
                <div class="col-4-xxxl col-xl-3 col-lg-3 col-12 form-group">
                    <input type="text" name="cl_name" maxlength="30" placeholder="Search by Class ..." class="form-control">
                </div>
                <div class="col-1-xxxl col-xl-2 col-lg-3 col-12 form-group">
                    <button type="submit" class="fw-btn-fill btn-gradient-yellow">SEARCH</button>
                </div>
            </div>
        </form>
        <div class="table-responsive">
            <table class="table table-striped display text-nowrap" id="classes_table">
                <thead>
                <tr>
                    <th>Photo</th>
                    <th>Name</th>
                    <th>Gender</th>
                    <th>Class</th>
                    <th>Address</th>
                    <th>Date Of Birth</th>
                    <th>Phone</th>
                    <th>E-mail</th>
                    <th>Card</th>
                    <th>State</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="st" items="${requestScope.table_data}">
                    <tr>
                        <td class="text-center"><img src="${pageContext.request.contextPath}/Uploads/${st.avatar}"
                                                     alt="student-image"
                                                     style="width: 100px;height: 100px;object-fit: cover"></td>
                        <td>${st.name}</td>
                        <td>${st.gender == 1 ? "Male":"Female"}</td>
                        <td>${st.class_id}</td>
                        <td>${st.address}</td>
                        <td>${st.birthday}</td>
                        <td>${st.phone}</td>
                        <td>${st.email}</td>
                        <td>${st.card}</td>
                        <td>${st.local_state == 1 ? "<button class='btn btn-info' disabled>Active</button>":"<button class='btn btn-danger' disabled>Locked</button>"}</td>
                        <td>
                            <div class="dropdown">
                                <a href="#" data-toggle="dropdown"
                                   aria-expanded="false">
                                    <span class="flaticon-more-button-of-three-dots"></span>
                                </a>
                                <c:if test = "${st.local_state == 1}">
                                <div class="dropdown-menu dropdown-menu-right">
                                    <a class="dropdown-item"
                                       href="${pageContext.request.contextPath}/students-remove?id=${st.id}"><i
                                            class="fas fa-ban text-orange-red mr-3"></i>Delete</a>
                                    <a class="dropdown-item"
                                       href="${pageContext.request.contextPath}/students-update?id=${st.id}"><i
                                            class="fas fa-edit text-dark-pastel-green mr-3"></i>Edit</a>
                                    <a class="dropdown-item"
                                       href="#" data-toggle="modal" data-target="#ModalCenter${st.id}"><i
                                            class="fas fa-info-circle text-light-sea-green mr-3"></i>Detail</a>
                                </div>
                                </c:if>
                                <c:if test = "${st.local_state == 0}">
                                    <div class="dropdown-menu dropdown-menu-right">
                                        <a class="dropdown-item"
                                           href="${pageContext.request.contextPath}/students-update?id=${st.id}"><i
                                                class="fas fa-edit text-dark-pastel-green mr-3"></i>Edit</a>
                                        <a class="dropdown-item"
                                           href="#" data-toggle="modal" data-target="#ModalCenter${st.id}"><i
                                                class="fas fa-info-circle text-light-sea-green mr-3"></i>Detail</a>
                                    </div>
                                </c:if>
                            </div>
                        </td>
                    </tr>


                    <!-- Modal -->
                    <div class="modal fade" id="ModalCenter${st.id}" tabindex="-1" role="dialog"
                         aria-labelledby="ModalCenterTitle${st.id}" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="ModalLongTitle${st.id}">Student Detail</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <div class="row">
                                        <div class="col-12 col-lg-4 text-center mb-5">
                                            <img src="${pageContext.request.contextPath}/Uploads/${st.avatar}"
                                                 alt="student-image" class="img-fluid" style="width: 200px!important;height: 200px!important;object-fit: cover">
                                        </div>
                                        <div class="col-12 col-lg-8">
                                            <h6 class="font-bold">Full name:<span
                                                    class="ml-3 font-normal">${st.name}</span></h6>
                                            <h6 class="font-bold">Birth:<span
                                                    class="ml-3 font-normal">${st.birthday}</span></h6>
                                            <h6 class="font-bold">Gender:<span
                                                    class="ml-3 font-normal">${st.gender == 1 ? "Male":"Female"}</span>
                                            </h6>
                                            <h6 class="font-bold">Class:<span
                                                    class="ml-3 font-normal">${st.class_id}</span></h6>
                                            <h6 class="font-bold">Address:<span
                                                    class="ml-3 font-normal">${st.address}</span></h6>
                                            <h6 class="font-bold">Phone:<span
                                                    class="ml-3 font-normal">${st.phone}</span></h6>
                                            <h6 class="font-bold">Email:<span
                                                    class="ml-3 font-normal">${st.email}</span></h6>
                                            <h6 class="font-bold">Card:<span class="ml-3 font-normal">${st.card}</span>
                                            </h6>
                                            <h6 class="font-bold">State:<span
                                                    class="ml-3 font-normal">${st.local_state == 1 ? "<button class='btn btn-info' disabled>Active</button>":"<button class='btn btn-danger' disabled>Blocked</button>"}</span>
                                            </h6>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <a class="dropdown-item"
                                       href="${pageContext.request.contextPath}/students-update?id=${st.id}"><i
                                            class="fas fa-edit text-dark-pastel-green mr-3"></i>Edit</a>
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%--end modal--%>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%--    phan trang--%>
<%
    int total_page = Integer.parseInt(request.getAttribute("total_page").toString());
    int current_page = Integer.parseInt(request.getAttribute("current_page").toString());
%>
<div class="dataTables_paginate paging_simple_numbers">
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-end">
            <%if(current_page > 1){%>
            <li class="page-item">
                <a class="page-link" href="${pageContext.request.contextPath}/students?page=<%=current_page - 1%>">Previous</a>
            </li>
            <% } %>

            <% for (int i = 1; i <= total_page + 1; i++) { %>
            <% if (i == current_page) {%>
            <li class="page-item"><a href="${pageContext.request.contextPath}/students?page=<%=i%>"
                                     class="page-link bg-warning px-4 text-white"><%=i%>
            </a></li>
            <% } else {%>
            <li class="page-item"><a href="${pageContext.request.contextPath}/students?page=<%=i%>"
                                     class="page-link px-4"><%=i%>
            </a></li>
            <% } %>
            <% } %>
            <% if (current_page < total_page + 1) {%>
            <li class="page-item">
                <a class="page-link" href="${pageContext.request.contextPath}/students?page=<%=current_page + 1%>">Next</a>
            </li>
            <% } %>
        </ul>
    </nav>
</div>
<%--phan trang     --%>

<script>
    $(document).ready(function () {
        toastr.options = {
            'closeButton': true,
            'debug': false,
            'newestOnTop': false,
            'progressBar': false,
            'positionClass': 'toast-bottom-right',
            'preventDuplicates': false,
            'showDuration': '1000',
            'hideDuration': '1000',
            'timeOut': '3000',
            'extendedTimeOut': '1000',
            'showEasing': 'swing',
            'hideEasing': 'linear',
            'showMethod': 'fadeIn',
            'hideMethod': 'fadeOut',
        }
    });

    const add = setTimeout(Add, 1000);
    const update = setTimeout(Update, 1000);
    const remove = setTimeout(Remove, 1000);

    function Add() {
        <c:if test="${sessionScope.SUCCESS_MSG != null}">
        toastr.success('${sessionScope.SUCCESS_MSG} <% session.setAttribute("SUCCESS_MSG", null); %>');
        </c:if>
    }

    function Update() {
        <c:if test="${sessionScope.UPDATE_SUCCESS_MSG != null}">
        toastr.info('${sessionScope.UPDATE_SUCCESS_MSG} <% session.setAttribute("UPDATE_SUCCESS_MSG", null); %>');
        </c:if>
    }

    function Remove() {
        <c:if test="${sessionScope.DELETE_SUCCESS_MSG != null}">
        toastr.error('${sessionScope.DELETE_SUCCESS_MSG} <% session.setAttribute("DELETE_SUCCESS_MSG", null); %>');
        </c:if>
    }
</script>