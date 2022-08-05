package com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.DAO;


import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.EF.Classes;

import java.util.List;

public interface IClasses extends IBaseDAO<Classes> {
    public List<Classes> FilterByName(String name);
}
