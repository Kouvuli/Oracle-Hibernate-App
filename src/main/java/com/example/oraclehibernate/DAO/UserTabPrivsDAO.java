package com.example.oraclehibernate.DAO;

import com.example.oraclehibernate.Entities.UserTabPrivs;
import com.example.oraclehibernate.Utils.HibernateUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;

import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class UserTabPrivsDAO implements DAOInterface{
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
    public ObservableList<UserTabPrivs> getAll() {
        Session session = HibernateUtils.getFACTORY().openSession();
        CriteriaBuilder cb= session.getCriteriaBuilder();
        CriteriaQuery query=cb.createQuery(UserTabPrivs.class);
        query.from(UserTabPrivs.class);
        List<UserTabPrivs> rList=session.createQuery(query).getResultList();
        session.close();
        return FXCollections.observableArrayList(rList);
    }

    public boolean isTabPrivsExist(String tableName,String grantee,String privilege ){
        Session session=HibernateUtils.getFACTORY().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery query=cb.createQuery(UserTabPrivs.class);
        Root<UserTabPrivs> root = query.from(UserTabPrivs.class);
        query.select(root);
        String s1=String.format("%%%s%%",tableName);
        String s2=String.format("%%%s%%",grantee);
        String s3=String.format("%%%s%%",privilege);
        Predicate p1=cb.like(root.get("tableName"),tableName);
        Predicate p2=cb.like(root.get("grantee"),grantee);
        Predicate p3=cb.like(root.get("privilege"),privilege);
        query.where(cb.and(p1,p2,p3));
        List<UserTabPrivs> rList=session.createQuery(query).getResultList();
        session.close();
        if(rList.isEmpty()){
            return false;
        }
        return true;
    }
    public ObservableList<UserTabPrivs> getUserTabPrivs(String username) {
        Session session = HibernateUtils.getFACTORY().openSession();
        CriteriaBuilder cb= session.getCriteriaBuilder();
        CriteriaQuery<Tuple> query=cb.createTupleQuery();
        Root<UserTabPrivs> root=query.from(UserTabPrivs.class);
        query.multiselect(root.get("grantable").alias("grantable"),
                root.get("tableName").alias("tableName"),
                root.get("privilege").alias("privilege"),
                root.get("grantee").alias("grantee"),
                root.get("owner").alias("owner"),
                root.get("grantor").alias("grantor"));


        String s1=String.format("%%%s%%",username);

        Predicate p1=cb.like(root.get("grantee"),s1);

        query.where(p1);
        TypedQuery<Tuple> list=session.createQuery(query);
        List<UserTabPrivs>results=new ArrayList<>();
        for (Tuple tuple:list.getResultList()) {
            UserTabPrivs r=new UserTabPrivs();
            r.setGrantable(tuple.get("grantable", Boolean.class));
            r.setOwner(tuple.get("owner", String.class));
            r.setGrantor(tuple.get("grantor", String.class));
            r.setGrantee(tuple.get("grantee", String.class));
            r.setPrivilege(tuple.get("privilege", String.class));
            r.setTableName(tuple.get("tableName", String.class));
            results.add(r);
        }
//
        session.close();
        return FXCollections.observableArrayList(results);
    }
}
