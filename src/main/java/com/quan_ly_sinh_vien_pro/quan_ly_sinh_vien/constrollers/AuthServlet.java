package com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.constrollers;

import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.DAO.AuthDAO;
import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.DAO.StudentDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Objects;

@WebServlet(urlPatterns = {"/login"})

public class AuthServlet extends BaseController {
    String layout = "";
    AuthDAO model = new AuthDAO();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            this.setContentHtml(response);
            boolean isRemember = false;
            String us_email = "";
            String us_password = "";
            Cookie[] cks = request.getCookies();
            if (cks != null) {
                for (int i = 0; i < cks.length; i++) {
                    if (Objects.equals(cks[i].getName(), "user_claim")) {
                        isRemember = true;
                    }
                    if (Objects.equals(cks[i].getName(), "user_email")) {
                        us_email = cks[i].getValue();
                    }
                    if (Objects.equals(cks[i].getName(), "user_password")) {
                        us_password = cks[i].getValue();
                    }
                }
                if (isRemember && !Objects.equals(us_email, "")) {
                    boolean result = authDAO.Login(us_email, us_password);
                    if (result) {
                        response.sendRedirect(this.getBaseUrl(request) + "/dashboard");
                    }
                }
            }
            this.setResponseView("Login", "", "/WEB-INF/auth/login.jsp", request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String us_email = request.getParameter("txt_email");
            String us_password = request.getParameter("txt_password");
            String us_remember = request.getParameter("txt_remember");
            int remember = us_remember == null ? 0 : 1;
            Boolean result = model.Login(us_email, us_password);
            if (result) {
                if (remember == 1) {
                    Cookie ck_claim = new Cookie("user_claim", "true");
                    Cookie ck_email = new Cookie("user_email", us_email);
                    Cookie ck_password = new Cookie("user_password", us_password);
                    response.addCookie(ck_claim);
                    response.addCookie(ck_email);
                    response.addCookie(ck_password);
                    response.sendRedirect(this.getBaseUrl(request) + "/dashboard");
                } else {
                    Cookie ck_claim = new Cookie("user_claim", "true");
                    Cookie ck_email = new Cookie("user_email", us_email);
                    Cookie ck_password = new Cookie("user_password", us_password);
                    response.addCookie(ck_claim);
                    response.addCookie(ck_email);
                    response.addCookie(ck_password);
                    response.sendRedirect(this.getBaseUrl(request) + "/dashboard");
                }
            } else {
                this.setContentHtml(response);
                request.setAttribute("err_msg", "login failed,Email or password incorrect!");
                this.setResponseView("Login", "", "/WEB-INF/auth/login.jsp", request, response);
            }
        } catch (Exception ex) {
            this.responseErr(ex, request, response, true);
        }
    }
}
