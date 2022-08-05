package com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.DAO;

import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.EF.Student;

import java.util.List;

public interface IStudent extends IBaseDAO<Student> {
    public List<Student> FilterByName(String name);
}
