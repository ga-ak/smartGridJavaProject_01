package project.view2.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import project.view2.PWDial;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserPage {

    FXMLLoader userInfoLoader;
    FXMLLoader userProjectLoader;
    FXMLLoader userSignLoader;
    FXMLLoader userNoticeLoader;
    StackPane stack_userInfo;
    StackPane stack_userProject;
    StackPane stack_userSign;
    StackPane stack_userNotice;

    UserProject projectController;

    @FXML private StackPane stack_user_inner;
    @FXML private StackPane stack_userPage;
    @FXML private Button btn_userInfo;
    @FXML private Button btn_userProject;
    @FXML private Button btn_userSign;
    @FXML private Button btn_userNotice;


    @FXML
    public void initialize() {
        try {
            userInfoLoader = new FXMLLoader(getClass().getResource("userInfoStack.fxml"));
            userProjectLoader = new FXMLLoader(getClass().getResource("userProjectStack.fxml"));
            userSignLoader = new FXMLLoader(getClass().getResource("userSignStack.fxml"));
            userNoticeLoader = new FXMLLoader(getClass().getResource("userNoticeStack.fxml"));

            stack_userInfo = userInfoLoader.load();
            stack_userProject = userProjectLoader.load();
            stack_userSign = userSignLoader.load();
            stack_userNotice = userNoticeLoader.load();

            projectController = userProjectLoader.getController();
            projectController.getUserInnerPage(stack_user_inner);

            btn_userInfo.setOnAction(event -> handle_btn_userInfo(event));
            btn_userProject.setOnAction(event -> handle_btn_userProject(event));
            btn_userSign.setOnAction(event -> handle_btn_userSign(event));
            btn_userNotice.setOnAction(event -> handle_btn_userNotice(event));

            stack_user_inner.getChildren().add(stack_userInfo);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handle_btn_userInfo (ActionEvent event) {
        stack_user_inner.getChildren().clear();
        stack_user_inner.getChildren().add(stack_userInfo);
    }
    public void handle_btn_userProject (ActionEvent event) {
        stack_user_inner.getChildren().clear();
        stack_user_inner.getChildren().add(stack_userProject);
    }
    public void handle_btn_userSign (ActionEvent event) {
        stack_user_inner.getChildren().clear();
        stack_user_inner.getChildren().add(stack_userSign);
    }
    public void handle_btn_userNotice (ActionEvent event) {
        stack_user_inner.getChildren().clear();
        stack_user_inner.getChildren().add(stack_userNotice);
    }
}
