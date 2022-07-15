package com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.DAO;

import java.sql.ResultSet;
import java.sql.Statement;

public class PageDAO extends BaseDAO {
    private String tb_name;
    public int count;

    public PageDAO(String tbl_name) {
        this.tb_name = tbl_name;
        this.count = 0;
        get_count_of_table();
    }

    public void get_count_of_table() {
        try {
            this.open();
            Statement statement = this.conn.createStatement();

            String _COUNT_SQL = String.format("SELECT COUNT(*) AS count FROM %s WHERE _status = 1;", this.tb_name);
            ResultSet rs = statement.executeQuery(_COUNT_SQL);
            while (rs.next()) {
                this.count = rs.getInt("count");
            }
            this.destroy();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int get_total_page(int limit) {
        System.out.println(this.count);
        return (int) Math.ceil(this.count / limit)+1;
    }
}
