package allowance.nonsalarydependentallowance;

import allowance.NonSalaryDependentAllowance;

public class Operational implements NonSalaryDependentAllowance {
    private double days;
    private double lunch;
    private double transport;

    public Operational(double days, double lunch, double transport) {
        this.days = days;
        this.lunch = lunch;
        this.transport = transport;
    }

    @Override
    public double calcTotalAllowance() {
        return days * (lunch + transport);
    }

}
