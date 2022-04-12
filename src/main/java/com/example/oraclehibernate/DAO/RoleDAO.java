package com.example.oraclehibernate.DAO;

import com.example.oraclehibernate.Models.Role;
import com.example.oraclehibernate.Models.User;
import com.example.oraclehibernate.Utils.HibernateUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
}
