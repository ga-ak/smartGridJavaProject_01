package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAO_practice_main {

    //

    public static void main(String[] args) {
        Project_DAO dao = new Project_DAO();
        Controller controller = new Controller();

        //String[] columns = {"JOBGRADE_ID", "JOBGRADE_NAME"};
        //CERTIFICATE_ID, CERTIFICATE_NAME, CERTIFICATE_MONEY
        ArrayList<String> columns = new ArrayList<>();
        columns.add("CERTIFICATE_ID");
        columns.add("CERTIFICATE_NAME");
        columns.add("CERTIFICATE_MONEY");
        ArrayList<String> values = new ArrayList<>();
        values.add("115");
        values.add("행정고시");
        values.add("20000");
        //String[] columns = {"CERTIFICATE_ID", "CERTIFICATE_NAME", "CERTIFICATE_MONEY"};
        //String[] columns = {"", "", ""};

        String limit = "CERTIFICATE_ID = 112";
        //dao.insert("JOBGRADES", colums, values);

        controller.printSelected(dao.select("CERTIFICATE"));

        dao.update("CERTIFICATE", "CERTIFICATE_ID", "오라클마스터","CERTIFICATE_ID = ?");
        //dao.delete("CERTIFICATE", "CERTIFICATE_ID = 115");
        System.out.println("=====변경 완료!======");
        controller.printSelected(dao.select("CERTIFICATE"));
    }
}