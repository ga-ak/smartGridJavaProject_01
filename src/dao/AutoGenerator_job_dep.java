package dao;

import java.util.ArrayList;

public class AutoGenerator_job_dep {

    //

    public static void main(String[] args) {
        Controller controller = new Controller();
        Project_DAO dao = new Project_DAO(controller);

        String[] job_grades = {
                "수습사원", "사원", "주임", "계장", "대리", "과장", "차장", "부장", "이사","사장"
        };

        String[] departments = {
                "영업", "관리", "생산", "품질", "연구", "인사", "회계", "IT", "구매", "마케팅"
        };

        // JOBGRADES 테이블의 행 생성
        // JOBGRADE_ID, JOBGRADE_NAME
        ArrayList<String> jobColumns = new ArrayList<>();
        jobColumns.add("JOBGRADE_ID");
        jobColumns.add("JOBGRADE_NAME");

        for (int i = 0; i < job_grades.length; i++) {
            ArrayList<String> jobValues = new ArrayList<>();
            jobValues.add(Integer.toString(i+1));
            jobValues.add(job_grades[i]);
            dao.insert("JOBGRADES", jobColumns, jobValues);
        }

        // DEPARTMENTS 테이블의 행 생성
        // DEPARTMENT_ID, DEPARTMENT_NAME, LOCATION
        ArrayList<String> depColumns = new ArrayList<>();
        depColumns.add("DEPARTMENT_ID");
        depColumns.add("DEPARTMENT_NAME");
        depColumns.add("LOCATION");

        for (int i = 0; i < departments.length; i++) {
            ArrayList<String> depValues = new ArrayList<>();
            depValues.add(Integer.toString(100 + (10 * i))); // 100 ~ 990 까지 10씩 증가하도록 설정
            depValues.add(departments[i]);
            depValues.add("광주본사");
            dao.insert("DEPARTMENTS", depColumns, depValues);
        }
    }
}
