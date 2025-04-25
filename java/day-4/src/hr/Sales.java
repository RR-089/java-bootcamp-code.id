package hr;

import enums.Roles;
import interfaces.ISalary;
import salary.Commission;
import salary.Overtime;

import java.time.LocalDate;

public class Sales extends Employee implements ISalary {
    Commission commission;
    Overtime overtime;

    public Sales(int empId, String fullName, LocalDate hireDate,
                 double salary, Commission commission, Overtime overtime) {
        super(empId, fullName, hireDate, Roles.SALES, salary);
        this.commission = commission;
        this.overtime = overtime;
    }

    @Override
    public void calculateTotalSalary() {
        setTotalSalary(getSalary() + commission.getCommission() + commission.getBonus()
                + overtime.getMeal());
    }
}
