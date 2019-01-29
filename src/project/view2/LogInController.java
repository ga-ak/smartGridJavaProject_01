package project.view2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import project.dao.DAO;
import project.view2.DAOContainer;

import java.io.IOException;
import java.util.ArrayList;

public class LogInController {

    DAO dao;
    ArrayList<String> columns;
    FXMLLoader fxmlLoader;


    @FXML private TextField txf_loginID;
    @FXML private TextField txf_loginPW;
    @FXML private Button btn_login;
    @FXML private CheckBox chb_loginADM;
    @FXML private Hyperlink hpl_loginPW_find;



    @FXML
    public void initialize() {
        dao = DAOContainer.dao;
        columns = new ArrayList<>();
        columns.add("password");
        columns.add("department_id");
        btn_login.setOnAction(event -> handle_btn_login(event));
        hpl_loginPW_find.setOnAction(event -> handle_hpl_loginPW_find(event));
    }

    public void handle_btn_login(ActionEvent event) {

        boolean isADM = chb_loginADM.isSelected();

        String loggedID = txf_loginID.getText();
        System.out.println("입력한 아이디 : "+loggedID);
        String loggedPW = txf_loginPW.getText();
        System.out.println("입력한 비밀번호 : "+loggedPW);
        String limit = "employee_id = "+loggedID;


        // 사원번호는 중복이 없기 때문에 .get(0).get(0)으로 비밀번호를 가져올 수 있다
        ArrayList<ArrayList<String>> tempResult = dao.select("employees", columns, limit);
        String selectedPW = tempResult.get(0).get(0);
        String selectedDept = tempResult.get(0).get(1);
        if (isADM && loggedPW.equals(selectedPW) && selectedDept.equals("150")) {
            System.out.println("관리자로 로그인");
        } else if (!isADM && loggedPW.equals(selectedPW)) {
            System.out.println("사용자로 로그인");
        } else {
            System.out.println("로그인 실패..");
        }
    }

    public void handle_hpl_loginPW_find(ActionEvent event) {
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("PWDial.fxml"));
            AnchorPane anchorFindPW = fxmlLoader.load();
            PWDial controller = fxmlLoader.getController();
            Stage parent = (Stage)btn_login.getScene().getWindow();
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(parent);
            Scene scene = new Scene(anchorFindPW);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
