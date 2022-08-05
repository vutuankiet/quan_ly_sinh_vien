package com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.constrollers;

import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.DAO.AuthDAO;
import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.DAO.ClassesDAO;
import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.DAO.StudentDAO;
import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.DAO.SubjectDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

@WebServlet(urlPatterns = {"/dashboard", "/logout"})
public class DashBoardServlet extends BaseController {
    protected StudentDAO model = new StudentDAO();
    protected ClassesDAO modelClass = new ClassesDAO();
    protected SubjectDAO modelSubject = new SubjectDAO();

    public final String layout = "/WEB-INF/layout/layout.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            boolean isAuth = this.checkAuthenticate(request, response, String.valueOf(request.getRequestURL()));
            if (isAuth) {
                String action_path = this.GetAction(request, response);
                switch (action_path) {
                    case "/dashboard":
                        this.GetDashBoard(request, response);
                        break;
                    case "/logout":
                        this.Logout(request, response);
                        break;

                }
            } else {
                response.sendRedirect(this.getBaseUrl(request) + "/login");
            }
        } catch (Exception ex) {
            this.responseErr(ex, request, response, true);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            boolean isAuth = this.checkAuthenticate(request, response, String.valueOf(request.getRequestURL()));
            if (isAuth) {
                String action_path = this.GetAction(request, response);
                request.setCharacterEncoding("UTF-8");
                switch (action_path) {
                    case "/logout":
                        this.Logout(request, response);
                        break;
                }
            } else {
                response.sendRedirect(this.getBaseUrl(request) + "/login");
            }
        } catch (Exception ex) {
            this.responseErr(ex, request, response, true);
        }

    }

    public void GetDashBoard(HttpServletRequest req, HttpServletResponse res) throws IOException, SQLException {
        try {
            this.setContentHtml(res);
            int count = 0;
            req.setAttribute("count_student", model.GetCountOfStudent(count));
            req.setAttribute("count_class", modelClass.GetCountOfClass(count));
            req.setAttribute("count_subject", modelSubject.GetCountOfSubject(count));
            this.setResponseView("DashBoard", "/WEB-INF/dashboard/index.jsp", this.layout, req, res);
        } catch (Exception e) {
            this.responseErr(e, req, res, true);
        }
    }

    public void Logout(HttpServletRequest req, HttpServletResponse res) throws IOException, SQLException {
        try {
            this.setContentHtml(res);
            Cookie[] cks = req.getCookies();
            for (int i = 0; i < cks.length; i++) {

                if (Objects.equals(cks[i].getName(), "user_claim") || Objects.equals(cks[i].getName(), "user_email") || Objects.equals(cks[i].getName(), "user_password")) {
                    cks[i].setMaxAge(0);
                }
            }
            this.setResponseView("Login", "", "/WEB-INF/auth/login.jsp", req, res);
        } catch (Exception ex) {
            this.responseErr(ex, req, res, true);
        }
    }
}
