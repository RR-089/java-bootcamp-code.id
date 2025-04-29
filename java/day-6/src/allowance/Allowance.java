package allowance;

public class Allowance {
    private final NonSalaryDependentAllowanceCalculator nonSalaryDependentAllowanceCalculator;
    private final SalaryDependentAllowanceCalculator salaryDependentAllowanceCalculator;

    public Allowance(NonSalaryDependentAllowanceCalculator nonSalaryDependentAllowanceCalculator,
                     SalaryDependentAllowanceCalculator salaryDependentAllowanceCalculator) {
        this.nonSalaryDependentAllowanceCalculator = nonSalaryDependentAllowanceCalculator;
        this.salaryDependentAllowanceCalculator = salaryDependentAllowanceCalculator;
    }

    public double calcTotalAllTypeAllowances(double baseSalary) {
        return nonSalaryDependentAllowanceCalculator.calcTotalAllAllowances()
                - salaryDependentAllowanceCalculator.calcTotalAllAllowances(baseSalary);
    }

    public NonSalaryDependentAllowanceCalculator getNonSalaryDependentAllowanceCalculator() {
        return nonSalaryDependentAllowanceCalculator;
    }

    public SalaryDependentAllowanceCalculator getSalaryDependentAllowanceCalculator() {
        return salaryDependentAllowanceCalculator;
    }
}
