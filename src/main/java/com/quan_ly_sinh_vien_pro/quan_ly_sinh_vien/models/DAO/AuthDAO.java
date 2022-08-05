package com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.DAO;

import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.EF.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

public class AuthDAO extends BaseDAO {
    public Boolean Login(String email, String password) throws SQLException {
        try {
            byte[] base64_byte = password.getBytes();
            String base64_pass = Base64.getEncoder().encodeToString(base64_byte);
            this.open();
            String sql = String.format("SELECT COUNT(*) AS total FROM users WHERE us_email='%s' AND us_password = '%s'", email, base64_pass);
            Statement stm = this.conn.createStatement();
            int count = 0;
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                count = rs.getInt("total");
            }
            this.destroy();
            return count != 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public User GetById(String email) throws SQLException {
        try {
            this.open();
            User user = new User();
            String sql = String.format("SELECT * FROM users WHERE us_email = '%s'", email);
            Statement stm = this.conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                user.setEmail(rs.getString("us_email"));
                user.setPassword(rs.getString("us_password"));
                user.setValid_token(rs.getString("valid_token"));
                user.setId(rs.getString("us_id"));
            }
            this.destroy();
            return user;
        } catch (Exception ex) {
            this.ThrowsErr(ex);
            return null;
        }
    }
}
