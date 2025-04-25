package hr;

import enums.Roles;
import interfaces.ISalary;
import salary.Overtime;

import java.time.LocalDate;

public class QualityAssurance extends Employee implements ISalary {
    Overtime overtime;

    public QualityAssurance(int empId, String fullName
            , LocalDate hireDate, double salary, Overtime overtime) {
        super(empId, fullName, hireDate, Roles.QA, salary);
        this.overtime = overtime;
    }

    @Override
    public void calculateTotalSalary() {
        setTotalSalary(getSalary() + overtime.getMeal());
    }
}
