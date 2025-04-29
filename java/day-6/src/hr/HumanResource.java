package hr;

import constants.Status;
import employee.Employee;

import java.util.List;

public interface HumanResource {
    List<Employee> initEmployeeData();

    int getTotalEmployee(List<Employee> employees);

    int getTotalEmployeeByStatus(List<Employee> employees, Status statusEmployee);

    void displayEmployees(List<Employee> employees);
}
