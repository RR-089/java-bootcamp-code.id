package allowance.salarydependentallowance;

import allowance.SalaryDependentAllowance;

public class Insurance implements SalaryDependentAllowance {
    private int self;
    private int dependent;
    private float medicalPercentage;

    public Insurance(int self, int dependent, float medicalPercentage) {
        this.self = self;
        this.dependent = dependent;
        this.medicalPercentage = medicalPercentage;
    }

    @Override
    public double calcTotalAllowance(double baseSalary) {
        return (baseSalary * (medicalPercentage / 100)) * (self + dependent);
    }
}
