package allowance.nonsalarydependentallowance;

import allowance.NonSalaryDependentAllowance;
import allowance.NonSalaryDependentAllowanceCalculator;

import java.util.List;

public class NonSalaryDependentAllowanceCalculatorImpl implements NonSalaryDependentAllowanceCalculator {
    private List<NonSalaryDependentAllowance> nonSalaryDependentAllowances;

    public NonSalaryDependentAllowanceCalculatorImpl(List<NonSalaryDependentAllowance> nonSalaryDependentAllowances) {
        this.nonSalaryDependentAllowances = nonSalaryDependentAllowances;
    }

    public List<NonSalaryDependentAllowance> getNonSalaryDependentAllowances() {
        return nonSalaryDependentAllowances;
    }

    @Override
    public double calcTotalAllAllowances() {
        double totalAllAllowances = 0;

        for (NonSalaryDependentAllowance allowance : nonSalaryDependentAllowances) {
            totalAllAllowances += allowance.calcTotalAllowance();
        }

        return totalAllAllowances;
    }
}
