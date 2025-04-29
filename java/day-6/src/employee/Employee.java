package employee;

import constants.Status;
import salary.Salary;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

public class Employee {
    private Long empNo;
    private String fullName;
    private LocalDate hireDate;
    private Status status;
    private double salary;

    private List<Salary> salaries;

    public Employee(Long empNo, String fullName, LocalDate hireDate, Status status,
                    double salary,
                    List<Salary> salaries) {
        this.empNo = empNo;
        this.fullName = fullName;
        this.hireDate = hireDate;
        this.status = status;
        this.salary = salary;
        this.salaries = salaries;
    }

    public List<Salary> getSalaries() {
        return salaries;
    }

    public void addNewSalary(Salary salary) {
        salaries.add(salary);
    }

    public Long getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Long empNo) {
        this.empNo = empNo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }


    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##"); // Format to two decimal places
        return "EmployeeImpl{" +
                "empNo=" + empNo +
                ", fullName='" + fullName + '\'' +
                ", hireDate=" + hireDate +
                ", status=" + status +
                ", baseSalary=" + decimalFormat.format(salary) +
                ", totalSalary=" + decimalFormat.format(
                salaries.stream()
                        .mapToDouble(salary -> salary.calcTotalSalary(this.salary))
                        .sum()
        ) +
                '}';
    }

}
