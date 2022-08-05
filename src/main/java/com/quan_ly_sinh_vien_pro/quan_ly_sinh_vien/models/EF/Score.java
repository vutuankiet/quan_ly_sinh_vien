package com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.EF;

import java.util.Date;

public class Score {
    String id;
    String student_id;
    String subject_id;
    String class_id;
    String score;
    Date create_at;
    Date update_at;
    int local_state;
    int global_state;

    public Score(){

    }

    public Score(String id, String student_id, String subject_id, String score, Date create_at, Date update_at, int local_state, int global_state) {
        this.id = id;
        this.student_id = student_id;
        this.subject_id = subject_id;
        this.score = score;
        this.create_at = create_at;
        this.update_at = update_at;
        this.local_state = local_state;
        this.global_state = global_state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }

    public int getLocal_state() {
        return local_state;
    }

    public void setLocal_state(int local_state) {
        this.local_state = local_state;
    }

    public int getGlobal_state() {
        return global_state;
    }

    public void setGlobal_state(int global_state) {
        this.global_state = global_state;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }
}
