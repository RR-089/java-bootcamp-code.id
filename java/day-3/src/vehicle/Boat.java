package vehicle;

public class Boat extends Vehicle {
    public Boat(String noPolice, String type, String year, double price, double taxInYear, int seat) {
        super(noPolice, type, year, price, taxInYear, seat);
    }

    @Override
    public String toString() {
        return super.toString() + " (Boat)";
    }
}
