package allowance;

import java.util.Collections;
import java.util.List;

public class SalaryDependentAllowanceCalculator {
    private List<ISalaryDependentAllowance> allowances;

    public SalaryDependentAllowanceCalculator(List<ISalaryDependentAllowance> allowances) {
        this.allowances = allowances;
    }

    public double calculateTotalAllowances(double baseSalary) {
        double totalAllowances = 0;
        for (ISalaryDependentAllowance allowance : allowances) {
            totalAllowances += allowance.calculateTotalAllowance(baseSalary);
        }
        return totalAllowances;
    }

    public List<ISalaryDependentAllowance> getAllowances() {
        return Collections.unmodifiableList(allowances);
    }
}
