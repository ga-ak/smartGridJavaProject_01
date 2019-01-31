package project.view2.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import project.dao.DAO;
import project.view2.DAOContainer;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminProject implements Initializable {

    DAO dao = DAOContainer.dao;

    @FXML TableView<ProjectTable> tbv_projectTable;
    @FXML TableColumn<ProjectTable,String> tbc_projetID;
    @FXML TableColumn<ProjectTable,String> tbc_projectName;
    @FXML TableColumn<ProjectTable,String> tbc_projectLeader;
    @FXML TableColumn<ProjectTable,String> tbc_projectStart;
    @FXML TableColumn<ProjectTable,String> tbc_projectEnd;

    @FXML ComboBox<String> cbx_searchOpt;
    @FXML TextField tbx_searchVal;
    @FXML Button btn_search;
    @FXML Button btn_insertProject;
    @FXML Button btn_updateProject;
    @FXML Button btn_deleteProject;

    ArrayList<String> columns = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columns.add("project_id");
        columns.add("project_name");
        columns.add("employee_id");
        columns.add("start_date");
        columns.add("end_date");

        ArrayList<ArrayList<String>> tempArray = dao.select("project");
        makeTable(tempArray);

        btn_search.setOnAction(event -> handle_btn_search(event));
        btn_insertProject.setOnAction(event -> handle_btn_insertProject(event));
        btn_updateProject.setOnAction(event -> handle_btn_updateProject(event));
        btn_deleteProject.setOnAction(event -> handle_deleteProject(event));

        ObservableList<String> searchOptList = FXCollections.observableArrayList();
        searchOptList.add("프로젝트 이름");
        searchOptList.add("프로젝트 번호");
        cbx_searchOpt.setItems(searchOptList);

    }

    public void handle_btn_search(ActionEvent event) {
        int choiceOpt = cbx_searchOpt.getSelectionModel().getSelectedIndex();

        if (choiceOpt == 0) {// 이름으로 검색
            String limit = "project_name = "+tbx_searchVal.getText();
            ArrayList<ArrayList<String>> tempArray = dao.select("project", columns, limit);
            makeTable(tempArray);

        } else if (choiceOpt == 1) {// 번호로 검색
            String limit = "project_id = "+tbx_searchVal.getText();
            ArrayList<ArrayList<String>> tempArray = dao.select("project", columns, limit);
            makeTable(tempArray);
        }
    }

    // 이 밑으로는 해야 할 것들임
    public void handle_btn_insertProject(ActionEvent event) {

    }

    public void handle_btn_updateProject(ActionEvent event) {

    }

    public void handle_deleteProject(ActionEvent event) {

    }

    public void makeTable(ArrayList<ArrayList<String>> inputArray) {
        ObservableList<ProjectTable> resultArray = FXCollections.observableArrayList();
        for (int i = 0; i < inputArray.size(); i++) {
            resultArray.add(new ProjectTable(inputArray.get(i)));
        }

        tbc_projetID.setCellValueFactory(cellData -> cellData.getValue().projectIDProperty());
        tbc_projectName.setCellValueFactory(cellData -> cellData.getValue().projectNameProperty());
        tbc_projectLeader.setCellValueFactory(cellData -> cellData.getValue().projectLeaderProperty());
        tbc_projectStart.setCellValueFactory(cellData -> cellData.getValue().startDateProperty());
        tbc_projectEnd.setCellValueFactory(cellData -> cellData.getValue().endDateProperty());

        tbv_projectTable.setItems(resultArray);
    }
}
