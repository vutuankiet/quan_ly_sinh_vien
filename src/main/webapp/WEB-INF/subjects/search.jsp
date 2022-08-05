<%--
  Created by IntelliJ IDEA.
  User: vutua
  Date: 7/16/2022
  Time: 6:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="breadcrumbs-area">
    <h3>Subjects</h3>
    <ul>
        <li>
            <a href="${pageContext.request.contextPath}/dashboard">Home</a>
        </li>
        <li>All Subjects</li>
    </ul>
</div>
<!-- Breadcubs Area End Here -->
<!-- Student Table Area Start Here -->
<div class="card height-auto">
    <div class="card-body">
        <div class="heading-layout1">
            <div class="item-title">
                <h3>All Subjects Data</h3>
            </div>
        </div>
        <form class="mg-b-20" method="post" action="${pageContext.request.contextPath}/subjects-search?page=1">
            <div class="row gutters-8">
                <div class="col-4-xxxl col-xl-3 col-lg-3 col-12 form-group">
                    <input type="text" name="sj_name" maxlength="30" placeholder="Search by Subject ..." class="form-control">
                </div>
                <div class="col-1-xxxl col-xl-2 col-lg-3 col-12 form-group">
                    <button type="submit" class="fw-btn-fill btn-gradient-yellow">SEARCH</button>
                </div>
            </div>
        </form>
        <div class="table-responsive">
            <table class="table table-striped display text-nowrap">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>State</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>

                <%--3 row here--%>
                <c:forEach var="sj" items="${requestScope.table_data}">
                    <tr>
                        <td>${sj.name}</td>
                        <td>${sj.local_state == 1 ? "<button class='btn btn-info' disabled>Active</button>":"<button class='btn btn-danger' disabled>Locked</button>"}</td>
                        <td>
                            <div class="dropdown">
                                <a href="#" class="" data-toggle="dropdown"
                                   aria-expanded="false">
                                    <span class="flaticon-more-button-of-three-dots"></span>
                                </a>
                                <c:if test = "${sj.local_state == 1}">
                                <div class="dropdown-menu dropdown-menu-right">
                                    <a class="dropdown-item"
                                       href="${pageContext.request.contextPath}/subjects-remove?id=${sj.id}"><i
                                            class="fas fa-ban text-orange-red mr-3"></i>Delete</a>
                                    <a class="dropdown-item"
                                       href="${pageContext.request.contextPath}/subjects-update?id=${sj.id}"><i
                                            class="fas fa-edit text-dark-pastel-green mr-3"></i>Edit</a>
                                    <a class="dropdown-item"
                                       href="#" data-toggle="modal" data-target="#ModalCenter${sj.id}"><i
                                            class="fas fa-info-circle text-light-sea-green mr-3"></i>Detail</a>
                                </div>
                                </c:if>
                                <c:if test = "${sj.local_state == 0}">
                                    <div class="dropdown-menu dropdown-menu-right">
                                        <a class="dropdown-item"
                                           href="${pageContext.request.contextPath}/subjects-update?id=${sj.id}"><i
                                                class="fas fa-edit text-dark-pastel-green mr-3"></i>Edit</a>
                                        <a class="dropdown-item"
                                           href="#" data-toggle="modal" data-target="#ModalCenter${sj.id}"><i
                                                class="fas fa-info-circle text-light-sea-green mr-3"></i>Detail</a>
                                    </div>
                                </c:if>
                            </div>
                        </td>
                    </tr>

                    <!-- Modal -->
                    <div class="modal fade" id="ModalCenter${sj.id}" tabindex="-1" role="dialog"
                         aria-labelledby="ModalCenterTitle${sj.id}" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="ModalLongTitle${sj.id}">Subject Detail</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <div class="row">
                                        <div class="col-12 col-lg-8">
                                            <h6 class="font-bold">Subject name:<span
                                                    class="ml-3 font-normal">${sj.name}</span></h6>
                                            <h6 class="font-bold">State:<span
                                                    class="ml-3 font-normal">${sj.local_state == 1 ? "<button class='btn btn-info' disabled>Active</button>":"<button class='btn btn-danger' disabled>Locked</button>"}</span>
                                            </h6>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
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
            <%if (current_page > 1) {%>
            <li class="page-item">
                <a class="page-link"
                   href="${pageContext.request.contextPath}/subjects-search?page=<%=current_page - 1%>&sj_name=${requestScope.subject_key}">Previous</a>
            </li>
            <% } %>

            <% for (int i = 1; i <= total_page; i++) { %>
            <% if (i == current_page) {%>
            <li class="page-item"><a
                    href="${pageContext.request.contextPath}/subjects-search?page=<%=i%>&sj_name=${requestScope.subject_key}"
                    class="page-link bg-warning px-4 text-white"><%=i%>
            </a></li>
            <% } else {%>
            <li class="page-item"><a
                    href="${pageContext.request.contextPath}/subjects-search?page=<%=i%>&sj_name=${requestScope.subject_key}"
                    class="page-link px-4"><%=i%>
            </a></li>
            <% } %>
            <% } %>
            <% if (current_page < total_page) {%>
            <li class="page-item">
                <a class="page-link" href="${pageContext.request.contextPath}/subjects-search?page=<%=current_page + 1%>&sj_name=${requestScope.subject_key}">Next</a>
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