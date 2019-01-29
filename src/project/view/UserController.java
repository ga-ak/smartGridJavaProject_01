package project.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import project.dao.Controller;
import project.dao.Project_DAO;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    //private MyController controller;
    private Controller con;
    private Project_DAO dao;
    private String loggedId = "1000";
    @FXML private StackPane user_inner_stack;
    @FXML private Button btn_side_myInfo;
    @FXML private Button btn_side_project;
    @FXML private Button btn_side_bbs;
    @FXML private Button btn_side_sign;
    @FXML private Button btn_side_announce;
    private StackPane myInfoPane;
    private StackPane projectPane;
    private StackPane bbsPane;
    private StackPane signPane;
    private StackPane announcePane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //con = new Controller();
        dao = new Project_DAO(new Controller());

        //controller = new MyController(dao);

        btn_side_myInfo.setOnAction(event -> handle_btn_side_myInfo(event));
        btn_side_project.setOnAction(event -> handle_btn_side_project(event));
        btn_side_bbs.setOnAction(event -> handle_btn_side_bbs(event));
        btn_side_sign.setOnAction(event -> handle_btn_side_sign(event));
        btn_side_announce.setOnAction(event -> handle_btn_side_announce(event));

        try {

            myInfoPane = FXMLLoader.load(getClass().getResource("myInfoPage.fxml"));
            projectPane = FXMLLoader.load(getClass().getResource("projectPage.fxml"));
            bbsPane = null;
            signPane = FXMLLoader.load(getClass().getResource("signPage.fxml"));
            announcePane = FXMLLoader.load(getClass().getResource("announcePage.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        user_inner_stack.getChildren().add(myInfoPane);


    }

    public void handle_btn_side_myInfo(ActionEvent event) {
        user_inner_stack.getChildren().clear();
        user_inner_stack.getChildren().add(myInfoPane);
    }

    public void handle_btn_side_project(ActionEvent event) {

        //controller.refreshProject();

        user_inner_stack.getChildren().clear();
        user_inner_stack.getChildren().add(projectPane);
    }

    public void handle_btn_side_bbs(ActionEvent event) {

    }

    public void handle_btn_side_sign(ActionEvent event) {
        user_inner_stack.getChildren().clear();
        user_inner_stack.getChildren().add(signPane);
    }

    public void handle_btn_side_announce(ActionEvent event) {
        user_inner_stack.getChildren().clear();
        user_inner_stack.getChildren().add(announcePane);
    }


}
