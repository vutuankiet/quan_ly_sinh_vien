package com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.constrollers;

import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.DAO.AuthDAO;

import javax.imageio.IIOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.Objects;

public class BaseController extends HttpServlet {
    AuthDAO authDAO = new AuthDAO();

    public Boolean checkAuthenticate(HttpServletRequest request, HttpServletResponse response, String redirectUrl) throws ServletException, IOException {
        try {
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
                if (isRemember) {
                    return authDAO.Login(us_email, us_password);
                } else {
                    return false;
                }
            }
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public BaseController() {

    }

    public String GetAction(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        try {
            String action = req.getServletPath();
            if (!Objects.equals(action, "")) {
                return action;
            }
            return "";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            res.sendError(500, e.getMessage());
            return null;
        }
    }

    public String getBaseUrl(HttpServletRequest request) {
        String scheme = request.getScheme() + "://";
        String serverName = request.getServerName();
        String serverPort = (request.getServerPort() == 80) ? "" : ":" + request.getServerPort();
        String contextPath = request.getContextPath();
        return scheme + serverName + serverPort + contextPath;
    }

    public void setContentHtml(HttpServletResponse res) {
        res.setContentType("text/html");
        res.setCharacterEncoding("UTF-8");
    }

    public void responseAPI(HttpServletResponse res) {

    }

    public void setContentAPI(HttpServletResponse res) {
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
    }

    public void responseRedirect(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.sendRedirect(this.getBaseUrl(req) + "/not-found");
    }

    public void responseErr(Exception e, HttpServletRequest req, HttpServletResponse res, Boolean redirect) throws IOException {
        if (redirect) {
            res.sendRedirect(this.getBaseUrl(req) + "/not-found");
        } else {
            System.out.println(e.getMessage());
            res.sendError(500, e.getMessage());
        }
    }

    public void setResponseView(String title, String view_path, String layout, HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            req.setAttribute("web_title", title);
            req.setAttribute("web_content", view_path);
            req.getRequestDispatcher(layout).forward(req, res);
        } catch (Exception e) {
            this.responseErr(e, req, res, true);
        }
    }
}
