package com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.EF;

import java.util.Date;

public class ScoreView extends Score {
    String student_name;
    String subject_name;

    public ScoreView() {
    }

    public ScoreView(String student_name, String subject_name) {
        this.student_name = student_name;
        this.subject_name = subject_name;
    }

    public ScoreView(String id, String student_id, String subject_id, String score, Date create_at, Date update_at, int local_state, int global_state, String student_name, String subject_name) {
        super(id, student_id, subject_id, score, create_at, update_at, local_state, global_state);
        this.student_name = student_name;
        this.subject_name = subject_name;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }
}
