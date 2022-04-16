package com.example.oraclehibernate.Utils;


import com.example.oraclehibernate.Entities.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;


public class HibernateUtils {
    private final static SessionFactory FACTORY;
    static {
        Configuration conf=new Configuration();
        Properties props=new Properties();
        props.put(Environment.DIALECT,"org.hibernate.dialect.Oracle8iDialect");
        props.put(Environment.DRIVER,"oracle.jdbc.OracleDriver");
        props.put(Environment.URL,"jdbc:oracle:thin:@localhost:1521/xe");
        props.put(Environment.PASS,"19120644@Tam");
        props.put(Environment.USER,"system");
        props.put(Environment.SHOW_SQL,"true");

        conf.setProperties(props);


        conf.addAnnotatedClass(User.class);
        conf.addAnnotatedClass(Role.class);
        conf.addAnnotatedClass(UserRole.class);
        conf.addAnnotatedClass(RoleSysPrivs.class);
        conf.addAnnotatedClass(RoleTabPrivs.class);
        conf.addAnnotatedClass(UserTabPrivs.class);
        conf.addAnnotatedClass(UserSysPrivs.class);
        conf.addAnnotatedClass(DBTable.class);
        conf.addAnnotatedClass(TableColumns.class);
        conf.addAnnotatedClass(ColumnPermission.class);

        ServiceRegistry registry=new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        FACTORY=conf.buildSessionFactory(registry);
    }

    public static SessionFactory getFACTORY() {
        return FACTORY;
    }
}
