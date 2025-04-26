package allowance;

public class Insurance implements ISalaryDependentAllowance {
    private int self;
    private int dependent;
    private float medicalPercentage;

    public Insurance(int self, int dependent, float medicalPercentage) {
        this.self = self;
        this.dependent = dependent;
        this.medicalPercentage = medicalPercentage;
    }

    @Override
    public double calculateTotalAllowance(double baseSalary) {
        return ((self + dependent) * (medicalPercentage / 100)) * baseSalary;
    }
}
