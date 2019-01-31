package project.view2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import project.dao.Controller;
import project.dao.DAO;
import project.view2.DAOContainer;
import project.view2.user.UserController;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        DAO dao = DAOContainer.dao;
        System.out.println("debug1");
        FXMLLoader mainloader = new FXMLLoader(getClass().getResource("main.fxml"));
        System.out.println("debug2");
        Main_init mainController = new Main_init();
        mainController.setPrimaryStage(primaryStage);
        LogInController logInController = new LogInController();
        PWDial pd = new PWDial();
        System.out.println("debug3");

        Parent root = mainloader.load();

        System.out.println("debug4");
        Scene scene = new Scene(root);
        int loginState = mainController.loginState;

        System.out.println("debug5");

        primaryStage.setTitle("삼종 치즈 토핑 - 프로젝트 중심 인사 관리 프로그램");
        primaryStage.setScene(scene);
        primaryStage.show();
        if (loginState == 2) {
            FXMLLoader userLoader = new FXMLLoader(getClass().getResource("user/userPage.fxml"));
            StackPane testPane = userLoader.load();
            UserController userController = userLoader.getController();

        }

        System.out.println();

    }
}
