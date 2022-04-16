package com.example.oraclehibernate.DAO;

import com.example.oraclehibernate.Entities.TableColumns;
import com.example.oraclehibernate.Entities.Role;
import com.example.oraclehibernate.Utils.HibernateUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class DBColumnDAO implements DAOInterface{
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
    public ObservableList<TableColumns> getAll() {
        Session session = HibernateUtils.getFACTORY().openSession();
        CriteriaBuilder cb= session.getCriteriaBuilder();
        CriteriaQuery query=cb.createQuery(TableColumns.class);
        query.from(TableColumns.class);
        List<TableColumns> rList=session.createQuery(query).getResultList();
        session.close();
        return FXCollections.observableArrayList(rList);
    }
    public ObservableList<TableColumns>getColumnTable(String tableName){
        Session session=HibernateUtils.getFACTORY().openSession();
//        String hql = "SELECT UR.grantedRole FROM UserRole UR WHERE grantee="+id;
//        Query query = session.createQuery(hql);
//        List<String> results = query.list();

        CriteriaBuilder cb= session.getCriteriaBuilder();
        CriteriaQuery query=cb.createQuery(TableColumns.class);
        Root<Role> root = query.from(TableColumns.class);
        query.select(root);
        String str=String.format("%%%s%%",tableName);
        query.where(cb.like(root.get("tableName").as(String.class),str));

        List<TableColumns> results= session.createQuery(query).getResultList();
        session.close();
        return FXCollections.observableArrayList(results);
    }
}
