<%@ page import="com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.EF.Score" %>
<%@ page import="com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.EF.Classes" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vutua
  Date: 7/16/2022
  Time: 11:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Breadcubs Area Start Here -->
<div class="breadcrumbs-area">

    <h3>Score</h3>
    <ul>
        <li>
            <a href="${pageContext.request.contextPath}/dashboard">Home</a>
        </li>
        <li>Score Admit Form</li>
    </ul>
</div>
<!-- Breadcubs Area End Here -->
<!-- Admit Form Area Start Here -->
<div class="card height-auto">
    <div class="card-body">
        <div class="heading-layout1">
            <div class="item-title">
                <h3>Add New Score by class <span class="text-success">${requestScope.name_class.name}</span></h3>

            </div>
        </div>
        <form class="mg-b-20" method="post" action="${pageContext.request.contextPath}/add-by-class">
            <div class="row gutters-8 align-items-end">
                <div class="col-4-xxxl col-xl-4 col-lg-3 col-12 form-group">
                    <label>Change Class</label>
                    <%--                    <input type="text" name="_student_id" placeholder="Student Name" minlength="10" maxlength="25" class="form-control" required>--%>
                    <select name="_class_id" class="select2 form-control">
                        <c:forEach var="Data" items="${requestScope.DataClass}">
                            <option value="${Data.id}">${Data.name}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="col-12 col-4-xxxl col-xl-4 col-lg-3 form-group mg-t-8">
                    <button type="submit" class="btn-fill-lg btn-gradient-yellow btn-hover-bluedark">Change</button>
                </div>
            </div>
        </form>

        <form class="new-added-form" method="post" action="${pageContext.request.contextPath}/scores-add">
            <div class="row">
                <div class="col-xl-3 col-lg-6 col-12 form-group">
                    <label>Student Name *</label>
                    <%--                    <input type="text" name="_student_id" placeholder="Student Name" minlength="10" maxlength="25" class="form-control" required>--%>
                    <select name="_student_id" class="select2 form-control">
                        <c:forEach var="Data" items="${requestScope.DataStudent}">
                            <option value="${Data.id}">${Data.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-xl-3 col-lg-6 col-12 form-group">
                    <label>Subject *</label>
                    <select name="_subject_id" class="select2 form-control">
                        <c:forEach var="Data" items="${requestScope.DataSubject}">
                            <option value="${Data.id}">${Data.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-xl-3 col-lg-6 col-12 form-group">
                    <label>State *</label>
                    <select name="sc_state" class="select2 form-control">
                        <option value="1">Active</option>
                        <option value="0">Locked</option>
                    </select>
                </div>
                <div class="col-xl-3 col-lg-6 col-12 form-group">
                    <label>Score *</label>
                    <input type="text" name="sc_score" placeholder="Score" minlength="1" maxlength="4" class="form-control" pattern="^10|((\d)(\.(\d)|\.(\d\d)))$" required>
                </div>

                <div class="col-12 form-group mg-t-8">
                    <button id="create" type="submit" class="btn-fill-lg btn-gradient-yellow btn-hover-bluedark">Save</button>
                    <button type="reset" class="btn-fill-lg bg-blue-dark btn-hover-yellow">Reset</button>
                </div>
            </div>
        </form>
    </div>
</div>

<div style="height: 10vh;"></div>