package project.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import project.dao.Project_DAO;

import java.io.IOException;
import java.util.ArrayList;

public class MyController {
    Project_DAO dao;
    @FXML
    VBox vbox_project;
    @FXML
    HBox hbox_project_inner;

    public MyController(Project_DAO dao) {
        this.dao = dao;
    }

    public void refreshProject () {
        ArrayList<String> columns1 = new ArrayList<>();
        ArrayList<String> columns2 = new ArrayList<>();

        columns1.add("DISTINCT PROJECT_ID");
        columns1.add("EMPLOYEE_ID");

        columns2.add("DISTINCT PROJECT_ID");
        columns2.add("PROJECT_NAME");
        columns2.add("EMPLOYEE_ID");
        columns2.add("START_DATE");
        columns2.add("START_DATE");

        String limit1 = "EMPLOYEE_ID = " + "1000";
        // 맡은 프로젝트가 2개이상인경우 ArrayList로 선언해줘야 한다
        String limit2 = "PROJECT_ID = ";

        ArrayList<ArrayList<String>> projectWorksSelected = dao.select("PROJECT_WORKS", columns1, limit1);

        for (int i = 0; i < projectWorksSelected.size(); i++) {
            String tempLimit = limit2 + projectWorksSelected.get(i).get(0);
            ArrayList<ArrayList<String>> projectSelected = dao.select("PROJECT",columns2,tempLimit);
            for (int j = 0; j < projectSelected.size(); j++) {
                String tempProcjectName = projectSelected.get(j).get(0);
                try {
                    hbox_project_inner = FXMLLoader.load(getClass().getResource("projectPage_projects.fxml"));
                    Label projectNameLbl = (Label) hbox_project_inner.getChildren().get(0);
                    projectNameLbl.setText(tempProcjectName);
                    System.out.println("라벨 생성완료!"+tempProcjectName);
                    vbox_project.getChildren().add(hbox_project_inner);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
