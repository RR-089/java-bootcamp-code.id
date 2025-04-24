package rental;

import vehicle.Boat;

import java.time.LocalDate;

public class RentalBoat extends Boat {
    private LocalDate transactionDate;

    private double order;

    public RentalBoat(String noPolice, String type, String year, double price,
                      double taxInYear, int seat, LocalDate transactionDate,
                      double order) {
        super(noPolice, type, year, price, taxInYear, seat);
        this.transactionDate = transactionDate;
        this.order = order;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getOrder() {
        return order;
    }

    public void setOrder(double order) {
        this.order = order;
    }

    public double getTotalIncome() {
        return this.order;
    }
}
