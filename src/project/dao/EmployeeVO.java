package project.dao;

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

    public String getEmployee_id() {
        return Integer.toString(employee_id);
    }

    public String getDepartment_id() {
        return Integer.toString(department_id);
    }

    public String getJobgrade_id() {
        return Integer.toString(jobgrade_id);
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public String getBase_salary() {
        return Integer.toString(base_salary);
    }

    public String getAddress() {
        return address;
    }

    public String getContact() {
        return contact;
    }

    public String getSsn() {
        return ssn;
    }

    public String getHire_date() {
        return hire_date;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return employee_id+"\t"+department_id+"\t"+jobgrade_id+"\t"+employee_name+"\t"+base_salary+"\t"+address+"\t"+contact+"\t"+ssn
                +"\t"+hire_date+"\t"+password;
    }
}
