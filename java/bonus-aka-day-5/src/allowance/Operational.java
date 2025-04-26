package allowance;

public class Operational implements IAllowance {
    private double days;
    private double lunch;
    private double transport;

    public Operational(double days, double lunch, double transport) {
        this.days = days;
        this.lunch = lunch;
        this.transport = transport;
    }

    @Override
    public double calculateTotalAllowance() {
        return days * (lunch + transport);
    }
}
