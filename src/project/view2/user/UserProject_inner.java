package project.view2.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import project.dao.DAO;
import project.view2.DAOContainer;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserProject_inner implements Initializable {
    StackPane stack_user_inner;
    StackPane stack_inner;
    String projectID;
    DAO dao = DAOContainer.dao;
    ArrayList<HBox> hBoxArrayList;
    ArrayList<ArrayList<String>> memberSelected;

    @FXML VBox vbox_inner_project;
    @FXML Button btn_inner_back;
    @FXML Button btn_inner_change;


    public UserProject_inner(String projectID) {
        this.projectID = projectID;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        makeMemberPage(getMembers());
        for (int i = 0; i < hBoxArrayList.size(); i++) {
            final int index = i;
            hBoxArrayList.get(i).setOnMouseClicked(event -> handle_hbox_work(index));
        }
        btn_inner_back.setOnAction(event -> handle_btn_inner_back(event));
    }

    public ArrayList<HBox> getMembers() {
        ArrayList<String> columns = new ArrayList<>();
        columns.add("distinct employee_id");
        columns.add("work_name");
        columns.add("start_date");
        columns.add("end_date");
        String limit = "project_id = " + projectID;
        memberSelected = dao.select("project_works", columns, limit);

        hBoxArrayList = new ArrayList<>();

        for (int i = 0; i < memberSelected.size(); i++) {
            String memberID = memberSelected.get(i).get(0);
            ArrayList<String> column1 = new ArrayList<>();
            column1.add("employee_name");
            String memberName = dao.select("employees", column1, "employee_id = " + memberID).get(0).get(0);
            String workName = memberSelected.get(i).get(1);
            String startDate = memberSelected.get(i).get(2);
            String endDate = memberSelected.get(i).get(3);

            HBox tempMember = null;
            try {
                FXMLLoader tempLoader = new FXMLLoader(getClass().getResource("userProject_members_H.fxml"));
                tempMember = tempLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Label tempMemName = (Label) tempMember.getChildren().get(0);
            Label tempMSubName = (Label) tempMember.getChildren().get(1);
            Label tempMPeriod = (Label) tempMember.getChildren().get(3);

            tempMemName.setText(memberName);
            tempMSubName.setText(workName);
            tempMPeriod.setText(startDate + " ~ " + endDate);

            hBoxArrayList.add(tempMember);
        }
        return hBoxArrayList;
    }

    public void makeMemberPage(ArrayList<HBox> hBoxArrayList) {
        for (int i = 0; i < hBoxArrayList.size(); i++) {
            vbox_inner_project.getChildren().add(hBoxArrayList.get(i));
        }
    }

    public void handle_btn_inner_back(ActionEvent event) {
        UserProject.focus--;
        stack_user_inner.getChildren().clear();
        stack_user_inner.getChildren().add(UserProject.projectStacks.get(UserProject.focus));

    }

    public void handle_hbox_work(int i) {
        System.out.println(i+"hbox clicked!");
        FXMLLoader innerLoader = new FXMLLoader(getClass().getResource("userProject_inner_Stack.fxml"));
        UserProject_inner innerController = new UserProject_inner(getMemberID(i));
        try {
            innerLoader.setController(innerController);
            stack_inner = innerLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        UserProject.projectStacks.add(stack_inner);
        UserProject.focus++;
        stack_user_inner.getChildren().clear();
        stack_user_inner.getChildren().add(UserProject.projectStacks.get(UserProject.focus));
    }

    public void setUserInnerPage(StackPane stack_user_inner) {

        this.stack_user_inner = stack_user_inner;
    }

    public String getMemberID(int index) {
        return memberSelected.get(index).get(0);
    }


}
