package rental;

import vehicle.Jet;

import java.time.LocalDate;

public class RentalJet extends Jet {
    private LocalDate transactionDate;

    //private double orderPerHours;
    private double order;

    public RentalJet(String noPolice, String type, String year, double price,
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
