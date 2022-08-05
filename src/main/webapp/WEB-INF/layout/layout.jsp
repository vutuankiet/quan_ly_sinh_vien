<%--
  Created by IntelliJ IDEA.
  User: vutua
  Date: 7/16/2022
  Time: 11:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html class="no-js" lang="">
<head>
    <title>${web_title}</title>
    <%@include file="assets.jsp" %>
    <%@include file="bootstrap.jsp" %>

</head>
<body>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="preloader"></div>
<!-- Preloader End Here -->
<div id="wrapper" class="wrapper bg-ash">
    <%@include file="top_navbar.jsp"%>

    <div class="dashboard-page-one">
        <%@include file="sidebar.jsp"%>
        <div class="dashboard-content-one">
            <%--        content--%>
            <div>
            <jsp:include page="${web_content}"></jsp:include>
            </div>
            <%@include file="footer.jsp"%>
        </div>
    </div>
</div>



<%@include file="scripts.jsp" %>
</body>
</html>
