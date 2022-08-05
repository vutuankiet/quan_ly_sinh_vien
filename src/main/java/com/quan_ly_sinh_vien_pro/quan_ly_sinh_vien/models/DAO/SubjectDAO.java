package com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.DAO;

import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.helpers.GenKey;
import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.EF.Classes;
import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.EF.Subject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class SubjectDAO extends BaseDAO implements ISubject {
    @Override
    public Boolean Add(Subject subject) throws SQLException {
        try {
            this.open();
            GenKey gen = new GenKey(45);
            int result = 0;
            String _INSERT_SUBJECT_SQL = "INSERT INTO subjects" +
                    "(sj_name,sj_state,_state,sj_id) VALUES" +
                    "(?,?,?,?);";
            String id = gen.gen();

            PreparedStatement prepareStatement = this.conn.prepareStatement(_INSERT_SUBJECT_SQL);
            prepareStatement.setString(1, subject.getName());
            prepareStatement.setInt(2, subject.getLocal_state());
            prepareStatement.setInt(3, subject.getLocal_state());
            prepareStatement.setString(4, id);
            result = prepareStatement.executeUpdate();
            this.destroy();
            return result > 0;
        } catch (Exception ex) {
            this.ThrowsErr(ex);
            return null;
        }
    }

    @Override
    public Boolean Update(Subject subject) throws SQLException {
        try {
            this.open();
            String update_sql = String.format("UPDATE subjects SET subjects.sj_name = %s,subjects.sj_state=%2d WHERE subjects.sj_id = '%s';", "'" + subject.getName() + "'", subject.getLocal_state(), subject.getId());
            PreparedStatement preparedStatement = this.conn.prepareStatement(update_sql);
            int update_row = preparedStatement.executeUpdate();
            if (subject.getLocal_state() == 0) {
                String remove_score_sql = String.format("UPDATE scores SET sc_state = 0 , _state = 0 WHERE scores._subject_id = '%s';", subject.getId());
                PreparedStatement preparedStatement_st = this.conn.prepareStatement(remove_score_sql);
                preparedStatement_st.executeUpdate();
            } else {
                String remove_score_sql = String.format("UPDATE scores SET sc_state = 1 , _state = 1 WHERE scores._subject_id = '%s';", subject.getId());
                PreparedStatement preparedStatement_st = this.conn.prepareStatement(remove_score_sql);
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
            String update_sql = String.format("UPDATE subjects SET sj_state = 0 , _state = 0 WHERE subjects.sj_id = '%s';", id);
            PreparedStatement preparedStatement = this.conn.prepareStatement(update_sql);
            int remove_row = preparedStatement.executeUpdate();
            if (remove_row > 0) {
                String remove_score_sql = String.format("UPDATE scores SET sc_state = 0 , _state = 0 WHERE scores._subject_id = '%s';", id);
                PreparedStatement preparedStatement_st = this.conn.prepareStatement(remove_score_sql);
                preparedStatement_st.executeUpdate();
            }
            this.destroy();
            return remove_row > 0;
        } catch (Exception ex) {
            this.ThrowsErr(ex);
            return null;
        }
    }

    public int GetCountOfSubject(int count) throws SQLException {
        try {
            this.open();

            String sql = String.format("SELECT COUNT(*) AS total FROM subjects WHERE subjects.sj_state = 1;");
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
    public List<Subject> GetAllByPage(int page, int limit) throws SQLException {
        try {
            this.open();
            List<Subject> subjects = new LinkedList<Subject>();
            String sql = String.format("SELECT * FROM subjects LIMIT %2d OFFSET %2d;", limit, ((page - 1) * limit));
            ResultSet rs = this.Execute(sql);
            while (rs.next()) {
                Subject sj = new Subject();
                sj.setId(rs.getString("sj_id"));
                sj.setName(rs.getString("sj_name"));
                sj.setLocal_state(rs.getInt("sj_state"));
                sj.setCreate_at(rs.getTimestamp("_create_at"));
                sj.setUpdate_at(rs.getTimestamp("_update_at"));
                subjects.add(sj);
            }
            this.destroy();
            return subjects;
        } catch (Exception ex) {
            this.ThrowsErr(ex);
            return null;
        }
    }

    public List<Subject> SearchByPage(String nameSubject, int page, int limit) throws SQLException {
        try {
            this.open();
            List<Subject> subjects = new LinkedList<Subject>();
            String sql = String.format("SELECT * FROM subjects WHERE subjects.sj_name LIKE '%s' LIMIT %2d OFFSET %2d;", nameSubject, limit, ((page - 1) * limit));
            ResultSet rs = this.Execute(sql);
            while (rs.next()) {
                Subject sj = new Subject();
                sj.setId(rs.getString("sj_id"));
                sj.setName(rs.getString("sj_name"));
                sj.setLocal_state(rs.getInt("sj_state"));
                sj.setCreate_at(rs.getTimestamp("_create_at"));
                sj.setUpdate_at(rs.getTimestamp("_update_at"));
                subjects.add(sj);
            }
            this.destroy();
            return subjects;
        } catch (Exception ex) {
            this.ThrowsErr(ex);
            return null;
        }
    }

    @Override
    public Subject GetById(String id) throws SQLException {
        try {
            this.open();
            Subject subjects = new Subject();
            String sql = String.format("SELECT * FROM subjects WHERE sj_id = '%s'", id);
            Statement stm = this.conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                subjects.setName(rs.getString("sj_name"));
                subjects.setLocal_state(rs.getInt("sj_state"));
                subjects.setId(rs.getString("sj_id"));
            }
            this.destroy();
            return subjects;
        } catch (Exception ex) {
            this.ThrowsErr(ex);
            return null;
        }
    }

    public Boolean HasExit(String name) {
        try {
            this.open();
            String sql = String.format("SELECT COUNT(*)  AS total FROM subjects WHERE sj_name='%s'", name);
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
    public List<Subject> FilterByName(String name) {
        return null;
    }

    public List<Subject> GetAllSubjectAdd() throws SQLException {
        try {
            this.open();
            List<Subject> subjects = new LinkedList<Subject>();
            String sql = String.format("SELECT * FROM subjects WHERE sj_state = 1");
            ResultSet rs = this.Execute(sql);
            while (rs.next()) {
                    Subject sj = new Subject();
                    sj.setId(rs.getString("sj_id"));
                    sj.setName(rs.getString("sj_name"));
                    sj.setLocal_state(rs.getInt("sj_state"));
                    sj.setCreate_at(rs.getTimestamp("_create_at"));
                    sj.setUpdate_at(rs.getTimestamp("_update_at"));
                    subjects.add(sj);
            }
            this.destroy();
            return subjects;
        } catch (Exception ex) {
            this.ThrowsErr(ex);
            return null;
        }
    }

    public int GetCountOfSearch(String nameSubject) throws SQLException {
        try {

            this.open();
            int count = 0;
            String sql = String.format("SELECT COUNT(*) AS total FROM subjects WHERE subjects.sj_state = 1 AND subjects.sj_name LIKE '%s';", nameSubject);
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
