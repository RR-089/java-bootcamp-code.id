import rental.Rental;
import rental.RentalBoat;
import rental.RentalCar;
import rental.RentalJet;

import java.time.LocalDate;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // === SUV ===
        RentalCar suv1 = new RentalCar("D 1001 UM", "SUV", "2010"
                , 350_000_000, 3_500_000, 4, LocalDate.of(2023, 1, 10)
                , 500_000.00, 150_000);


        RentalCar suv2 = new RentalCar("D 1002 UM", "SUV", "2010"
                , 350_000_000, 3_500_000, 4, LocalDate.of(2023, 1, 10)
                , 500_000.00, 150_000);

        RentalCar suv3 = new RentalCar("D 1003 UM", "SUV", "2015"
                , 350_000_000, 3_500_000, 5, LocalDate.of(2023, 1, 12)
                , 500_000.00, 150_000);

        RentalCar suv4 = new RentalCar("D 1004 UM", "SUV", "2015"
                , 350_000_000, 3_500_000, 5, LocalDate.of(2023, 1, 13)
                , 500_000.00, 150_000);

        // === Taxi ===
        RentalCar taxi1 = new RentalCar("D 11 UK", "Taxi", "2002"
                , 175_000_000, 1_750_000, 4, LocalDate.of(2023, 1, 12)
                , 45, 4_500);

        RentalCar taxi2 = new RentalCar("D 12 UK", "Taxi", "2015"
                , 225_000_000, 2_250_000, 4, LocalDate.of(2023, 1, 13)
                , 75, 4_500);

        RentalCar taxi3 = new RentalCar("D 13 UK", "Taxi", "2020"
                , 275_000_000, 2_750_000, 4, LocalDate.of(2023, 1, 13)
                , 90, 4_500);

        // === Jet ===
        RentalJet jet1 = new RentalJet("ID8089", "PrivateJet", "2015"
                , 150_000_000_000.00, 1_500_000_000, 12, LocalDate.of(2023, 12, 23)
                , 55_000_000);

        RentalJet jet2 = new RentalJet("ID8099", "PrivateJet", "2018"
                , 175_000_000_000.00, 1_750_000_000, 15, LocalDate.of(2023, 12, 25)
                , 75_000_000);

        // === Boat ===
        RentalBoat boat1 = new RentalBoat("BOAT18", "PrivateJet", "2020"
                , 2_000_000_000, 20_000_000, 12, LocalDate.of(2023, 12, 25)
                , 10_000_000);

        // === Rental ===
        Rental rental1 = new Rental(suv1, suv2, suv3, suv4, taxi1,
                taxi2, taxi3, jet1, jet2, boat1);


        Map<String, Object> rental1Summary = rental1.getSummary();

        System.out.println("Summary: ");
        for (Map.Entry<String, Object> entry : rental1Summary.entrySet()) {
            if (entry.getValue() instanceof Double) {
                System.out.println(entry.getKey() + ": " + String.format("%.2f", entry.getValue()));
            } else {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }

        System.out.println("\nIndividual income: ");
        System.out.println("================================");
        System.out.println("SUV 1: " + suv1.getTotalIncome());
        System.out.println("SUV 2: " + suv2.getTotalIncome());
        System.out.println("SUV 3: " + suv3.getTotalIncome());
        System.out.println("SUV 4: " + suv4.getTotalIncome());
        System.out.println("================================");
        System.out.println("Taxi 1: " + taxi1.getTotalIncome());
        System.out.println("Taxi 2: " + taxi2.getTotalIncome());
        System.out.println("Taxi 3: " + taxi3.getTotalIncome());
        System.out.println("================================");
        System.out.println("Jet 1: " + String.format("%.2f", jet1.getTotalIncome()));
        System.out.println("Jet 2: " + String.format("%.2f", jet2.getTotalIncome()));
        System.out.println("================================");
        System.out.println("Boat 1: " + String.format("%.2f", boat1.getTotalIncome()));

        // Sub Total
        System.out.println("Sub Total: " + String.format("%.2f", rental1.getTotalIncome()));
    }
}