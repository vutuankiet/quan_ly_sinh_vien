package com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.DAO;

import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.EF.Subject;

import java.util.List;

public interface ISubject extends IBaseDAO<Subject>{
    public List<Subject> FilterByName(String name);
}
