package interfaces;

import hr.Employee;
import hr.Programmer;

import java.util.List;

public interface IEmployee {
    List<Employee> initListEmployee();

    void displayEmployees(List<Programmer> employees);

    void generateSalary(List<Programmer> employees);

    void displayAllEmployees(List<Employee> employees);

    void generateAllSalaries(List<Employee> employees);
}
