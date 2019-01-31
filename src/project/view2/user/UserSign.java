package project.view2.user;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import project.dao.DAO;
import project.view2.DAOContainer;
import project.view2.LoginInfo;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserSign implements Initializable {

    ArrayList<ArrayList<String>> resultArray;
    int jobGrade = Integer.parseInt(LoginInfo.loggedJGID);
    DAO dao = DAOContainer.dao;

    ArrayList<String> columns = new ArrayList<>();

    @FXML ComboBox<String> cbx_signMaker;
    @FXML ComboBox<String> cbx_signKind;
    @FXML ComboBox<String> cbx_formDown;
    @FXML Button btn_formDown;
    @FXML Button btn_sign_demand;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        columns.add("employee_name");
        ObservableList<String> signMakerList = FXCollections.observableArrayList();
        ObservableList<String> signKindList = FXCollections.observableArrayList();
        ObservableList<String> formKindList = FXCollections.observableArrayList();
        btn_formDown.setOnAction(event -> handle_btn_formDown(event));
        // 결재권자 메뉴 생성
        if (jobGrade < 8) {
            ArrayList<ArrayList<String>> tempArray = getAboveMembers();

            for (int i = 0; i < tempArray.size(); i++) {
                signMakerList.add(tempArray.get(i).get(0));
            }
        } else {
            ArrayList<ArrayList<String>> tempArray = getDirector();

            for (int i = 0; i < tempArray.size(); i++) {
                signMakerList.add(tempArray.get(i).get(0));
            }


        }

        // 결재 항목 메뉴 생성
        signKindList.add("보고서");
        signKindList.add("결근/지각");
        signKindList.add("휴가/병가");
        signKindList.add("조퇴");
        signKindList.add("자격증 획득");

        // 다운받을 항목 메뉴 생성
        formKindList.add("보고서 양식");
        formKindList.add("결근 시말서 양식");
        formKindList.add("지각 시말서 양식");
        formKindList.add("휴가 신청서 양식");
        formKindList.add("병가 신청서 양식");
        formKindList.add("조퇴 신청서 양식");
        formKindList.add("자격증 수당 양식");


        // 체크박스에 삽입
        cbx_signMaker.setItems(signMakerList);
        cbx_signKind.setItems(signKindList);
        cbx_formDown.setItems(formKindList);

    }

    public ArrayList<ArrayList<String>> getAboveMembers() {
        ArrayList<ArrayList<String>> result = null;
        String limit = "department_id = " + LoginInfo.loggedDeptID + " and jobgrade_id > " + LoginInfo.loggedJGID + " and jobgrade_id < 9";

        result = dao.select("employees", columns, limit);

        return result;
    }

    public ArrayList<ArrayList<String>> getDirector() {
        ArrayList<ArrayList<String>> result = null;
        String limit = "jobgrade_id > 8";

        result = dao.select("employees", columns, limit);

        return result;
    }

    public void handle_btn_formDown(ActionEvent event) {

        getData();
    }

    public void getData() {
        if (!cbx_formDown.getSelectionModel().isEmpty()) {
            Stage thisStage = (Stage)btn_formDown.getScene().getWindow();
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(thisStage);

            if(selectedDirectory != null){
                String downPath = selectedDirectory.getAbsolutePath();
                dao.connect();
                Connection conn = dao.getConnect();
                PreparedStatement psmt;
                System.out.println(downPath);
                int selectedIndex = cbx_formDown.getSelectionModel().getSelectedIndex();
                String sql = "select * from formdata where form_id = "+Integer.toString(selectedIndex);
                try {
                    psmt = conn.prepareStatement(sql);
                    ResultSet rs = psmt.executeQuery();
                    if(rs.next()) {
                        String filename = rs.getString("form_name");
                        System.out.println("파일명 : " + filename);
                        Blob blob = rs.getBlob("form_data");
                        InputStream fin = blob.getBinaryStream();
                        try {
                            OutputStream fos = new FileOutputStream(downPath+"/"+filename);
                            byte[] buffer = new byte[1024 * 8];

                            while(true) {
                                int count = fin.read(buffer);   //buffer 크기로 스캔한다.
                                System.out.println("COUNT : " + count);
                                if(count == -1) {
                                    System.out.println("더이상 읽은 데이터가 없다.");
                                    break;
                                }
                                fos.write(buffer, 0, count );

                                // buffer크기만큼 쓰고 시작위치부터 count값 까지만 쓴다.
                            }
                            fin.close();
                            fos.close();


                        } catch (Exception e) {
                            e.printStackTrace();
                        }



                    }
                    dao.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else{

            }

        }
    }


}
