package employee.impl;

import constants.Status;
import employee.Employee;
import salary.Salary;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

public class EmployeeImpl implements Employee {
    private Long empNo;
    private String fullName;
    private LocalDate hireDate;
    private Status status;
    private double salary;

    private List<Salary> salaries;

    public EmployeeImpl(Long empNo, String fullName, LocalDate hireDate, Status status,
                        double salary,
                        List<Salary> salaries) {
        this.empNo = empNo;
        this.fullName = fullName;
        this.hireDate = hireDate;
        this.status = status;
        this.salary = salary;
        this.salaries = salaries;
    }

    @Override
    public List<Salary> getSalaries() {
        return salaries;
    }

    @Override
    public void addNewSalary(Salary salary) {
        salaries.add(salary);
    }

    @Override
    public Long getEmpNo() {
        return empNo;
    }

    @Override
    public void setEmpNo(Long empNo) {
        this.empNo = empNo;
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public LocalDate getHireDate() {
        return hireDate;
    }

    @Override
    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public double getSalary() {
        return salary;
    }

    @Override
    public void setSalary(double salary) {
        this.salary = salary;
    }


    @Override
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
