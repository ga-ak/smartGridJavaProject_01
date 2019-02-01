package project.dao;

public class insertPractice_main {

    //

    public static void main(String[] args) {
        Controller controller = new Controller();
        DAO dao = new DAO(controller);
        AutoGenerator_employee ag = new AutoGenerator_employee();
        int startNum = 1000;
        int lastNum = 1099;
        int volume = lastNum - startNum + 1;
        long st = System.currentTimeMillis();
        ag.insertAllEmps(ag.employeeGenerator(dao, startNum, lastNum),dao);
        long et = System.currentTimeMillis();
        double result = (int)(et-st)/1000.0;
        System.out.println(volume+"회 실행에 걸린 시간 : "+result);

    }
}
