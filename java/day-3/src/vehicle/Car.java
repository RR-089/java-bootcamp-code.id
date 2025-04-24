package vehicle;

public class Car extends Vehicle {

    public Car(String noPolice, String type, String year, double price, double taxInYear, int seat) {
        super(noPolice, type, year, price, taxInYear, seat);
    }

    @Override
    public String toString() {
        return super.toString() + " (Car)";
    }
}
