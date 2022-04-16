package com.example.oraclehibernate.DAO;

import com.example.oraclehibernate.Entities.DBTable;
import com.example.oraclehibernate.Entities.Role;
import com.example.oraclehibernate.Entities.RoleSysPrivs;
import com.example.oraclehibernate.Utils.HibernateUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class DBTableDAO implements DAOInterface{
    @Override
    public int addData(Object data) {
        return 0;
    }

    @Override
    public int delData(Object data) {
        return 0;
    }

    @Override
    public int updateData(Object newData) {
        return 0;
    }

    @Override
    public ObservableList<DBTable> getAll() {
        Session session = HibernateUtils.getFACTORY().openSession();
        CriteriaBuilder cb= session.getCriteriaBuilder();
        CriteriaQuery query=cb.createQuery(DBTable.class);
        query.from(DBTable.class);
        List<DBTable> rList=session.createQuery(query).getResultList();
        session.close();
        return FXCollections.observableArrayList(rList);
    }
    public ObservableList<String> getAllTableByOwner(String owner){

        Session session = HibernateUtils.getFACTORY().openSession();
        CriteriaBuilder cb= session.getCriteriaBuilder();
        CriteriaQuery query=cb.createQuery(DBTable.class);
        Root<DBTable> root = query.from(DBTable.class);
        String str = String.format("%%%s%%", owner);
        query.orderBy(cb.asc(root.get("tableName")));
        query.select(root.get("tableName"));
        query.where(cb.like(root.get("owner").as(String.class), str));
        List<String> list=session.createQuery(query).getResultList();
        session.close();
        return FXCollections.observableArrayList(list);
    }
    public ObservableList<String> getAllTable(){
        Session session = HibernateUtils.getFACTORY().openSession();
        CriteriaBuilder cb= session.getCriteriaBuilder();
        CriteriaQuery query=cb.createQuery(DBTable.class);
        Root<DBTable> root = query.from(DBTable.class);
        query.orderBy(cb.asc(root.get("tableName")));
        query.select(root.get("tableName"));
        List<String> list=session.createQuery(query).getResultList();
        session.close();
        return FXCollections.observableArrayList(list);
    }
}
