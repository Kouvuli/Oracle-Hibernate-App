package com.example.oraclehibernate.DAO;

import com.example.oraclehibernate.Entities.*;
import com.example.oraclehibernate.Utils.HibernateUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserSysPrivsDAO implements DAOInterface {

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
    public ObservableList<String> getAll() {
        Session session = HibernateUtils.getFACTORY().openSession();
        CriteriaBuilder cb= session.getCriteriaBuilder();
        CriteriaQuery query=cb.createQuery(UserSysPrivs.class);
        Root<UserSysPrivs> root=query.from(UserSysPrivs.class);
        query.select(root.get("privilege")).distinct(true);
        List<String> list=session.createQuery(query).getResultList();
        session.close();
        return FXCollections.observableArrayList(list);
    }

    public ObservableList<UserSysPrivs>getUserSysPriv(String username){
        Session session=HibernateUtils.getFACTORY().openSession();
//        String hql = "SELECT UR.grantedRole FROM UserRole UR WHERE grantee="+id;
//        Query query = session.createQuery(hql);
//        List<String> results = query.list();

        CriteriaBuilder cb= session.getCriteriaBuilder();
        CriteriaQuery query=cb.createQuery(UserSysPrivs.class);
        Root<UserSysPrivs> root = query.from(UserSysPrivs.class);
        query.select(root);
        String str=String.format("%%%s%%",username);
//        Predicate p=cb.like(root.get("grantee").as(String.class),str);
        query.where(cb.like(root.get("grantee").as(String.class),str));
        List<UserSysPrivs> results=session.createQuery(query).getResultList();
        session.close();
        return FXCollections.observableArrayList(results);
    }
}
