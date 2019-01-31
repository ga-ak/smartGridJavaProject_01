package project.view2.admin;

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
import project.view2.LogInController;
import project.view2.user.UserPage;

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

    StackPane stack_main;
    AnchorPane loginPage;
    Stage primaryStage;
    int loginState;



    @FXML private StackPane stack_adminPage;
    @FXML private StackPane stack_admin_inner;
    @FXML private Button btn_adminInfo;
    @FXML private Button btn_adminProject;
    @FXML private Button btn_adminNotice;
    @FXML private Button btn_adminLogout;


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
        btn_adminLogout.setOnAction(event -> handle_btn_adminLogout(event));

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

    public void handle_btn_adminLogout(ActionEvent event) {
        stack_main.getChildren().clear();

        try {
            FXMLLoader loginLoader= new FXMLLoader(getClass().getResource("../loginPage.fxml"));
            loginPage = loginLoader.load();
            LogInController logInController = loginLoader.getController();
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(loginPage);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
            loginState = logInController.getLoginState();
            if (loginState == 1) {
                System.out.println("관리자로 로그인!");
                FXMLLoader userLoader = new FXMLLoader(getClass().getResource("adminPage.fxml"));
                StackPane tempPane = userLoader.load();
                AdminPage tempController = userLoader.getController();
                tempController.setPrimaryStage(primaryStage);
                tempController.setMainStack(stack_main);
                tempController.setLoginPage(loginPage);
                stack_main.getChildren().add(tempPane);

            } else if (loginState == 2) {
                System.out.println("사용자로 로그인!");
                FXMLLoader userLoader = new FXMLLoader(getClass().getResource("../user/userPage.fxml"));
                StackPane tempPane = userLoader.load();
                UserPage tempController = userLoader.getController();

                // userpage 콘트롤러에 상위 변수 주입
                tempController.setPrimaryStage(primaryStage);
                tempController.setMainStack(stack_main);
                tempController.setLoginPage(loginPage);
                stack_main.getChildren().add(tempPane);

            } else {
                System.out.println("로그인 취소...");
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMainStack(StackPane stack_main) {
        this.stack_main = stack_main;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    public void setLoginPage(AnchorPane loginPage) {
        this.loginPage = loginPage;
    }

}
