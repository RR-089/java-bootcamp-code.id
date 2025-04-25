package implementations;


import hr.Employee;
import hr.Programmer;
import hr.QualityAssurance;
import hr.Sales;
import interfaces.IEmployee;
import interfaces.ISalary;
import salary.Commission;
import salary.Overtime;
import salary.Transportasi;

import java.time.LocalDate;
import java.util.List;

public class EmployeeImpl implements IEmployee {
    @Override
    public List<Employee> initListEmployee() {

        Programmer emp1 = new Programmer(101, "Yuli",
                LocalDate.of(2025, 12, 12), 6_000_000,
                new Transportasi(LocalDate.now(), 500_000, 150_000, 100_000),
                new Overtime(LocalDate.now(), 100_000)
        );

        Programmer emp2 = new Programmer(102, "Widi",
                LocalDate.of(2025, 12, 12), 5_000_000,
                new Transportasi(LocalDate.now(), 100_000, 50_000, 100_000),
                new Overtime(LocalDate.now(), 200_000)
        );

        Sales emp3 = new Sales(103, "Budi", LocalDate.of(2025, 12, 14),
                5_100_000, new Commission(LocalDate.now(), 1_000_000,
                200_000), new Overtime(LocalDate.now(), 100_000)
        );

        Sales emp4 = new Sales(104, "Dudi", LocalDate.of(2025, 12, 14),
                5_100_000, new Commission(LocalDate.now(), 100_000,
                50_000), new Overtime(LocalDate.now(), 0)
        );

        QualityAssurance emp5 = new QualityAssurance(105, "Sri", LocalDate.of(2025, 12
                , 20), 6_000_000, new Overtime(LocalDate.now(), 200_000)
        );


        QualityAssurance emp6 = new QualityAssurance(106, "Azis", LocalDate.of(2025, 12
                , 22), 6_000_000, new Overtime(LocalDate.now(), 300_000)
        );


        return List.of(emp1, emp2, emp3, emp4, emp5, emp6);
    }

    @Override
    public void displayEmployees(List<Programmer> employees) {
        for (Employee emp : employees) {

            System.out.println(emp.toString());
        }
    }

    @Override
    public void generateSalary(List<Programmer> employees) {
        for (Programmer emp : employees) {
            emp.calculateTotalSalary();
        }
    }

    @Override
    public void displayAllEmployees(List<Employee> employees) {
        for (Employee emp : employees) {
            System.out.println(emp.toString());
        }
    }

    @Override
    public void generateAllSalaries(List<Employee> employees) {
        for (Employee emp : employees) {
            if (emp instanceof ISalary) {
                ((ISalary) emp).calculateTotalSalary();
            }
        }
    }
}