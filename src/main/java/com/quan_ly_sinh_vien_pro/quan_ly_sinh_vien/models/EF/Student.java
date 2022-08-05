package com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.EF;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

public class Student{
    String id;
    String name;
    String email;
    String address;
    String phone;
    String gender_text;
    int gender;
    Date birthday;
    Timestamp create_at;
    Timestamp update_at;
    int local_state;
    int global_state;
    String avatar;
    String card;
    String class_id;

    public Student() {
    }

    public Student(String id, String name, String email, String address, String phone, int gender, Date birthday, int local_state, int global_state, String avatar, String card, String class_id) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
        this.birthday = birthday;
        this.local_state = local_state;
        this.global_state = global_state;
        this.avatar = avatar;
        this.card = card;
        this.class_id = class_id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender_text() {
        return gender_text;
    }

    public void setGender_text(String gender_text) {
        this.gender_text = gender_text;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }
}
