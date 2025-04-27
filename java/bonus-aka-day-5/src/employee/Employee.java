package employee;

import constants.Status;
import salary.SalaryCalculator;

import java.time.LocalDate;

public class Employee {
    private int empNo;
    private String fullName;
    private LocalDate hireDate;
    private Status status;

    private double baseSalary;

    private SalaryCalculator salaryCalculator;


    public Employee(int empNo, String fullName, LocalDate hireDate,
                    Status status, double baseSalary, SalaryCalculator salaryCalculator) {
        this.empNo = empNo;
        this.fullName = fullName;
        this.hireDate = hireDate;
        this.status = status;
        this.baseSalary = baseSalary;
        this.salaryCalculator = salaryCalculator;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
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

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public SalaryCalculator getSalaryCalculator() {
        return salaryCalculator;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empNo=" + empNo +
                ", fullName='" + fullName + '\'' +
                ", hireDate=" + hireDate +
                ", status=" + status +
                ", baseSalary=" + String.format("%.2f", baseSalary) +
                ", totalSalary=" + String.format("%.2f",
                salaryCalculator.calculateTotalSalary(baseSalary)) +
                '}';
    }
}
