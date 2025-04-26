import employee.Employee;
import employee.impl.EmployeeImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        EmployeeImpl empImpl = new EmployeeImpl();

        List<Employee> employees = empImpl.initEmployess();

        empImpl.displayEmployess(employees);

        empImpl.summary(employees);
    }
}