package tax;

public class Tax implements ITax {
    private float PPHPercentage;
    private float taperaPercentage;
    private float PPNPercentage;

    public Tax(float PPHPercentage, float taperaPercentage, float PPNPercentage) {
        this.PPHPercentage = PPHPercentage;
        this.taperaPercentage = taperaPercentage;
        this.PPNPercentage = PPNPercentage;
    }

    @Override
    public double calculateTotalTax(double baseSalary) {
        return (baseSalary * (PPHPercentage / 100))
                + (baseSalary * (taperaPercentage / 100))
                + (baseSalary * (PPNPercentage / 100));
    }
}
