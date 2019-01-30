package project.view2.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import project.dao.DAO;
import project.view2.DAOContainer;
import project.view2.LoginInfo;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserProject implements Initializable {

    FXMLLoader innerLoader;
    FXMLLoader projectLoader;
    FXMLLoader memberLoader;
    FXMLLoader workLoader;

    StackPane stack_project;
    StackPane stack_inner;
    StackPane stack_user_inner;
    HBox hbox_member;
    HBox hbox_project;
    HBox hbox_work;

    DAO dao = DAOContainer.dao;
    String loggedID = LoginInfo.loggedID;
    ArrayList<HBox> hBoxArrayList;

    @FXML private VBox vbox_project;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        makeProjectPage(getProjects());
        for (int i = 0; i < hBoxArrayList.size(); i++) {
            final int index = i;
            hBoxArrayList.get(i).setOnMouseClicked(event -> handle_hbox_member(index));
        }

    }

    public ArrayList<HBox> getProjects() {
        ArrayList<String> column1 = new ArrayList<>();
        ArrayList<String> column2 = new ArrayList<>();
        ArrayList<String> column3 = new ArrayList<>();
        ArrayList<String> column4 = new ArrayList<>();

        column1.add("distinct project_id");
        String limit1 = "employee_id = "+ loggedID;
        column2.add("project_name");
        column2.add("employee_id");
        column2.add("start_date");
        column2.add("end_date");
        String limit2 = "project_id = ";
        column3.add("employee_name");
        column4.add("distinct employee_id");

        ArrayList<ArrayList<String>> selected1 = dao.select("project_works", column1, limit1);
        hBoxArrayList = new ArrayList<>();

        for (int i = 0; i < selected1.size(); i++) {

            String limitValue = selected1.get(i).get(0);
            String tempLimit = limit2+limitValue;
            ArrayList<ArrayList<String>> tempSelected = dao.select("project", column2, tempLimit);
            String leaderID = tempSelected.get(0).get(1);

            String leaderName = dao.select("employees",column3,"employee_id = "+leaderID).get(0).get(0);
            int projectNumber = dao.select("project_works", column4, "project_id = "+ limitValue).size();
            String projectName = tempSelected.get(0).get(0);
            String startDate = tempSelected.get(0).get(2);
            String endDate = tempSelected.get(0).get(3);

            HBox tempProject = null;
            try {
                FXMLLoader tempLoader= new FXMLLoader(getClass().getResource("userProject_projects_H.fxml"));
                tempProject = tempLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Label tempPJName = (Label)tempProject.getChildren().get(0);
            Label tempPJLeaderName = (Label)tempProject.getChildren().get(1);
            Label tempPJPeriod = (Label) tempProject.getChildren().get(3);
            Label tempPJNumber = (Label) tempProject.getChildren().get(4);
            ProgressBar tempPJbar = new ProgressBar();

            tempPJName.setText(projectName);
            tempPJLeaderName.setText(leaderName);
            tempPJPeriod.setText(startDate + " ~ " + endDate);
            tempPJNumber.setText(Integer.toString(projectNumber));

            hBoxArrayList.add(tempProject);
        }
        return hBoxArrayList;
    }

    public void makeProjectPage(ArrayList<HBox> hBoxArrayList) {
        for (int i = 0; i < hBoxArrayList.size(); i++) {
            vbox_project.getChildren().add(hBoxArrayList.get(i));
        }
    }

    public void handle_hbox_member(int i) {
        System.out.println(i+"hbox clicked!");
        FXMLLoader innerLoader = new FXMLLoader(getClass().getResource("userProject_inner_Stack.fxml"));
        try {
            stack_inner = innerLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stack_user_inner.getChildren().clear();
        stack_user_inner.getChildren().add(stack_inner);
    }

    public void getUserInnerPage(StackPane stack_user_inner) {
        this.stack_user_inner = stack_user_inner;
    }
}
