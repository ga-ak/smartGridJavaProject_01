package project.view2.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import project.dao.DAO;
import project.view2.DAOContainer;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminAnnounce implements Initializable {

    DAO dao = DAOContainer.dao;

    @FXML
    TableView<AnnounceTable> tbv_adminNotice;
    @FXML TableColumn<AnnounceTable, String> tbc_noticeID;
    @FXML TableColumn<AnnounceTable, String> tbc_noticeName;
    @FXML TableColumn<AnnounceTable, String> tbc_noticeContent;
    @FXML TableColumn<AnnounceTable, String> tbc_noticeDate;
    @FXML Button btn_insertNotice;
    @FXML Button btn_updateNotice;
    @FXML Button btn_deleteNotice;
    @FXML Button btn_message;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<ArrayList<String>> tempArray = dao.select("announces");

        makeTable(tempArray);

        btn_insertNotice.setOnAction(event -> handle_btn_insertNotice(event));
        btn_updateNotice.setOnAction(event -> handle_btn_updateNotice(event));
        btn_deleteNotice.setOnAction(event -> handle_btn_deleteNotice(event));


    }

    public void makeTable(ArrayList<ArrayList<String>> inputArray) {
        ObservableList<AnnounceTable> resultArray = FXCollections.observableArrayList();
        for (int i = 0; i < inputArray.size(); i++) {
            resultArray.add(new AnnounceTable(inputArray.get(i)));
        }

        tbc_noticeID.setCellValueFactory(cellData -> cellData.getValue().annIDProperty());
        tbc_noticeName.setCellValueFactory(cellData -> cellData.getValue().annNameProperty());
        tbc_noticeContent.setCellValueFactory(cellData -> cellData.getValue().annContentProperty());
        tbc_noticeDate.setCellValueFactory(cellData -> cellData.getValue().annDateProperty());

        tbv_adminNotice.setItems(resultArray);
    }

    public void handle_btn_message(ActionEvent event) {

    }

    // 이 밑으로는 해야 할 것들임
    public void handle_btn_insertNotice(ActionEvent event) {

    }
    public void handle_btn_updateNotice(ActionEvent event) {

    }
    public void handle_btn_deleteNotice(ActionEvent event) {

    }



}
