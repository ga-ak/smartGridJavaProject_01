package project.view2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import project.dao.Controller;
import project.dao.DAO;
import project.view2.user.UserController;
import project.view2.user.UserPage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Main_init implements Initializable{

    DAO dao;

    @FXML StackPane stack_main;
    AnchorPane loginPage;
    Stage primaryStage;
    public int loginState;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            FXMLLoader loginLoader= new FXMLLoader(getClass().getResource("loginPage.fxml"));
            loginPage = loginLoader.load();
            LogInController logInController = loginLoader.getController();
            logInController.setPrimaryStage(primaryStage);
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(loginPage);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
            loginState = logInController.loginState;

            if (loginState == 1) {
                System.out.println("관리자로 로그인!");
                FXMLLoader userLoader = new FXMLLoader(getClass().getResource("admin/adminPage.fxml"));
                StackPane tempPane = userLoader.load();
                stack_main.getChildren().add(tempPane);

            } else if (loginState == 2) {
                System.out.println("사용자로 로그인!");
                FXMLLoader userLoader = new FXMLLoader(getClass().getResource("user/userPage.fxml"));
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

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }



}
