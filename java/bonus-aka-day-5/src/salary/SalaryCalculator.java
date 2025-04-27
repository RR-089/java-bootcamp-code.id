package salary;

import allowance.AllowanceCalculator;
import allowance.SalaryDependentAllowanceCalculator;
import preorder.IPreOrder;
import tax.ITax;

import java.time.LocalDate;

public class SalaryCalculator {
    /* TODO: Consider splitting the AllowanceCalculator into two distinct calculators:
     * 1. NonSalaryDependentAllowanceCalculator: Calculates allowances that do not
     * depend on the salary.
     * 2. SalaryDependentAllowanceCalculator: Calculates allowances based on the salary.
     * Alternatively, rename AllowanceCalculator to
     * NonSalaryDependentAllowanceCalculator for clarity.
     */

    private AllowanceCalculator allowanceCalculator;
    private SalaryDependentAllowanceCalculator salaryDependentAllowanceCalculator;
    private ITax tax;
    private IPreOrder preOrder;
    private LocalDate payday;

    public SalaryCalculator(AllowanceCalculator allowanceCalculator,
                            SalaryDependentAllowanceCalculator salaryDependentAllowanceCalculator,
                            IPreOrder preOrder, ITax tax, LocalDate payday) {
        this.allowanceCalculator = allowanceCalculator;
        this.salaryDependentAllowanceCalculator = salaryDependentAllowanceCalculator;
        this.preOrder = preOrder;
        this.tax = tax;
        this.payday = payday;
    }

    public double calculateTotalSalary(double baseSalary) {
        double totalSalary = baseSalary;
        totalSalary +=
                salaryDependentAllowanceCalculator.calculateTotalAllowances(baseSalary);
        totalSalary += allowanceCalculator.calculateTotalAllowances();
        totalSalary += preOrder.calculateTotalPreOrder();
        totalSalary -= tax.calculateTotalTax(totalSalary);

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
