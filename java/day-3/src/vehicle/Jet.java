package vehicle;

public class Jet extends Vehicle {
    public Jet(String noPolice, String type, String year, double price, double taxInYear, int seat) {
        super(noPolice, type, year, price, taxInYear, seat);
    }

    @Override
    public String toString() {
        return super.toString() + " (Jet)";
    }
}
