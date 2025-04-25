package salary;

import java.time.LocalDate;

public class Overtime extends Salary {
    private double meal;

    public Overtime(LocalDate payDay, double meal) {
        super(payDay);
        this.meal = meal;
    }

    public double getMeal() {
        return meal;
    }

    public void setMeal(double meal) {
        this.meal = meal;
    }
}
