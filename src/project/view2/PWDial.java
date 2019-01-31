package project.view2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import project.controller.CreatePassword;
import project.controller.SendMessage;
import project.dao.DAO;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PWDial implements Initializable {

    DAO dao = DAOContainer.dao;
    SendMessage sm = new SendMessage();
    ArrayList<String> columns = new ArrayList<>();

    @FXML private TextField txf_pwdUserID;
    @FXML private TextField txf_pwdSSN;
    @FXML private TextField txf_pwdCon;
    @FXML private Button btn_pwdSend;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columns.add("SSN");
        columns.add("CONTACT");
        columns.add("EMPLOYEE_NAME");

        btn_pwdSend.setOnAction(event -> handle_btn_pwdSen(event));

    }

    public void handle_btn_pwdSen(ActionEvent event) {
        String inputId = txf_pwdUserID.getText().trim();
        String inputSsn = txf_pwdSSN.getText().trim();
        String inputCon = txf_pwdCon.getText().trim();

        String tempSsn = null;
        String tempCon = null;
        String tempName = null;

        String limit = "employee_id = "+inputId;

        ArrayList<ArrayList<String>> selectedValue = dao.select("employees", columns, limit);
        String formatedtext = null;
        if (selectedValue.size() >= 1) {
            tempSsn = selectedValue.get(0).get(0).substring(6, 13);
            tempCon = selectedValue.get(0).get(1);
            tempName = selectedValue.get(0).get(2);


            String tempStr = "[%s]님 안녕하세요 변경된 비밀번호는 [%s]입니다\n비밀번호를 변경해 주세요!";
            CreatePassword cp = new CreatePassword();

            formatedtext = String.format(tempStr, tempName, cp.createPW());


        }
        if (inputSsn.equals(tempSsn) && inputCon.equals(tempCon)) {
            sm.send(tempCon, tempName, formatedtext);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("비밀번호 변경");

            alert.setHeaderText("비밀번호 변경");
            alert.setContentText("변경된 비밀번호가 저장된 연락처로 전송되었습니다\n[내 정보 보기]에서 비밀번호를 변경해주세요!");
            Stage thisStage = (Stage)btn_pwdSend.getScene().getWindow();
            alert.showAndWait();
            thisStage.close();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("비밀번호 변경");
            alert.setHeaderText("비밀번호 변경");
            alert.setContentText("입력한 정보가 올바르지 않습니다!");

            alert.showAndWait();
        }


    }

}
