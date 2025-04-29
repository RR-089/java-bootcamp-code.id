package salary;

import allowance.Allowance;
import po.PurchaseOrder;
import tax.Tax;

import java.time.LocalDateTime;

public interface Salary {
    double calcTotalSalary(double baseSalary);

    Allowance getAllowance();

    PurchaseOrder getPurchaseOrder();

    Tax getTax();

    LocalDateTime getPayDay();

    void setPayDay(LocalDateTime payDay);
}
