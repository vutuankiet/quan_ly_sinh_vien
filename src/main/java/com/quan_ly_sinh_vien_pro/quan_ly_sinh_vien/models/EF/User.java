package com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.EF;

public class User {
    String id;
    String email;
    String password;
    String valid_token;

    public User(){

    }

    public User(String id, String email, String password, String valid_token) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.valid_token = valid_token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getValid_token() {
        return valid_token;
    }

    public void setValid_token(String valid_token) {
        this.valid_token = valid_token;
    }
}
