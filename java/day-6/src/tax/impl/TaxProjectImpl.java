package tax.impl;

import po.PurchaseOrder;
import tax.TaxProject;

public class TaxProjectImpl implements TaxProject {
    private float ppnPercentage;


    public TaxProjectImpl(float ppnPercentage) {
        this.ppnPercentage = ppnPercentage;
    }

    @Override
    public double calcPpn(PurchaseOrder po) {
        return (ppnPercentage / 100) * po.getPurchaseOrder();
    }

    public double getPpnPercentage() {
        return ppnPercentage;
    }

    public void setPpnPercentage(float ppnPercentage) {
        this.ppnPercentage = ppnPercentage;
    }
}
