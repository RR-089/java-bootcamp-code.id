package allowance;

import java.util.List;

public class NonSalaryDependentAllowanceCalculator {
    private List<NonSalaryDependentAllowance> nonSalaryDependentAllowances;

    public NonSalaryDependentAllowanceCalculator(List<NonSalaryDependentAllowance> nonSalaryDependentAllowances) {
        this.nonSalaryDependentAllowances = nonSalaryDependentAllowances;
    }

    public List<NonSalaryDependentAllowance> getNonSalaryDependentAllowances() {
        return nonSalaryDependentAllowances;
    }

    public double calcTotalAllAllowances() {
        double totalAllAllowances = 0;

        for (NonSalaryDependentAllowance allowance : nonSalaryDependentAllowances) {
            totalAllAllowances += allowance.calcTotalAllowance();
        }

        return totalAllAllowances;
    }
}
