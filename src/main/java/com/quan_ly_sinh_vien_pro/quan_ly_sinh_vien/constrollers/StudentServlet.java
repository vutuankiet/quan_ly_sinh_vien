package com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.constrollers;

import com.google.gson.Gson;
import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.helpers.Pager;
import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.helpers.UploadImage;
import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.DAO.ClassesDAO;
import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.DAO.StudentDAO;
import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.EF.Classes;
import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.EF.Student;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;

@MultipartConfig
@WebServlet(urlPatterns = {"/students", "/single-student", "/students-add", "/students-update", "/students-remove", "/students-filter-card", "/students-filter-email", "/students-search"})
public class StudentServlet extends BaseController {
    protected StudentDAO model = new StudentDAO();
    Pager pager = new Pager("students");
    public final String layout = "/WEB-INF/layout/layout.jsp";



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            boolean isAuth = this.checkAuthenticate(request, response, String.valueOf(request.getRequestURL()));
            if (isAuth) {
                String action_path = this.GetAction(request, response);
                switch (action_path) {
                    case "/students":
                        this.GetListStudent(request, response);
                        break;
                    case "/students-search":
                        this.SearchStudent(request, response);
                        break;
                    case "/students-add":
                        this.AddStudent(request, response);
                        break;
                    case "/students-update":
                        this.UpdateStudent(request, response);
                        break;
                    case "/students-remove":
                        this.RemoveStudent(request, response);
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
        request.setCharacterEncoding("UTF-8");
        try {
            request.setCharacterEncoding("UTF-8");
            boolean isAuth = this.checkAuthenticate(request, response, String.valueOf(request.getRequestURL()));
            if (isAuth) {
                String action_path = this.GetAction(request, response);
                switch (action_path) {
                    case "/students-add":
                        this.AddStudentPost(request, response);
                        break;
                    case "/students-update":
                        this.UpdateStudentPost(request, response);
                        break;
                    case "/students-filter-card":
                        this.CheckCardExit(request, response);
                        break;
                    case "/students-filter-email":
                        this.HasEmailExit(request, response);
                        break;
                    case "/students-search":
                        this.SearchStudent(request, response);
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


//    action detail

    public void HasEmailExit(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            this.setContentAPI(res);

            String email = req.getParameter("st_email");
            Boolean result = model.EmailExit(email);
            res.getWriter().write(new Gson().toJson(result));
        } catch (Exception ex) {
            this.responseErr(ex, req, res, true);

        }
    }

    public Boolean AddStudent(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        try {
            ClassesDAO classesDAO = new ClassesDAO();
            req.setAttribute("DataTable", classesDAO.GetAllClassAdd());
            this.setContentHtml(res);
            this.setResponseView("Add Students", "/WEB-INF/students/add.jsp", this.layout, req, res);
            return false;
        } catch (Exception e) {
            this.responseErr(e, req, res, true);

            return false;
        }
    }

    public void AddStudentPost(HttpServletRequest req, HttpServletResponse res) throws IOException, SQLException {
        try {
            res.setCharacterEncoding("UTF-8");
            String partName = "st_avatar";
            Student st = new Student();
            st.setName(req.getParameter("st_name").trim());
            st.setClass_id(req.getParameter("_class_id").trim());
            st.setEmail(req.getParameter("st_email").trim());
            st.setAddress(req.getParameter("st_address").trim());
            st.setPhone(req.getParameter("st_phone").trim());
            st.setGender(Integer.parseInt(req.getParameter("st_gender").trim()));
            st.setLocal_state(Integer.parseInt(req.getParameter("st_state").trim()));
            st.setCard(req.getParameter("st_card"));
            st.setAvatar(UploadImage.GetFileName(req, partName));
            st.setBirthday(Date.valueOf(req.getParameter("birthday")));

            //save image to local
            UploadImage.SaveToLocal(req, partName);

//             response
            Boolean result = model.Add(st);

            HttpSession session = req.getSession();
            session.setAttribute("SUCCESS_MSG","Add Success!");

//            req.setAttribute("req_msg", result ? "Add Success!" : "Add Failed!");
            res.sendRedirect(this.getBaseUrl(req) + "/students?page=1");
        } catch (Exception e) {
            this.responseErr(e, req, res, true);
        }
    }

    public void GetListStudent(HttpServletRequest req, HttpServletResponse res) throws IOException, SQLException {
        try {
            this.setContentHtml(res);
            int limit = 8;
            int page = Integer.parseInt(req.getParameter("page"));
            req.setAttribute("current_page", page);
            req.setAttribute("total_page", pager.get_total_page(limit));
            req.setAttribute("table_data", model.GetAllByPage(page, limit));
            this.setResponseView("List Students", "/WEB-INF/students/index.jsp", this.layout, req, res);
        } catch (Exception e) {
            this.responseErr(e, req, res, true);
        }
    }

    public void SearchStudent(HttpServletRequest req, HttpServletResponse res) throws IOException, SQLException {
        try {
            this.setContentHtml(res);
            String nameStudent = "%" + req.getParameter("st_name") + "%";
            String nameClass = "%" + req.getParameter("cl_name") + "%";
            req.setAttribute("name_key", req.getParameter("st_name"));
            req.setAttribute("class_key", req.getParameter("cl_name"));
            int limit = 8;
            int page = Integer.parseInt(req.getParameter("page"));
            req.setAttribute("current_page", page);
            pager.setCount(model.GetCountOfSearch(nameStudent, nameClass));
            req.setAttribute("total_page", pager.get_total_page(limit));
            req.setAttribute("table_data", model.SearchByPage(nameStudent, nameClass, page, limit));
            this.setResponseView("List Students", "/WEB-INF/students/search.jsp", this.layout, req, res);
        } catch (Exception e) {
            res.sendRedirect(this.getBaseUrl(req) + "/students?page=1");
        }
    }

    public void UpdateStudent(HttpServletRequest req, HttpServletResponse res) throws IOException, SQLException {
        try {
            String id = req.getParameter("id");
            String req_code = req.getParameter("req_code");
            if (req_code != null) {
                req.setAttribute("req_msg", "Student Has Limit!");
            }
            ClassesDAO classesDAO = new ClassesDAO();
            req.setAttribute("classes", classesDAO.GetAllClassUpdate());
            req.setAttribute("single_student", model.GetById(id));
            this.setContentHtml(res);
            this.setResponseView("Edit Student", "/WEB-INF/students/edit.jsp", this.layout, req, res);

        } catch (Exception ex) {
            this.responseErr(ex, req, res, true);
        }
    }

    public void UpdateStudentPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            this.setContentHtml(res);
            String partName = "st_avatar";
            String st_id = req.getParameter("st_id");
            String st_name = req.getParameter("st_name");
            String st_email = req.getParameter("st_email");
            String st_address = req.getParameter("st_address");
            String st_phone = req.getParameter("st_phone");
            String class_id = req.getParameter("_class_id");
            int st_gender = Integer.parseInt(req.getParameter("st_gender"));
            int st_state = Integer.parseInt(req.getParameter("st_state"));
            String st_card = req.getParameter("st_card");
            Date st_birthday = Date.valueOf(req.getParameter("birthday"));

            Boolean checkUploadFile = UploadImage.CheckPartNull(req, partName);
            String st_avatar = req.getParameter("st_avatar_default");
            if (!checkUploadFile) {
                st_avatar = UploadImage.GetFileName(req, partName);
                UploadImage.SaveToLocal(req, partName);
            }

            Student st = new Student(st_id, st_name, st_email, st_address, st_phone, st_gender, st_birthday, st_state, st_state, st_avatar, st_card, class_id);

            Boolean result = model.Update(st);
            if (result) {
                HttpSession session = req.getSession();
                session.setAttribute("UPDATE_SUCCESS_MSG","Update Success!");
//                req.setAttribute("req_msg", "Update Success!");
                res.sendRedirect(this.getBaseUrl(req) + "/students?page=1");
            } else {
                res.getWriter().write(new Gson().toJson("CLass Has Limit Student!"));
                res.sendRedirect(this.getBaseUrl(req) + "/students-update?id=" + st_id + "&req_code=401");
            }
        } catch (Exception ex) {
            this.responseErr(ex, req, res, true);
        }
    }

    public void RemoveStudent(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            String id = req.getParameter("id");
            this.setContentHtml(res);
            model.Remove(id);
            HttpSession session = req.getSession();
            session.setAttribute("DELETE_SUCCESS_MSG","Delete Success!");
            res.sendRedirect(this.getBaseUrl(req) + "/students?page=1");
        } catch (Exception ex) {
            this.responseErr(ex, req, res, true);
        }
    }

    public void CheckCardExit(HttpServletRequest req, HttpServletResponse response) throws IOException {
        try {
            String card = req.getParameter("st_card");
            Boolean result = model.HasExit(card);
            response.getWriter().write(new Gson().toJson(result));
        } catch (Exception e) {
            this.responseErr(e, req, response, true);
        }
    }

}
