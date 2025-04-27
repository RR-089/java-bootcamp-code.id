package employee.impl;

import allowance.*;
import constants.Status;
import employee.Employee;
import preorder.PreOrder;
import salary.SalaryCalculator;
import tax.Tax;

import java.time.LocalDate;
import java.util.List;

public class EmployeeImpl implements IEmployee {

    @Override
    public List<Employee> initEmployess() {
        Employee emp1 = new Employee(100, "Anton",
                LocalDate.of(2020, 4, 2),
                Status.PERMANENT, 20_000_000,
                new SalaryCalculator(
                        new AllowanceCalculator(List.of(
                                new Overtime(10, 50_000)
                        )),
                        new SalaryDependentAllowanceCalculator(List.of(
                                new Insurance(1, 2, 2)
                        )),
                        new PreOrder(0),
                        new Tax(1, 1, 0),
                        LocalDate.of(2025, 4, 30)
                ));

        Employee emp2 = new Employee(101, "Budi",
                LocalDate.of(2021, 4, 2),
                Status.PERMANENT, 15_000_000,
                new SalaryCalculator(
                        new AllowanceCalculator(List.of(
                                new Overtime(5, 50_000)
                        )),
                        new SalaryDependentAllowanceCalculator(List.of(
                                new Insurance(1, 3, 2)
                        )),
                        new PreOrder(0),
                        new Tax(1, 1, 0),
                        LocalDate.of(2025, 4, 30)
                ));

        Employee emp3 = new Employee(102, "Charlie",
                LocalDate.of(2022, 4, 2),
                Status.CONTRACT, 15_000_000,
                new SalaryCalculator(
                        new AllowanceCalculator(List.of(
                                new Overtime(5, 45_000)
                        )),
                        new SalaryDependentAllowanceCalculator(List.of(
                                new Insurance(1, 0, 2)
                        )),
                        new PreOrder(0),
                        new Tax(1, 1, 0),
                        LocalDate.of(2025, 4, 30)
                ));

        Employee emp4 = new Employee(103, "Dian",
                LocalDate.of(2023, 4, 2),
                Status.CONTRACT, 10_000_000,
                new SalaryCalculator(
                        new AllowanceCalculator(List.of(
                                new Overtime(6, 45_000)
                        )),
                        new SalaryDependentAllowanceCalculator(List.of(
                                new Insurance(1, 0, 2)
                        )),
                        new PreOrder(0),
                        new Tax(1, 1, 0),
                        LocalDate.of(2025, 4, 30)
                ));

        Employee emp5 = new Employee(104, "Gita",
                LocalDate.of(2024, 4, 2),
                Status.INTERN, 0,
                new SalaryCalculator(
                        new AllowanceCalculator(
                                List.of(new Operational(25, 30_000, 20_000))
                        ),
                        new SalaryDependentAllowanceCalculator(List.of(
                        )),
                        new PreOrder(0),
                        new Tax(0, 0, 0),
                        LocalDate.of(2025, 4, 30)
                ));

        Employee emp6 = new Employee(105, "Rima",
                LocalDate.of(2025, 4, 2),
                Status.FREELANCE, 0,
                new SalaryCalculator(
                        new AllowanceCalculator(List.of(
                        )),
                        new SalaryDependentAllowanceCalculator(List.of(
                        )),
                        new PreOrder(25_000_000),
                        new Tax(0, 0, 3),
                        LocalDate.of(2025, 4, 30)
                ));


        return List.of(emp1, emp2, emp3, emp4, emp5, emp6);
    }

    @Override
    public void displayEmployess(List<Employee> employees) {
        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }
    }

    @Override
    public void summary(List<Employee> employees) {
        int totalEmployee = employees.toArray().length;
        double totalSalary = 0;
        double totalCalculatedSalary = 0;
        double totalInsurance = 0;
        double totalOvertime = 0;
        double totalAllowance = 0;
        double totalTax = 0;

        for (Employee employee : employees) {
            totalSalary += employee.getBaseSalary();

            totalCalculatedSalary += employee.getSalaryCalculator()
                                             .calculateTotalSalary(employee.getBaseSalary());

            totalInsurance += employee.getSalaryCalculator()
                                      .getSalaryDependentAllowanceCalculator()
                                      .getAllowances()
                                      .stream()
                                      .filter(allowance -> allowance instanceof Insurance)
                                      .mapToDouble(allowance -> ((Insurance) allowance).calculateTotalAllowance(employee.getBaseSalary()))
                                      .sum();

            totalOvertime += employee.getSalaryCalculator()
                                     .getAllowanceCalculator().getAllowances()
                                     .stream()
                                     .filter(allowance -> allowance instanceof Overtime)
                                     .mapToDouble(allowance -> ((Overtime) allowance).calculateTotalAllowance())
                                     .sum();

            totalAllowance += employee.getSalaryCalculator().getAllowanceCalculator().calculateTotalAllowances();

            totalAllowance += employee.getSalaryCalculator().getSalaryDependentAllowanceCalculator().calculateTotalAllowances(employee.getBaseSalary());

            totalTax += employee.getSalaryCalculator().getTax().calculateTotalTax(employee.getBaseSalary());
        }

        System.out.println("========== Employee Summary ==========");
        System.out.printf("Total Employees           : %d\n", totalEmployee);
        System.out.printf("Total Base Salaries       : %.2f\n", totalSalary);
        System.out.printf("Total Calculated Salaries : %.2f\n", totalCalculatedSalary);
        System.out.printf("Total Insurance           : %.2f\n", totalInsurance);
        System.out.printf("Total Overtime Pay        : %.2f\n", totalOvertime);
        System.out.printf("Total Allowances          : %.2f\n", totalAllowance);
        System.out.printf("Total Tax Deducted        : %.2f\n", totalTax);
        System.out.println("======================================");
    }
}
