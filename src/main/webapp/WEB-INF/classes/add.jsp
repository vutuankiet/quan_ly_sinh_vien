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
    <h3>Class</h3>
    <ul>
        <li>
            <a href="${pageContext.request.contextPath}/dashboard">Home</a>
        </li>
        <li>Class Admit Form</li>
    </ul>
</div>
<!-- Breadcubs Area End Here -->
<!-- Admit Form Area Start Here -->
<div class="card height-auto">
    <div class="card-body">
        <div class="heading-layout1">
            <div class="item-title">
                <h3>Add New Class</h3>
            </div>
        </div>
        <form class="new-added-form" method="post" action="${pageContext.request.contextPath}/classes-add">
            <div class="row">
                <div class="col-xl-3 col-lg-6 col-12 form-group">
                    <label>Class Name *</label>
                    <input type="text" name="cl_name" id="cl_name" minlength="3" maxlength="25" placeholder="Class Name" class="form-control"
                           required pattern="^[A-Za-z0-9][A-Za-z0-9 \.\-_]*[A-Za-z0-9]$">
                    <span class="text-danger" style="font-size: 12px;" id="cl_name_valid"></span>
                </div>
                <div class="col-xl-3 col-lg-6 col-12 form-group">
                    <label>Class Students Max *</label>
                    <input type="number" min="1" max="50" name="cl_max" placeholder="Class Student Max"
                           class="form-control"
                           required>
                </div>

                <div class="col-xl-3 col-lg-6 col-12 form-group">
                    <label>State *</label>
                    <select name="cl_state" class="select2 form-control">
                        <option value="1">Active</option>
                        <option value="0">Locked</option>
                    </select>
                </div>
                <div class="col-12 form-group mg-t  -8">
                    <button id="create" type="submit" class="btn-fill-lg btn-gradient-yellow btn-hover-bluedark">Save</button>
                    <button type="reset" class="btn-fill-lg bg-blue-dark btn-hover-yellow">Reset</button>
                </div>
            </div>
        </form>
    </div>
</div>
<div style="height: 30vh;"></div>
<script>
    let txt_class_name = document.getElementById("cl_name")

    txt_class_name.oninput = (event) => {
        let value = event.target.value

        if (value.trim() !== "") {
            $.ajax("${pageContext.request.contextPath}" + "/classes-filter-name", {
                method: "POST",
                data: {cl_name: value},
                success: (res) => {
                    console.log(typeof JSON.parse(res))
                    if (JSON.parse(res) === true) {
                        document.getElementById("cl_name_valid").innerHTML = "Name Has Exit!";
                        document.getElementById("create").setAttribute("disabled", "");
                    } else {
                        document.getElementById("cl_name_valid").innerHTML = "";
                        document.getElementById('create').disabled = false;
                    }
                }
            })
        } else {
            document.getElementById("cl_name_valid").innerHTML = "";
        }
    }
</script>