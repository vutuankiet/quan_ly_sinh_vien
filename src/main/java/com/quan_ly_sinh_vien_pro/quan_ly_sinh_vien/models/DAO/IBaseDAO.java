package com.quan_ly_sinh_vien_pro.quan_ly_sinh_vien.models.DAO;

import java.sql.SQLException;
import java.util.List;

public interface IBaseDAO<E> {
    public Boolean Add(E ef) throws SQLException;

    public Boolean Update(E ef) throws SQLException;

    public Boolean Remove(String id) throws SQLException;

    public List<E> GetAllByPage(int page, int limit) throws SQLException;

    public E GetById(String id) throws SQLException;
}
