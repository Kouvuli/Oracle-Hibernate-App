package com.example.oraclehibernate.DAO;

import java.util.List;

public interface DAOInterface<T> {
    public int addData(T data);
    public int delData(T data);
    public int updateData(T newData);
    List<T> getAll();
}
