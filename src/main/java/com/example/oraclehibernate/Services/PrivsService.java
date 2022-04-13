package com.example.oraclehibernate.Services;

import com.example.oraclehibernate.Utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PrivsService {
    private Connection connection;
    public PrivsService(){}
    public void grantSelect(List<String>cols,String object, String grantee, boolean withGrantOption){
        JDBCUtils utils=new JDBCUtils();
        connection=utils.getConnection();
        String query="";
        try{
            Statement stmt = connection.createStatement();
            if (cols.size()==0){
                if (!withGrantOption) {
                    query="grant select on "+ object + " to "+ grantee;
                } else {
                    query="grant select on "+ object + " to "+ grantee +" with grant option";
                }
            }
            else{
                if (!withGrantOption) {
                    query="grant select ("+String.join(",",cols) +") on "+ object + " to "+ grantee;
                } else {
                    query="grant select ("+String.join(",",cols) +") on "+ object + " to "+ grantee +" with grant option";
                }
            }
            stmt.execute(query);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void grantDelete(List<String>cols,String object, String grantee, boolean withGrantOption){
        JDBCUtils utils=new JDBCUtils();
        connection=utils.getConnection();
        String query="";
        try{
            Statement stmt = connection.createStatement();
            if (cols.size()==0){
                if (!withGrantOption) {
                    query="grant delete on "+ object + " to "+ grantee;
                } else {
                    query="grant delete on "+ object + " to "+ grantee +" with grant option";
                }
            }
            else{
                if (!withGrantOption) {
                    query="grant delete ("+String.join(",",cols) +") on "+ object + " to "+ grantee;
                } else {
                    query="grant delete ("+String.join(",",cols) +") on "+ object + " to "+ grantee +" with grant option";
                }
            }
            stmt.execute(query);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void grantUpdate(List<String>cols,String object, String grantee, boolean withGrantOption){
        JDBCUtils utils=new JDBCUtils();
        connection=utils.getConnection();
        String query="";
        try{
            Statement stmt = connection.createStatement();
            if (cols.size()==0){
                if (!withGrantOption) {
                    query="grant update on "+ object + " to "+ grantee;
                } else {
                    query="grant update on "+ object + " to "+ grantee +" with grant option";
                }
            }
            else{
                if (!withGrantOption) {
                    query="grant update ("+String.join(",",cols) +") on "+ object + " to "+ grantee;
                } else {
                    query="grant update ("+String.join(",",cols) +") on "+ object + " to "+ grantee +" with grant option";
                }
            }
            stmt.execute(query);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void grantInsert(List<String>cols,String object, String grantee, boolean withGrantOption){
        JDBCUtils utils=new JDBCUtils();
        connection=utils.getConnection();
        String query="";
        try{
            Statement stmt = connection.createStatement();
            if (cols.size()==0){
                if (!withGrantOption) {
                    query="grant insert on "+ object + " to "+ grantee;
                } else {
                    query="grant insert on "+ object + " to "+ grantee +" with grant option";
                }
            }
            else{
                if (!withGrantOption) {
                    query="grant insert ("+String.join(",",cols) +") on "+ object + " to "+ grantee;
                } else {
                    query="grant insert ("+String.join(",",cols) +") on "+ object + " to "+ grantee +" with grant option";
                }
            }
            stmt.execute(query);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void revokeSelect(String object,String grantee){
        JDBCUtils utils=new JDBCUtils();
        connection=utils.getConnection();

        try{
            Statement stmt = connection.createStatement();
            String query="revoke select on " + object + " from " + grantee;
            stmt.execute(query);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void revokeUpdate(String object,String grantee){
        JDBCUtils utils=new JDBCUtils();
        connection=utils.getConnection();

        try{
            Statement stmt = connection.createStatement();
            String query="revoke update on " + object + " from " + grantee;
            stmt.execute(query);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void revokeDelete(String object,String grantee){
        JDBCUtils utils=new JDBCUtils();
        connection=utils.getConnection();

        try{
            Statement stmt = connection.createStatement();
            String query="revoke delete on " + object + " from " + grantee;
            stmt.execute(query);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void revokeInsert(String object,String grantee){
        JDBCUtils utils=new JDBCUtils();
        connection=utils.getConnection();
        try{
            Statement stmt = connection.createStatement();
            String query="revoke insert on " + object + " from " + grantee;
            stmt.execute(query);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
