package salary;


import java.time.LocalDate;

public class Salary {
    private LocalDate payDay;

    public Salary(LocalDate payDay) {
        this.payDay = payDay;
    }

    public LocalDate getPayDay() {
        return payDay;
    }

    public void setPayDay(LocalDate payDay) {
        this.payDay = payDay;
    }

}

