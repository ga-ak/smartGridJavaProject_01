package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class DAO_practice_main {

    //

    public static void main(String[] args) {

        Controller controller = new Controller();
        Project_DAO dao = new Project_DAO(controller);

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
        controller.printSelected(dao.select("CERTIFICATE"));
        System.out.print("변경할 자격증 이름 입력 >> ");
        Scanner sc = new Scanner(System.in);
        String tempLimit = sc.next();
        if (!controller.isNumber(tempLimit)) {
            tempLimit = "\'" + tempLimit + "\'";
        }
        String limit = "CERTIFICATE_NAME = " + tempLimit;


        System.out.print("값 입력 >> ");
        String tempValue = sc.next();

        dao.update("CERTIFICATE", "CERTIFICATE_NAME", tempValue, limit);
        //dao.insert("CERTIFICATE", columns, values);
        controller.printSelected(dao.select("CERTIFICATE"));


//        dao.update("CERTIFICATE", "CERTIFICATE_ID", "오라클마스터","CERTIFICATE_ID = ?");
//        //dao.delete("CERTIFICATE", "CERTIFICATE_ID = 115");
//        System.out.println("=====변경 완료!======");
//        controller.printSelected(dao.select("CERTIFICATE"));
    }
}