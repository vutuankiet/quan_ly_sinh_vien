package com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.DAO;

import java.sql.*;

class DatabaseInfo {
    private final String url = "jdbc:mysql://localhost:3306/quan_ly_sinh_vien";
    private final String user = "root";
    private final String password = "";

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}

public class BaseDAO {
    protected Connection conn = null;
    final DatabaseInfo db_info = new DatabaseInfo();

    public void open() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.conn = DriverManager.getConnection(db_info.getUrl(), db_info.getUser(), db_info.getPassword());
            System.out.println("db connected!");
        } catch (Exception e) {
            this.ThrowsErr(e);
        }
    }

    public void ThrowsErr(Exception e) {
        System.out.println(e.getMessage());
    }

    public ResultSet Execute(String sql) throws SQLException {
        Statement stm = this.conn.createStatement();
        return stm.executeQuery(sql);
    }

    public void destroy() throws SQLException {
        try {
            this.conn.close();
            System.out.println("db disconnected!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}