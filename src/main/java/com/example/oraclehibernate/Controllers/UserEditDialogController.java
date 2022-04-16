package com.example.oraclehibernate.Controllers;

import com.example.oraclehibernate.DAO.*;
import com.example.oraclehibernate.Entities.*;
import com.example.oraclehibernate.Models.TabPrivsRow;
import com.example.oraclehibernate.Models.UserRoleRow;
import com.example.oraclehibernate.Models.UserSysPrivsRow;
import com.example.oraclehibernate.Models.UserTabPrivsRow;
import com.example.oraclehibernate.Services.PrivsService;
import com.example.oraclehibernate.Services.RoleService;
import com.example.oraclehibernate.Services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserEditDialogController implements Initializable {
    @FXML
    private Text alertText;

    @FXML
    private TextField password;

    @FXML
    private TextField confirmPassword;

    @FXML
    private TableView<UserRoleRow> roleTable;

    @FXML
    private TableView<UserSysPrivsRow> sysPrivsTable;

    @FXML
    private TableView<UserTabPrivsRow> tabPrivsTable;

    @FXML
    private TableColumn<UserRoleRow,String> roleName;

    @FXML
    private TableColumn<UserRoleRow, String> grantRole;

    @FXML
    private TableColumn<UserRoleRow, String> withAdminRole;


    @FXML
    private TableColumn<UserSysPrivsRow,String> sysPrivsName;

    @FXML
    private TableColumn<UserSysPrivsRow, String> grantSysPrivs;

    @FXML
    private TableColumn<UserSysPrivsRow, String> withAdminSysPrivs;

    @FXML
    private TableColumn<UserTabPrivsRow,String>tabPrivsName;

    @FXML
    private TableColumn<UserTabPrivsRow,String>grantTabPrivs;
    @FXML
    private TableColumn<UserTabPrivsRow,String>withGrantTabPrivs;

    @FXML
    private ComboBox<String>tableComboBox;

    @FXML
    private Button columnPermission;

    private List<UserRoleRow> roleList;
    private List<String> checkedRoleList;

    private List<UserSysPrivsRow> sysPrivsList;
    private List<String> checkedSysPrivsList;

    private List<UserTabPrivsRow> tabPrivsList;
    private List<TabPrivsRow> checkedTabPrivsList;
    private List<String> checkTabPrivsList;
    private String username;
    private String currTable;
    private String selectedPrivilege;
    private List<TabPrivsRow> selectedTabPrivsCol;
    private List<String>selectedColumns;
    public UserEditDialogController() {
    }

    public void setUsername(String username){
        this.username=username;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        checkedRoleList=new ArrayList<>();
        roleList=new ArrayList<>();
        sysPrivsList=new ArrayList<>();
        checkedTabPrivsList=new ArrayList<>();
        selectedColumns=new ArrayList<>();
        checkedSysPrivsList=new ArrayList<>();
        tabPrivsList=new ArrayList<>();
        selectedTabPrivsCol=new ArrayList<>();
        checkTabPrivsList=new ArrayList<>();
        columnPermission.setDisable(true);



        tabPrivsTable.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, t1) -> {
            if(t1.equals(0)){
                selectedPrivilege="UPDATE";
                columnPermission.setDisable(false);
            }
            else if(t1.equals(3)){
                selectedPrivilege="SELECT";
                columnPermission.setDisable(false);
            }
            else{
                columnPermission.setDisable(true);
            }
        });
    }


    public void closeHandler(ActionEvent event) {
        Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
    }

    public void applyHandler(ActionEvent event) {
        RoleService roleService=new RoleService();
        PrivsService privsService=new PrivsService();
        UserService userService=new UserService();
        if (!password.getText().isEmpty()){
            if(password.getText().trim().equals(confirmPassword.getText().trim())){
                userService.changePassword(username,password.getText().trim());
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Password does not match!");
                alert.showAndWait();
            }
        }


        for (String checkedRole:checkedRoleList) {
            roleService.revokeRole(checkedRole,username);
        }
        for (UserRoleRow row : roleTable.getItems()) {
            if(row.getGrant().isSelected()) {
                roleService.grantRole(row.getRoleName(), username, row.getWithAdmin().isSelected());
            }
        }
        checkedRoleList.clear();



        for (String checkSysPrivs:checkedSysPrivsList) {
            privsService.revokeSysPrivilege(checkSysPrivs,username);
        }
        for (UserSysPrivsRow row:sysPrivsTable.getItems()){
            if(row.getGrant().isSelected()) {
                privsService.grantSysPrivilege(row.getSysPrivsName(), username, row.getWithAdmin().isSelected());
            }
        }
        checkedSysPrivsList.clear();

        UserTabPrivsDAO userTabPrivsDAO =new UserTabPrivsDAO();
        ColumnPermissionDAO columnPermissionDAO=new ColumnPermissionDAO();

//        ObservableList<TabPrivs> a = tabPrivsDAO.getUserTabPrivs(username);
//        ObservableList<ColumnPermission>b=columnPermissionDAO.getUserColTabPrivs(username);
//        for (int i=0;i<b.size();i++){
//            for (int j=0;j<a.size();j++){
//                if(a.get(j).getPrivilege().equals(b.get(i).getPrivilege())&&a.get(j).getTableName().equals(b.get(i).getTableName())){
//                    b.remove(i);
//                }
//            }
//        }
//        a.forEach(index->{
//            if (index.getPrivilege().equals("UPDATE")) {
//
//                privsService.revokeUpdate(index.getTableName(), username);
//            } else if (index.getPrivilege().equals("SELECT")) {
//                privsService.revokeSelect(index.getTableName(), username);
//            } else if (index.getPrivilege().equals("INSERT")) {
//                privsService.revokeInsert(index.getTableName(), username);
//            } else if (index.getPrivilege().equals("DELETE")) {
//                privsService.revokeDelete(index.getTableName(), username);
//            }
//        });
//        b.forEach(index->{
//            if (index.getPrivilege().equals("UPDATE")) {
//                privsService.revokeUpdate(index.getTableName(), username);
//            } else if (index.getPrivilege().equals("SELECT")) {
//                privsService.revokeSelect(index.getTableName(), username);
//            } else if (index.getPrivilege().equals("INSERT")) {
//                privsService.revokeInsert(index.getTableName(), username);
//            } else if (index.getPrivilege().equals("DELETE")) {
//                privsService.revokeDelete(index.getTableName(), username);
//            }
//        });
        if(currTable!=null){
            for (UserTabPrivsRow row:tabPrivsTable.getItems()) {
                if(row.getGrant().isSelected()){
                    if(row.getTabPrivsName().equals("DELETE")){
                        privsService.grantDelete(selectedColumns,currTable,username,row.getWithGrant().isSelected());
                    }
                    else if(row.getTabPrivsName().equals("INSERT")) {
                        privsService.grantInsert(selectedColumns,currTable,username,row.getWithGrant().isSelected());
                    }
                    else if(row.getTabPrivsName().equals("UPDATE")){
                        privsService.grantUpdate(selectedColumns,currTable,username,row.getWithGrant().isSelected());
                    }
                    else if(row.getTabPrivsName().equals("SELECT")){
                        privsService.grantSelect(selectedColumns,currTable,username,row.getWithGrant().isSelected());
                    }
                }
                else{
                    UserTabPrivsDAO dao=new UserTabPrivsDAO();

                    if(row.getTabPrivsName().equals("DELETE")&&dao.isTabPrivsExist(currTable,username,"DELETE")){
                        privsService.revokeDelete(currTable,username);
                    }
                    else if(row.getTabPrivsName().equals("INSERT")&&dao.isTabPrivsExist(currTable,username,"INSERT")) {
                        privsService.revokeInsert(currTable,username);
                    }
                    else if(row.getTabPrivsName().equals("UPDATE")&&dao.isTabPrivsExist(currTable,username,"UPDATE")){
                        privsService.revokeUpdate(currTable,username);
                    }
                    else if(row.getTabPrivsName().equals("SELECT")&&dao.isTabPrivsExist(currTable,username,"SELECT")){
                        privsService.revokeSelect(currTable,username);
                    }
                }

            }
        }

        Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
    }


    public void roleTabHandler(Event event) {
//        System.out.println("role tab");
//        roleTable.
//        Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
//        Stage stage=(Stage) node.getScene().getWindow();
//
//        username = (String) stage.getUserData();

        UserRoleDAO userRoleDAO=new UserRoleDAO();
        RoleDAO roleDAO=new RoleDAO();
//        ObservableList<UserRole> allRoles=userRoleDAO.getAll();
        ObservableList<UserRole> checkedRoles=userRoleDAO.getUserRoles(username);
        ObservableList<Role> allRoles=roleDAO.getAll();

        allRoles.forEach(item -> {
            UserRoleRow roleRow = new UserRoleRow();
            roleRow.setRoleName(item.getRole());
            checkedRoles.forEach(i->{
                if(i.getGrantRole().equals(item.getRole())){
                    checkedRoleList.add(item.getRole());
                    roleRow.setGrant(true);
                    roleRow.setWithAdmin(i.getIsAdminOption());
                }

            });
            roleList.add(roleRow);
        });

        roleName.setCellValueFactory(new PropertyValueFactory<UserRoleRow,String>("roleName"));
        grantRole.setCellValueFactory(new PropertyValueFactory<UserRoleRow,String>("grant"));
        withAdminRole.setCellValueFactory(new PropertyValueFactory<UserRoleRow,String>("withAdmin"));

        roleTable.setItems(FXCollections.observableArrayList(roleList));
    }

    public void sysPrivsHandler(Event event) {

        UserSysPrivsDAO userSysPrivsDAO=new UserSysPrivsDAO();
//        ObservableList<UserRole> allPrivs=user.getAll();
        ObservableList<UserSysPrivs> checkPrivs=userSysPrivsDAO.getUserSysPriv(username);
        ObservableList<String> allPrivs=userSysPrivsDAO.getAll();

        allPrivs.forEach(item -> {
            UserSysPrivsRow userSysPrivsRow = new UserSysPrivsRow();
            userSysPrivsRow.setSysPrivsName(item);
            checkPrivs.forEach(i->{
                if(i.getPrivilege().equals(item)){
                    checkedSysPrivsList.add(item);
                    userSysPrivsRow.setGrant(true);
                    userSysPrivsRow.setWithAdmin(i.getAdminOption());
                }

            });
            sysPrivsList.add(userSysPrivsRow);
        });

        sysPrivsName.setCellValueFactory(new PropertyValueFactory<UserSysPrivsRow,String>("sysPrivsName"));
        grantSysPrivs.setCellValueFactory(new PropertyValueFactory<UserSysPrivsRow,String>("grant"));
        withAdminSysPrivs.setCellValueFactory(new PropertyValueFactory<UserSysPrivsRow,String>("withAdmin"));

        sysPrivsTable.setItems(FXCollections.observableArrayList(sysPrivsList));
    }

    public void tabPrivsHanlder(Event event) {
        DBTableDAO dbTableDAO=new DBTableDAO();
        tableComboBox.setItems(dbTableDAO.getAllTableByOwner("SYSTEM"));
    }

    public void comboBoxChange(ActionEvent event) {
        PrivsService privsService=new PrivsService();
        if(currTable!=null){
            for (UserTabPrivsRow row:tabPrivsTable.getItems()) {
                if(row.getGrant().isSelected()){
                    if(row.getTabPrivsName().equals("DELETE")){
                        privsService.grantDelete(selectedColumns,currTable,username,row.getWithGrant().isSelected());
                    }
                    else if(row.getTabPrivsName().equals("INSERT")) {
                        privsService.grantInsert(selectedColumns,currTable,username,row.getWithGrant().isSelected());
                    }
                    else if(row.getTabPrivsName().equals("UPDATE")){
                        privsService.grantUpdate(selectedColumns,currTable,username,row.getWithGrant().isSelected());
                    }
                    else if(row.getTabPrivsName().equals("SELECT")){
                        privsService.grantSelect(selectedColumns,currTable,username,row.getWithGrant().isSelected());
                    }
                }
                else{
                    UserTabPrivsDAO dao=new UserTabPrivsDAO();

                    if(row.getTabPrivsName().equals("DELETE")&&dao.isTabPrivsExist(currTable,username,"DELETE")){
                        privsService.revokeDelete(currTable,username);
                    }
                    else if(row.getTabPrivsName().equals("INSERT")&&dao.isTabPrivsExist(currTable,username,"INSERT")) {
                        privsService.revokeInsert(currTable,username);
                    }
                    else if(row.getTabPrivsName().equals("UPDATE")&&dao.isTabPrivsExist(currTable,username,"UPDATE")){
                        privsService.revokeUpdate(currTable,username);
                    }
                    else if(row.getTabPrivsName().equals("SELECT")&&dao.isTabPrivsExist(currTable,username,"SELECT")){
                        privsService.revokeSelect(currTable,username);
                    }
                }

            }
        }

        tabPrivsList.clear();
        currTable=tableComboBox.getValue();
        UserTabPrivsDAO userTabPrivsDAO =new UserTabPrivsDAO();
//        ObservableList<UserRole> allRoles=userRoleDAO.getAll();
        ObservableList<UserTabPrivs> checkedUserTabPrivs = userTabPrivsDAO.getUserTabPrivs(username);

        String[] listofTabPrivs={"UPDATE","INSERT","DELETE","SELECT"};
        for(int i=0;i<listofTabPrivs.length;i++){
            UserTabPrivsRow r=new UserTabPrivsRow();
            String privName=listofTabPrivs[i];
            r.setTabPrivsName(privName);
            checkedUserTabPrivs.forEach(index->{
                if(index.getTableName().equals(currTable)&&index.getPrivilege().equals(privName)){
                    r.setGrant(true);
                    r.setWithGrant(index.getGrantable());
                }
            });
            tabPrivsList.add(r);
        }

        tabPrivsName.setCellValueFactory(new PropertyValueFactory<UserTabPrivsRow,String>("tabPrivsName"));
        grantTabPrivs.setCellValueFactory(new PropertyValueFactory<UserTabPrivsRow,String>("grant"));
        withGrantTabPrivs.setCellValueFactory(new PropertyValueFactory<UserTabPrivsRow,String>("withGrant"));

        tabPrivsTable.setItems(FXCollections.observableArrayList(tabPrivsList));
    }

    public void columnPermissionHandler(ActionEvent event) throws IOException {

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/column-permission-view.fxml"));
        ColumnPermissionController controller=new ColumnPermissionController();
        controller.setValue(selectedPrivilege,username,currTable);
        loader.setController(controller);

        Parent root=loader.load();
        Scene scene = new Scene(root);
        window.setTitle("Column Permission");
        window.setScene(scene);
        window.showAndWait();
        selectedColumns=controller.getSelectedColumns();
        TabPrivsRow tabPrivsRow=new TabPrivsRow();
        tabPrivsRow.setTable(currTable);
        tabPrivsRow.setGrantee(username);
        tabPrivsRow.setPrivilege(selectedPrivilege);
        tabPrivsRow.setColumns(selectedColumns);
        selectedTabPrivsCol.add(tabPrivsRow);
    }
}
