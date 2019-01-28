package dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class AutoGenerator_employee {

    //

    public ArrayList<EmployeeVO> employeeGenerator(Project_DAO dao, int startNum, int lastNum) {

        System.out.println("ArrayList 생성 시작....");
        HashSet<String> contacts = new HashSet<>();
        HashSet<String> ssns = new HashSet<>();
        ArrayList<EmployeeVO> result = new ArrayList<>();
        ArrayList<String> columns = new ArrayList<>();
        columns.add("EMPLOYEE_ID");
        columns.add("DEPARTMENT_ID");
        columns.add("JOBGRADE_ID");
        columns.add("EMPLOYEE_NAME");
        columns.add("BASE_SALARY");
        columns.add("ADDRESS");
        columns.add("CONTACT");
        columns.add("SSN");
        columns.add("HIRE_DATE");
        columns.add("PASSWORD");
        int depSize = dao.select("DEPARTMENTS").size();
        int jobSize = dao.select("JOBGRADES").size();

        String[] addresses = {
                "서울", "부산", "대구", "대전", "광주", "인천", "전라남도", "전라북도","경기도","강원도","충청남도","충청북도", "경상남도", "경상북도","제주도"
        };
        String[] firstName = {
                "김", "이", "박", "최", "정", "강", "조", "오", "윤", "장","지"
        };
        String[] middleName = {
                "철", "영", "성", "태", "귀", "세", "의", "경", "수", "예","안","선","서","관","현"
        };
        String[] lastName = {
                "준", "윤", "호", "재", "군", "석", "옥", "민", "정", "현","훈","희","미","순","산","우"
        };
        String[][] names = new String[3][];
        names[0] = firstName;
        names[1] = middleName;
        names[2] = lastName;

        Random ran = new Random();

        for (int k = startNum; k <= lastNum; k++) {
            int employee_id = k;
            int department_id;
            int jobgrade_id;
            String employee_name = "";
            int base_salary;
            String address;
            String contact = "";
            String ssn = "";
            String hire_date="";
            String password="";

            // 이름 생성

            for (int j = 0; j < names.length; j++) {
                int ranNum = ran.nextInt(names[j].length);

                if (j >= 1) {
                    while (true) {
                        ranNum = ran.nextInt(names[j].length);
                        String tempName = names[j][ranNum];
                        if (!employee_name.substring(employee_name.length()-1,employee_name.length()).equals(tempName)) {
                            employee_name += tempName;
                            break;
                        }
                    }
                } else {
                    employee_name += names[j][ranNum];
                }
            }
            System.out.print(k+" >> 이름생성\t");

            // 이름 출력
//        for (int i = 0; i < result.length; i++) {
//            System.out.println(result[i]);
//
//        }

            // 부서 랜덤 할당
            department_id = 100 + 10 * (ran.nextInt(depSize));
            System.out.print("부서 할당\t");

            // 직급 랜덤 할당
            jobgrade_id = ran.nextInt(jobSize) + 1;
            System.out.print("직급 할당\t");

            // 기본급 랜덤 할당
            base_salary = ran.nextInt(800)+200;
            base_salary = base_salary / 10 * 10; // 일의 자리 버림
            System.out.print("기본급 할당\t");
            // 주소 랜덤 할당

            address = addresses[ran.nextInt(addresses.length)];
            System.out.print("주소 할당 \t");
            // 전화번호 랜덤 할당
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
            System.out.print("전화번호 할당\t");

            // 주민번호 랜덤 할당
            while (true) {
                ssn = Integer.toString(ran.nextInt(5)+5); // 년도
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
                ssn += Integer.toString(ran.nextInt(2) + 1);
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
            System.out.print("주민번호 할당\t");

            // 입사일 랜덤 할당
            hire_date += Integer.toString(ran.nextInt(2018-1980+1)+1980); // 년도 1980~2018
            hire_date += "/";
            hire_date += Integer.toString(ran.nextInt(2)); // 월 첫자리
            // 월 앞자리가 0일경우
            if (hire_date.substring(hire_date.length()-1,hire_date.length()).equals("0")) {
                hire_date += Integer.toString(ran.nextInt(9)+1);
            } else {
                hire_date += Integer.toString(ran.nextInt(3));
            }
            hire_date += "/";
            hire_date += Integer.toString(ran.nextInt(3)); // 일 앞자리 20일대까지만..
            // 일 앞자리가 0일경우
            if (hire_date.substring(hire_date.length()-1,hire_date.length()).equals("0")) {
                hire_date += Integer.toString(ran.nextInt(9)+1);
            } else {
                hire_date += Integer.toString(ran.nextInt(10));
            }
            System.out.print("입사일 할당\t");

            // 비밀번호 랜덤 할당
            int passwordSize = ran.nextInt(5)+8;
            int[] pN = {48, 57};
            int[] pB = {65, 90};
            int[] pS = {97, 122};
            int[][] pA = new int[3][];
            pA[0] = pN;
            pA[1] = pB;
            pA[2] = pS;

            for (int i = 0; i < passwordSize; i++) {
                int choosen = ran.nextInt(pA.length);
                int small = pA[choosen][0];
                int big = pA[choosen][1];
                char tempP = (char)(ran.nextInt(big - small + 1) + small);
                //System.out.println((int)tempP+"\t"+tempP);
                password += tempP;
            }
            System.out.println("비밀번호 할당");

            EmployeeVO emp = new EmployeeVO(k, department_id, jobgrade_id, employee_name, base_salary, address, contact, ssn, hire_date, password);
            result.add(emp);
            //System.out.println(k+" 번째 생성!");
        }
        System.out.println("ArrayList 생성 완료!");
        return result;
    }

    public void insertAllEmps(ArrayList<EmployeeVO> result, Project_DAO dao) {
        ArrayList<String> columns = new ArrayList<>();
        ArrayList<ArrayList<String>> valueArrays = new ArrayList<>();
        columns.add("EMPLOYEE_ID");
        columns.add("DEPARTMENT_ID");
        columns.add("JOBGRADE_ID");
        columns.add("EMPLOYEE_NAME");
        columns.add("BASE_SALARY");
        columns.add("ADDRESS");
        columns.add("CONTACT");
        columns.add("SSN");
        columns.add("HIRE_DATE");
        columns.add("PASSWORD");
        for (int i = 0; i < result.size(); i++) {
            ArrayList<String> tempValues = new ArrayList<>();
            tempValues.add(result.get(i).getEmployee_id());
            tempValues.add(result.get(i).getDepartment_id());
            tempValues.add(result.get(i).getJobgrade_id());
            tempValues.add(result.get(i).getEmployee_name());
            tempValues.add(result.get(i).getBase_salary());
            tempValues.add(result.get(i).getAddress());
            tempValues.add(result.get(i).getContact());
            tempValues.add(result.get(i).getSsn());
            tempValues.add(result.get(i).getHire_date());
            tempValues.add(result.get(i).getPassword());
            valueArrays.add(tempValues);
            System.out.println((i + 1) + " : " + result.get(i));
        }
        dao.insertBatch("EMPLOYEES", columns, valueArrays);
    }

}
