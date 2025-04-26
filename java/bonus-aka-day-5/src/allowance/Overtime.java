package allowance;

public class Overtime implements IAllowance {
    private double hours;
    private double payment;

    public Overtime(double hours, double payment) {
        this.hours = hours;
        this.payment = payment;
    }

    @Override
    public double calculateTotalAllowance() {
        return hours * payment;
    }
}
