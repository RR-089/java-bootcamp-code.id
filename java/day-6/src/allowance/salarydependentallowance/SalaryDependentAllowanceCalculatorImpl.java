package allowance.salarydependentallowance;

import allowance.SalaryDependentAllowance;
import allowance.SalaryDependentAllowanceCalculator;

import java.util.List;

public class SalaryDependentAllowanceCalculatorImpl implements SalaryDependentAllowanceCalculator {
    private List<SalaryDependentAllowance> salaryDependentAllowances;

    public SalaryDependentAllowanceCalculatorImpl(List<SalaryDependentAllowance> salaryDependentAllowances) {
        this.salaryDependentAllowances = salaryDependentAllowances;
    }

    @Override
    public double calcTotalAllAllowances(double baseSalary) {
        double totalAllAllowances = 0;
        for (SalaryDependentAllowance allowance : salaryDependentAllowances) {
            totalAllAllowances += allowance.calcTotalAllowance(baseSalary);
        }
        return totalAllAllowances;
    }

    public List<SalaryDependentAllowance> getSalaryDependentAllowances() {
        return salaryDependentAllowances;
    }
}
