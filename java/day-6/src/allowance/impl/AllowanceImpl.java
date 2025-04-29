package allowance.impl;

import allowance.Allowance;
import allowance.NonSalaryDependentAllowanceCalculator;
import allowance.SalaryDependentAllowanceCalculator;

public class AllowanceImpl implements Allowance {
    private final NonSalaryDependentAllowanceCalculator nonSalaryDependentAllowanceCalculator;
    private final SalaryDependentAllowanceCalculator salaryDependentAllowanceCalculator;


    public AllowanceImpl(NonSalaryDependentAllowanceCalculator nonSalaryDependentAllowanceCalculator,
                         SalaryDependentAllowanceCalculator salaryDependentAllowanceCalculator) {
        this.nonSalaryDependentAllowanceCalculator = nonSalaryDependentAllowanceCalculator;
        this.salaryDependentAllowanceCalculator = salaryDependentAllowanceCalculator;
    }

    @Override
    public double calcTotalAllTypeAllowances(double baseSalary) {
        return nonSalaryDependentAllowanceCalculator.calcTotalAllAllowances()
                - salaryDependentAllowanceCalculator.calcTotalAllAllowances(baseSalary);
    }

    @Override
    public NonSalaryDependentAllowanceCalculator getNonSalaryDependentAllowanceCalculator() {
        return nonSalaryDependentAllowanceCalculator;
    }

    @Override
    public SalaryDependentAllowanceCalculator getSalaryDependentAllowanceCalculator() {
        return salaryDependentAllowanceCalculator;
    }
}
