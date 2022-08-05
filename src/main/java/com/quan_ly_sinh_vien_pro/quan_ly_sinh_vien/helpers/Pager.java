package com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.helpers;

import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.DAO.BaseDAO;

import java.sql.ResultSet;
import java.sql.Statement;

public class Pager extends BaseDAO {
    private String tb_name;
    public int count;

    public Pager(String tbl_name) {
        this.tb_name = tbl_name;
        this.count = 0;
        get_count_of_table();
    }

    public void setCount(int count) {
        this.count = count;
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
        int pages = (int) Math.ceil(this.count / limit);
        if (pages > 0) {
            return pages % 2 == 0 ? pages : pages + 1;
        }else {
            return 1;
        }
    }
}

