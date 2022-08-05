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
        <h3>Class</h3>
        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/dashboard">Home</a>
            </li>
            <li>Edit Class</li>
        </ul>
    </div>
    <!-- Breadcubs Area End Here -->
    <!-- Admit Form Area Start Here -->
    <div class="card height-auto">
        <div class="card-body">
            <div class="heading-layout1">
                <div class="item-title">
                    <h3>Edit Class</h3>
                </div>
            </div>
            <form class="new-added-form" method="post" action="${pageContext.request.contextPath}/classes-update">
                <div class="row">
                    <div class="col-xl-3 col-lg-6 col-12 form-group hidden">
                        <input type="text" name="cl_id" value="${requestScope.single_class.id}" placeholder="Class Name"
                               class="form-control" required>
                    </div>
                    <div class="col-xl-3 col-lg-6 col-12 form-group">
                        <label>Class Name *</label>
                        <input type="text" name="cl_name" value="${requestScope.single_class.name}"
                               placeholder="Class Name" class="form-control" required pattern="^[A-Za-z0-9][A-Za-z0-9 \.\-_]*[A-Za-z0-9]$">
                    </div>
                    <div class="col-xl-3 col-lg-6 col-12 form-group">
                        <label>Class Students Max *</label>
                        <input type="number" name="cl_max" value="${requestScope.single_class.max}"
                               placeholder="Class Student Max" class="form-control"
                               required>
                    </div>

                    <div class="col-xl-3 col-lg-6 col-12 form-group">
                        <label>State *</label>
                        <select name="cl_state" class="select2 form-control">
                            ${requestScope.single_class.local_state == 1 ? "<option value='1' selected='selected'>Active</option><option value='0'>Locked</option>" : "<option value='0' selected='selected'>Locked</option><option value='1'>Active</option>"}
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
<div style="height: 30vh;"></div>