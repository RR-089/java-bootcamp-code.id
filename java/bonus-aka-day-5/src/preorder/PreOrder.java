package preorder;

public class PreOrder implements IPreOrder {
    private double paid;

    public PreOrder(double paid) {
        this.paid = paid;
    }


    @Override
    public double calculateTotalPreOrder() {
        return paid;
    }
}
