package allowance;

public class PreOrder implements IAllowance {
    private double paid;

    public PreOrder(double paid) {
        this.paid = paid;
    }


    @Override
    public double calculateTotalAllowance() {
        return paid;
    }
}
