package salary;

import allowance.AllowanceCalculator;
import allowance.SalaryDependentAllowanceCalculator;
import tax.ITax;

import java.time.LocalDate;

public class SalaryCalculator {
    private AllowanceCalculator allowanceCalculator;
    private SalaryDependentAllowanceCalculator salaryDependentAllowanceCalculator;
    private ITax tax;
    private LocalDate payday;

    public SalaryCalculator(AllowanceCalculator allowanceCalculator,
                            SalaryDependentAllowanceCalculator salaryDependentAllowanceCalculator,
                            ITax tax, LocalDate payday) {
        this.allowanceCalculator = allowanceCalculator;
        this.salaryDependentAllowanceCalculator = salaryDependentAllowanceCalculator;
        this.tax = tax;
        this.payday = payday;
    }

    public double calculateTotalSalary(double baseSalary) {
        double totalSalary = baseSalary;
        totalSalary -=
                salaryDependentAllowanceCalculator.calculateTotalAllowances(baseSalary);
        totalSalary -= allowanceCalculator.calculateTotalAllowances();
        totalSalary -= tax.calculateTotalTax(baseSalary);

        // TODO: Delete later
        //System.out.println("Salary Dependent allowance: " + salaryDependentAllowanceCalculator.calculateTotalAllowances(baseSalary));
        //System.out.println("Not Salary Dependent allowance: " + allowanceCalculator.calculateTotalAllowances());
        //System.out.println("Tax: " + tax.calculateTotalTax(baseSalary));

        return totalSalary;
    }

    public AllowanceCalculator getAllowanceCalculator() {
        return allowanceCalculator;
    }

    public SalaryDependentAllowanceCalculator getSalaryDependentAllowanceCalculator() {
        return salaryDependentAllowanceCalculator;
    }

    public ITax getTax() {
        return tax;
    }

    public LocalDate getPayday() {
        return payday;
    }
}
