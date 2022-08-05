package com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.constrollers;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/not-found", "/bad-request"})
public class ErrorServlet extends BaseController {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = this.GetAction(request, response);
        switch (action) {
            case "/not-found":
                this.NotFoundPage(request, response);
                break;
            case "/bad-request":
                this.BadRequestPage(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void NotFoundPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setAttribute("web_content", "/WEB-INF/Errors/404.jsp");
        req.getRequestDispatcher("/WEB-INF/layout/layout.jsp").forward(req, res);
    }

    public void BadRequestPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setAttribute("web_content", "/WEB-INF/Errors/500.jsp");
        req.getRequestDispatcher("/WEB-INF/layout/layout.jsp").forward(req, res);
    }
}

