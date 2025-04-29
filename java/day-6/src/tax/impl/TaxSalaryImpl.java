package tax.impl;

import tax.TaxSalary;

public class TaxSalaryImpl implements TaxSalary {
    private float pphPercentage;
    private float taperaPercentage;

    public TaxSalaryImpl(float pphPercentage, float taperaPercentage) {
        this.pphPercentage = pphPercentage;
        this.taperaPercentage = taperaPercentage;
    }

    @Override
    public double calcTotalTaxSalary(double baseSalary) {
        return (baseSalary * (pphPercentage / 100)) + (baseSalary * (taperaPercentage / 100));
    }

    public double getPphPercentage() {
        return pphPercentage;
    }

    public void setPphPercentage(float pphPercentage) {
        this.pphPercentage = pphPercentage;
    }

    public double getTaperaPercentage() {
        return taperaPercentage;
    }

    public void setTaperaPercentage(float taperaPercentage) {
        this.taperaPercentage = taperaPercentage;
    }
}
