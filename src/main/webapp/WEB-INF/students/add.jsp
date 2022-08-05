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
    <h3>Student</h3>
    <ul>
        <li>
            <a href="${pageContext.request.contextPath}/dashboard">Home</a>
        </li>
        <li>Student Admit Form</li>
    </ul>
</div>
<!-- Breadcubs Area End Here -->
<!-- Admit Form Area Start Here -->
<div class="card height-auto">
    <div class="card-body">
        <div class="heading-layout1">
            <div class="item-title">
                <h3>Add New Student</h3>
            </div>
        </div>
        <form class="new-added-form" method="post" action="${pageContext.request.contextPath}/students-add?page=1"
              enctype="multipart/form-data">
            <div class="row">
                <div class="col-xl-3 col-lg-6 col-12 form-group">
                    <label>Student Name *</label>
                    <input type="text" name="st_name" placeholder="Student Name" minlength="10" maxlength="25" class="form-control" required pattern="^[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\s\W|_]+$">
                </div>


                <div class="col-xl-3 col-lg-6 col-12 form-group">
                    <label>Card</label>
                    <input name="st_card" id="st_card" type="text" placeholder="001101020102" pattern="([0-9]{12})\b" class="form-control" required>
                    <span class="text-danger" style="font-size: 12px;" id="st_card_valid"></span>
                </div>
                <div class="col-xl-3 col-lg-6 col-12 form-group">
                    <label>Address</label>
                    <input name="st_address" type="text" placeholder="Address" class="form-control" required>
                </div>

                <div class="col-xl-3 col-lg-6 col-12 form-group">
                    <label>E-Mail</label>
                    <input name="st_email" type="email" id="txt_email" placeholder="exaple@gmail.com" class="form-control" required>
                    <span id="txt_email_valid" class="text-danger" style="font-size: 12px;"></span>
                </div>

                <div class="col-xl-3 col-lg-6 col-12 form-group">
                    <label>Gender *</label>
                    <select class="select2 form-control" name="st_gender">
                        <option value="1">Male</option>
                        <option value="0">Female</option>
                    </select>
                </div>
                <div class="col-xl-3 col-lg-6 col-12 form-group">
                    <label>Class *</label>
                    <select name="_class_id" class="select2 form-control">
                        <c:forEach var="Data" items="${requestScope.DataTable}">
                            <option value="${Data.id}">${Data.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-xl-3 col-lg-6 col-12 form-group">
                    <label>State *</label>
                    <select name="st_state" class="select2 form-control">
                        <option value="1">Active</option>
                        <option value="0">Locked</option>
                    </select>
                </div>
                <div class="col-xl-3 col-lg-6 col-12 form-group">
                    <label>Phone</label>
                    <input name="st_phone" type="tel" placeholder="0336704581" pattern="(84|0[3|5|7|8|9])+([0-9]{8})\b" class="form-control" required>
                </div>
                <div class="col-xl-3 col-lg-6 col-12 form-group">
                    <label>Date Of Birth *</label>
                    <input name="birthday" type="date" placeholder="dd/mm/yyyy" id="datefield" class="form-control"
                           data-position='bottom right' required>
                </div>

                <div class="col-12 col-lg-12 my-5">
                    <div class="row">
                        <div class="col-12 col-lg-6 px-3" id="preview-image-col">
                            <h5 class="my-3">Preview Upload</h5>

                        </div>
                    </div>
                </div>

                <div class="col-lg-12 col-12 form-group mg-t-30">
                    <label class="text-dark-medium">Upload Student Photo</label>
                    <input name="st_avatar" type="file" accept="image/*" id="upload-image" class="d-flex justify-content-start align-items-center py-3 px-2 bg-light w-100 rounded" required>
                </div>
                <div class="col-12 form-group mg-t  -8">
                    <button id="create" type="submit" class="btn-fill-lg btn-gradient-yellow btn-hover-bluedark">Save</button>
                    <button type="reset" class="btn-fill-lg bg-blue-dark btn-hover-yellow">Reset</button>
                </div>
            </div>
        </form>
    </div>
</div>

<script>
    let txt_student_card = document.getElementById("st_card")
    let txt_email = document.getElementById("txt_email")

    txt_email.oninput = (event) => {
        let value = event.target.value

        if (value.trim() !== "") {
            $.ajax("${pageContext.request.contextPath}" + "/students-filter-email", {
                method: "POST",
                data: {st_email: value},
                success: (res) => {
                    if (JSON.parse(res) === true) {
                        document.getElementById("txt_email_valid").innerHTML = "Card Has Exit!";
                        document.getElementById("create").setAttribute("disabled", "");
                    } else {
                        document.getElementById("txt_email_valid").innerHTML = "";
                        document.getElementById('create').disabled = false;
                    }
                }
            })
        } else {
            document.getElementById("txt_email_valid").innerHTML = "";
        }
    }
    txt_student_card.oninput = (event) => {
        let value = event.target.value

        if (value.trim() !== "") {
            $.ajax("${pageContext.request.contextPath}" + "/students-filter-card", {
                method: "POST",
                data: {st_card: value},
                success: (res) => {
                    console.log(JSON.parse(res))
                    if (JSON.parse(res) === true) {
                        document.getElementById("st_card_valid").innerHTML = "Card Has Exit!";
                        document.getElementById("create").setAttribute("disabled", "");
                    } else {
                        document.getElementById("st_card_valid").innerHTML = "";
                        document.getElementById('create').disabled = false;
                    }
                }
            })
        } else {
            document.getElementById("st_card_valid").innerHTML = "";
        }
    }

    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1; //January is 0 so need to add 1 to make it 1!
    var yyyy = today.getFullYear();
    if(dd<10){
        dd='0'+dd
    }
    if(mm<10){
        mm='0'+mm
    }
    today = yyyy+'-'+mm+'-'+dd;
    document.getElementById("datefield").setAttribute("max", today);


    let uploadFile = document.getElementById("upload-image")
    let previewCol = document.getElementById("preview-image-col")
    uploadFile.onchange = (event) => {
        let url = URL.createObjectURL(event.target.files[0])
        let imageHtml = "<img id='img-preview' onclick='triggerImage()'  src='" + url + "' alt='preview upload!' class='img-fluid' width='400' height='400'/>"
        if (document.getElementById("img-preview")) {
            document.getElementById("img-preview").remove()
        }
        previewCol.innerHTML += imageHtml
    }

    function triggerImage() {
        $("#upload-image").trigger('click')
    }
</script>