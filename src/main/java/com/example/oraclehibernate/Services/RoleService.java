package com.example.oraclehibernate.Services;

import com.example.oraclehibernate.Utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class RoleService {
    private Connection connection;

    public RoleService(){

    }
    public void createNewRole(String rolename) {
        JDBCUtils utils=new JDBCUtils();
        connection=utils.getConnection();
        try {
            Statement stmt = connection.createStatement();
            String alterSession = "alter session set \"_ORACLE_SCRIPT\"=true ";
            stmt.execute(alterSession);
            String query="CREATE ROLE "+rolename;
            stmt.execute(query);
            System.out.println("Create thanh cong");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropRole(String rolename){
        JDBCUtils utils=new JDBCUtils();
        connection=utils.getConnection();
        try {
            Statement stmt = connection.createStatement();
            String query = "DROP ROLE "+ rolename;
            stmt.execute(query);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void grantRole(String roleName, String grantee, boolean withAdminOption) {
        JDBCUtils utils=new JDBCUtils();
        connection=utils.getConnection();
        String query= "";
        try {
            Statement stmt = connection.createStatement();
            if (!withAdminOption) {
                query="grant "+ roleName + " to " + grantee;
            } else {
                query="grant "+ roleName + " to " + grantee + " with admin option";
            }
            stmt.execute(query);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void revokeRole(String rolename,String grantee){
        JDBCUtils utils=new JDBCUtils();
        connection = utils.getConnection();
        try{
            Statement stmt = connection.createStatement();
            String query="revoke "+ rolename+  " from " + grantee;
            stmt.execute(query);
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
