package project.view2.user;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import project.dao.DAO;

public class UserController {

    DAO dao;

    @FXML private StackPane stack_user_inner;
    @FXML private StackPane stack_userPage;
    @FXML private Button btn_userInfo;
    @FXML private Button btn_userProject;
    @FXML private Button btn_userSign;
    @FXML private Button btn_userNotice;
    @FXML private Label lbl_userName;
    @FXML private Label lbl_userId;
    @FXML private Label lbl_userDept;
    @FXML private Label lbl_userJG;
    @FXML private Label lbl_userHD;
    @FXML private TextField txf_userADD;
    @FXML private TextField txf_userCon;
    @FXML private Button btn_userPW_change;
    @FXML private Button btn_userInfo_save;
    @FXML private DatePicker dp_userWork_start;
    @FXML private DatePicker dp_userWork_end;
    @FXML private Button btn_userWork_search;
    @FXML private Button btn_userWork_excel;
    @FXML private TableView<String> tbv_userWorkTable;
    @FXML private DatePicker dp_userEPay_start;
    @FXML private DatePicker dp_userEPay_end;
    @FXML private Button btn_userEPay_search;
    @FXML private Button btn_userEPay_excel;
    @FXML private TableView<String> tbv_userEPayTable;

    @FXML private StackPane stack_userProject;
    @FXML private StackPane stack_inner_project;
    @FXML private VBox vbox_inner_project;
    @FXML private Button btn_inner_back;
    @FXML private Button btn_inner_change;
    @FXML private HBox hbox_projects;
    @FXML private Label lbl_projectName;
    @FXML private Label lbl_projectLeader;
    @FXML private ProgressBar pgb_projectPG;
    @FXML private HBox hbox_members;
    @FXML private Label lbl_memberName;
    @FXML private Label lbl_memberBigSub;
    @FXML private ProgressBar pgb_memberPG;
    @FXML private HBox hbox_works;
    @FXML private Label lbl_workBigSub;
    @FXML private Label lbl_workSmallSub;
    @FXML private Label lbl_workSmallExp;
    @FXML private Label lbl_workSmallPeriod;
    @FXML private Label lbl_workState;
    @FXML private Button btn_work_submit;
    @FXML private StackPane stack_userSign;
    @FXML private ChoiceBox<String> cbx_signMaker;
    @FXML private ChoiceBox<String> cbx_signKind;
    @FXML private ChoiceBox<String> cbx_formDown;
    @FXML private Button btn_formDown;
    @FXML private TextArea txa_signCon;
    @FXML private Button btn_sign_demand;


}
