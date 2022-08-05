<%--
  Created by IntelliJ IDEA.
  User: vutua
  Date: 7/16/2022
  Time: 12:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="breadcrumbs-area">
    <h3>Admin Dashboard</h3>
    <ul>
        <li>
            <a href="${pageContext.request.contextPath}/dashboard">Home</a>
        </li>
        <li>Admin</li>
    </ul>
</div>
<!-- Breadcubs Area End Here -->
<!-- Dashboard summery Start Here -->
<div class="row gutters-20">
    <div class="col-xl-4 col-sm-6 col-12">
        <div class="dashboard-summery-one mg-b-20">
            <div class="row align-items-center">
                <div class="col-6">
                    <div class="item-icon bg-light-green ">
                        <i class="flaticon-classmates text-green"></i>
                    </div>
                </div>
                <div class="col-6">
                    <div class="item-content">
                        <div class="item-title">Students</div>
                        <%
                            int count_student = Integer.parseInt(request.getAttribute("count_student").toString());
                            int count_class = Integer.parseInt(request.getAttribute("count_class").toString());
                            int count_subject = Integer.parseInt(request.getAttribute("count_subject").toString());
                        %>
                        <div class="item-number"><span class="counter" data-num="<%=count_student%>"><%=count_student%></span></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-xl-4 col-sm-6 col-12">
        <div class="dashboard-summery-one mg-b-20">
            <div class="row align-items-center">
                <div class="col-6">
                    <div class="item-icon bg-light-blue">
                        <i class="flaticon-multiple-users-silhouette text-blue"></i>
                    </div>
                </div>
                <div class="col-6">
                    <div class="item-content">
                        <div class="item-title">Classes</div>
                        <div class="item-number"><span class="counter" data-num="<%=count_class%>"><%=count_class%></span></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-xl-4 col-sm-6 col-12">
        <div class="dashboard-summery-one mg-b-20">
            <div class="row align-items-center">
                <div class="col-6">
                    <div class="item-icon bg-light-yellow">
                        <i class="flaticon-couple text-orange"></i>
                    </div>
                </div>
                <div class="col-6">
                    <div class="item-content">
                        <div class="item-title">Subjects</div>
                        <div class="item-number"><span class="counter" data-num="<%=count_subject%>"><%=count_subject%></span></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="row gutters-20">
    <div class="col-12 col-xl-6 col-4-xxxl">
        <div class="card dashboard-card-four pd-b-20">
            <div class="card-body">
                <div class="heading-layout1">
                    <div class="item-title">
                        <h3>Event Calender</h3>
                    </div>
                </div>
                <div class="calender-wrap">
                    <div id="fc-calender" class="fc-calender"></div>
                </div>
            </div>
        </div>
    </div>
</div>
