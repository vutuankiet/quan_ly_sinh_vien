<%@ page import="com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.EF.Classes" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.EF.Student" %>
<%@ page import="java.util.Objects" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vutua
  Date: 7/16/2022
  Time: 11:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="dashboard-content-one">
    <!-- Breadcubs Area Start Here -->
    <div class="breadcrumbs-area">
        <h3>Student</h3>
        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/dashboard">Home</a>
            </li>
            <li>Edit Student</li>
        </ul>
    </div>
    <!-- Breadcubs Area End Here -->
    <!-- Admit Form Area Start Here -->
    <div class="card height-auto">
        <div class="card-body">
            <div class="heading-layout1">
                <div class="item-title">
                    <h3>Edit Student</h3>
                </div>
            </div>
            <form class="new-added-form" method="post" action="${pageContext.request.contextPath}/students-update?page=1"
                  enctype="multipart/form-data">
                <div class="row">
                    <div class="col-xl-3 col-lg-6 col-12 form-group hidden">
                        <label>Student ID *</label>
                        <input type="text" name="st_id" placeholder="Student ID"
                               value="${requestScope.single_student.id}" class="form-control hidden" required>
                    </div>
                    <div class="col-xl-3 col-lg-6 col-12 form-group">
                        <label>Student Name *</label>
                        <input type="text" name="st_name" placeholder="Student Name"
                               value="${requestScope.single_student.name}" class="form-control" required pattern="^[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\s\W|_]+$">
                    </div>


                    <div class="col-xl-3 col-lg-6 col-12 form-group">
                        <label>Card</label>
                        <input name="st_card" type="text" placeholder="001101020102" pattern="([0-9]{12})\b" value="${requestScope.single_student.card}"
                               class="form-control" required>
                    </div>
                    <div class="col-xl-3 col-lg-6 col-12 form-group">
                        <label>Address</label>
                        <input name="st_address" type="text" placeholder="Address"
                               value="${requestScope.single_student.address}" class="form-control" required>
                    </div>

                    <div class="col-xl-3 col-lg-6 col-12 form-group">
                        <label>E-Mail</label>
                        <input name="st_email" type="email" placeholder="exaple@gmail.com"
                               value="${requestScope.single_student.email}" class="form-control" required>
                    </div>
                    <div class="col-xl-3 col-lg-6 col-12 form-group">
                        <label>Gender *</label>
                        <select class="select2 form-control" name="st_gender">
                            ${requestScope.single_student.gender == 1 ? "<option value='1' selected='selected'>Male</option><option value='0'>Female</option>" : "<option value='0' selected='selected'>Female</option><option value='1'>Male</option>"}
                        </select>
                    </div>
                    <div class="col-xl-3 col-lg-6 col-12 form-group">
                        <label>Class *</label>
                        <select name="_class_id" class="select2 form-control">
                            <% Student single_student = (Student) request.getAttribute("single_student");%>
                            <% List<Classes> classesList = (LinkedList<Classes>) request.getAttribute("classes"); %>
                            <% for (Classes cl : classesList) { %>
                            <% if (Objects.equals(cl.getId(), single_student.getClass_id())) {%>
                            <option selected value="<%=cl.getId()%>"><%=cl.getName()%>
                            </option>
                            <%} else {%>
                            <option value="<%=cl.getId()%>"><%=cl.getName()%>
                            </option>
                            <% }%>
                            <% } %>
                        </select>
                    </div>
                    <div class="col-xl-3 col-lg-6 col-12 form-group">
                        <label>State *</label>
                        <select name="st_state" class="select2 form-control">
                            ${requestScope.single_student.local_state == 1 ? "<option value='1' selected='selected'>Active</option><option value='0'>Locked</option>" : "<option value='0' selected='selected'>Locked</option><option value='1'>Active</option>"}
                        </select>
                    </div>
                    <div class="col-xl-3 col-lg-6 col-12 form-group">
                        <label>Phone</label>
                        <input name="st_phone" type="text" placeholder="Phone"
                               value="${requestScope.single_student.phone}" class="form-control" required>
                    </div>
                    <div class="col-xl-3 col-lg-6 col-12 form-group">
                        <label>Date Of Birth *</label>
                        <input name="birthday" type="date" placeholder="dd/mm/yyyy"
                               value="${requestScope.single_student.birthday}" class="form-control"
                               data-position='bottom right' required>
                    </div>
                    <div class="col-12 col-lg-12 my-5">
                        <div class="row">
                            <div class="col-12 col-lg-6 px-3">
                                <h5 class="my-2">Current Avatar</h5>
                                <img src="${pageContext.request.contextPath}/Uploads/${requestScope.single_student.avatar}"
                                     alt="student avatar" class="img-fluid"/>
                            </div>
                            <div class="col-12 col-lg-6 px-3" id="preview-image-col">
                                <h5 class="my-2">Preview Upload</h5>

                            </div>
                        </div>
                        <div class="w-50">

                        </div>
                    </div>
                    <div class="col-lg-12 col-12 form-group mg-t-30">
                        <label class="text-dark-medium">Change Student Photo (if nothing change there , please make it
                            empty!)</label>
                        <input name="st_avatar" type="file" accept="image/*" id="upload-image"
                               class="d-flex justify-content-start align-items-center py-3 px-2 bg-light w-100 rounded">
                    </div>
                    <div class="col-lg-12 col-12 form-group mg-t-30 hidden">
                        <input name="st_avatar_default" type="text" value="${requestScope.single_student.avatar}"/>
                    </div>
                    <div class="col-12 form-group mg-t  -8">
                        <button type="submit" class="btn-fill-lg btn-gradient-yellow btn-hover-bluedark">Save</button>
                        <button type="reset" class="btn-fill-lg bg-blue-dark btn-hover-yellow">Reset</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <!-- Admit Form Area End Here -->
    <footer class="footer-wrap-layout1">
        <div class="copyright">© Copyrights <a href="#">akkhor</a> 2019. All rights reserved. Designed by <a
                href="#">PsdBosS</a></div>
    </footer>
</div>
<script>
    let uploadFile = document.getElementById("upload-image")
    let previewCol = document.getElementById("preview-image-col")
    uploadFile.onchange = (event) => {
        let url = URL.createObjectURL(event.target.files[0])
        let imageHtml = "<img id='img-preview' onclick='triggerImage()'  src='" + url + "' alt='preview upload!' class='img-fluid'/>"
        if (document.getElementById("img-preview")) {
            document.getElementById("img-preview").remove()
        }
        previewCol.innerHTML += imageHtml
    }

    function triggerImage() {
        $("#upload-image").trigger('click')
    }
</script>
<%
    String req_msg = (String) request.getAttribute("req_msg");
%>
<% if (req_msg != null) { %>
<script>alert('<%=req_msg%>')</script>
<%}%>
