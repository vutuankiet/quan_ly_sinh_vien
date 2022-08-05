package com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.constrollers;

import com.google.gson.Gson;
import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.helpers.Pager;
import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.DAO.ClassesDAO;
import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.DAO.SubjectDAO;
import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.EF.Classes;
import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.EF.Subject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/subjects", "/single-subjects", "/subjects-add", "/subjects-update", "/subjects-remove", "/subjects-filter-name", "/subjects-search"})

public class SubjectServlet extends BaseController {

    protected SubjectDAO model = new SubjectDAO();
    Pager pager = new Pager("subjects");
    public final String layout = "/WEB-INF/layout/layout.jsp";


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            boolean isAuth = this.checkAuthenticate(request, response, String.valueOf(request.getRequestURL()));
            if (isAuth) {
                String action_path = this.GetAction(request, response);
                switch (action_path) {
                    case "/subjects":
                        this.GetListSubject(request, response);
                        break;
                    case "/subjects-add":
                        this.AddSubject(request, response);
                        break;
                    case "/subjects-update":
                        this.UpdateSubject(request, response);
                        break;
                    case "/subjects-search":
                        this.SearchSubject(request, response);
                        break;
                    case "/subjects-remove":
                        this.RemoveSubject(request, response);
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
            request.setCharacterEncoding("UTF-8");
            boolean isAuth = this.checkAuthenticate(request, response, String.valueOf(request.getRequestURL()));
            if (isAuth) {
                String action_path = this.GetAction(request, response);
                switch (action_path) {
                    case "/subjects-add":
                        this.AddSubjectPost(request, response);
                        break;
                    case "/subjects-update":
                        this.UpdateSubjectPost(request, response);
                        break;
                    case "/subjects-search":
                        this.SearchSubject(request, response);
                        break;
                    case "/subjects-filter-name":
                        this.CheckNameExit(request, response);
                        break;

                }
            } else {
                response.sendRedirect(this.getBaseUrl(request) + "/login");
            }
        } catch (Exception ex) {
            this.responseErr(ex, request, response, true);
        }
    }

    public void GetListSubject(HttpServletRequest req, HttpServletResponse res) throws IOException, SQLException {
        try {
            this.setContentHtml(res);
            int page = Integer.parseInt(req.getParameter("page"));
            int limit = 8;
            req.setAttribute("current_page", page);
            req.setAttribute("total_page", pager.get_total_page(limit));
            req.setAttribute("table_data", model.GetAllByPage(page, limit));
            this.setResponseView("List Subjects", "/WEB-INF/subjects/index.jsp", this.layout, req, res);
        } catch (Exception e) {
            this.responseErr(e, req, res, true);
        }
    }

    public void SearchSubject(HttpServletRequest req, HttpServletResponse res) throws IOException, SQLException {
        try {
            this.setContentHtml(res);
            String nameSubject = "%" + req.getParameter("sj_name") + "%";
            req.setAttribute("subject_key", req.getParameter("sj_name"));
            int limit = 8;
            int page = Integer.parseInt(req.getParameter("page"));
            req.setAttribute("current_page", page);
            pager.setCount(model.GetCountOfSearch(nameSubject));
            req.setAttribute("total_page", pager.get_total_page(limit));
            req.setAttribute("table_data", model.SearchByPage(nameSubject, page, limit));
            this.setResponseView("List Subjects", "/WEB-INF/subjects/search.jsp", this.layout, req, res);
        } catch (Exception e) {
            res.sendRedirect(this.getBaseUrl(req) + "/subjects?page=1");
        }
    }

    public Boolean AddSubject(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        try {
            this.setContentHtml(res);
            this.setResponseView("Add Classes", "/WEB-INF/subjects/add.jsp", this.layout, req, res);
            return false;
        } catch (Exception e) {
            this.responseErr(e, req, res, true);
            return false;
        }
    }

    public void AddSubjectPost(HttpServletRequest req, HttpServletResponse res) throws IOException, SQLException {
        try {
            res.setCharacterEncoding("UTF-8");
            Subject sj = new Subject();
            sj.setName(req.getParameter("sj_name").trim());
            sj.setLocal_state(Integer.parseInt(req.getParameter("sj_state").trim()));
            Boolean result = model.Add(sj);
            HttpSession session = req.getSession();
            session.setAttribute("SUCCESS_MSG", "Add Success!");
//            req.setAttribute("req_msg", result ? "Add Success!" : "Add Failed!");
            res.sendRedirect(this.getBaseUrl(req) + "/subjects?page=1");
        } catch (Exception e) {
            this.responseErr(e, req, res, true);
        }
    }

    public void UpdateSubject(HttpServletRequest req, HttpServletResponse res) throws IOException, SQLException {
        try {
            String id = req.getParameter("id");
            req.setAttribute("single_subject", model.GetById(id));
            this.setContentHtml(res);
            this.setResponseView("Edit subject", "/WEB-INF/subjects/edit.jsp", this.layout, req, res);
        } catch (Exception ex) {
            this.responseErr(ex, req, res, true);
        }
    }

    public void UpdateSubjectPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            this.setContentHtml(res);

            String sj_id = req.getParameter("sj_id");
            String sj_name = req.getParameter("sj_name");
            int sj_state = Integer.parseInt(req.getParameter("sj_state"));
            Subject sj = new Subject(sj_id, sj_name, sj_state, sj_state);

            Boolean result = model.Update(sj);
            HttpSession session = req.getSession();
            session.setAttribute("UPDATE_SUCCESS_MSG","Update Success!");
//            req.setAttribute("req_msg", result ? "Update Success!" : "Update Failed!");
            res.sendRedirect(this.getBaseUrl(req) + "/subjects?page=1");

        } catch (Exception ex) {
            this.responseErr(ex, req, res, true);
        }
    }

    public void RemoveSubject(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            String id = req.getParameter("id");
            this.setContentHtml(res);
            model.Remove(id);
            HttpSession session = req.getSession();
            session.setAttribute("DELETE_SUCCESS_MSG","Delete Success!");
            res.sendRedirect(this.getBaseUrl(req) + "/subjects?page=1");
        } catch (Exception ex) {
            this.responseErr(ex, req, res, true);
        }
    }

    public void CheckNameExit(HttpServletRequest req, HttpServletResponse response) throws IOException {
        try {
            String name = req.getParameter("sj_name");
            Boolean result = model.HasExit(name);
            response.getWriter().write(new Gson().toJson(result));
        } catch (Exception e) {
            this.responseErr(e, req, response, true);
        }
    }
}
