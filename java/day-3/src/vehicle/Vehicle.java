package vehicle;

public class Vehicle {
    public int seat;
    private String noPolice;
    private String type;
    private String year;
    private double price;
    private double taxInYear;

    public Vehicle() {
    }

    public Vehicle(String noPolice, String type, String year, double price, double taxInYear,
                   int seat) {
        this.seat = seat;
        this.noPolice = noPolice;
        this.type = type;
        this.year = year;
        this.price = price;
        this.taxInYear = taxInYear;
    }

    public String getNoPolice() {
        return noPolice;
    }

    public void setNoPolice(String noPolice) {
        this.noPolice = noPolice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTaxInYear() {
        return taxInYear;
    }

    public void setTaxInYear(double taxInYear) {
        this.taxInYear = taxInYear;
    }

    @Override
    public String toString() {
        return "Vechile{" +
                "noPolice='" + noPolice + '\'' +
                ", type='" + type + '\'' +
                ", year='" + year + '\'' +
                ", price=" + price +
                ", taxInYear=" + taxInYear +
                ", seat=" + seat +
                '}';
    }
}
