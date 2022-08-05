<%--
  Created by IntelliJ IDEA.
  User: vutua
  Date: 7/16/2022
  Time: 11:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <!-- Breadcubs Area Start Here -->
    <div class="breadcrumbs-area">
        <h3>Score</h3>
        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/dashboard">Home</a>
            </li>
            <li>Edit Score</li>
        </ul>
    </div>
    <!-- Breadcubs Area End Here -->
    <!-- Admit Form Area Start Here -->
    <div class="card height-auto">
        <div class="card-body">
            <div class="heading-layout1">
                <div class="item-title">
                    <h3>Edit Score</h3>
                </div>
            </div>
            <form class="new-added-form" method="post" action="${pageContext.request.contextPath}/scores-update">
                <div class="row">
                    <div class="col-xl-3 col-lg-6 col-12 form-group hidden">
                        <input type="text" name="sc_id" value="${requestScope.single_score.id}"
                               placeholder="Class Name"
                               class="form-control" required>
                    </div>
                    <div class="col-xl-3 col-lg-6 col-12 form-group">
                        <label>Subject Name *</label>
                        <input type="text" name="sc_name" disabled value="${requestScope.single_score.subject_name}"
                               placeholder="Class Name" class="form-control" required>
                    </div>
                    <div class="col-xl-3 col-lg-6 col-12 form-group">
                        <label>Student *</label>
                        <input type="text" name="sc_name" disabled value="${requestScope.single_score.student_name}"
                               placeholder="Class Name" class="form-control" required>
                    </div>
                    <div class="col-xl-3 col-lg-6 col-12 form-group">
                        <label>Score *</label>
                        <input type="text" name="sc_score" pattern="^10|((\d)(\.(\d)|\.(\d\d)))$" value="${requestScope.single_score.score}"
                               placeholder="Class Name" class="form-control" required>
                    </div>
                    <div class="col-xl-3 col-lg-6 col-12 form-group">
                        <label>State *</label>
                        <select name="sc_state" class="select2 form-control">
                            ${requestScope.single_score.local_state == 1 ? "<option value='1' selected='selected'>Active</option><option value='0'>Locked</option>" : "<option value='0' selected='selected'>Locked</option><option value='1'>Active</option>"}
                        </select>
                    </div>
                    <div class="col-12 form-group mg-t  -8">
                        <button type="submit" class="btn-fill-lg btn-gradient-yellow btn-hover-bluedark">Save</button>
                        <button type="reset" class="btn-fill-lg bg-blue-dark btn-hover-yellow">Reset</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>