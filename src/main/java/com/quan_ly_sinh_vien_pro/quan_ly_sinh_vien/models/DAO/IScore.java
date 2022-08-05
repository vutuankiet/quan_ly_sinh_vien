package com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.DAO;

import com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.EF.Score;

import java.util.List;

public interface IScore extends IBaseDAO<Score>{
    public List<Score> FilterByName(String name);
}
