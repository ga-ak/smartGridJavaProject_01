package project.view2.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        ArrayList<String> columns = new ArrayList<>();
//        columns.add("project_id");
//        columns.add("project_name");
//        columns.add("employee_id");
//        columns.add("start_date");
//        columns.add("end_date");

        ArrayList<ArrayList<String>> tempArray = dao.select("project");
        ObservableList<ProjectTable> resultArray = FXCollections.observableArrayList();
        for (int i = 0; i < tempArray.size(); i++) {
            resultArray.add(new ProjectTable(tempArray.get(i)));
        }
        tbc_projetID.setCellValueFactory(cellData -> cellData.getValue().projectIDProperty());
        tbc_projectName.setCellValueFactory(cellData -> cellData.getValue().projectNameProperty());
        tbc_projectLeader.setCellValueFactory(cellData -> cellData.getValue().projectLeaderProperty());
        tbc_projectStart.setCellValueFactory(cellData -> cellData.getValue().startDateProperty());
        tbc_projectEnd.setCellValueFactory(cellData -> cellData.getValue().endDateProperty());

        tbv_projectTable.setItems(resultArray);



    }
}
