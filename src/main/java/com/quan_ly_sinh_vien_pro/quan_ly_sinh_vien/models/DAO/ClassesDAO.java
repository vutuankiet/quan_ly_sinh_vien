package com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.DAO;

import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.helpers.GenKey;
import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.EF.Classes;
import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.EF.Student;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ClassesDAO extends BaseDAO implements IClasses {

    @Override
    public Boolean Add(Classes classes) throws SQLException {
        try {
            this.open();
            GenKey gen = new GenKey(45);
            int result = 0;
            String _INSERT_CLASS_SQL = "INSERT INTO classes" +
                    "(cl_name,cl_state,_state,max,cl_id) VALUES" +
                    "(?,?,?,?,?);";
            String id = gen.gen();

            PreparedStatement prepareStatement = this.conn.prepareStatement(_INSERT_CLASS_SQL);
            prepareStatement.setString(1, classes.getName());
            prepareStatement.setInt(2, classes.getLocal_state());
            prepareStatement.setInt(3, classes.getLocal_state());
            prepareStatement.setInt(4, classes.getMax());
            prepareStatement.setString(5, id);
            result = prepareStatement.executeUpdate();
            this.destroy();
            return result > 0;
        } catch (Exception ex) {
            this.ThrowsErr(ex);
            return null;
        }
    }

    @Override
    public Boolean Update(Classes classes) throws SQLException {
        try {
            this.open();
            String update_sql = String.format("UPDATE classes SET classes.cl_name = %s,classes.cl_state=%2d , classes.max = %2d WHERE classes.cl_id = '%s';", "'" + classes.getName() + "'", classes.getLocal_state(), classes.getMax(), classes.getId());
            PreparedStatement preparedStatement = this.conn.prepareStatement(update_sql);
            int update_row = preparedStatement.executeUpdate();
            if (classes.getLocal_state() == 0) {
                String remove_student_sql = String.format("UPDATE students SET st_state = 0 , _state = 0 WHERE students._class_id = '%s';", classes.getId());
                PreparedStatement preparedStatement_st = this.conn.prepareStatement(remove_student_sql);
                preparedStatement_st.executeUpdate();
            } else {
                String remove_student_sql = String.format("UPDATE students SET st_state = 1 , _state = 1 WHERE students._class_id = '%s';", classes.getId());
                PreparedStatement preparedStatement_st = this.conn.prepareStatement(remove_student_sql);
                preparedStatement_st.executeUpdate();
            }
            this.destroy();
            return update_row > 0;
        } catch (Exception ex) {
            this.ThrowsErr(ex);
            return null;
        }
    }

    @Override
    public Boolean Remove(String id) throws SQLException {
        try {
            this.open();
            String update_sql = String.format("UPDATE classes SET cl_state = 0 , _state = 0 WHERE classes.cl_id = '%s';", id);
            PreparedStatement preparedStatement = this.conn.prepareStatement(update_sql);
            int remove_row = preparedStatement.executeUpdate();
            if (remove_row > 0) {
                String remove_student_sql = String.format("UPDATE students SET st_state = 0 , _state = 0 WHERE students._class_id = '%s';", id);
                PreparedStatement preparedStatement_st = this.conn.prepareStatement(remove_student_sql);
                preparedStatement_st.executeUpdate();
            }
            this.destroy();
            return remove_row > 0;
        } catch (Exception ex) {
            this.ThrowsErr(ex);
            return null;
        }
    }

    public int GetCountOfClass(int count) throws SQLException {
        try {
            this.open();

            String sql = String.format("SELECT COUNT(*) AS total FROM classes WHERE classes.cl_state = 1;");
            ResultSet rs = this.Execute(sql);
            while (rs.next()) {
                count = rs.getInt("total");
            }
            this.destroy();
            return count;

        } catch (Exception ex) {
            this.ThrowsErr(ex);
            return 0;
        }
    }

    @Override
    public List<Classes> GetAllByPage(int page, int limit) throws SQLException {
        try {
            this.open();
            List<Classes> classes = new LinkedList<Classes>();
            String sql = String.format("SELECT * FROM classes LIMIT %2d OFFSET %2d;", limit, ((page - 1) * limit));
            ResultSet rs = this.Execute(sql);
            while (rs.next()) {
                Classes cl = new Classes();
                cl.setId(rs.getString("cl_id"));
                cl.setName(rs.getString("cl_name"));
                cl.setMax(rs.getInt("max"));
                cl.setLocal_state(rs.getInt("cl_state"));
                cl.setCreate_at(rs.getTimestamp("_create_at"));
                cl.setUpdate_at(rs.getTimestamp("_update_at"));
                classes.add(cl);
            }
            this.destroy();
            return classes;
        } catch (Exception ex) {
            this.ThrowsErr(ex);
            return null;
        }
    }

    public List<Classes> SearchByPage(String nameClass,int page, int limit) throws SQLException {
        try {
            this.open();
            List<Classes> classes = new LinkedList<Classes>();
            String sql = String.format("SELECT * FROM classes WHERE classes.cl_name LIKE '%s' LIMIT %2d OFFSET %2d;", nameClass, limit, ((page - 1) * limit));
            ResultSet rs = this.Execute(sql);
            while (rs.next()) {
                Classes cl = new Classes();
                cl.setId(rs.getString("cl_id"));
                cl.setName(rs.getString("cl_name"));
                cl.setMax(rs.getInt("max"));
                cl.setLocal_state(rs.getInt("cl_state"));
                cl.setCreate_at(rs.getTimestamp("_create_at"));
                cl.setUpdate_at(rs.getTimestamp("_update_at"));
                classes.add(cl);
            }
            this.destroy();
            return classes;
        } catch (Exception ex) {
            this.ThrowsErr(ex);
            return null;
        }
    }

    @Override
    public Classes GetById(String id) throws SQLException {
        try {
            this.open();
            Classes classes = new Classes();
            String sql = String.format("SELECT * FROM classes WHERE cl_id = '%s'", id);
            Statement stm = this.conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                classes.setName(rs.getString("cl_name"));
                classes.setMax(rs.getInt("max"));
                classes.setLocal_state(rs.getInt("cl_state"));
                classes.setId(rs.getString("cl_id"));
            }
            this.destroy();
            return classes;
        } catch (Exception ex) {
            this.ThrowsErr(ex);
            return null;
        }
    }

    public Classes GetByName(String name) throws SQLException {
        try {
            this.open();
            Classes classes = new Classes();
            String sql = String.format("SELECT * FROM classes WHERE cl_name = '%s'", name);
            Statement stm = this.conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                classes.setName(rs.getString("cl_name"));
                classes.setMax(rs.getInt("max"));
                classes.setLocal_state(rs.getInt("cl_state"));
                classes.setId(rs.getString("cl_id"));
            }
            this.destroy();
            return classes;
        } catch (Exception ex) {
            this.ThrowsErr(ex);
            return null;
        }
    }

    public Boolean HasExit(String name) {
        try {
            this.open();
            String sql = String.format("SELECT COUNT(*)  AS total FROM classes WHERE cl_name='%s'", name);
            Statement stm = this.conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            int rows = 0;
            while (rs.next()) {
                rows = rs.getInt("total");
            }
            Boolean exit = rows != 0;
            this.destroy();
            return exit;
        } catch (Exception ex) {
            this.ThrowsErr(ex);
            return false;
        }
    }

    @Override
    public List<Classes> FilterByName(String name) {
        return null;
    }


    public List<Classes> GetAllClassAdd() throws SQLException {
        try {
            this.open();
            List<Classes> classes = new LinkedList<Classes>();
            String sql = String.format("SELECT * FROM classes WHERE cl_state = 1");
            ResultSet rs = this.Execute(sql);
            while (rs.next()) {
                String st_count_sql = String.format("SELECT COUNT(*) AS st_count FROM students WHERE _class_id='%s' AND st_state = 1;", rs.getString("cl_id"));
                Statement stm_2 = this.conn.createStatement();
                ResultSet rs_2 = stm_2.executeQuery(st_count_sql);
                int count = 0;
                while (rs_2.next()) {
                    count = rs_2.getInt("st_count");
                }
                String name = rs.getString("cl_name");
                int max = rs.getInt("max");
                if (count < max) {
                    Classes cl = new Classes();
                    cl.setId(rs.getString("cl_id"));
                    cl.setName(rs.getString("cl_name"));
                    cl.setMax(rs.getInt("max"));
                    cl.setLocal_state(rs.getInt("cl_state"));
                    cl.setCreate_at(rs.getTimestamp("_create_at"));
                    cl.setUpdate_at(rs.getTimestamp("_update_at"));
                    classes.add(cl);
                }
            }
            this.destroy();
            return classes;
        } catch (Exception ex) {
            this.ThrowsErr(ex);
            return null;
        }
    }

    public List<Classes> GetAllClassAddByScore() throws SQLException {
        try {
            this.open();
            List<Classes> classes = new LinkedList<Classes>();
            String sql = String.format("SELECT * FROM classes WHERE cl_state = 1");
            ResultSet rs = this.Execute(sql);
            while (rs.next()) {
                String st_count_sql = String.format("SELECT COUNT(*) AS st_count FROM students WHERE _class_id='%s' AND st_state = 1;", rs.getString("cl_id"));
                Statement stm_2 = this.conn.createStatement();
                ResultSet rs_2 = stm_2.executeQuery(st_count_sql);
                int count = 0;
                while (rs_2.next()) {
                    count = rs_2.getInt("st_count");
                }
                String name = rs.getString("cl_name");
                int max = rs.getInt("max");
                if (count > 0 && count < max) {
                    Classes cl = new Classes();
                    cl.setId(rs.getString("cl_id"));
                    cl.setName(rs.getString("cl_name"));
                    cl.setMax(rs.getInt("max"));
                    cl.setLocal_state(rs.getInt("cl_state"));
                    cl.setCreate_at(rs.getTimestamp("_create_at"));
                    cl.setUpdate_at(rs.getTimestamp("_update_at"));
                    classes.add(cl);
                }
            }
            this.destroy();
            return classes;
        } catch (Exception ex) {
            this.ThrowsErr(ex);
            return null;
        }
    }

    public List<Classes> GetAllClassUpdate() throws SQLException {
        try {
            this.open();
            List<Classes> classes = new LinkedList<Classes>();
            String sql = String.format("SELECT * FROM classes WHERE cl_state = 1");
            ResultSet rs = this.Execute(sql);
            while (rs.next()) {
                String st_count_sql = String.format("SELECT COUNT(*) AS st_count FROM students WHERE _class_id='%s' AND st_state = 1;", rs.getString("cl_id"));
                Statement stm_2 = this.conn.createStatement();
                ResultSet rs_2 = stm_2.executeQuery(st_count_sql);
                int count = 0;
                while (rs_2.next()) {
                    count = rs_2.getInt("st_count");
                }
                String name = rs.getString("cl_name");
                int max = rs.getInt("max");
                if (count <= max) {
                    Classes cl = new Classes();
                    cl.setId(rs.getString("cl_id"));
                    cl.setName(rs.getString("cl_name"));
                    cl.setMax(rs.getInt("max"));
                    cl.setLocal_state(rs.getInt("cl_state"));
                    cl.setCreate_at(rs.getTimestamp("_create_at"));
                    cl.setUpdate_at(rs.getTimestamp("_update_at"));
                    classes.add(cl);
                }
            }
            this.destroy();
            return classes;
        } catch (Exception ex) {
            this.ThrowsErr(ex);
            return null;
        }
    }

    public int GetCountOfSearch(String nameClass) throws SQLException {
        try {

            this.open();
            int count = 0;
            String sql = String.format("SELECT COUNT(*) AS total FROM classes WHERE classes.cl_state = 1 AND classes.cl_name LIKE '%s';",nameClass);
            ResultSet rs = this.Execute(sql);
            while (rs.next()) {
                count = rs.getInt("total");
            }
            this.destroy();
            return count;
        } catch (Exception ex) {
            this.ThrowsErr(ex);
            return 0;
        }
    }
}
