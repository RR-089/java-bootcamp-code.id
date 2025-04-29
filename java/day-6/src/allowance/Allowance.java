package allowance;

public interface Allowance {
    double calcTotalAllTypeAllowances(double baseSalary);

    NonSalaryDependentAllowanceCalculator getNonSalaryDependentAllowanceCalculator();

    SalaryDependentAllowanceCalculator getSalaryDependentAllowanceCalculator();
}
