package project.view2.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminPage implements Initializable {

    FXMLLoader adminInfoLoader;
    FXMLLoader adminProjectLoader;
    FXMLLoader adminNoticeLoader;

    StackPane stack_adminInfo;
    StackPane stack_adminProject;
    StackPane stack_adminNotice;


    @FXML private StackPane stack_adminPage;
    @FXML private StackPane stack_admin_inner;
    @FXML private Button btn_adminInfo;
    @FXML private Button btn_adminProject;
    @FXML private Button btn_adminNotice;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adminInfoLoader = new FXMLLoader(getClass().getResource("adminInfoStack.fxml"));
        adminProjectLoader = new FXMLLoader(getClass().getResource("adminProjectStack.fxml"));
        adminNoticeLoader = new FXMLLoader(getClass().getResource("adminNoticeStack.fxml"));

        try {
            stack_adminInfo = adminInfoLoader.load();
            stack_adminProject = adminProjectLoader.load();
            stack_adminNotice = adminNoticeLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        btn_adminInfo.setOnAction(event -> handle_btn_adminInfo(event));
        btn_adminProject.setOnAction(event -> handle_btn_adminProject(event));
        btn_adminNotice.setOnAction(event -> handle_btn_adminNotice(event));

        stack_admin_inner.getChildren().add(stack_adminInfo);

    }

    public void handle_btn_adminInfo (ActionEvent event) {
        stack_admin_inner.getChildren().clear();
        stack_admin_inner.getChildren().add(stack_adminInfo);
    }
    public void handle_btn_adminProject (ActionEvent event) {
        stack_admin_inner.getChildren().clear();
        stack_admin_inner.getChildren().add(stack_adminProject);
    }
    public void handle_btn_adminNotice (ActionEvent event) {
        stack_admin_inner.getChildren().clear();
        stack_admin_inner.getChildren().add(stack_adminNotice);
    }
}
