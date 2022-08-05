package com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.constrollers;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "LogoutServlet", value = "/LogoutServlet")
public class LogoutServlet extends BaseController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try{
            this.setContentHtml(res);
            HttpSession session = req.getSession();
            session.invalidate();
            this.setResponseView("Login", "", "/WEB-INF/auth/login.jsp", req, res);
        }catch (Exception ex){
            this.responseErr(ex, req, res, true);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try{
            this.setContentHtml(res);
            HttpSession session = req.getSession();
            session.invalidate();
            this.setResponseView("Login", "", "/WEB-INF/auth/login.jsp", req, res);
        }catch (Exception ex){
            this.responseErr(ex, req, res, true);
        }
    }
}
