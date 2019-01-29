package project.view2.user;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import project.dao.Project_DAO;

import java.net.URL;
import java.util.ResourceBundle;

public class UserInit implements Initializable {

    Project_DAO dao

    @FXML Button btn_userInfo;
    @FXML Button btn_userProject;
    @FXML Button btn_userSign;
    @FXML Button btn_userNotice;
    @FXML
    StackPane stackPane_user;
    @FXML
    StackPane stackPane_userPage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {



    }
}
