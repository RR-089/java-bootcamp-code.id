package tax;

import po.PurchaseOrder;

public interface Tax {
    double calcTotalTax(PurchaseOrder purchaseOrder, double baseSalary);
}
