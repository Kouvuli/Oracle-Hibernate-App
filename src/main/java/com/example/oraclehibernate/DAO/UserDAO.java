package com.example.oraclehibernate.DAO;

import com.example.oraclehibernate.Models.User;
import com.example.oraclehibernate.Models.UserRole;
import com.example.oraclehibernate.Utils.HibernateUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDAO implements DAOInterface<User> {
    @Override
    public int addData(User data) {

        return 0;
    }

    @Override
    public int delData(User data) {
        return 0;
    }

    @Override
    public int updateData(User newData) {
        return 0;
    }

    @Override
    public ObservableList<User> getAll() {
        Session session = HibernateUtils.getFACTORY().openSession();
        CriteriaBuilder cb= session.getCriteriaBuilder();
        CriteriaQuery query=cb.createQuery(User.class);
        query.from(User.class);
        List<User>uList=session.createQuery(query).getResultList();
        session.close();
        return FXCollections.observableArrayList(uList);
    }
    public int addNewUser(String username,String password){
        Session session=new HibernateUtils().getFACTORY().openSession();
        Transaction transaction= session.beginTransaction();
        NativeQuery query = session.createNativeQuery("CREATE "+username+" IDENTIFIED BY "+ password);
        transaction.commit();
        session.close();
        return 1;
    }
    public ObservableList<String> getAllUsername(){
        Session session = HibernateUtils.getFACTORY().openSession();
        CriteriaBuilder cb= session.getCriteriaBuilder();
        CriteriaQuery query=cb.createQuery(User.class);
        Root<User> root=query.from(User.class);
        query.select(root.get("username"));
        List<String> list=session.createQuery(query).getResultList();
        session.close();
        return FXCollections.observableArrayList(list);
    }
    public User getUser(String username){
        Session session=HibernateUtils.getFACTORY().openSession();
//        String hql = "SELECT UR.grantedRole FROM UserRole UR WHERE grantee="+id;
//        Query query = session.createQuery(hql);
//        List<String> results = query.list();

        CriteriaBuilder cb= session.getCriteriaBuilder();
        CriteriaQuery query=cb.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root);
        String str=String.format("%%%s%%",username);
//        Predicate p=cb.like(root.get("grantee").as(String.class),str);
        query.where(cb.like(root.get("username").as(String.class),str));
        User results= (User) session.createQuery(query).getSingleResult();
        session.close();
        return results;
    }
}
