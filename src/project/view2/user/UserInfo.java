package project.view2.user;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import project.dao.DAO;
import project.view2.DAOContainer;
import project.view2.LoginInfo;

import java.net.URL;
import java.time.LocalDate;
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

    @FXML private TableView<WorkRecord> tbv_userWorkTable;
    @FXML private TableColumn<WorkRecord, String> tc_userWorkDate;
    @FXML private TableColumn<WorkRecord, String> tc_userIn;
    @FXML private TableColumn<WorkRecord, String> tc_userOut;
    @FXML private DatePicker dp_userWork_start;
    @FXML private DatePicker dp_userWork_end;
    @FXML private Button btn_userWork_search;

    @FXML private TableView<ExtraPayRecord> tbv_userEPayTable;
    @FXML private TableColumn<ExtraPayRecord, String> tc_userEPId;
    @FXML private TableColumn<ExtraPayRecord, String> tc_userOvertime;
    @FXML private TableColumn<ExtraPayRecord, String> tc_userBonus;
    @FXML private TableColumn<ExtraPayRecord, String> tc_userCerNum;
    @FXML private TableColumn<ExtraPayRecord, String> tc_userSum;
    @FXML private TableColumn<ExtraPayRecord, String> tc_userEPDate;
    @FXML private DatePicker dp_userEPay_start;
    @FXML private DatePicker dp_userEPay_end;
    @FXML private Button btn_userEPay_search;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dao = DAOContainer.dao;
        ArrayList<String> col = new ArrayList<>();
        MyInfo();
        btn_userInfo_save.setOnAction(event -> handle_myInfo_update(event));
        ArrayList<ArrayList<String>> tempArr;

        col.add("START_TIME");
        col.add("END_TIME");

        tempArr = dao.select("WORKRECORDS", col, "EMPLOYEE_ID = " + LoginInfo.loggedID);

        ObservableList<WorkRecord> workList = FXCollections.observableArrayList();
        ObservableList<ExtraPayRecord> extraList = FXCollections.observableArrayList();

        for(int i = 0; i < tempArr.size(); i++){
            workList.add(new WorkRecord(tempArr.get(i)));
        }

        btn_userInfo_save.setOnAction(event -> handle_myInfo_update(event));

        tc_userIn.setCellValueFactory(cellData -> cellData.getValue().gettc_userIn());
        tc_userOut.setCellValueFactory(cellData -> cellData.getValue().gettc_userOut());
        tbv_userWorkTable.setItems(workList);

        ArrayList<String> extraCol = new ArrayList<>();
        ArrayList<ArrayList<String>> tempArr2;
        extraCol.add("extrapay_id");
        extraCol.add("overtime");
        extraCol.add("bonus");
        extraCol.add("certificate_id");
        extraCol.add("sum");
        extraCol.add("extrapay_date");

        tempArr2 = dao.select("extrapay", extraCol, "EMPLOYEE_ID = " + LoginInfo.loggedID);

        for(int i = 0; i < tempArr2.size(); i++){
            extraList.add(new ExtraPayRecord(tempArr2.get(i)));
        }

        tc_userEPId.setCellValueFactory(cellData -> cellData.getValue().gettc_userEPId());
        tc_userOvertime.setCellValueFactory(cellData -> cellData.getValue().gettc_userOvertime());
        tc_userBonus.setCellValueFactory(cellData -> cellData.getValue().gettc_userBonus());
        tc_userCerNum.setCellValueFactory(cellData -> cellData.getValue().gettc_userCerNum());
        tc_userSum.setCellValueFactory(cellData -> cellData.getValue().gettc_userSum());
        tc_userEPDate.setCellValueFactory(cellData -> cellData.getValue().gettc_userEPDate());
        tbv_userEPayTable.setItems(extraList);

        MyInfo();
        btn_userWork_search.setOnAction(event -> handle_myWorkDate_Search(event));
        btn_userEPay_search.setOnAction(event -> handle_myExtra_Search(event));

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

    public void handle_myWorkDate_Search(ActionEvent event){
        ArrayList<String> col = new ArrayList<>();

        LocalDate stDate = dp_userWork_start.getValue();
        LocalDate enDate = dp_userWork_end.getValue();
        LocalDate arrStart = null;
        LocalDate arrEnd = null;

        col.add("start_time");
        col.add("end_time");
        ArrayList<ArrayList<String>> temp = dao.select("WORKRECORDS", col, "EMPLOYEE_ID = " + LoginInfo.loggedID);
        ArrayList<ArrayList<String>> temp2 = new ArrayList<>();
        ArrayList<String> resStr = new ArrayList<>();
        ArrayList<Integer> cnt = new ArrayList<>();

        LocalDateStringConverter conv = new LocalDateStringConverter();

        //System.out.println(stDate.until(enDate).getDays());

        for(int i = 0; i < temp.size(); i++){
            arrStart = conv.fromString(temp.get(i).get(0).substring(0, 10));
            arrEnd = conv.fromString(temp.get(i).get(1).substring(0, 10));

            if(stDate.minusDays(1).isBefore(arrStart) && enDate.plusDays(2).isAfter(arrEnd)){
                System.out.println(arrStart);
                cnt.add(i);
            }
        }

        if(cnt.size() == 0){

        } else {
            for(int i = cnt.get(0); i < cnt.get(cnt.size()-1); i++){
                temp2.add(temp.get(i));
            }

            ObservableList<WorkRecord> workList = FXCollections.observableArrayList();
            System.out.println(temp2.size());
            for(int i = 0; i < temp2.size(); i++){
                workList.add(new WorkRecord(temp2.get(i)));
                //System.out.println(temp2.get(i));

                tc_userIn.setCellValueFactory(cellData -> cellData.getValue().gettc_userIn());
                tc_userOut.setCellValueFactory(cellData -> cellData.getValue().gettc_userOut());
            }
            tbv_userWorkTable.setItems(workList);
        }
    }

    public void handle_myExtra_Search(ActionEvent event){
        LocalDateStringConverter conv = new LocalDateStringConverter();
        ArrayList<String> col = new ArrayList<>();

        col.add("EXTRAPAY_ID");
        col.add("OVERTIME");
        col.add("BONUS");
        col.add("CERTIFICATE_ID");
        col.add("SUM");
        col.add("EXTRAPAY_DATE");

        ArrayList<ArrayList<String>> temp = dao.select("extrapay", col, "employee_id = " + LoginInfo.loggedID);
        ArrayList<ArrayList<String>> temp2 = new ArrayList<>();
        LocalDate stDate = dp_userEPay_start.getValue();
        LocalDate enDate = dp_userEPay_end.getValue();
        LocalDate extraDate;

        ArrayList<Integer> cnt = new ArrayList<>();
        System.out.println(temp.size());
        for(int i = 0; i < temp.size(); i++){
            extraDate = conv.fromString(temp.get(i).get(5).substring(0, 10));
            System.out.println(extraDate);
            System.out.println(i);
            if(stDate.minusDays(1).isBefore(extraDate) && enDate.plusDays(2).isAfter(extraDate)){
                System.out.println(extraDate);
                cnt.add(i);
            }
        }

        if(cnt.size() == 0){

        } else {
            for(int i = cnt.get(0); i < cnt.get(cnt.size()-1); i++){
                temp2.add(temp.get(i));
            }

            ObservableList<ExtraPayRecord> extraList = FXCollections.observableArrayList();
            System.out.println(temp2.size());
            for(int i = 0; i < temp2.size(); i++){
                extraList.add(new ExtraPayRecord(temp2.get(i)));
                //System.out.println(temp2.get(i));

                tc_userIn.setCellValueFactory(cellData -> cellData.getValue().gettc_userIn());
                tc_userOut.setCellValueFactory(cellData -> cellData.getValue().gettc_userOut());
            }
            tbv_userEPayTable.setItems(extraList);
        }

    }

}
