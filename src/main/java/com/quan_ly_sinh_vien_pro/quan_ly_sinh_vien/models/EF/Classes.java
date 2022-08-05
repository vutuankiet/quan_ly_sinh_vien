package com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.EF;

import java.sql.Timestamp;
import java.util.Date;

public class Classes {
    String id;
    String name;
    Timestamp create_at;
    Timestamp update_at;
    int local_state;
    int global_state;
    int max;

    public Classes() {
    }

    public Classes(String id, String name, int local_state, int global_state, int max) {
        this.id = id;
        this.name = name;
        this.local_state = local_state;
        this.global_state = global_state;
        this.max = max;
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

    public Timestamp getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Timestamp create_at) {
        this.create_at = create_at;
    }

    public Timestamp getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Timestamp update_at) {
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

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
