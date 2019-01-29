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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Main_init implements Initializable{

    DAO dao;

    @FXML  StackPane stack_main;
    AnchorPane loginPage;
    Stage primaryStage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loginPage = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(loginPage);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }



//    public Main_init(DAO dao, Stage primaryStage) {
//        this.dao = dao;
//        this.primaryStage = primaryStage;
//    }
}
