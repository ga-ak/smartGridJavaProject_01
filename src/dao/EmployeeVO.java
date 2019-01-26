package dao;

public class EmployeeVO {
    private int employee_id;
    private int department_id;
    private int jobgrade_id;
    private String employee_name;
    private int base_salary;
    private String address;
    private String contact;
    private String ssn;
    private String hire_date;
    private String password;

    public EmployeeVO(int employee_id, int department_id, int jobgrade_id, String employee_name, int base_salary, String address, String contact, String ssn, String hire_date, String password) {
        this.employee_id = employee_id;
        this.department_id = department_id;
        this.jobgrade_id = jobgrade_id;
        this.employee_name = employee_name;
        this.base_salary = base_salary;
        this.address = address;
        this.contact = contact;
        this.ssn = ssn;
        this.hire_date = hire_date;
        this.password = password;
    }

    @Override
    public String toString() {
        return employee_id+"\t"+department_id+"\t"+jobgrade_id+"\t"+employee_name+"\t"+base_salary+"\t"+address+"\t"+contact+"\t"+ssn
                +"\t"+hire_date+"\t"+password;
    }
}
