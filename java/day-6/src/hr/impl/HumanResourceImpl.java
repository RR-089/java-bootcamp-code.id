package hr.impl;

import allowance.Allowance;
import allowance.NonSalaryDependentAllowanceCalculator;
import allowance.SalaryDependentAllowanceCalculator;
import allowance.nonsalarydependentallowance.Operational;
import allowance.nonsalarydependentallowance.Overtime;
import allowance.salarydependentallowance.Insurance;
import constants.Status;
import employee.Employee;
import hr.HumanResource;
import po.PurchaseOrder;
import salary.Salary;
import tax.Tax;
import tax.impl.TaxProjectImpl;
import tax.impl.TaxSalaryImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class HumanResourceImpl implements HumanResource {
    @Override
    public List<Employee> initEmployeeData() {
        Employee emp1 = new Employee(
                100L, "Anton", LocalDate.of(2020, 2, 4),
                Status.PERMANENT, 20_000_000, List.of(
                new Salary(
                        new Allowance(
                                new NonSalaryDependentAllowanceCalculator(
                                        List.of(
                                                new Overtime(10, 50_000),
                                                new Operational(2, 30_000, 20_000)
                                        )
                                ),
                                new SalaryDependentAllowanceCalculator(
                                        List.of(
                                                new Insurance(1, 2, 2)
                                        )
                                )
                        ),
                        new PurchaseOrder(0),
                        new Tax(
                                new TaxProjectImpl(0),
                                new TaxSalaryImpl(0.5f, 0.5f)
                        ),
                        LocalDateTime.of(2025, 4, 30, 0, 0)
                )
        )
        );

        Employee emp2 = new Employee(
                101L, "Budi", LocalDate.of(2021, 2, 4),
                Status.PERMANENT, 15_000_000, List.of(
                new Salary(
                        new Allowance(
                                new NonSalaryDependentAllowanceCalculator(
                                        List.of(
                                                new Overtime(5, 50_000)
                                        )
                                ),
                                new SalaryDependentAllowanceCalculator(
                                        List.of(
                                                new Insurance(1, 3, 2)
                                        )
                                )
                        ),
                        new PurchaseOrder(0),
                        new Tax(
                                new TaxProjectImpl(0),
                                new TaxSalaryImpl(0.5f, 0.5f)
                        ),
                        LocalDateTime.of(2025, 4, 30, 0, 0)
                )
        )
        );

        Employee emp3 = new Employee(
                102L, "Charlie", LocalDate.of(2022, 2, 4),
                Status.CONTRACT, 15_000_000, List.of(
                new Salary(
                        new Allowance(
                                new NonSalaryDependentAllowanceCalculator(
                                        List.of(
                                                new Overtime(5, 45_000)
                                        )
                                ),
                                new SalaryDependentAllowanceCalculator(
                                        List.of(
                                                new Insurance(1, 0, 2)
                                        )
                                )
                        ),
                        new PurchaseOrder(0),
                        new Tax(
                                new TaxProjectImpl(0),
                                new TaxSalaryImpl(0.5f, 0.5f)
                        ),
                        LocalDateTime.of(2025, 4, 30, 0, 0)
                )
        )
        );

        Employee emp4 = new Employee(
                103L, "Dian", LocalDate.of(2023, 2, 4),
                Status.CONTRACT, 10_000_000, List.of(
                new Salary(
                        new Allowance(
                                new NonSalaryDependentAllowanceCalculator(
                                        List.of(
                                                new Overtime(6, 45_000)
                                        )
                                ),
                                new SalaryDependentAllowanceCalculator(
                                        List.of(
                                                new Insurance(1, 0, 2)
                                        )
                                )
                        ),
                        new PurchaseOrder(0),
                        new Tax(
                                new TaxProjectImpl(0),
                                new TaxSalaryImpl(0.5f, 0.5f)
                        ),
                        LocalDateTime.of(2025, 4, 30, 0, 0)
                )
        )
        );

        Employee emp5 = new Employee(
                104L, "Gita", LocalDate.of(2024, 2, 4),
                Status.TRAINEE, 0, List.of(
                new Salary(
                        new Allowance(
                                new NonSalaryDependentAllowanceCalculator(
                                        List.of(
                                                new Operational(25, 30_000, 20_000)
                                        )
                                ),
                                new SalaryDependentAllowanceCalculator(
                                        List.of(
                                        )
                                )
                        ),
                        new PurchaseOrder(0),
                        new Tax(
                                new TaxProjectImpl(0),
                                new TaxSalaryImpl(0, 0)
                        ),
                        LocalDateTime.of(2025, 4, 30, 0, 0)
                )
        )
        );

        Employee emp6 = new Employee(
                105L, "Rima", LocalDate.of(2025, 2, 4),
                Status.FREELANCER, 0, List.of(
                new Salary(
                        new Allowance(
                                new NonSalaryDependentAllowanceCalculator(
                                        List.of(
                                        )
                                ),
                                new SalaryDependentAllowanceCalculator(
                                        List.of(
                                        )
                                )
                        ),
                        new PurchaseOrder(25_000_000),
                        new Tax(
                                new TaxProjectImpl(2.5f),
                                new TaxSalaryImpl(0, 0)
                        ),
                        LocalDateTime.of(2025, 4, 30, 0, 0)
                )
        )
        );

        return List.of(emp1, emp2, emp3, emp4, emp5, emp6);
    }

    @Override
    public int getTotalEmployee(List<Employee> employees) {
        return employees.size();
    }

    @Override
    public int getTotalEmployeeByStatus(List<Employee> employees, Status status) {
        return (int) employees.stream().filter(employee -> employee.getStatus() == status).count();
    }

    @Override
    public void displayEmployees(List<Employee> employees) {
        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }
    }
}
