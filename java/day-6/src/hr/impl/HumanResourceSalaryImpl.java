package hr.impl;

import constants.Status;
import employee.Employee;
import hr.HumanResourceSalary;
import salary.Salary;

import java.util.List;

public class HumanResourceSalaryImpl implements HumanResourceSalary {
    @Override
    public double generateTotalSalary(List<Employee> employees) {
        double totalSalary = 0;
        for (Employee employee : employees) {
            List<Salary> salaries = employee.getSalaries();

            for (Salary salary : salaries) {
                totalSalary += salary.calcTotalSalary(employee.getSalary());
            }
        }

        return totalSalary;
    }

    @Override
    public double generateTotalAllowances(List<Employee> employees) {
        double totalAllowance = 0;
        for (Employee employee : employees) {
            List<Salary> salaries = employee.getSalaries();

            for (Salary salary : salaries) {
                totalAllowance += salary.getAllowance().calcTotalAllTypeAllowances(employee.getSalary());
            }
        }

        return totalAllowance;
    }

    @Override
    public double generateTotalSalaryByStatusEmployee(List<Employee> employees, Status statusEmployee) {
        List<Employee> filteredEmployees =
                employees.stream().filter(employee -> employee.getStatus() == statusEmployee).toList();

        double totalSalary = 0;

        for (Employee employee : filteredEmployees) {
            List<Salary> salaries = employee.getSalaries();

            for (Salary salary : salaries) {
                totalSalary += salary.calcTotalSalary(employee.getSalary());
            }
        }

        return totalSalary;
    }
}
