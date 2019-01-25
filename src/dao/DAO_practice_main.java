package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAO_practice_main {

    //

    public static void main(String[] args) {
        Project_DAO dao = new Project_DAO();
        Controller controller = new Controller();

        String[] colums = {"JOBGRADE_ID", "JOBGRADE_NAME"};
        String[] values = {"1", "\'이등병\'"};
        String limit = "JOBGRADE_ID = 1";
        //dao.insert("JOBGRADES", colums, values);
        ArrayList<ArrayList<String>> queryOut = dao.select("JOBGRADES");
        System.out.println(queryOut.size());
        controller.printSelected(queryOut);

    }
}