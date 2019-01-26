package dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class AutoGenerator_employee {

    //

    public static void main(String[] args) {
        HashSet<String> contacts = new HashSet<>();
        HashSet<String> ssns = new HashSet<>();

        ArrayList<EmployeeVO> result = new ArrayList<>();
        int employee_id;
        int department_id;
        int jobgrade_id;
        String employee_name;
        int base_salary;
        String address;
        String contact = "";
        String ssn = "";
        String hire_date="";
        String password="";

        String[] firstName = {
                "김", "이", "박", "최", "정", "강", "조", "오", "윤", "장"
        };
        String[] middleName = {
                "철", "영", "성", "태", "귀", "세", "의", "경", "수", "예"
        };
        String[] lastName = {
                "준", "윤", "호", "재", "군", "석", "옥", "민", "정", "현"
        };
        String[][] names = new String[3][];
        names[0] = firstName;
        names[1] = middleName;
        names[2] = lastName;

        Random ran = new Random();
        //String[] result = new String[10];
        employee_name = "";

//        for (int i = 0; i < result.length; i++) {
//            result[i] = "";
//        }

        // 이름 생성
        //for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < names.length; j++) {
                int ranNum = ran.nextInt(names[j].length);
                employee_name += names[j][ranNum];
            }
        //}

        // 이름 출력
//        for (int i = 0; i < result.length; i++) {
//            System.out.println(result[i]);
//
//        }

        // 부서 갯수 가져오기
        Controller controller = new Controller();
        Project_DAO dao = new Project_DAO(controller);

        int depSize = dao.select("DEPARTMENTS").size();

        department_id = 100 + 10 * (ran.nextInt(depSize)+1);

        int jobSize = dao.select("JOBGRADES").size();
        jobgrade_id = ran.nextInt(jobSize) + 1;
        base_salary = ran.nextInt(800)+200;
        base_salary = base_salary / 10 * 10; // 일의 자리 버림
        String[] addresses = {
                "서울", "부산", "대구", "대전", "광주", "인천", "전라남도", "길바닥인생"
        };
        address = addresses[ran.nextInt(addresses.length)];

        // 전화번호 생성
        while (true) {
            contact = "010";
            for (int i = 0; i < 8; i++) {
                contact += Integer.toString(ran.nextInt(10));
            }
            if (!contacts.contains(contact)) {
                contacts.add(contact);
                break;
            }
        }

        // 주민번호 생성
        while (true) {
            ssn += Integer.toString(ran.nextInt(10)); // 년도
            ssn += Integer.toString(ran.nextInt(10)); // 년도
            ssn += Integer.toString(ran.nextInt(2)); // 월 첫자리
            ssn += Integer.toString(ran.nextInt(3)); // 월 뒷자리
            ssn += Integer.toString(ran.nextInt(3)); // 일 앞자리 20일대까지만..
            // 일 앞자리가 0일경우
            if (ssn.substring(ssn.length()-1,ssn.length()).equals("0")) {
                ssn += Integer.toString(ran.nextInt(9)+1);
            } else {
                ssn += Integer.toString(ran.nextInt(10));
            }
            ssn += Integer.toString(ran.nextInt(4) + 1);
            ssn += Integer.toString(ran.nextInt(10));
            if (ssn.substring(ssn.length()-1,ssn.length()).equals("9")) {
                ssn += Integer.toString(ran.nextInt(6));
            } else {
                ssn += Integer.toString(ran.nextInt(10));
            }
            ssn += Integer.toString(ran.nextInt(10));
            ssn += Integer.toString(ran.nextInt(10));
            ssn += Integer.toString(ran.nextInt(10));
            ssn += Integer.toString(ran.nextInt(10));
            if (!ssns.contains(ssn)) {
                ssns.add(ssn);
                break;
            }
        }

        //hire_date
        hire_date += Integer.toString(ran.nextInt(10)); // 년도
        hire_date += Integer.toString(ran.nextInt(10)); // 년도
        hire_date += Integer.toString(ran.nextInt(2)); // 월 첫자리
        hire_date += Integer.toString(ran.nextInt(3)); // 월 뒷자리
        hire_date += Integer.toString(ran.nextInt(3)); // 일 앞자리 20일대까지만..
        // 일 앞자리가 0일경우
        if (hire_date.substring(hire_date.length()-1,hire_date.length()).equals("0")) {
            hire_date += Integer.toString(ran.nextInt(9)+1);
        } else {
            hire_date += Integer.toString(ran.nextInt(10));
        }

        int passwordSize = ran.nextInt(5)+8;
        int[] pN = {48, 57};
        int[] pB = {65, 90};
        int[] pS = {97, 128};
        int[][] pA = new int[3][];
        pA[0] = pN;
        pA[1] = pB;
        pA[2] = pS;

        for (int i = 0; i < passwordSize; i++) {
            int choosen = ran.nextInt(pA.length);
            int small = pA[choosen][0];
            int big = pA[choosen][1];
            char tempP = (char)(ran.nextInt(big - small + 1) + small);
            password += tempP;
        }

        EmployeeVO emp = new EmployeeVO(1000, department_id, jobgrade_id, employee_name, base_salary, address, contact, ssn, hire_date, password);

        System.out.println(emp);

    }
}
