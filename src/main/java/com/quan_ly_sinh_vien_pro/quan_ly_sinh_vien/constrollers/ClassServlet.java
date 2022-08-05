package com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.constrollers;

import com.google.gson.Gson;
import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.helpers.Pager;
import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.DAO.ClassesDAO;
import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.EF.Classes;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/classes", "/single-classes", "/classes-add", "/classes-update", "/classes-remove", "/classes-filter-name", "/classes-search"})

public class ClassServlet extends BaseController {
    protected ClassesDAO model = new ClassesDAO();
    Pager pager = new Pager("classes");
    public final String layout = "/WEB-INF/layout/layout.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            boolean isAuth = this.checkAuthenticate(request, response, String.valueOf(request.getRequestURL()));
            if (isAuth) {
                String action_path = this.GetAction(request, response);
                switch (action_path) {
                    case "/classes":
                        this.GetListClass(request, response);
                        break;
                    case "/classes-add":
                        this.AddClass(request, response);
                        break;
                    case "/classes-update":
                        this.UpdateClass(request, response);
                        break;
                    case "/classes-remove":
                        this.RemoveClass(request, response);
                        break;
                    case "/classes-search":
                        this.SearchClass(request, response);
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
                    case "/classes-add":
                        this.AddClassPost(request, response);
                        break;
                    case "/classes-update":
                        this.UpdateClassPost(request, response);
                        break;
                    case "/classes-filter-name":
                        this.CheckNameExit(request, response);
                        break;
                    case "/classes-search":
                        this.SearchClass(request, response);
                        break;
                }
            } else {
                response.sendRedirect(this.getBaseUrl(request) + "/login");
            }
        } catch (Exception ex) {
            this.responseErr(ex, request, response, true);
        }
    }

    public void CheckNameExit(HttpServletRequest req, HttpServletResponse response) throws IOException {
        try {
            String name = req.getParameter("cl_name");
            Boolean result = model.HasExit(name);
            response.getWriter().write(new Gson().toJson(result));
        } catch (Exception e) {
            this.responseErr(e, req, response, true);
        }
    }

    public Boolean AddClass(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        try {
            this.setContentHtml(res);
            this.setResponseView("Add Classes", "/WEB-INF/classes/add.jsp", this.layout, req, res);
            return false;
        } catch (Exception e) {
            this.responseErr(e, req, res, true);
            return false;
        }
    }

    public void AddClassPost(HttpServletRequest req, HttpServletResponse res) throws IOException, SQLException {
        try {
            res.setCharacterEncoding("UTF-8");
            Classes cl = new Classes();
            cl.setName(req.getParameter("cl_name").trim());
            cl.setMax(Integer.parseInt(req.getParameter("cl_max").trim()));
            cl.setLocal_state(Integer.parseInt(req.getParameter("cl_state").trim()));
            Boolean result = model.Add(cl);
            HttpSession session = req.getSession();
            session.setAttribute("SUCCESS_MSG","Add Success!");
//            req.setAttribute("req_msg", result ? "Add Success!" : "Add Failed!");
            res.sendRedirect(this.getBaseUrl(req) + "/classes?page=1");
        } catch (Exception e) {
            this.responseErr(e, req, res, true);
        }
    }

    public void GetListClass(HttpServletRequest req, HttpServletResponse res) throws IOException, SQLException {
        try {
            this.setContentHtml(res);
            int page = Integer.parseInt(req.getParameter("page"));
            int limit = 8;
            req.setAttribute("current_page", page);
            req.setAttribute("total_page", pager.get_total_page(limit));
            req.setAttribute("table_data", model.GetAllByPage(page, limit));
            this.setResponseView("List Classes", "/WEB-INF/classes/index.jsp", this.layout, req, res);
        } catch (Exception e) {
            this.responseErr(e, req, res, true);
        }
    }

    public void SearchClass(HttpServletRequest req, HttpServletResponse res) throws IOException, SQLException {
        try {
            this.setContentHtml(res);
            String nameClass = "%" + req.getParameter("cl_name") + "%";
            req.setAttribute("class_key", req.getParameter("cl_name"));
            int limit = 8;
            int page = Integer.parseInt(req.getParameter("page"));
            req.setAttribute("current_page", page);
            pager.setCount(model.GetCountOfSearch(nameClass));
            req.setAttribute("total_page", pager.get_total_page(limit));
            req.setAttribute("table_data", model.SearchByPage(nameClass, page, limit));
            this.setResponseView("List Classes", "/WEB-INF/classes/search.jsp", this.layout, req, res);
        } catch (Exception e) {
            res.sendRedirect(this.getBaseUrl(req) + "/classes?page=1");
        }
    }

    public void UpdateClass(HttpServletRequest req, HttpServletResponse res) throws IOException, SQLException {
        try {
            String id = req.getParameter("id");
            req.setAttribute("single_class", model.GetById(id));
            this.setContentHtml(res);
            this.setResponseView("Edit Class", "/WEB-INF/classes/edit.jsp", this.layout, req, res);
        } catch (Exception ex) {
            this.responseErr(ex, req, res, true);
        }
    }

    public void UpdateClassPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            this.setContentHtml(res);

            String cl_id = req.getParameter("cl_id");
            String cl_name = req.getParameter("cl_name");
            int cl_max = Integer.parseInt(req.getParameter("cl_max"));
            int cl_state = Integer.parseInt(req.getParameter("cl_state"));
            Classes cl = new Classes(cl_id, cl_name, cl_state, cl_state, cl_max);

            Boolean result = model.Update(cl);
            HttpSession session = req.getSession();
            session.setAttribute("UPDATE_SUCCESS_MSG","Update Success!");
//            req.setAttribute("req_msg", result ? "Update Success!" : "Update Failed!");
            res.sendRedirect(this.getBaseUrl(req) + "/classes?page=1");

        } catch (Exception ex) {
            this.responseErr(ex, req, res, true);
        }
    }

    public void RemoveClass(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            String id = req.getParameter("id");
            this.setContentHtml(res);
            model.Remove(id);
            HttpSession session = req.getSession();
            session.setAttribute("DELETE_SUCCESS_MSG","Delete Success!");
            res.sendRedirect(this.getBaseUrl(req) + "/classes?page=1");
        } catch (Exception ex) {
            this.responseErr(ex, req, res, true);
        }
    }
}
