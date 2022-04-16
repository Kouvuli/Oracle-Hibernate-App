package com.example.oraclehibernate.DAO;

import com.example.oraclehibernate.Entities.ColumnPermission;
import com.example.oraclehibernate.Entities.Role;
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

public class ColumnPermissionDAO {
    public ObservableList<ColumnPermission> getAllColumnPermission(String privilege,String tableName,String grantee){
        Session session = HibernateUtils.getFACTORY().openSession();
        CriteriaBuilder cb= session.getCriteriaBuilder();
        CriteriaQuery<Tuple> query=cb.createTupleQuery();
        Root<ColumnPermission> root=query.from(ColumnPermission.class);
        query.multiselect(root.get("columnName").alias("columnName"),
                root.get("tableName").alias("tableName"),
                root.get("privilege").alias("privilege"),
                root.get("grantee").alias("grantee"));


        String s1=String.format("%%%s%%",tableName);
        String s2=String.format("%%%s%%",privilege);
        String s3=String.format("%%%s%%",grantee);
        Predicate p1=cb.like(root.get("tableName"),s1);
        Predicate p2=cb.like(root.get("grantee"),s3);
        Predicate p3=cb.like(root.get("privilege"),s2);
        query.where(cb.and(p1,p2,p3));
        TypedQuery<Tuple> list=session.createQuery(query);
        List<ColumnPermission>results=new ArrayList<>();
        for (Tuple tuple:list.getResultList()) {
            ColumnPermission r=new ColumnPermission();
            r.setColumnName(tuple.get("columnName", String.class));
            r.setGrantee(tuple.get("grantee", String.class));
            r.setPrivilege(tuple.get("privilege", String.class));
            r.setTableName(tuple.get("tableName", String.class));
            results.add(r);
        }

        session.close();
        return FXCollections.observableArrayList(results);
    }
    public ObservableList<ColumnPermission> getUserColTabPrivs(String grantee){
        Session session = HibernateUtils.getFACTORY().openSession();
        CriteriaBuilder cb= session.getCriteriaBuilder();
        CriteriaQuery<Tuple> query=cb.createTupleQuery();
        Root<ColumnPermission> root=query.from(ColumnPermission.class);
        query.multiselect(root.get("columnName").alias("columnName"),
                root.get("tableName").alias("tableName"),
                root.get("privilege").alias("privilege"),
                root.get("grantee").alias("grantee"));



        String s3=String.format("%%%s%%",grantee);

        Predicate p2=cb.like(root.get("grantee"),s3);

        query.where(p2);
        TypedQuery<Tuple> list=session.createQuery(query);
        List<ColumnPermission>results=new ArrayList<>();
        for (Tuple tuple:list.getResultList()) {
            ColumnPermission r=new ColumnPermission();
            r.setColumnName(tuple.get("columnName", String.class));
            r.setGrantee(tuple.get("grantee", String.class));
            r.setPrivilege(tuple.get("privilege", String.class));
            r.setTableName(tuple.get("tableName", String.class));
            results.add(r);
        }

        session.close();
        return FXCollections.observableArrayList(results);
    }
}
