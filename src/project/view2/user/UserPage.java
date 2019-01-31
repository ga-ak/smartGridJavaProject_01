package project.view2.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import project.view2.LogInController;
import project.view2.admin.AdminPage;

import java.io.IOException;

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

    StackPane stack_main;
    AnchorPane loginPage;
    Stage primaryStage;
    //LogInController logInController;
    int loginState;

    @FXML private StackPane stack_user_inner;
    @FXML private StackPane stack_userPage;
    @FXML private Button btn_userInfo;
    @FXML private Button btn_userProject;
    @FXML private Button btn_userSign;
    @FXML private Button btn_userNotice;
    @FXML private Button btn_userLogout;


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
            UserProject.projectStacks.add(stack_userProject);
            projectController = userProjectLoader.getController();
            projectController.setUserInnerPage(stack_user_inner);

            btn_userInfo.setOnAction(event -> handle_btn_userInfo(event));
            btn_userProject.setOnAction(event -> handle_btn_userProject(event));
            btn_userSign.setOnAction(event -> handle_btn_userSign(event));
            btn_userNotice.setOnAction(event -> handle_btn_userNotice(event));
            btn_userLogout.setOnAction(event -> handle_btn_userLogout(event));

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

    public void handle_btn_userLogout(ActionEvent event) {
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
                FXMLLoader userLoader = new FXMLLoader(getClass().getResource("../admin/adminPage.fxml"));
                StackPane tempPane = userLoader.load();
                AdminPage tempController = userLoader.getController();
                stack_main.getChildren().add(tempPane);
                tempController.setPrimaryStage(primaryStage);
                tempController.setMainStack(stack_main);
                tempController.setLoginPage(loginPage);

            } else if (loginState == 2) {
                System.out.println("사용자로 로그인!");
                FXMLLoader userLoader = new FXMLLoader(getClass().getResource("userPage.fxml"));
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

    public void setLoginPage(AnchorPane loginPage) {
        this.loginPage = loginPage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }


}
