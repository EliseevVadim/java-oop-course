package models;

public class Employee {
    private long id;
    private String employeeFIO;
    private String employeeINN;

    public Employee(long id, String employeeFIO, String employeeINN) {
        this.id = id;
        this.employeeFIO = employeeFIO;
        this.employeeINN = employeeINN;
    }

    public Employee(String employeeFIO, String employeeINN) {
        this.employeeFIO = employeeFIO;
        this.employeeINN = employeeINN;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmployeeFIO() {
        return employeeFIO;
    }

    public void setEmployeeFIO(String employeeFIO) {
        this.employeeFIO = employeeFIO;
    }

    public String getEmployeeINN() {
        return employeeINN;
    }

    public void setEmployeeINN(String employeeINN) {
        this.employeeINN = employeeINN;
    }
}
