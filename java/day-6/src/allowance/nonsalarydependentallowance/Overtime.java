package allowance.nonsalarydependentallowance;

import allowance.NonSalaryDependentAllowance;

public class Overtime implements NonSalaryDependentAllowance {
    private double hours;
    private double payment;

    public Overtime(double hours, double payment) {
        this.hours = hours;
        this.payment = payment;
    }

    @Override
    public double calcTotalAllowance() {
        return hours * payment;
    }
}
