package project.methods;

import project.dao.Controller;
import project.dao.DAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Integration {
    static String InputID = null;

    public static void main(String[] args) {
//		login(); 						// 로그인
//		Emp_FindPW();					//패스워드찾기(철호선생님 문자로쏴주세요)
//		Emp_search(); 					//사원정보검색
//		Emp_ADupdate();					//주소수정
//		Emp_PWupdate();					//비밀번호변경
//		Emp_Telupdate();				//전화번호변경
//		Emp_Salary(); 					// 월급나옴
//		Emp_Pro_Insert();				//개인프로젝트 삽입 (추후 작업기간 삭제해야함)
//		Emp_Pro_Ser_All();				//큰 프로젝트번호로 검색
//		Emp_Pro_Ser_EmpName();			//로그인 한 사원의 개인 프로젝트 검색
//		Emp_Pro_Update_wName();			//개인작업 이름수정
//		Emp_Pro_Update_wSign();			//개인작업 결재0이면1로 1이면0으로
//		Emp_Pro_Update_Sdate();			//개인작업 시작일 변경(작업이름으로검색)
//		Emp_Pro_Update_Edate();			//개인작업 종료일 변경(작업이름으로검색)
//		Emp_Pro_Calculate_progress();	//전체 프로젝트 진척률 계산

//		Man_Emp_Ser_Name();				//사원 이름으로 검색
//		Man_Emp_Ser_Dept();				//부서 이름으로 검색
//		Man_Emp_Ser_Job();				//직급 이름으로 검색
//		Man_Emp_Update_EmpAD(); 		//사원번호 변경
//		Man_Emp_Del_EmpDel(); 			//사원삭제
//		Man_Emp_Add_EmpAdd();			//사원추가
//		Man_Cer_Add();					//자격증추가
//		Man_Cer_Ser();					//자격증 검색
//		Man_Cer_AllSer();				//자격증 전체 검색
//		Man_Cer_Update();				//자격증 수정
//		Man_Cer_Del();					//자격증 삭제
//		Man_Pro_AllSer();				//전체프로젝트 조회
//		Man_Pro_EmpNameSer();			//사원이름으로 프로젝트 조회
//		Man_Pro_ProNameSer();			//프로젝트 이름으로 조회
//		Man_Pro_ProInsert();			//프로젝트 추가
//		Man_Pro_ProWork_EmpName_Ser();	//프로젝트 검색 = 개인작업 수행중인 사원이름으로 검색
//		Man_Pro_Ser_ProName();			//큰 프로젝트번호로 검색할 때 개인작업중인 멤버이름 검색
//		Man_Pro_EmpID();				//프로젝트 장 변경
//		Man_Pro_StartUpdate(); 			//프로젝트 시작일 변경
//		Man_Pro_EndUpdate();			//프로젝트 종료일 변경
//		Man_Pro_ProNameUpdate();		//프로젝트 이름변경
//		Man_Pro_ProDel();				//프로젝트 삭제
//		Man_Msg_Emp();					//사원선택해서 문자보내기
//		Man_Msg_Dept(); 				//부서별 문자보내기
//		Man_Msg_Job();					//직급별 문자보내기
    }

    public static void login() {// 로그인하기(사원/관리자 체크)
        Controller con = new Controller();
        DAO dao = new DAO(con);

        Scanner sc = new Scanner(System.in);
        ArrayList<String> columns = new ArrayList<>();

        columns.add("employee_id");
        columns.add("password");
        columns.add("department_id");

        System.out.println("관리자 체크(0과 1만 입력 할 것)");
        int check = sc.nextInt();
        System.out.println("ID를 입력하세요(사원번호)>> ");
        String ID = sc.next();
        InputID = ID;
        System.out.println("PW를 입력하세요>> ");
        String PW = sc.next();

        // where문에 id를 입력하고 패스워드를 입력해서 같으면 로그인 틀리면 로그인 실패
        String limit = "EMPLOYEE_ID=" + ID + " AND PASSWORD ='" + PW + "'";

        // ID와 PW가 맞으면 arraylist에 사원번호, 비밀번호, 부서번호를 차례차례 넣어줌.
        // 없으면 배열의 크기는 0
        ArrayList<ArrayList<String>> tempArray = dao.select("employees", columns, limit);
        ArrayList<String> temparr2 = new ArrayList<String>();
        for (int i = 0; i < tempArray.size(); i++) {
            for (int j = 0; j < tempArray.get(i).size(); j++) {
                String tempValue2 = tempArray.get(i).get(j);
                temparr2.add(tempValue2);
            }
        }

        // 관리자일때
        if (check == 1) {

            // ID와 비밀번호가 들어있는 배열의 크기가 0이 아니면서 부서번호가 150일때
            if (temparr2.size() != 0 && temparr2.get(2).equals("150")) {
                System.out.println("관리자로 로그인 성공");
                con.printSelected(dao.select("employees", columns, limit));
            } else {
                System.out.println("관리자로 로그인 실패");
            }

            // 사원일때
        } else if (check == 0) {

            // ID와 비밀번호가 들어있는 배열의 크기가 0이 아닐때
            if (temparr2.size() != 0) {
                System.out.println("사원으로 로그인 성공");
                con.printSelected(dao.select("employees", columns, limit));
            } else {
                System.out.println("사원으로 로그인 실패");
            }
        }

    }

    public static void Emp_FindPW() {
        Controller con = new Controller();
        DAO dao = new DAO(con);
        Scanner sc = new Scanner(System.in);

        System.out.println("비밀번호를 찾을 사원번호를 입력해주세요");
        String empid = sc.next();
        System.out.println("비밀번호를 찾을 사원의 주민번호를 입력해주세요(-빼고 입력해주세요)");
        String empssn = sc.next();

        ArrayList<String> columns = new ArrayList<>();

        // 전화번호 가져올 column
        columns.add("contact");

        // 제약조건 dao.select문에서는 주민번호까지 확인
        String limit = "employee_id= " + empid + " and ssn ='" + empssn + "'";

        // 제약조건 update문에서는 empid로만 식별(' 때문에 제약조건 두개는 못넣음)
        String limit2 = "employee_id =" + empid;
        ArrayList<ArrayList<String>> tempArray = dao.select("employees", columns, limit);
        ArrayList<String> temparr2 = new ArrayList<String>();
        for (int i = 0; i < tempArray.size(); i++) {
            for (int j = 0; j < tempArray.get(i).size(); j++) {
                String tempValue2 = tempArray.get(i).get(j);
                temparr2.add(tempValue2);
            }
        }
        // 전화번호 Tel 문자열에 저장하기.
        String Tel = temparr2.get(0);

        // 임의의 비밀번호 새로 만들기
        String updatePW = "";
        int passwordSize = new Random().nextInt(5) + 8;
        int[] pN = { 48, 57 };
        int[] pB = { 65, 90 };
        int[] pS = { 97, 122 };
        int[][] pA = new int[3][];
        pA[0] = pN;
        pA[1] = pB;
        pA[2] = pS;

        for (int i = 0; i < passwordSize; i++) {
            int choosen = new Random().nextInt(pA.length);
            int small = pA[choosen][0];
            int big = pA[choosen][1];
            char tempP = (char) (new Random().nextInt(big - small + 1) + small);
            // System.out.println((int)tempP+"\t"+tempP);
            updatePW += tempP;
        }
        // 변경된 비밀번호 업데이트
        dao.update("employees", "password", updatePW, limit2);
        // 문자열 password에 있는 생성된 임의의 비밀번호를 Tel에 문자로 쏴주면됨.
    }

    public static void Emp_search() {

        // 회원정보보기
        Scanner sc = new Scanner(System.in);
        Controller con = new Controller();
        DAO dao = new DAO(con);

        String limit = "EMPLOYEE_ID = " + InputID; // 로그인할 사원번호

        // 어레이리스트를 만들고 컬럼을 묶음
        ArrayList<String> columns = new ArrayList<>();
        columns.add("EMPLOYEE_NAME");
        columns.add("EMPLOYEE_ID");
        columns.add("DEPARTMENT_ID");
        columns.add("JOBGRADE_ID");
        columns.add("HIRE_DATE");
        columns.add("ADDRESS");
        columns.add("CONTACT");
        // 회원정보 보기
        // System.out.println("[1]사원이름\t[2]사원번호\t[3]부서\t[4]직급\t[5]입사일\t[6]주소[7]연락처");
        con.printSelected(dao.select("EMPLOYEES", columns, limit));

    }

    public static void Emp_ADupdate() {

        // 주소 변경
        Scanner sc = new Scanner(System.in);
        Controller con = new Controller();
        DAO dao = new DAO(con);

        String limit = "EMPLOYEE_ID = " + InputID; // 로그인할 사원번호

        // 회원정보 수정
        System.out.println("변경할 주소를 입력주세요.");
        String a = sc.next();

        // 수정
        dao.update("EMPLOYEES", "ADDRESS", a, limit);

    }

    public static void Emp_PWupdate() {

        Scanner sc = new Scanner(System.in);
        Controller con = new Controller();
        DAO dao = new DAO(con);
        // 로그인할 사원번호
        String limit = "EMPLOYEE_ID = " + InputID;

        System.out.println("변경할 패스워드를 입력하세요.");
        String c = sc.next();
        dao.update("EMPLOYEES", "PASSWORD", c, limit);

    }

    public static void Emp_extrapay() {
        Scanner sc = new Scanner(System.in);
        Controller con = new Controller();
        DAO dao = new DAO(con);
        String[] columnremake = { "ADDRESS", "CONTACT", "PASSWORD" };
        System.out.println("로그인한 사원 번호");
        String login = sc.next();
        String limit = "EMPLOYEE_ID = " + login; // 로그인할 사원번호
        // 어레이리스트를 만들고 컬럼을 묶음
        ArrayList<String> columns = new ArrayList<>();
        columns.add("EMPLOYEE_NAME");
        columns.add("EMPLOYEE_ID");
        columns.add("DEPARTMENT_ID");
        columns.add("JOBGRADE_ID");
        columns.add("HIRE_DATE");
        columns.add("ADDRESS");
        columns.add("CONTACT");
        columns.add("PASSWORD");

        // 추가수당 보기
        ArrayList<String> extrapay = new ArrayList<>();
        System.out.println("[1]추가근무수당[2]보너스[3]합계");
        extrapay.add("OVERTIME");
        extrapay.add("BONUS");
        extrapay.add("SUM");
        con.printSelected(dao.select("EXTRAPAY", extrapay, limit));
    }

    public static void Emp_Telupdate() { // 연락처변경
        Scanner sc = new Scanner(System.in);
        Controller con = new Controller();
        DAO dao = new DAO(con);

        String limit = "EMPLOYEE_ID = " + InputID; // 로그인할 사원번호

        System.out.println("변경할 연락처를 입력해주세요.");
        String b = sc.next();
        dao.update("EMPLOYEES", "CONTACT", b, limit);
    }

    public static void Emp_Salary() {
        Scanner sc = new Scanner(System.in);
        Controller con = new Controller();
        DAO dao = new DAO(con);

        ArrayList<String> columns = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();
        ArrayList<String> overtime = new ArrayList<>();

        // 초과근무시간, 상여금을 columns 에 넣어줌
        columns.add("OVERTIME");
        columns.add("BONUS");

        int overtime0 = 0;
        int Cer0 = 0;

        // 로그인한 사원으로 바꾸면됨
        String limit = "EMPLOYEE_ID = " + InputID;

        ArrayList<String> bs = new ArrayList<>();
        bs.add("BASE_SALARY");
        System.out.println("==========기본급=========");
        ArrayList<ArrayList<String>> base = dao.select("EMPLOYEES", bs, limit);
        ArrayList<String> base2 = new ArrayList<String>();
        for (int i = 0; i < base.size(); i++) {
            for (int j = 0; j < base.get(i).size(); j++) {
                String basesal = base.get(i).get(j);
                base2.add(basesal);
            }
        }
        String base3 = base2.get(0);

        // base0 : 기본급 int형
        int base0 = Integer.parseInt(base3);
        System.out.println("기본급 : " + base0 + "만원");

        ArrayList<ArrayList<String>> tmparr = dao.select("EXTRAPAY", columns, limit);
        ArrayList<String> tmparr2 = new ArrayList<String>();
        for (int i = 0; i < tmparr.size(); i++) {
            for (int j = 0; j < tmparr.get(i).size(); j++) {
                String tempValue2 = tmparr.get(i).get(j);
                tmparr2.add(tempValue2);
            }
        }
        if (tmparr2.size() != 0) {
            for (int i = 0; i < tmparr2.size(); i++) {
            }
            String overtime2 = tmparr2.get(0);

            // overtime0 : 기본급 int형
            overtime0 = Integer.parseInt(overtime2);
            System.out.println("추가근무시간 : " + overtime0 + "시간");
        } else {
            overtime0 = 0;
        }

        ArrayList<String> CE = new ArrayList<String>();
        CE.add("CERTIFICATE_MONEY");
        ArrayList<ArrayList<String>> ls = dao.select("CERTIFICATE", CE, "CERTIFICATE_ID = 9000");
        ArrayList<String> ls2 = new ArrayList<String>();
        if (ls2.size() != 0) {
            for (int i = 0; i < ls.size(); i++) {
                for (int j = 0; j < ls.get(i).size(); j++) {
                    String Cer = ls.get(i).get(j);
                    ls2.add(Cer);
                }
            }
            String Cer1 = ls2.get(0);

            // Cer0 : 기본급 int형
            Cer0 = Integer.parseInt(Cer1);
            System.out.println("자격증수당 : " + Cer0 + "원");
        } else {
            Cer0 = 0;
        }

//      합계 = 기본급 + ((기본급/(20*8))*1.5*초과근무시간 + 상여금 + 자격증수당
        // 추가수당: overtime0 , 자격증수당 : Cer0 , 기본급 : base0
        // BA 기본급, OT 추가수당, BO 상여금, SUM0 합계
        int BA = (int) (base0) * 10000;
        int OT = (int) (((base0 / (20 * 8)) * 1.5 * overtime0) * 10000);
        int BO = (int) (base0 * 4) / 12;
        int sum0 = (int) BA + Cer0 + OT + BO;
        System.out.println("월급 : " + sum0 + "원");

        // int형 sum0를 String형 sum으로 바꿔줌
        String sum = Integer.toString(sum0);

        dao.update("EXTRAPAY", "SUM", sum, limit);

    }

    public static void Emp_Pro_Insert() {
        Controller con = new Controller();
        DAO dao = new DAO(con);

        Scanner sc = new Scanner(System.in);
        // 작업기간 변수
        String period = null;

        ArrayList<String> columns = new ArrayList<>();
        columns.add("project_id");
        columns.add("employee_id");
        columns.add("work_name");
        columns.add("start_date");
        columns.add("end_date");
        columns.add("work_period");
//		result 컬럼 부분은 나중에 다시 확인해줘야함.
//		columns.add("result");
        columns.add("sign");

        // 나중에 자동으로 프로젝트번호 생성되게 해줘야되나?(잘모르겠음)
        ArrayList<String> values = new ArrayList<>();
        System.out.print("참여할 프로젝트 번호>> ");
        String proid = sc.next();
        values.add(proid);
        // 로그인아이디
        values.add(InputID);
        System.out.print("시작할 업무 이름>> ");
        String wname = sc.next();
        values.add(wname);
        System.out.print("시작할 단기과제 일자(yyyy/mm/dd 형식으로 입력할 것)>> ");
        String sdate = sc.next();
        values.add(sdate);
        System.out.print("종료될 단기과제 일자(yyyy/mm/dd 형식으로 입력할 것)>> ");
        String edate = sc.next();
        values.add(edate);

        // 작업일수 = 종료일자-시작일자
        try {

            sdate = sdate.replaceAll("/", "");
            edate = edate.replaceAll("/", "");

            String sd1 = sdate.substring(0, 4);
            String sd2 = sdate.substring(4, 6);
            String sd3 = sdate.substring(6, 8);

            String ed1 = edate.substring(0, 4);
            String ed2 = edate.substring(4, 6);
            String ed3 = edate.substring(6, 8);

            sdate = sd1 + "/" + sd2 + "/" + sd3;
            edate = ed1 + "/" + ed2 + "/" + ed3;

            // 날짜를 data타입으로 변경

            SimpleDateFormat fm = new SimpleDateFormat("yyyy/MM/dd");
            Date sDate = fm.parse(sdate);
            Date eDate = fm.parse(edate);

            // 시간차이를 시간,분,초를 곱한 값으로 나누면 하루 단위가 나옴
            long diff = eDate.getTime() - sDate.getTime();
            long diffDays = diff / (24 * 60 * 60 * 1000);
            long difMonth = (diffDays + 1) / 30; // 총개월수 ( 대략 30으로 나눴을때 나오는 개월수 )
            long chkNum = 0;
            int j = 0;
            // 개월수 체크 ( 시작한날짜월부터 위에서 대충 계산한 개월수까지 )

            // 각 월별로 해당하는 월수에 맞게 더해줌
            for (int i = Integer.parseInt(sd2); j < difMonth; i++) {
                if (i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12) {
                    chkNum += 31;
                } else if (i == 4 || i == 6 || i == 9 || i == 11) {
                    chkNum += 30;
                }
                if (i == 2) {
                    // 윤달체크
                    if (((Integer.parseInt(sd2)) % 400) == 0) {
                        chkNum += 29;
                    } else {
                        chkNum += 28;
                    }
                }
                j++;
                if (i > 12) {
                    i = 1;
                    j = j - 1;
                }
            }
            long allMonth = (chkNum + 1) / 30; // 진짜 총개월수
            if (diffDays < chkNum) {
                allMonth = allMonth - 1; // 대충 구한개월수는 더많을수 있어서 1빼줘서 진짜 개월수를 구함
            }
            period = diffDays + "";
            values.add(period);
        } catch (Exception e) {
        }

//        String result=sc.next();
//        values.add(result);

        // 만약 결재여부 직접 입력안하려면 바꿔줘야함 결재여부는 0과 1만 들어갈 수 있음.
        System.out.print("결재여부>> ");
        String sign = sc.next();
        values.add(sign);

        dao.insert("project_works", columns, values);

    }

    public static void Emp_Pro_Ser_All() {
        Controller con = new Controller();
        DAO dao = new DAO(con);

        Scanner sc = new Scanner(System.in);

//로그인->프로젝트->개인 프로젝트로 들어가기
//프로젝트 ID를 입력받으면 그 프로젝트의 개인프로젝트 현황이 나옴
//프로젝트 ID에 해당하는 모든 정보가 다 나옴.

        ArrayList<String> columns = new ArrayList<>();
//ArrayList<String> arr = new ArrayList<>();

        columns.add("w.project_id");
        columns.add("w.employee_id");
        columns.add("e.employee_name");
        columns.add("w.start_date");
        columns.add("w.end_date");
        columns.add("w.work_name");
        columns.add("w.work_period");

//	result 부분 나중에 추가 필요(Main3)도 동일
//	columns.add("w.result");
        columns.add("w.sign");

//	우선은 아무 프로젝트나 들어갈 수 있게 해뒀음(참여자가 아니더라도 프로젝트 참여 가능)
        System.out.print("들어갈 프로젝트 선택>> ");
        String project = sc.next();
//	System.out.print("개인일정이 존재하는 이의 사원번호>> ");
//	String empid=sc.next();

        String limit = "w.project_id= " + project + " and w.project_id=p.project_id"
                + " and w.employee_id=e.employee_id";
        con.printSelected(dao.select("project_works w, project p, employees e", columns, limit));

        System.out.println("====================검색완료====================");
//	con.printSelected(dao.select("project_works w, project p", columns, limit));
    }

    public static void Emp_Pro_Ser_EmpName() {
        Controller con = new Controller();
        DAO dao = new DAO(con);

        // 로그인->프로젝트->개인 프로젝트로 들어가기
        // 로그인 ID의 개인프로젝트 현황을 보여줌

        ArrayList<String> columns = new ArrayList<>();

        columns.add("w.project_id");
        columns.add("w.employee_id");
        columns.add("e.employee_name");
        columns.add("w.start_date");
        columns.add("w.end_date");
        columns.add("w.work_name");
        columns.add("w.work_period");

        // result 부분 나중에 추가 필요
        // columns.add("w.result");
        columns.add("w.sign");

        // 제약조건 사원이름으로 받고 조인
        String limit = "e.employee_id= " + InputID + " and w.employee_id=e.employee_id";

        con.printSelected(dao.select("project_works w, employees e", columns, limit));

        System.out.println("====================완료====================");

    }

    public static void Emp_Pro_Update_wName() {

        Controller con = new Controller();
        DAO dao = new DAO(con);

        Scanner sc = new Scanner(System.in);
        ArrayList<String> columns = new ArrayList<>();

        columns.add("PROJECT_ID");
        columns.add("PROJECT_NAME");
        columns.add("EMPLOYEE_ID");
        columns.add("START_DATE");
        columns.add("END_DATE");

        System.out.print("변경할 개인업무 이름 >> ");
        String tempLimit = sc.next();
        String limit = "work_name = " + tempLimit;

        System.out.print("변경될 개인업무 이름 >>  ");
        String tempValue = sc.next();

        dao.update("PROJECT_WORKS", "WORK_NAME", tempValue, limit);
        System.out.println("====================변경완료====================");

    }

    public static void Emp_Pro_Update_wSign() {

        // 결재(sign) 받았을 때 0이면 1인상태로 1이면 0으로 바뀌게 하는 메소드
        Controller con = new Controller();
        DAO dao = new DAO(con);

        Scanner sc = new Scanner(System.in);
        ArrayList<String> columns = new ArrayList<>();

        columns.add("sign");

        System.out.print("사인 체크할 프로젝트 이름 >> ");
        String tempLimit = sc.next();
        String limit = "work_name = '" + tempLimit + "'";
        String limit2 = "work_name = " + tempLimit;

        ArrayList<ArrayList<String>> tempArray = dao.select("project_works", columns, limit);

        ArrayList<String> temparr2 = new ArrayList<String>();
        for (int i = 0; i < tempArray.size(); i++) {
            for (int j = 0; j < tempArray.get(i).size(); j++) {
                String tempValue2 = tempArray.get(i).get(j);
                temparr2.add(tempValue2);
            }
        }
        String sign2 = temparr2.get(0);
        if (sign2.equals("0")) {
            sign2 = 1 + "";
        } else {
            sign2 = 0 + "";
        }
        dao.update("project_works", "sign", sign2, limit2);

        System.out.println("====================변경완료====================");
    }

    public static void Emp_Pro_Update_Sdate() {
        // 사원->개인프로젝트->시작일 변경하기
        // 사원 로그인->프로젝트->개인프로젝트->프로젝트 종료일 변경하기(프로젝트 종료일을 변경하면 period도 자동으로 바뀌어버림)
        Controller con = new Controller();
        DAO dao = new DAO(con);

        Scanner sc = new Scanner(System.in);

        // 전역변수 period
        String period = null;
        ArrayList<String> columns = new ArrayList<>();

        columns.add("start_date");

        System.out.print("시작일을 변경할 개인작업이름을 입력하세요>>  ");
        String wname = sc.next();
        String limit = "work_name = " + wname;

        System.out.print("변경될 시작일 (yyyy/mm/dd 형식으로 입력해주세요) >>  ");
        String sdate10 = sc.next();

        dao.update("PROJECT_WORKS", "start_date", sdate10, limit);

        System.out.println("====================변경완료====================");
    }

    public static void Emp_Pro_Update_Edate() {

        // 사원 로그인->프로젝트->개인프로젝트->프로젝트 종료일 변경하기(프로젝트 종료일을 변경하면 period도 자동으로 바뀌어버림)
        Controller con = new Controller();
        DAO dao = new DAO(con);

        Scanner sc = new Scanner(System.in);

        // 전역변수 period
        String period = null;
        ArrayList<String> columns = new ArrayList<>();

        columns.add("end_date");

        System.out.print("종료일을 변경할 개인작업이름을 입력하세요>>  ");
        String wname = sc.next();
        String limit = "work_name = " + wname;

        System.out.print("변경될 종료일 (yyyy/mm/dd 형식으로 입력해주세요) >>  ");
        String edate10 = sc.next();

        dao.update("PROJECT_WORKS", "END_DATE", edate10, limit);

        System.out.println("====================변경완료====================");
    }

    public static void Emp_Pro_Calculate_progress() {
        Controller con = new Controller();
        DAO dao = new DAO(con);

        Scanner sc = new Scanner(System.in);
        ArrayList<String> columns = new ArrayList<>();

        columns.add("project_id");
        columns.add("sign");

        System.out.print("진척률 계산해야 할 프로젝트이름>> ");
        String proid = sc.next();
        String limit = "project_id = " + proid;

        // 진척률 계산하기(project_id에 속해있는 개인일정의 배열이 temparr2, sign에 속해있는 개인일정의 배열)
        ArrayList<ArrayList<String>> tempArray = dao.select("project_works", columns, limit);
        ArrayList<String> temparr2 = new ArrayList<String>();
        ArrayList<String> temparr3 = new ArrayList<String>();
        double cnt = 0;
        for (int i = 0; i < tempArray.size(); i++) {
            for (int j = 0; j < tempArray.get(i).size(); j++) {
                String tempValue2 = tempArray.get(i).get(j);
                if (tempValue2.equals(proid)) {
                    temparr2.add(tempValue2);
                } else {
                    temparr3.add(tempValue2);
                }
            }
        }
        for (int i = 0; i < temparr3.size(); i++) {
            if (temparr3.get(i).equals("1")) {
                cnt++;
            }
        }
        int progress = (int) (cnt / temparr2.size() * 10000);
        double pro2 = (double) progress / 100;
        System.out.println("진척율: " + pro2 + "%");
    }

    public static void Man_Emp_Ser_Name() {
        Scanner sc = new Scanner(System.in);
        String da;
        Controller controller = new Controller();
        DAO dao = new DAO(controller);
        ArrayList<String> a = new ArrayList<>();

        ArrayList<ArrayList<String>> alist = new ArrayList<>();

        a.add("e.employee_id");
        a.add("department_name");
        a.add("jobgrade_name");
        a.add("employee_name");
        a.add("base_salary");
        a.add("address");
        a.add("contact");
        a.add("ssn");
        a.add("hire_date");
        a.add("password");

        // 이름검색
        System.out.print("이름검색 : ");
        String searName = sc.next();
        controller.printSelected(dao.select("employees e, departments d, jobgrades j", a,
                "e.department_id = d.department_id and e.jobgrade_id = j.jobgrade_id and upper(employee_name) like \'%\'||upper("
                        + "\'%" + searName + "%\')||\'%\'"));
    }

    public static void Man_Emp_Ser_Dept() {
        Scanner sc = new Scanner(System.in);
        String da;
        Controller controller = new Controller();
        DAO dao = new DAO(controller);
        ArrayList<String> a = new ArrayList<>();

        ArrayList<ArrayList<String>> alist = new ArrayList<>();

        a.add("e.employee_id");
        a.add("department_name");
        a.add("jobgrade_name");
        a.add("employee_name");
        a.add("base_salary");
        a.add("address");
        a.add("contact");
        a.add("ssn");
        a.add("hire_date");
        a.add("password");

        System.out.print("부서검색 : ");
        String searDept = sc.next();
        controller.printSelected(dao.select("employees e, departments d, jobgrades j", a,
                "e.department_id = d.department_id and e.jobgrade_id = j.jobgrade_id and upper(d.department_name) like \'%\'||upper("
                        + "\'%" + searDept + "%\')||\'%\'"));
    }

    public static void Man_Emp_Ser_Job() {
        Scanner sc = new Scanner(System.in);
        String da;
        Controller controller = new Controller();
        DAO dao = new DAO(controller);
        ArrayList<String> a = new ArrayList<>();

        ArrayList<ArrayList<String>> alist = new ArrayList<>();

        a.add("e.employee_id");
        a.add("department_name");
        a.add("jobgrade_name");
        a.add("employee_name");
        a.add("base_salary");
        a.add("address");
        a.add("contact");
        a.add("ssn");
        a.add("hire_date");
        a.add("password");
        System.out.print("직급검색 : ");
        String searJob = sc.next();
        controller.printSelected(dao.select("employees e, departments d, jobgrades j", a,
                "d.department_id = e.department_id and e.jobgrade_id = j.jobgrade_id and upper(j.jobgrade_name) like \'%\'||upper("
                        + "\'%" + searJob + "%\')||\'%\'"));
    }

    public static void Man_Emp_Update_EmpAD() {
        Scanner sc = new Scanner(System.in);

        Controller controller = new Controller();
        DAO dao = new DAO(controller);

        int empId = sc.nextInt();
        String updateStr = sc.next();
        dao.update("employees", "address", updateStr, "employee_id = " + empId);
    }

    public static void Man_Emp_Del_EmpDel() {
        Scanner sc = new Scanner(System.in);
        Controller controller = new Controller();
        DAO dao = new DAO(controller);

        String delId = sc.next();
        dao.delete("employees", "employee_id = " + delId);
    }

    public static void Man_Emp_Add_EmpAdd() {
        Controller con = new Controller();
        DAO dao = new DAO(con);
        Scanner sc = new Scanner(System.in);
        ArrayList<String> columns = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();

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

        System.out.print("추가할 사원번호>> ");
        String emp_id = sc.next();
        values.add(emp_id);
        System.out.print("추가할 부서번호>> ");
        String dept_id = sc.next();
        values.add(dept_id);
        System.out.print("추가할 직급>> ");
        String jobgrade = sc.next();
        values.add(jobgrade);
        System.out.print("사원 이름>> ");
        String emp_name = sc.next();
        values.add(emp_name);
        System.out.print("사원 기본급>> ");
        String base_salary = sc.next();
        values.add(base_salary);
        System.out.print("사원 주소>> ");
        String address = sc.next();
        values.add(address);
        System.out.print("사원 전화번호>> ");
        String contact = sc.next();
        values.add(contact);
        System.out.print("사원 주민등록번호>> ");
        String ssn = sc.next();
        values.add(ssn);
        System.out.print("입사한 날짜(YYYY/MM/DD)>> ");
        String hire = sc.next();
        values.add(hire);
        System.out.print("패스워드>> ");
        String password = sc.next();
        values.add(password);

        dao.insert("EMPLOYEES", columns, values);
    }

    public static void Man_Cer_Add() {
        // 관리자로 로그인 했을 때 프로젝트 생성하기
        Controller con = new Controller();
        DAO dao = new DAO(con);

        Scanner sc = new Scanner(System.in);

        ArrayList<String> columns = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();

        columns.add("CERTIFICATE_ID");
        columns.add("CERTIFICATE_NAME");
        columns.add("CERTIFICATE_MONEY");

        System.out.print("자격증 번호 : ");
        String Cer_id = sc.next();
        values.add(Cer_id);

        System.out.print("자격증 이름 : ");
        String Cer_name = sc.next();
        values.add(Cer_name);

        System.out.print("자격증 수당 : ");
        String Cer_money = sc.next();
        values.add(Cer_money);

        dao.insert("CERTIFICATE", columns, values);
    }

    public static void Man_Cer_Ser() {
        Controller con = new Controller();
        DAO dao = new DAO(con);
        Scanner sc = new Scanner(System.in);
        ArrayList<String> columns = new ArrayList<>();

        columns.add("CERTIFICATE_ID");
        columns.add("CERTIFICATE_NAME");
        columns.add("CERTIFICATE_MONEY");

        System.out.print("자격증 이름 검색");
        String CerStr = sc.next();
        String limit = "CERTIFICATE_NAME = '" + CerStr + "'";
        con.printSelected(dao.select("CERTIFICATE", columns, limit));

    }

    public static void Man_Cer_AllSer() {
        Controller con = new Controller();
        DAO dao = new DAO(con);
        ArrayList<String> columns = new ArrayList<>();

        columns.add("CERTIFICATE_ID");
        columns.add("CERTIFICATE_NAME");
        columns.add("CERTIFICATE_MONEY");
        con.printSelected(dao.select("CERTIFICATE"));

    }

    public static void Man_Cer_Update() {
        Controller con = new Controller();
        DAO dao = new DAO(con);
        Scanner sc = new Scanner(System.in);
        ArrayList<String> columns = new ArrayList<>();

        columns.add("CERTIFICATE_NAME");
        columns.add("CERTIFICATE_MONEY");

        System.out.print("수정 할 자격증 이름 : ");
        String CerName = sc.next();
        System.out.print("수정 할 자격증 수당 : ");
        String UpMoney = sc.next();
        dao.update("CERTIFICATE", "CERTIFICATE_MONEY", UpMoney, "CERTIFICATE_NAME = " + CerName);
    }

    public static void Man_Cer_Del() {
        Controller con = new Controller();
        DAO dao = new DAO(con);

        Scanner sc = new Scanner(System.in);
        ArrayList<String> columns = new ArrayList<>();

        columns.add("CERTIFICATE_NAME");

        System.out.print("삭제할 자격증 이름 : ");
        String DelName = sc.next();
        dao.delete("CERTIFICATE", "CERTIFICATE_NAME = '" + DelName + "'");
    }

    public static void Man_Pro_AllSer() {
        Controller con = new Controller();
        DAO dao = new DAO(con);
        Scanner sc = new Scanner(System.in);
        // 로그인->프로젝트->프로젝트로 들어가기
        // 실행하면 모든 프로젝트 조회
        // select문에 넣을 columns 가변배열로 등록
        ArrayList<String> columns = new ArrayList<>();
        columns.add("p.project_id");
        columns.add("p.employee_id");
        columns.add("e.employee_name");
        columns.add("p.start_date");
        columns.add("p.end_date");
        // 제약조건
        String limit = "p.employee_id=e.employee_id";

        con.printSelected(dao.select("project p, employees e", columns, limit));

    }

    public static void Man_Pro_EmpNameSer() {
        Controller con = new Controller();
        DAO dao = new DAO(con);
        Scanner sc = new Scanner(System.in);
        // 로그인->프로젝트->프로젝트로 들어가기
        // 프로젝트장의 이름을 입력하면 그에 해당하는 프로젝트 현황을 볼 수 있음
        // select문에 넣을 columns 가변배열로 등록
        ArrayList<String> columns = new ArrayList<>();
        columns.add("p.project_id");
        columns.add("p.employee_id");
        columns.add("e.employee_name");
        columns.add("p.start_date");
        columns.add("p.end_date");
        // 우선은 아무 프로젝트나 들어갈 수 있게 해뒀음(참여자가 아니더라도 프로젝트 참여 가능)
        System.out.print("찾을 프로젝트의 담당자 이름>> ");
        String empid = sc.next();
        // 제약조건
        String limit = "e.employee_name= '" + empid + "'" + " and p.employee_id=e.employee_id";

        con.printSelected(dao.select("project p, employees e", columns, limit));
    }

    public static void Man_Pro_ProNameSer() {
        Controller con = new Controller();
        DAO dao = new DAO(con);
        Scanner sc = new Scanner(System.in);
        // 프로젝트 이름을 통해 프로젝트 들어가기(검색)
        // 가변배열을 통해 select문장 구성
        ArrayList<String> columns = new ArrayList<>();
        columns.add("p.project_id");
        columns.add("p.project_name");
        columns.add("p.employee_id");
        columns.add("e.employee_name");
        columns.add("p.start_date");
        columns.add("p.end_date");
        // 검색할 프로젝트이름이 제약조건
        System.out.print("프로젝트 이름을 입력>> ");
        String proname = sc.next();
        String limit = "e.employee_id=p.employee_id" + " and p.project_name like '%" + proname + "%'";

        con.printSelected(dao.select("project p, employees e", columns, limit));
    }

    public static void Man_Pro_ProInsert() {
        Controller con = new Controller();
        DAO dao = new DAO(con);
        Scanner sc = new Scanner(System.in);
        ArrayList<String> columns = new ArrayList<>();

        columns.add("PROJECT_ID");
        columns.add("PROJECT_NAME");
        columns.add("EMPLOYEE_ID");
        columns.add("START_DATE");
        columns.add("END_DATE");

        ArrayList<String> values = new ArrayList<>();
        // 순차적으로 생성되게 하면 필요X
        System.out.print("추가할 프로젝트번호>> ");
        String proid = sc.next();
//		만약 순차적으로 생성되게 한다면
//		int num = 1;
//  	if(num<=row의숫자){
//      num++;
//      }
//		String proid=num+"";
//        이런 방식인데 정확하진않음

        values.add(proid);
        System.out.print("프로젝트의 이름>> ");
        String proname = sc.next();
        values.add(proname);
        System.out.print("프로젝트 담당자의 사원번호>> ");
        String empid = sc.next();
        values.add(empid);
        System.out.print("프로젝트 시작일(yyyy/mm/dd형식으로 입력할 것)>> ");
        String sdate = sc.next();
        values.add(sdate);
        System.out.print("프로젝트 종료일(yyyy/mm/dd형식으로 입력할 것)>> ");
        String edate = sc.next();
        values.add(edate);

        dao.insert("PROJECT", columns, values);
    }

    public static void Man_Pro_ProWork_EmpName_Ser() {
        Controller con = new Controller();
        DAO dao = new DAO(con);

        Scanner sc = new Scanner(System.in);

        // 로그인->프로젝트->프로젝트로 들어가기
        // 프로젝트장의 이름을 입력하면 그에 해당하는 프로젝트 현황을 볼 수 있음

        // select문에 넣을 columns 가변배열로 등록
        ArrayList<String> columns = new ArrayList<>();

        columns.add("p.project_id");
        columns.add("p.employee_id");
        columns.add("e.employee_name");
        columns.add("p.start_date");
        columns.add("p.end_date");

        // 자기자신의 프로젝트 확인하려면 loginID넣어주면됨
        System.out.print("소속되어있는 프로젝트 멤버의 사원이름>> ");
        String empname = sc.next();
        String limit = "e.employee_name like'%" + empname + "%'"
                + " and w.project_id = p.project_id and w.employee_id=e.employee_id";
        con.printSelected(dao.select("project_works w, project p, employees e", columns, limit));
    }

    public static void Man_Pro_Ser_ProName() {

        Controller con = new Controller();
        DAO dao = new DAO(con);

        Scanner sc = new Scanner(System.in);
        // 로그인->프로젝트->개인 프로젝트로 들어가기
        // 프로젝트이름을 입력하면 해당 프로젝트에서 개인작업을 수행하고있는 사람들의 이름이 나옴

        ArrayList<String> columns = new ArrayList<>();

        columns.add("e.employee_name");

        System.out.print("소속멤버를 볼 프로젝트 이름>> ");
        String proname = sc.next();

        // 제약조건 사원이름으로 받고 조인
        String limit = "p.project_name like '%" + proname + "%'" + " and w.employee_id=e.employee_id"
                + " and w.project_id=p.project_id";

        con.printSelected(dao.select("project p,project_works w, employees e", columns, limit));

        System.out.println("====================완료====================");

    }

    public static void Man_Pro_EmpID() {
        Controller con = new Controller();
        DAO dao = new DAO(con);

        Scanner sc = new Scanner(System.in);
        ArrayList<String> columns = new ArrayList<>();

        columns.add("PROJECT_ID");
        columns.add("PROJECT_NAME");
        columns.add("EMPLOYEE_ID");
        columns.add("START_DATE");
        columns.add("END_DATE");
//		업데이트시에 시작과 끝에 자동으로 '이 붙어서 조건을 2개주기가 난해함.
//		System.out.print("변경할 프로젝트 이름 입력 >> ");
//		String tempLimit2 = sc.next();
        System.out.print("변경할 프로젝트 장의 사원번호 입력 >> ");
        String tempLimit = sc.next();
        String limit = "employee_id = " + tempLimit
//        							+" and project_name ="+tempLimit2
                ;
        System.out.print("새로 임명할 프로젝트 장의 이름 입력 >> ");
        String tempValue = sc.next();

        dao.update("PROJECT", "EMPLOYEE_id", tempValue, limit);
        System.out.println("====================변경완료====================");
        con.printSelected(dao.select("PROJECT"));
    }

    public static void Man_Pro_StartUpdate() {
        Controller con = new Controller();
        DAO dao = new DAO(con);

        Scanner sc = new Scanner(System.in);
        ArrayList<String> columns = new ArrayList<>();

        columns.add("PROJECT_ID");
        columns.add("PROJECT_NAME");
        columns.add("EMPLOYEE_ID");
        columns.add("START_DATE");
        columns.add("END_DATE");

        System.out.print("변경할 프로젝트 번호 입력 >>");
        String num = sc.next();
        String limit = "PROJECT_ID = " + num;

        System.out.print("변경할 시작일 (yyyy/mm/dd 형식으로 입력해주세요) >>  ");
        String tempValue = sc.next();

        dao.update("PROJECT", "START_DATE", tempValue, limit);

        con.printSelected(dao.select("PROJECT"));
    }

    public static void Man_Pro_EndUpdate() {
        Controller con = new Controller();
        DAO dao = new DAO(con);

        Scanner sc = new Scanner(System.in);
        ArrayList<String> columns = new ArrayList<>();

        columns.add("PROJECT_ID");
        columns.add("PROJECT_NAME");
        columns.add("EMPLOYEE_ID");
        columns.add("START_DATE");
        columns.add("END_DATE");

        // 기존의 종료일 입력
        System.out.print("변경할 프로젝트 번호 입력 >>");
        String num = sc.next();
        String limit = "PROJECT_ID = " + num;

        System.out.print("변경할 종료일 (yyyy/mm/dd 형식으로 입력해주세요) >>  ");
        String tempValue = sc.next();

        dao.update("PROJECT", "END_DATE", tempValue, limit);

        con.printSelected(dao.select("PROJECT"));
    }

    public static void Man_Pro_ProNameUpdate() {
        Controller con = new Controller();
        DAO dao = new DAO(con);

        Scanner sc = new Scanner(System.in);
        ArrayList<String> columns = new ArrayList<>();

        columns.add("PROJECT_ID");
        columns.add("PROJECT_NAME");
        columns.add("EMPLOYEE_ID");
        columns.add("START_DATE");
        columns.add("END_DATE");

        System.out.print("변경할 프로젝트 이름 >> ");
        String tempLimit = sc.next();
        String limit = "project_name = " + tempLimit;

        System.out.print("변경될 프로젝트 이름 >>  ");
        String tempValue = sc.next();
        dao.update("PROJECT", "PROJECT_NAME", tempValue, limit);
    }

    public static void Man_Pro_ProDel() {
        Controller con = new Controller();
        DAO dao = new DAO(con);
        Scanner sc = new Scanner(System.in);

        System.out.print("삭제할 프로젝트 번호 : ");
        int DelId = sc.nextInt();
        dao.delete("PROJECT", "PROJECT_ID = " + DelId);
        System.out.println("프로젝트 삭제 완료");
    }

    public static void Man_Msg_Emp() {
        Scanner sc = new Scanner(System.in);

        ArrayList<String> temparr2 = new ArrayList<String>();
        Controller con = new Controller();
        DAO dao = new DAO(con);

        // select를 하기위해 컬럼을 어레이 리스트로 만들어서 넣어줌
        ArrayList<String> Emp_name = new ArrayList<>();
        Emp_name.add("EMPLOYEE_NAME");

        ArrayList<ArrayList<String>> tempArray = new ArrayList<ArrayList<String>>();

        // con.printSelected(dao.select("EMPLOYEES", Emp_name, call_name));
        while (true) {
            System.out.println("[1]호출할 사원 [2]스톱");
            int cho = sc.nextInt();
            if (cho == 2) {
                break;
            }

            System.out.println("호출할 사원번호 입력");
            // 호출할 사원번호를 입력하면 EMPLOYEE_ID = 뒤에 스캐너로 입력받음
            String call_name = "EMPLOYEE_ID= " + sc.next();
            // select문 완성
            tempArray = dao.select("EMPLOYEES", Emp_name, call_name);

            for (int i = 0; i < tempArray.size(); i++) {
                for (int j = 0; j < tempArray.get(i).size(); j++) {
                    String tempValue2 = tempArray.get(i).get(j);
                    temparr2.add(tempValue2);
                }
            }

        }
        System.out.print("장소를 입력해주세요");
        String lc = sc.next();
        System.out.print("시간을 입력해주세요");
        int time = sc.nextInt();
        for (int i = 0; i < temparr2.size(); i++) {
            System.out.print(temparr2.get(i) + "님   ");
            System.out.println(lc + "로  " + time + "시까지 오세요");

        }
    }

    public static void Man_Msg_Dept() {
        Scanner sc = new Scanner(System.in);

        ArrayList<String> temparr2 = new ArrayList<String>();
        Controller con = new Controller();
        DAO dao = new DAO(con);
        ArrayList<String> Emp_name = new ArrayList<>();
        Emp_name.add("EMPLOYEE_NAME");
        ArrayList<String> Emp_id = new ArrayList<>();
        ArrayList<ArrayList<String>> tempArray = new ArrayList<ArrayList<String>>();

        // con.printSelected(dao.select("EMPLOYEES", Emp_name, call_name));

        System.out.println("호출할 부서 입력해주세요");
        String call_name = "DEPARTMENT_ID = " + sc.next();
        tempArray = dao.select("EMPLOYEES", Emp_name, call_name);

        for (int i = 0; i < tempArray.size(); i++) {
            for (int j = 0; j < tempArray.get(i).size(); j++) {
                String tempValue2 = tempArray.get(i).get(j);
                temparr2.add(tempValue2);
            }
        }

        System.out.print("장소를 입력해주세요");
        String lc = sc.next();
        System.out.print("시간을 입력해주세요");
        int time = sc.nextInt();
        for (int i = 0; i < temparr2.size(); i++) {
            System.out.print(temparr2.get(i) + "님   ");
            System.out.println(lc + "로  " + time + "시까지 오세요");

        }
    }

    public static void Man_Msg_Job() {
        Scanner sc = new Scanner(System.in);

        ArrayList<String> temparr2 = new ArrayList<String>();
        Controller con = new Controller();
        DAO dao = new DAO(con);
        ArrayList<String> Emp_name = new ArrayList<>();
        Emp_name.add("EMPLOYEE_NAME");
        ArrayList<String> Emp_id = new ArrayList<>();
        ArrayList<ArrayList<String>> tempArray = new ArrayList<ArrayList<String>>();

        // con.printSelected(dao.select("EMPLOYEES", Emp_name, call_name));

        System.out.println("호출할 직급 입력해주세요");
        String call_name = "JOBGRADE_ID = " + sc.next();
        tempArray = dao.select("EMPLOYEES", Emp_name, call_name);

        for (int i = 0; i < tempArray.size(); i++) {
            for (int j = 0; j < tempArray.get(i).size(); j++) {
                String tempValue2 = tempArray.get(i).get(j);
                temparr2.add(tempValue2);
            }
        }

        System.out.print("장소를 입력해주세요");
        String lc = sc.next();
        System.out.print("시간을 입력해주세요");
        int time = sc.nextInt();
        for (int i = 0; i < temparr2.size(); i++) {
            System.out.print(temparr2.get(i) + "님   ");
            System.out.println(lc + "로  " + time + "시까지 오세요");
        }
    }

}