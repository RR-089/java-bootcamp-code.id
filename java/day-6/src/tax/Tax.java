package tax;

import po.PurchaseOrder;

public class Tax {
    private TaxProject taxProject;
    private TaxSalary taxSalary;

    public Tax(TaxProject taxProject, TaxSalary taxSalary) {
        this.taxProject = taxProject;
        this.taxSalary = taxSalary;
    }

    public double calcTotalTax(PurchaseOrder purchaseOrder, double baseSalary) {
        return taxProject.calcPpn(purchaseOrder) + taxSalary.calcTotalTaxSalary(baseSalary);
    }
}
