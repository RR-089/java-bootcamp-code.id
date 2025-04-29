package salary;

import allowance.Allowance;
import po.PurchaseOrder;
import tax.Tax;

import java.time.LocalDateTime;

public class Salary {
    private Allowance allowance;
    private PurchaseOrder purchaseOrder;
    private Tax tax;
    private LocalDateTime payDay;

    public Salary(Allowance allowance, PurchaseOrder purchaseOrder
            , Tax tax, LocalDateTime payDay) {
        this.allowance = allowance;
        this.purchaseOrder = purchaseOrder;
        this.tax = tax;
        this.payDay = payDay;
    }

    public double calcTotalSalary(double baseSalary) {
        return (allowance.getNonSalaryDependentAllowanceCalculator().calcTotalAllAllowances() + baseSalary)
                - (allowance.getSalaryDependentAllowanceCalculator().calcTotalAllAllowances(baseSalary) + tax.calcTotalTax(purchaseOrder, baseSalary))
                + purchaseOrder.getPurchaseOrder();
    }

    public Allowance getAllowance() {
        return allowance;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public Tax getTax() {
        return tax;
    }

    public LocalDateTime getPayDay() {
        return payDay;
    }

    public void setPayDay(LocalDateTime payDay) {
        this.payDay = payDay;
    }
}
