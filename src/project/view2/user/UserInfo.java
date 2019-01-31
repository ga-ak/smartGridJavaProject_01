package project.view2.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import project.dao.DAO;
import project.view2.DAOContainer;
import project.view2.LoginInfo;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserInfo implements Initializable {
    DAO dao;

    @FXML private Label lbl_userName;
    @FXML private Label lbl_userId;
    @FXML private Label lbl_userDept;
    @FXML private Label lbl_userJG;
    @FXML private Label lbl_userHD;
    @FXML private TextField txf_userADD;
    @FXML private TextField txf_userCon;
    @FXML private Button btn_userPW_change;
    @FXML private Button btn_userInfo_save;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dao = DAOContainer.dao;
        MyInfo();
        btn_userInfo_save.setOnAction(event -> handle_myInfo_update(event));
    }

    public void MyInfo(){
        ArrayList<String> columns = new ArrayList<>();
        columns.add("EMPLOYEE_NAME");
        columns.add("E.EMPLOYEE_ID");
        columns.add("DEPARTMENT_NAME");
        columns.add("JOBGRADE_NAME");
        columns.add("HIRE_DATE");
        columns.add("ADDRESS");
        columns.add("CONTACT");
        ArrayList<ArrayList<String>> tempArr = dao.select("EMPLOYEES E, DEPARTMENTS D, JOBGRADES J", columns, "E.DEPARTMENT_ID = D.DEPARTMENT_ID AND E.JOBGRADE_ID = J.JOBGRADE_ID AND E.EMPLOYEE_ID = " + LoginInfo.loggedID);

        LoginInfo.loggedDeptName = tempArr.get(0).get(2);
        LoginInfo.loggedJGName = tempArr.get(0).get(3);

        lbl_userName.setText(tempArr.get(0).get(0));
        lbl_userId.setText(tempArr.get(0).get(1));
        lbl_userDept.setText(tempArr.get(0).get(2));
        lbl_userJG.setText(tempArr.get(0).get(3));
        lbl_userHD.setText(tempArr.get(0).get(4));
        txf_userADD.setText(tempArr.get(0).get(5));
        txf_userCon.setText(tempArr.get(0).get(6));
    }

    public void handle_myInfo_update(ActionEvent event){
        ArrayList<String> columns = new ArrayList<>();

        dao.update("EMPLOYEES", "ADDRESS", txf_userADD.getText(), "EMPLOYEE_ID = " + LoginInfo.loggedID);
        dao.update("EMPLOYEES", "CONTACT", txf_userCon.getText(), "EMPLOYEE_ID = " + LoginInfo.loggedID);

    }


}
