package com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.DAO;

import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.helpers.GenKey;
import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.EF.*;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ScoreDAO extends BaseDAO implements IScore {
    @Override
    public Boolean Add(Score score) throws SQLException {
        try {
            this.open();
            GenKey gen = new GenKey(45);
            int result = 0;
            String _INSERT_SCORE_SQL = "INSERT INTO scores" +
                    "(_student_id,_subject_id,sc_score,sc_state,_state,sc_id) VALUES" +
                    "(?,?,?,?,?,?);";
            String id = gen.gen();

            PreparedStatement prepareStatement = this.conn.prepareStatement(_INSERT_SCORE_SQL);
            prepareStatement.setString(1, score.getStudent_id());
            prepareStatement.setString(2, score.getSubject_id());
            prepareStatement.setString(3, score.getScore());
            prepareStatement.setInt(4, score.getLocal_state());
            prepareStatement.setInt(5, score.getLocal_state());
            prepareStatement.setString(6, id);
            result = prepareStatement.executeUpdate();
            System.out.println(result);
            this.destroy();
            return result > 0;
        } catch (Exception ex) {
            this.ThrowsErr(ex);
            return null;
        }
    }

    @Override
    public Boolean Update(Score score) throws SQLException {
        try {
            this.open();
            String update_sql = String.format("UPDATE scores SET scores.sc_score = '%s',scores.sc_state=%2d ,scores._state=%2d WHERE scores.sc_id = '%s';", score.getScore().toString(), score.getLocal_state(), score.getLocal_state(), score.getId());
            PreparedStatement preparedStatement = this.conn.prepareStatement(update_sql);
            int update_row = preparedStatement.executeUpdate();
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
            String update_sql = String.format("UPDATE scores SET sc_state = 0 , _state = 0 WHERE scores.sc_id = '%s';", id);
            PreparedStatement preparedStatement = this.conn.prepareStatement(update_sql);
            int remove_row = preparedStatement.executeUpdate();
            this.destroy();
            return remove_row > 0;
        } catch (Exception ex) {
            this.ThrowsErr(ex);
            return null;
        }
    }

    @Override
    public List<Score> GetAllByPage(int page, int limit) throws SQLException {
        try {
            this.open();
            List<Score> scores = new LinkedList<Score>();
            String sql = String.format("SELECT * FROM((( scores INNER JOIN students ON scores._student_id=students.st_id)\n" +
                    " INNER JOIN classes ON students._class_id=classes.cl_id)\n" +
                    " INNER JOIN subjects ON scores._subject_id=subjects.sj_id) LIMIT %2d OFFSET %2d;", limit, ((page - 1) * limit));
            ResultSet rs = this.Execute(sql);
            while (rs.next()) {
                Score sc = new Score();
                sc.setId(rs.getString("sc_id"));
                sc.setStudent_id(rs.getString("st_name"));
                sc.setSubject_id(rs.getString("sj_name"));
                sc.setClass_id(rs.getString("cl_name"));
                sc.setScore(rs.getString("sc_score"));
                sc.setLocal_state(Integer.parseInt(rs.getString("sc_state")));
                sc.setCreate_at(rs.getTimestamp("_create_at"));
                sc.setUpdate_at(rs.getTimestamp("_update_at"));
                scores.add(sc);
            }
            this.destroy();
            return scores;
        } catch (Exception ex) {
            this.ThrowsErr(ex);
            return null;
        }
    }


    public List<Score> SearchByPage(String nameStudent, String nameSubject, String nameClass, int page, int limit) throws SQLException {
        try {
            this.open();
            List<Score> scores = new LinkedList<Score>();
            String sql = String.format("SELECT * FROM((( scores JOIN students ON scores._student_id=students.st_id)\n" +
                    " INNER JOIN classes ON students._class_id=classes.cl_id)\n" +
                    " JOIN subjects ON scores._subject_id=subjects.sj_id)  WHERE students.st_name LIKE '%s' AND subjects.sj_name LIKE '%s' AND classes.cl_name LIKE '%s' LIMIT %2d OFFSET %2d;", nameStudent, nameSubject, nameClass, limit, ((page - 1) * limit));
            ResultSet rs = this.Execute(sql);
            while (rs.next()) {
                Score sc = new Score();
                sc.setId(rs.getString("sc_id"));
                sc.setStudent_id(rs.getString("st_name"));
                sc.setSubject_id(rs.getString("sj_name"));
                sc.setClass_id(rs.getString("cl_name"));
                sc.setScore(String.valueOf(rs.getInt("sc_score")));
                sc.setLocal_state(Integer.parseInt(rs.getString("sc_state")));
                scores.add(sc);
            }
            this.destroy();
            return scores;
        } catch (Exception ex) {
            this.ThrowsErr(ex);
            return null;
        }
    }

    @Override
    public ScoreView GetById(String id) throws SQLException {
        try {
            this.open();
            ScoreView score = new ScoreView();
            String sql = String.format("SELECT * FROM scores JOIN students ON scores._student_id = " +
                    "students.st_id JOIN subjects ON scores._subject_id = subjects.sj_id" +
                    " WHERE sc_id = '%s';", id);
            Statement stm = this.conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                score.setStudent_id(rs.getString("st_id"));
                score.setSubject_id(rs.getString("sj_id"));
                score.setScore(String.valueOf(rs.getInt("sc_score")));
                score.setLocal_state(Integer.parseInt(rs.getString("sc_state")));
                score.setStudent_name(rs.getString("st_name"));
                score.setSubject_name(rs.getString("sj_name"));
                score.setId(rs.getString("sc_id"));
            }
            this.destroy();
            return score;
        } catch (Exception ex) {
            this.ThrowsErr(ex);
            return null;
        }
    }

    @Override
    public List<Score> FilterByName(String name) {
        return null;
    }

    public int GetCountOfSearch(String nameStudent, String nameSubject, String nameClass) throws SQLException {
        try {

            this.open();
            int count = 0;
            String sql = String.format("SELECT COUNT(*) AS total FROM((( scores JOIN students ON scores._students_id=students.st_id\n" +
                    " INNER JOIN classes ON students._class_id=classes.cl_id)\n" +
                    " JOIN subjects ON scores._subjects_id=subjects.sc_id) WHERE scores.sc_state = 1 AND students.st_name LIKE '%s' AND subjects.sj_name LIKE '%s' AND classes.cl_name LIKE '%s';", nameStudent, nameSubject, nameClass);
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
