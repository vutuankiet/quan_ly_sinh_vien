package com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.DAO;

import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.helpers.GenKey;
import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.EF.Classes;
import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.EF.Student;
import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.EF.Subject;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class StudentDAO extends BaseDAO implements IStudent {


    @Override
    public Boolean Add(Student student) throws SQLException {
        try {
            this.open();
            GenKey gen = new GenKey(45);
            int result = 0;
            String _INSERT_STUDENT_SQL = "INSERT INTO students" +
                    "(st_name,st_email,st_address,st_phone,st_gender,st_state,_state,_class_id,st_card,st_avatar,birthday,st_id) VALUES" +
                    "(?,?,?,?,?,?,?,?,?,?,?,?);";
            String id = gen.gen();

            PreparedStatement prepareStatement = this.conn.prepareStatement(_INSERT_STUDENT_SQL);
            prepareStatement.setString(1, student.getName());
            prepareStatement.setString(2, student.getEmail());
            prepareStatement.setString(3, student.getAddress());
            prepareStatement.setString(4, student.getPhone());
            prepareStatement.setInt(5, student.getGender());
            prepareStatement.setInt(6, student.getLocal_state());
            prepareStatement.setInt(7, student.getLocal_state());
            prepareStatement.setString(8, student.getClass_id());
            prepareStatement.setString(9, student.getCard());
            prepareStatement.setString(10, student.getAvatar());
            prepareStatement.setDate(11, (Date) student.getBirthday());
            prepareStatement.setString(12, id);
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
    public Boolean Update(Student student) throws SQLException {
        try {
            this.open();
//            check max count in class
            boolean isValid = true;
            if (student.getLocal_state() == 1) {
                String sql = String.format("SELECT * FROM classes WHERE cl_state = 1 AND cl_id='%s';", student.getClass_id());
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
                    if (!(count < max)) {
                        isValid = false;
                    }
                }
            }

            if (isValid) {
                String update_sql = "UPDATE students SET students.st_name = ? ,students.st_email = ? ,students.st_address = ? , students.st_phone = ? , students.st_gender = ?, students.st_state = ?, students._state = ?, students._class_id = ?, students.st_card = ?, students.st_avatar = ?, students.birthday = ? WHERE students.st_id = ?;";
                PreparedStatement prepareStatement = this.conn.prepareStatement(update_sql);
                prepareStatement.setString(1, student.getName());
                prepareStatement.setString(2, student.getEmail());
                prepareStatement.setString(3, student.getAddress());
                prepareStatement.setString(4, student.getPhone());
                prepareStatement.setInt(5, student.getGender());
                prepareStatement.setInt(6, student.getLocal_state());
                prepareStatement.setInt(7, student.getLocal_state());
                prepareStatement.setString(8, student.getClass_id());
                prepareStatement.setString(9, student.getCard());
                prepareStatement.setString(10, student.getAvatar());
                prepareStatement.setDate(11, (Date) student.getBirthday());
                prepareStatement.setString(12, student.getId());
                int update_row = prepareStatement.executeUpdate();
                if (student.getLocal_state() == 0) {
                    String remove_score_sql = String.format("UPDATE scores SET sc_state = 0 , _state = 0 WHERE scores._student_id = '%s';", student.getId());
                    PreparedStatement preparedStatement_st = this.conn.prepareStatement(remove_score_sql);
                    preparedStatement_st.executeUpdate();
                } else {
                    String remove_score_sql = String.format("UPDATE scores SET sc_state = 1 , _state = 1 WHERE scores._student_id = '%s';", student.getId());
                    PreparedStatement preparedStatement_st = this.conn.prepareStatement(remove_score_sql);
                    preparedStatement_st.executeUpdate();
                }
                this.destroy();
                return update_row > 0;
            }
            return false;
        } catch (Exception ex) {
            this.ThrowsErr(ex);
            return null;
        }
    }

    public Boolean HasExit(String card) {
        try {
            this.open();
            String sql = String.format("SELECT COUNT(*)  AS total FROM students WHERE st_card='%s'", card);
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
    public Boolean Remove(String id) throws SQLException {
        try {
            this.open();
            String update_sql = String.format("UPDATE students SET st_state = 0 , _state = 0 WHERE students.st_id = '%s';", id);
            PreparedStatement preparedStatement = this.conn.prepareStatement(update_sql);
            int remove_row = preparedStatement.executeUpdate();
            if (remove_row > 0) {
                String remove_score_sql = String.format("UPDATE scores SET sc_state = 0 , _state = 0 WHERE scores._student_id = '%s';", id);
                PreparedStatement preparedStatement_sc = this.conn.prepareStatement(remove_score_sql);
                preparedStatement_sc.executeUpdate();
            }
            this.destroy();
            return remove_row > 0;
        } catch (Exception ex) {
            this.ThrowsErr(ex);
            return null;
        }
    }


    @Override
    public List<Student> GetAllByPage(int page, int limit) throws SQLException {
        try {
            this.open();
            List<Student> students = new LinkedList<Student>();
            String sql = String.format("SELECT * FROM students INNER JOIN classes ON students._class_id=classes.cl_id LIMIT %2d OFFSET %2d;", limit, ((page - 1) * limit));
            ResultSet rs = this.Execute(sql);
            while (rs.next()) {
                Student st = new Student();
                st.setId(rs.getString("st_id"));
                st.setName(rs.getString("st_name"));
                st.setEmail(rs.getString("st_email"));
                st.setAddress(rs.getString("st_address"));
                st.setPhone(rs.getString("st_phone"));
                st.setGender(Integer.parseInt(rs.getString("st_gender")));
                st.setClass_id(rs.getString("cl_name"));
                st.setCard(rs.getString("st_card"));
                st.setAvatar(rs.getString("st_avatar"));
                st.setBirthday(rs.getDate("birthday"));
                st.setLocal_state(Integer.parseInt(rs.getString("st_state")));
                students.add(st);
            }
            this.destroy();
            return students;
        } catch (Exception ex) {
            this.ThrowsErr(ex);
            return null;
        }
    }

    public List<Student> SearchByPage(String nameStudent, String nameClass, int page, int limit) throws SQLException {
        try {
            this.open();
            List<Student> students = new LinkedList<Student>();
            String sql = String.format("SELECT * FROM students JOIN classes ON students._class_id=classes.cl_id  WHERE students.st_name LIKE '%s' AND classes.cl_name LIKE '%s' LIMIT %2d OFFSET %2d;", nameStudent, nameClass, limit, ((page - 1) * limit));
            ResultSet rs = this.Execute(sql);
            while (rs.next()) {
                Student st = new Student();
                st.setId(rs.getString("st_id"));
                st.setName(rs.getString("st_name"));
                st.setEmail(rs.getString("st_email"));
                st.setAddress(rs.getString("st_address"));
                st.setPhone(rs.getString("st_phone"));
                st.setGender(Integer.parseInt(rs.getString("st_gender")));
                st.setClass_id(rs.getString("cl_name"));
                st.setCard(rs.getString("st_card"));
                st.setAvatar(rs.getString("st_avatar"));
                st.setBirthday(rs.getDate("birthday"));
                st.setLocal_state(Integer.parseInt(rs.getString("st_state")));
                students.add(st);
            }
            this.destroy();
            return students;
        } catch (Exception ex) {
            this.ThrowsErr(ex);
            return null;
        }
    }

    public int GetCountOfStudent(int count) throws SQLException {
        try {
            this.open();

            String sql = String.format("SELECT COUNT(*) AS total FROM students JOIN classes ON students._class_id=classes.cl_id  WHERE students.st_state = 1;");
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
    public Student GetById(String id) throws SQLException {
        try {
            this.open();
            Student student = new Student();
            String sql = String.format("SELECT * FROM students WHERE st_id = '%s'", id);
            Statement stm = this.conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                student.setName(rs.getString("st_name"));
                student.setEmail(rs.getString("st_email"));
                student.setAddress(rs.getString("st_address"));
                student.setPhone(rs.getString("st_phone"));
                student.setGender(Integer.parseInt(rs.getString("st_gender")));
                student.setClass_id(rs.getString("_class_id"));
                student.setCard(rs.getString("st_card"));
                student.setAvatar(rs.getString("st_avatar"));
                student.setBirthday(rs.getDate("birthday"));
                student.setLocal_state(Integer.parseInt(rs.getString("st_state")));
                student.setId(rs.getString("st_id"));
            }
            this.destroy();
            return student;
        } catch (Exception ex) {
            this.ThrowsErr(ex);
            return null;
        }
    }

    public Boolean EmailExit(String email) {
        try {
            this.open();
            String sql = String.format("SELECT COUNT(*) AS total FROM students WHERE st_email='%s'", email);
            Statement stm = this.conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            int rows = 0;
            while (rs.next()) {
                rows = rs.getInt("total");
            }
            this.destroy();
            return rows != 0;
        } catch (Exception e) {
            this.ThrowsErr(e);
            return false;
        }
    }

    @Override
    public List<Student> FilterByName(String name) {
        return null;
    }

    public List<Student> GetAllStudentAdd(String class_id) throws SQLException {
        try {
            this.open();
            List<Student> students = new LinkedList<Student>();
            String sql = String.format("SELECT * FROM students WHERE st_state = 1 AND _class_id = '%s';",class_id);
            ResultSet rs = this.Execute(sql);
            while (rs.next()) {
                Student student = new Student();
                student.setName(rs.getString("st_name"));
                student.setEmail(rs.getString("st_email"));
                student.setAddress(rs.getString("st_address"));
                student.setPhone(rs.getString("st_phone"));
                student.setGender(Integer.parseInt(rs.getString("st_gender")));
                student.setClass_id(rs.getString("_class_id"));
                student.setCard(rs.getString("st_card"));
                student.setAvatar(rs.getString("st_avatar"));
                student.setBirthday(rs.getDate("birthday"));
                student.setLocal_state(Integer.parseInt(rs.getString("st_state")));
                student.setId(rs.getString("st_id"));
                students.add(student);
            }
            this.destroy();
            return students;
        } catch (Exception ex) {
            this.ThrowsErr(ex);
            return null;
        }
    }

    public List<Student> GetAllStudent() throws SQLException {
        try {
            this.open();
            List<Student> students = new LinkedList<Student>();
            String sql = String.format("SELECT * FROM students WHERE st_state = 1");
            ResultSet rs = this.Execute(sql);
            while (rs.next()) {
                Student student = new Student();
                student.setName(rs.getString("st_name"));
                student.setEmail(rs.getString("st_email"));
                student.setAddress(rs.getString("st_address"));
                student.setPhone(rs.getString("st_phone"));
                student.setGender(Integer.parseInt(rs.getString("st_gender")));
                student.setClass_id(rs.getString("_class_id"));
                student.setCard(rs.getString("st_card"));
                student.setAvatar(rs.getString("st_avatar"));
                student.setBirthday(rs.getDate("birthday"));
                student.setLocal_state(Integer.parseInt(rs.getString("st_state")));
                student.setId(rs.getString("st_id"));
                students.add(student);
            }
            this.destroy();
            return students;
        } catch (Exception ex) {
            this.ThrowsErr(ex);
            return null;
        }
    }

    public int GetCountOfSearch(String nameStudent, String nameClass) throws SQLException {
        try {

            this.open();
            int count = 0;
            String sql = String.format("SELECT COUNT(*) AS total FROM students JOIN classes ON students._class_id=classes.cl_id  WHERE students.st_state = 1 AND students.st_name LIKE '%s' AND classes.cl_name LIKE '%s';", nameStudent, nameClass);
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
