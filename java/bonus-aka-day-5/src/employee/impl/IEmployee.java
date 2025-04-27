package employee.impl;

import employee.Employee;

import java.util.List;

public interface IEmployee {
    List<Employee> initEmployess();

    void displayEmployess(List<Employee> employees);

    void summary(List<Employee> employees);
}
