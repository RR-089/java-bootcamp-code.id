package hr.impl;

import allowance.impl.AllowanceImpl;
import allowance.nonsalarydependentallowance.NonSalaryDependentAllowanceCalculatorImpl;
import allowance.nonsalarydependentallowance.Operational;
import allowance.nonsalarydependentallowance.Overtime;
import allowance.salarydependentallowance.Insurance;
import allowance.salarydependentallowance.SalaryDependentAllowanceCalculatorImpl;
import constants.Status;
import employee.Employee;
import employee.impl.EmployeeImpl;
import hr.HumanResource;
import po.impl.PurchaseOrderImpl;
import salary.impl.SalaryImpl;
import tax.impl.TaxImpl;
import tax.impl.TaxProjectImpl;
import tax.impl.TaxSalaryImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class HumanResourceImpl implements HumanResource {
    @Override
    public List<Employee> initEmployeeData() {
        Employee emp1 = new EmployeeImpl(
                100L, "Anton", LocalDate.of(2020, 2, 4),
                Status.PERMANENT, 20_000_000, List.of(
                new SalaryImpl(
                        new AllowanceImpl(
                                new NonSalaryDependentAllowanceCalculatorImpl(
                                        List.of(
                                                new Overtime(10, 50_000),
                                                new Operational(2, 30_000, 20_000)
                                        )
                                ),
                                new SalaryDependentAllowanceCalculatorImpl(
                                        List.of(
                                                new Insurance(1, 2, 2)
                                        )
                                )
                        ),
                        new PurchaseOrderImpl(0),
                        new TaxImpl(
                                new TaxProjectImpl(0),
                                new TaxSalaryImpl(0.5f, 0.5f)
                        ),
                        LocalDateTime.of(2025, 4, 30, 0, 0)
                )
        )
        );

        Employee emp2 = new EmployeeImpl(
                101L, "Budi", LocalDate.of(2021, 2, 4),
                Status.PERMANENT, 15_000_000, List.of(
                new SalaryImpl(
                        new AllowanceImpl(
                                new NonSalaryDependentAllowanceCalculatorImpl(
                                        List.of(
                                                new Overtime(5, 50_000)
                                        )
                                ),
                                new SalaryDependentAllowanceCalculatorImpl(
                                        List.of(
                                                new Insurance(1, 3, 2)
                                        )
                                )
                        ),
                        new PurchaseOrderImpl(0),
                        new TaxImpl(
                                new TaxProjectImpl(0),
                                new TaxSalaryImpl(0.5f, 0.5f)
                        ),
                        LocalDateTime.of(2025, 4, 30, 0, 0)
                )
        )
        );

        Employee emp3 = new EmployeeImpl(
                102L, "Charlie", LocalDate.of(2022, 2, 4),
                Status.CONTRACT, 15_000_000, List.of(
                new SalaryImpl(
                        new AllowanceImpl(
                                new NonSalaryDependentAllowanceCalculatorImpl(
                                        List.of(
                                                new Overtime(5, 45_000)
                                        )
                                ),
                                new SalaryDependentAllowanceCalculatorImpl(
                                        List.of(
                                                new Insurance(1, 0, 2)
                                        )
                                )
                        ),
                        new PurchaseOrderImpl(0),
                        new TaxImpl(
                                new TaxProjectImpl(0),
                                new TaxSalaryImpl(0.5f, 0.5f)
                        ),
                        LocalDateTime.of(2025, 4, 30, 0, 0)
                )
        )
        );

        Employee emp4 = new EmployeeImpl(
                103L, "Dian", LocalDate.of(2023, 2, 4),
                Status.CONTRACT, 10_000_000, List.of(
                new SalaryImpl(
                        new AllowanceImpl(
                                new NonSalaryDependentAllowanceCalculatorImpl(
                                        List.of(
                                                new Overtime(6, 45_000)
                                        )
                                ),
                                new SalaryDependentAllowanceCalculatorImpl(
                                        List.of(
                                                new Insurance(1, 0, 2)
                                        )
                                )
                        ),
                        new PurchaseOrderImpl(0),
                        new TaxImpl(
                                new TaxProjectImpl(0),
                                new TaxSalaryImpl(0.5f, 0.5f)
                        ),
                        LocalDateTime.of(2025, 4, 30, 0, 0)
                )
        )
        );

        Employee emp5 = new EmployeeImpl(
                104L, "Gita", LocalDate.of(2024, 2, 4),
                Status.TRAINEE, 0, List.of(
                new SalaryImpl(
                        new AllowanceImpl(
                                new NonSalaryDependentAllowanceCalculatorImpl(
                                        List.of(
                                                new Operational(25, 30_000, 20_000)
                                        )
                                ),
                                new SalaryDependentAllowanceCalculatorImpl(
                                        List.of(
                                        )
                                )
                        ),
                        new PurchaseOrderImpl(0),
                        new TaxImpl(
                                new TaxProjectImpl(0),
                                new TaxSalaryImpl(0, 0)
                        ),
                        LocalDateTime.of(2025, 4, 30, 0, 0)
                )
        )
        );

        Employee emp6 = new EmployeeImpl(
                105L, "Rima", LocalDate.of(2025, 2, 4),
                Status.FREELANCER, 0, List.of(
                new SalaryImpl(
                        new AllowanceImpl(
                                new NonSalaryDependentAllowanceCalculatorImpl(
                                        List.of(
                                        )
                                ),
                                new SalaryDependentAllowanceCalculatorImpl(
                                        List.of(
                                        )
                                )
                        ),
                        new PurchaseOrderImpl(25_000_000),
                        new TaxImpl(
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
