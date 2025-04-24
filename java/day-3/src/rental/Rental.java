package rental;

import vehicle.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rental {
    List<Vehicle> rentalVehicles;

    public Rental() {
        this.rentalVehicles = new ArrayList<>();
    }

    public Rental(Vehicle... vehicles) {
        this.rentalVehicles = List.of(vehicles);
    }

    public double getTotalIncome() {
        double sumIncome = 0;

        for (Vehicle vehicle : rentalVehicles) {
            if (vehicle instanceof RentalCar) {
                sumIncome += ((RentalCar) vehicle).getTotalIncome();
            } else if (vehicle instanceof RentalBoat) {
                sumIncome += ((RentalBoat) vehicle).getTotalIncome();
            } else if (vehicle instanceof RentalJet) {
                sumIncome += ((RentalJet) vehicle).getTotalIncome();
            }
        }

        return sumIncome;
    }

    public Map<String, Object> getSummary() {
        double sumTax = 0;
        int sumCar = 0;
        double sumIncomeCar = 0;
        int sumBoat = 0;
        double sumIncomeBoat = 0;
        int sumJet = 0;
        double sumIncomeJet = 0;

        for (Vehicle vehicle : rentalVehicles) {
            sumTax += vehicle.getTaxInYear();

            if (vehicle instanceof RentalCar) {
                sumCar++;
                sumIncomeCar += ((RentalCar) vehicle).getTotalIncome();
            } else if (vehicle instanceof RentalBoat) {
                sumBoat++;
                sumIncomeBoat += ((RentalBoat) vehicle).getTotalIncome();
            } else if (vehicle instanceof RentalJet) {
                sumJet++;
                sumIncomeJet += ((RentalJet) vehicle).getTotalIncome();
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("sumTax", sumTax);
        result.put("sumCar", sumCar);
        result.put("sumIncomeCar", sumIncomeCar);
        result.put("sumBoat", sumBoat);
        result.put("sumIncomeBoat", sumIncomeBoat);
        result.put("sumJet", sumJet);
        result.put("sumIncomeJet", sumIncomeJet);
        
        return result;
    }


}
