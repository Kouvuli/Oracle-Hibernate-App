package com.example.oraclehibernate.DAO;

import com.example.oraclehibernate.Models.Role;
import com.example.oraclehibernate.Models.User;
import com.example.oraclehibernate.Models.UserRole;
import com.example.oraclehibernate.Utils.HibernateUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserRoleDAO implements DAOInterface<UserRole> {
    @Override
    public int addData(UserRole data) {
        return 0;
    }

    @Override
    public int delData(UserRole data) {
        return 0;
    }

    @Override
    public int updateData(UserRole newData) {
        return 0;
    }


    @Override
    public ObservableList<UserRole> getAll() {
        Session session = HibernateUtils.getFACTORY().openSession();
        CriteriaBuilder cb= session.getCriteriaBuilder();
        CriteriaQuery query=cb.createQuery(UserRole.class);
        query.from(UserRole.class);
        List<UserRole> list=session.createQuery(query).getResultList();
        session.close();
        return FXCollections.observableArrayList(list);
    }
    public ObservableList<String> getRoles(String id){
        Session session=HibernateUtils.getFACTORY().openSession();
//        String hql = "SELECT UR.grantedRole FROM UserRole UR WHERE grantee="+id;
//        Query query = session.createQuery(hql);
//        List<String> results = query.list();

        CriteriaBuilder cb= session.getCriteriaBuilder();
        CriteriaQuery query=cb.createQuery(UserRole.class);
        Root<UserRole> root = query.from(UserRole.class);
        query.select(root.get("grantedRole"));
        String str=String.format("%%%s%%",id);
//        Predicate p=cb.like(root.get("grantee").as(String.class),str);
        query.where(cb.like(root.get("grantee").as(String.class),str));
        List<String> results=session.createQuery(query).getResultList();
        session.close();
        return FXCollections.observableArrayList(results);
    }
}
