package tax;

import po.PurchaseOrder;

public interface TaxProject {
    double calcPpn(PurchaseOrder po);
}
