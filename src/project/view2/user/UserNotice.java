package project.view2.user;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import project.dao.DAO;
import project.view2.DAOContainer;
import project.view2.LoginInfo;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserNotice implements Initializable {

    DAO dao = DAOContainer.dao;

    @FXML TableView<NoticeTable> tbv_noticeTable;
    @FXML TableColumn<NoticeTable, String> tbc_noticeID;
    @FXML TableColumn<NoticeTable, String> tbc_sender;
    @FXML TableColumn<NoticeTable, String> tbc_time;
    @FXML TableColumn<NoticeTable, String> tbc_type;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> columns = new ArrayList<>();
        columns.add("notice_id");
        columns.add("notice_sender");
        columns.add("notice_time");
        columns.add("notice_ann");
        columns.add("notice_deptsign");
        columns.add("notice_projsign");
        String limit = "notice_receiver = "+LoginInfo.loggedID;
        System.out.println("loggedId : " + LoginInfo.loggedID);

        ArrayList<ArrayList<String>> tempArray = dao.select("notice", columns, limit);
        System.out.println("사용자 알림 초기화 : "+tempArray.size());
        ObservableList<NoticeTable> resultArray = FXCollections.observableArrayList();
        for (int i = 0; i < tempArray.size(); i++) {
            resultArray.add(new NoticeTable(tempArray.get(i)));
        }
        System.out.println(resultArray.size());

        tbc_noticeID.setCellValueFactory(cellData -> cellData.getValue().noticeIDProperty());
        tbc_sender.setCellValueFactory(cellData -> cellData.getValue().senderProperty());
        tbc_time.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
        tbc_type.setCellValueFactory(cellData -> cellData.getValue().typeProperty());


        tbv_noticeTable.setItems(resultArray);

    }
}
