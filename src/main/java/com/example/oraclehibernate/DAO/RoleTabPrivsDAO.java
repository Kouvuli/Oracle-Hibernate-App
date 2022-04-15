package com.example.oraclehibernate.DAO;

import com.example.oraclehibernate.Entities.RoleSysPrivs;
import com.example.oraclehibernate.Entities.RoleTabPrivs;
import com.example.oraclehibernate.Entities.User;
import com.example.oraclehibernate.Utils.HibernateUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class RoleTabPrivsDAO implements DAOInterface {

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
    public ObservableList<RoleTabPrivs> getAll() {
        Session session = HibernateUtils.getFACTORY().openSession();
        CriteriaBuilder cb= session.getCriteriaBuilder();
        CriteriaQuery query=cb.createQuery(RoleTabPrivs.class);
        query.from(RoleTabPrivs.class);
        List<RoleTabPrivs> uList=session.createQuery(query).getResultList();
        session.close();
        return FXCollections.observableArrayList(uList);
    }
}
