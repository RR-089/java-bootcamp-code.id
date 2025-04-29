package hr;

import constants.Status;
import employee.Employee;

import java.util.List;

public interface HumanResourceSalary {
    double generateTotalSalary(List<Employee> employees);

    double generateTotalAllowances(List<Employee> employees);

    double generateTotalSalaryByStatusEmployee(List<Employee> employees,
                                               Status statusEmployee);
}
