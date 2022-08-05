package com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.EF;

import java.util.Date;

public class Subject {
    String id;
    String name;
    Date create_at;
    Date update_at;
    int local_state;
    int global_state;

    public Subject(){

    }
    public Subject(String id, String name, int local_state, int global_state) {
        this.id = id;
        this.name = name;
        this.local_state = local_state;
        this.global_state = global_state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
