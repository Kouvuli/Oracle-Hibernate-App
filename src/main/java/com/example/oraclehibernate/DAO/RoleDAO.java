package com.example.oraclehibernate.DAO;

import com.example.oraclehibernate.Entities.Role;
import com.example.oraclehibernate.Entities.User;
import com.example.oraclehibernate.Utils.HibernateUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class RoleDAO implements DAOInterface<Role> {
    @Override
    public int addData(Role data) {
        return 0;
    }

    @Override
    public int delData(Role data) {
        return 0;
    }

    @Override
    public int updateData(Role newData) {
        return 0;
    }

    @Override
    public ObservableList<Role> getAll() {
        Session session = HibernateUtils.getFACTORY().openSession();
        CriteriaBuilder cb= session.getCriteriaBuilder();
        CriteriaQuery query=cb.createQuery(Role.class);
        query.from(Role.class);
        List<Role> rList=session.createQuery(query).getResultList();
        session.close();
        return FXCollections.observableArrayList(rList);
    }
    public ObservableList<String> getAllRole(){
        Session session = HibernateUtils.getFACTORY().openSession();
        CriteriaBuilder cb= session.getCriteriaBuilder();
        CriteriaQuery query=cb.createQuery(Role.class);
        Root<Role> root=query.from(Role.class);
        query.select(root.get("role"));
        List<String> list=session.createQuery(query).getResultList();
        session.close();
        return FXCollections.observableArrayList(list);
    }

    public Role getRole(String roleName) {

        Session session=HibernateUtils.getFACTORY().openSession();
//        String hql = "SELECT UR.grantedRole FROM UserRole UR WHERE grantee="+id;
//        Query query = session.createQuery(hql);
//        List<String> results = query.list();

        CriteriaBuilder cb= session.getCriteriaBuilder();
        CriteriaQuery query=cb.createQuery(Role.class);
        Root<Role> root = query.from(Role.class);
        query.select(root);
        String str=String.format("%%%s%%",roleName);
//        Predicate p=cb.like(root.get("grantee").as(String.class),str);
        query.where(cb.like(root.get("role").as(String.class),str));

        Role results= (Role) session.createQuery(query).setMaxResults(1).getSingleResult();
        session.close();
        return results;

    }
}
