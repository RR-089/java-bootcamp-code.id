package rental;

import vehicle.Car;

import java.time.LocalDate;

public class RentalCar extends Car {
    private LocalDate transactionDate;
    private double rentPrice;
    private double driverPrice;

    private int order;
    private double orderPerKm;

    public RentalCar(String noPolice, String type, String year, double price,
                     double taxInYear, int seat
            , LocalDate transactionDate, double rentPrice, double driverPrice) {
        super(noPolice, type, year, price, taxInYear, seat);
        this.transactionDate = transactionDate;
        this.rentPrice = rentPrice;
        this.driverPrice = driverPrice;
    }

    public RentalCar(String noPolice, String type, String year, double price,
                     double taxInYear, int seat
            , LocalDate transactionDate, int order, double orderPerKm) {
        super(noPolice, type, year, price, taxInYear, seat);
        this.transactionDate = transactionDate;
        this.order = order;
        this.orderPerKm = orderPerKm;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(double rentPrice) {
        this.rentPrice = rentPrice;
    }

    public double getDriverPrice() {
        return driverPrice;
    }

    public void setDriverPrice(double driverPrice) {
        this.driverPrice = driverPrice;
    }

    public double getTotalIncome() {
        return
                (this.rentPrice + this.driverPrice)
                        + (this.order * this.orderPerKm);
    }

}
