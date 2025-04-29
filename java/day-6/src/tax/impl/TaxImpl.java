package tax.impl;

import po.PurchaseOrder;
import tax.Tax;
import tax.TaxProject;
import tax.TaxSalary;

public class TaxImpl implements Tax {
    private TaxProject taxProject;
    private TaxSalary taxSalary;

    public TaxImpl(TaxProject taxProject, TaxSalary taxSalary) {
        this.taxProject = taxProject;
        this.taxSalary = taxSalary;
    }

    @Override
    public double calcTotalTax(PurchaseOrder purchaseOrder, double baseSalary) {
        return taxProject.calcPpn(purchaseOrder) + taxSalary.calcTotalTaxSalary(baseSalary);
    }
}
