package com.example.oraclehibernate.Controllers;

import com.example.oraclehibernate.DAO.ColumnPermissionDAO;
import com.example.oraclehibernate.DAO.DBColumnDAO;
import com.example.oraclehibernate.Entities.ColumnPermission;
import com.example.oraclehibernate.Entities.TableColumns;
import com.example.oraclehibernate.Models.ColumnPermissionRow;
import com.example.oraclehibernate.Models.TabPrivsRow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ColumnPermissionController implements Initializable {

    @FXML
    private TableView<ColumnPermissionRow> columnTable;
    @FXML
    private javafx.scene.control.TableColumn check;
    @FXML
    private javafx.scene.control.TableColumn columnName;
    private String tableName;
    private String grantee;
    private boolean withGrant;
    private String selectedPrivilege;
    private List<String>selectedColumns;
    private List<ColumnPermissionRow> columnPermissionList;
    public void setValue(String selectedPrivilege,String grantee,String tableName) {
        this.selectedPrivilege=selectedPrivilege;
        this.grantee=grantee;
        this.tableName=tableName;
//        this.withGrant=withGrant;
    }
    public ColumnPermissionController(){}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnPermissionList=new ArrayList<>();
        selectedColumns=new ArrayList<>();
        DBColumnDAO DBColumnDAO =new DBColumnDAO();
        ColumnPermissionDAO columnPermissionDAO=new ColumnPermissionDAO();
        if (tableName!=null) {
            ObservableList<TableColumns> columnList = DBColumnDAO.getColumnTable(tableName);
            ObservableList<ColumnPermission> checkedColumnList=columnPermissionDAO.getAllColumnPermission(selectedPrivilege,tableName,grantee);
            columnList.forEach(item -> {
                ColumnPermissionRow row = new ColumnPermissionRow();
                row.setColumnName(item.getColumnName());
                checkedColumnList.forEach(i->{
                    if(i.getColumnName().equals(item.getColumnName())){
                        row.setCheckBox(true);
                    }
                });


//                checkPrivs.forEach(i->{
//                    if(i.getPrivilege().equals(item)){
//                        checkedSysPrivsList.add(item);
//                        userSysPrivsRow.setGrant(true);
//                        userSysPrivsRow.setWithAdmin(i.getAdminOption());
//                    }
//
//                });
                columnPermissionList.add(row);
            });

            check.setCellValueFactory(new PropertyValueFactory<ColumnPermissionRow,String>("checkBox"));
            columnName.setCellValueFactory(new PropertyValueFactory<ColumnPermissionRow,String>("columnName"));


            columnTable.setItems(FXCollections.observableArrayList(columnPermissionList));
        }
    }
    public void closeHandler(ActionEvent event) {
        Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
    }

    public void setResultList(){
        TabPrivsRow r=new TabPrivsRow();
        r.setGrantee(grantee);
        r.setPrivilege(selectedPrivilege);
        r.setTable(tableName);
        r.setWithGrant(withGrant);
    }
    List<String>getSelectedColumns(){
        return selectedColumns;
    }
    public void applyHandler(ActionEvent event) {
        for (ColumnPermissionRow row:columnTable.getItems()) {
            if(row.getCheckBox().isSelected()){
                selectedColumns.add(row.getColumnName());
            }
        }
        Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
    }
}
