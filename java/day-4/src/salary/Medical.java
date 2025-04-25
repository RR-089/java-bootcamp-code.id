package salary;

import java.time.LocalDate;

public class Medical extends Salary {
    private double percentage;

    public Medical(LocalDate payDay, double percentage) {
        super(payDay);
        this.percentage = percentage;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
