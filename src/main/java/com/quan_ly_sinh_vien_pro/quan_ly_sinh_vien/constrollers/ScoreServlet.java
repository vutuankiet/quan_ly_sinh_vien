package com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.constrollers;

import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.helpers.Pager;
import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.helpers.UploadImage;
import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.DAO.*;
import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.EF.Score;
import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.EF.Student;
import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.EF.Subject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/scores", "/single-scores", "/scores-add", "/scores-update", "/scores-remove", "/scores-filter-name", "/scores-search", "/add-by-class"})

public class ScoreServlet extends BaseController {

    protected ScoreDAO model = new ScoreDAO();
    protected ClassesDAO modelClass = new ClassesDAO();
    Pager pager = new Pager("scores");
    public final String layout = "/WEB-INF/layout/layout.jsp";



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            boolean isAuth = this.checkAuthenticate(request, response, String.valueOf(request.getRequestURL()));
            if (isAuth) {
                String action_path = this.GetAction(request, response);
                switch (action_path) {
                    case "/scores":
                        this.GetListScore(request, response);
                        break;
                    case "/scores-add":
                        this.AddScore(request, response);
                        break;
                    case "/scores-update":
                        this.Update(request, response);
                        break;
                    case "/scores-remove":
                        this.Remove(request, response);
                        break;
                    case "/scores-search":
                        this.SearchScore(request, response);
                        break;
                    default:
                        this.responseRedirect(request, response);
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
                    case "/scores-add":
                        this.AddScorePost(request, response);
                        break;
                    case "/add-by-class":
                        this.AddScoreByClassPost(request, response);
                        break;
                    case "/scores-update":
                        this.UpdatePost(request, response);
                        break;
                    case "/scores-remove":
                        this.Remove(request, response);
                        break;
                    case "/scores-search":
                        this.SearchScore(request, response);
                        break;
                    default:
                        this.responseRedirect(request, response);
                        break;
                }
            } else {
                response.sendRedirect(this.getBaseUrl(request) + "/login");
            }

        } catch (Exception ex) {
            this.responseErr(ex, request, response, true);
        }
    }

    public void Remove(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            this.setContentHtml(res);
            String id = req.getParameter("id");
            model.Remove(id);
            HttpSession session = req.getSession();
            session.setAttribute("DELETE_SUCCESS_MSG","Delete Success!");
            res.sendRedirect(this.getBaseUrl(req) + "/scores?page=1");
        } catch (Exception ex) {
            this.responseErr(ex, req, res, true);
        }
    }

    public void UpdatePost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            this.setContentHtml(response);
            String id = request.getParameter("sc_id");
            String score = request.getParameter("sc_score");
            int state = Integer.parseInt(request.getParameter("sc_state"));
            Score score_obj = new Score();
            score_obj.setId(id);
            score_obj.setScore(score);
            score_obj.setLocal_state(state);
            model.Update(score_obj);
            HttpSession session = request.getSession();
            session.setAttribute("UPDATE_SUCCESS_MSG","Update Success!");
            response.sendRedirect(this.getBaseUrl(request) + "/scores?page=1");
        } catch (Exception ex) {
            this.responseErr(ex, request, response, true);
        }
    }

    public void Update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            this.setContentHtml(response);
            String id = request.getParameter("id");
            request.setAttribute("single_score", model.GetById(id));
            this.setResponseView("Update Score", "/WEB-INF/scores/edit.jsp", this.layout, request, response);
        } catch (Exception ex) {
            this.responseErr(ex, request, response, true);
        }
    }

    public void GetListScore(HttpServletRequest req, HttpServletResponse res) throws IOException, SQLException {
        try {
            this.setContentHtml(res);
            int limit = 8;
            int page = Integer.parseInt(req.getParameter("page"));
            req.setAttribute("current_page", page);
            req.setAttribute("total_page", pager.get_total_page(limit));
            req.setAttribute("table_data", model.GetAllByPage(page, limit));
            this.setResponseView("List Scores", "/WEB-INF/scores/index.jsp", this.layout, req, res);
        } catch (Exception e) {
            this.responseErr(e, req, res, true);
        }
    }

    public Boolean AddScore(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        try {
            String classId = req.getParameter("_class_id");
            req.setAttribute("class_key", req.getParameter("_class_id"));
            req.setAttribute("name_class", modelClass.GetById(classId));
            ClassesDAO classesDAO = new ClassesDAO();
            req.setAttribute("DataClass", classesDAO.GetAllClassAddByScore());
            SubjectDAO subjectDAO = new SubjectDAO();
            req.setAttribute("DataSubject", subjectDAO.GetAllSubjectAdd());
            StudentDAO studentDAO = new StudentDAO();
            req.setAttribute("DataStudent", studentDAO.GetAllStudent());
            this.setContentHtml(res);
            this.setResponseView("Add Scores", "/WEB-INF/scores/add.jsp", this.layout, req, res);
            return false;
        } catch (Exception e) {
            this.responseErr(e, req, res, true);

            return false;
        }
    }

    public void AddScorePost(HttpServletRequest req, HttpServletResponse res) throws IOException, SQLException {
        try {
            res.setCharacterEncoding("UTF-8");
            String classId = req.getParameter("_class_id");
            req.setAttribute("class_key", req.getParameter("_class_id"));
            req.setAttribute("name_class", modelClass.GetById(classId));
            Score sc = new Score();
            sc.setStudent_id(req.getParameter("_student_id"));
            sc.setSubject_id(req.getParameter("_subject_id"));
            sc.setScore(req.getParameter("sc_score"));
            sc.setLocal_state(Integer.parseInt(req.getParameter("sc_state")));
            Boolean result = model.Add(sc);

            HttpSession session = req.getSession();
            session.setAttribute("SUCCESS_MSG","Add Success!");
//            req.setAttribute("req_msg", result ? "Add Success!" : "Add Failed!");
            res.sendRedirect(this.getBaseUrl(req) + "/scores?page=1");
        } catch (Exception e) {
            this.responseErr(e, req, res, true);
        }
    }

    public void AddScoreByClassPost(HttpServletRequest req, HttpServletResponse res) throws IOException, SQLException {
        try {
            ClassesDAO classesDAO = new ClassesDAO();
            String classId = req.getParameter("_class_id");
            req.setAttribute("name_class", modelClass.GetById(classId));
            req.setAttribute("DataClass", classesDAO.GetAllClassAddByScore());
            SubjectDAO subjectDAO = new SubjectDAO();
            req.setAttribute("DataSubject", subjectDAO.GetAllSubjectAdd());
            StudentDAO studentDAO = new StudentDAO();
            String class_id = req.getParameter("_class_id");
            req.setAttribute("DataStudent", studentDAO.GetAllStudentAdd(class_id));
            this.setContentHtml(res);
            this.setResponseView("Add Scores", "/WEB-INF/scores/add_by_class.jsp", this.layout, req, res);
        } catch (Exception e) {
            this.responseErr(e, req, res, true);
        }
    }

    public void SearchScore(HttpServletRequest req, HttpServletResponse res) throws IOException, SQLException {
        try {
            this.setContentHtml(res);
            String nameStudent = "%" + req.getParameter("st_name") + "%";
            String nameSubject = "%" + req.getParameter("sj_name") + "%";
            String nameClass = "%" + req.getParameter("cl_name") + "%";
            req.setAttribute("name_key", req.getParameter("st_name"));
            req.setAttribute("subject_key", req.getParameter("sj_name"));
            req.setAttribute("class_key", req.getParameter("cl_name"));
            int limit = 8;
            int page = Integer.parseInt(req.getParameter("page"));
            req.setAttribute("current_page", page);
            pager.setCount(model.GetCountOfSearch(nameStudent, nameSubject, nameClass));
            req.setAttribute("total_page", pager.get_total_page(limit));
            req.setAttribute("table_data", model.SearchByPage(nameStudent, nameSubject, nameClass, page, limit));
            this.setResponseView("List Scores", "/WEB-INF/scores/search.jsp", this.layout, req, res);
        } catch (Exception e) {
            res.sendRedirect(this.getBaseUrl(req) + "/scores?page=1");
        }
    }
}
