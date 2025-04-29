package salary.impl;

import allowance.Allowance;
import po.PurchaseOrder;
import salary.Salary;
import tax.Tax;

import java.time.LocalDateTime;

public class SalaryImpl implements Salary {
    private Allowance allowance;
    private PurchaseOrder purchaseOrder;
    private Tax tax;
    private LocalDateTime payDay;

    public SalaryImpl(Allowance allowance, PurchaseOrder purchaseOrder
            , Tax tax, LocalDateTime payDay) {
        this.allowance = allowance;
        this.purchaseOrder = purchaseOrder;
        this.tax = tax;
        this.payDay = payDay;
    }

    @Override
    public double calcTotalSalary(double baseSalary) {
        return (allowance.getNonSalaryDependentAllowanceCalculator().calcTotalAllAllowances() + baseSalary)
                - (allowance.getSalaryDependentAllowanceCalculator().calcTotalAllAllowances(baseSalary) + tax.calcTotalTax(purchaseOrder, baseSalary))
                + purchaseOrder.getPurchaseOrder();
    }

    @Override
    public Allowance getAllowance() {
        return allowance;
    }

    @Override
    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    @Override
    public Tax getTax() {
        return tax;
    }

    @Override
    public LocalDateTime getPayDay() {
        return payDay;
    }

    @Override
    public void setPayDay(LocalDateTime payDay) {
        this.payDay = payDay;
    }
}
