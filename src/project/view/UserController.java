package project.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import project.dao.Controller;
import project.dao.Project_DAO;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    private MyController controller;
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
        controller = new MyController();
        con = new Controller();
        dao = new Project_DAO(con);
        btn_side_myInfo.setOnAction(event -> handle_btn_side_myInfo(event));
        btn_side_project.setOnAction(event -> handle_btn_side_project(event));
        btn_side_bbs.setOnAction(event -> handle_btn_side_bbs(event));
        btn_side_sign.setOnAction(event -> handle_btn_side_sign(event));
        btn_side_announce.setOnAction(event -> handle_btn_side_announce(event));
        try {
            myInfoPane = (StackPane) FXMLLoader.load(getClass().getResource("myInfoPage.fxml"));
            projectPane = (StackPane) FXMLLoader.load(getClass().getResource("projectPage.fxml"));
            bbsPane = null;
            signPane = (StackPane) FXMLLoader.load(getClass().getResource("signPage.fxml"));
            announcePane = (StackPane) FXMLLoader.load(getClass().getResource("announcePage.fxml"));
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

        // projectsHBox에 fxml 로딩해옴
        try {
            controller.hbox_project_inner = FXMLLoader.load(getClass().getResource("projectPage_projects.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String> columns1 = new ArrayList<>();
        ArrayList<String> columns2 = new ArrayList<>();

        columns1.add("PROJECT_ID");
        columns1.add("EMPLOYEE_ID");

        columns2.add("PROJECT_ID");
        columns2.add("PROJECT_NAME");
        columns2.add("EMPLOYEE_ID");
        columns2.add("START_DATE");
        columns2.add("START_DATE");

        String limit1 = "EMPLOYEE_ID = " + loggedId;
        // 맡은 프로젝트가 2개이상인경우 배열로 선언해줘야 한다
        String limit2 = "PROJECT_ID = ";

        ArrayList<ArrayList<String>> projectWorksSelected = dao.select("PROJECT_WORKS", columns1, limit1);

        for (int i = 0; i < projectWorksSelected.size(); i++) {
            String tempLimit = limit2 + projectWorksSelected.get(i).get(0);
            ArrayList<ArrayList<String>> projectSelected = dao.select("PROJECT",columns2,tempLimit);

            for (int j = 0; j < projectSelected.size(); j++) {
                HBox hBox = new HBox();
                hBox.getChildren().add(new Button());
                hBox.getChildren().add(new Button());
                System.out.println("여기까진...");
                controller.vbox_project.getChildren().add(hBox);
            }
        }

        //ArrayList<ArrayList<String>> projectSelected = dao.select("PROJECT",columns2,limit2);



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
