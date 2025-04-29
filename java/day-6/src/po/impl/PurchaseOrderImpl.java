package po.impl;

import po.PurchaseOrder;

public class PurchaseOrderImpl implements PurchaseOrder {
    private double purchaseOrder;

    public PurchaseOrderImpl(double purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public double getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(double purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }
}
