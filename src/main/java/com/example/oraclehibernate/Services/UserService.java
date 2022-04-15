package com.example.oraclehibernate.Services;

import com.example.oraclehibernate.Utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UserService {
    private Connection connection;

    public UserService(){

    }
    public void createNewUser(String username,String password) {
        JDBCUtils utils=new JDBCUtils();
        connection=utils.getConnection();
        try {
            Statement stmt = connection.createStatement();
            String alterSession = "alter session set \"_ORACLE_SCRIPT\"=true ";
            stmt.execute(alterSession);
            String query="CREATE USER "+username+" IDENTIFIED BY "+password;
            stmt.execute(query);
            String grantCreateSession="GRANT CREATE SESSION TO "+username;
            stmt.execute(grantCreateSession);

            System.out.println("Create thanh cong");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void dropUser(String username){
        JDBCUtils utils=new JDBCUtils();
        connection=utils.getConnection();
        try {
            Statement stmt = connection.createStatement();
            String query = "DROP USER "+ username + " CASCADE";
            stmt.execute(query);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void changePassword(String username,String password){
        JDBCUtils utils=new JDBCUtils();
        connection=utils.getConnection();
        try{
            Statement stmt = connection.createStatement();
            String query="ALTER USER "+ username+" IDENTIFIED BY "+  password;
            stmt.execute(query);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
