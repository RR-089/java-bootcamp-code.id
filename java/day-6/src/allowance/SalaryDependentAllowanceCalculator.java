package allowance;

import java.util.List;

public class SalaryDependentAllowanceCalculator {
    private List<SalaryDependentAllowance> salaryDependentAllowances;

    public SalaryDependentAllowanceCalculator(List<SalaryDependentAllowance> salaryDependentAllowances) {
        this.salaryDependentAllowances = salaryDependentAllowances;
    }

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
