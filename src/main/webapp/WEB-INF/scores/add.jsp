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
                <h3>Add New Score</h3>
            </div>
        </div>
        <form class="mg-b-20" method="post" action="${pageContext.request.contextPath}/add-by-class">
            <div class="row gutters-8">
                <div class="col-4-xxxl col-xl-4 col-lg-3 col-12 form-group">
                    <label>Class Name *</label>
                    <%--                    <input type="text" name="_student_id" placeholder="Student Name" minlength="10" maxlength="25" class="form-control" required>--%>
                    <select name="_class_id" class="select2 form-control">
                        <c:forEach var="Data" items="${requestScope.DataClass}">
                            <option value="${Data.id}">${Data.name}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="col-12 form-group mg-t  -8">
                    <button type="submit" class="btn-fill-lg btn-gradient-yellow btn-hover-bluedark">Save</button>
                </div>
            </div>
        </form>
    </div>
</div>
<div style="height: 30vh;"></div>