import constants.Status;
import employee.Employee;
import hr.HumanResource;
import hr.HumanResourceSalary;
import hr.impl.HumanResourceImpl;
import hr.impl.HumanResourceSalaryImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        HumanResource hrImpl = new HumanResourceImpl();
        HumanResourceSalary hrsImpl = new HumanResourceSalaryImpl();

        List<Employee> employees = hrImpl.initEmployeeData();

        System.out.println("Total Employee: " + hrImpl.getTotalEmployee(employees));
        System.out.println("Total Permanent Employee:" + hrImpl.getTotalEmployeeByStatus(employees, Status.PERMANENT));

        hrImpl.displayEmployees(employees);

        System.out.println("Total Allowance: " + String.format("%.2f",
                hrsImpl.generateTotalAllowances(employees)));
        System.out.println("Total Salary: " + String.format("%.2f",
                hrsImpl.generateTotalSalary(employees)));
        System.out.println("Total Salary by Status: " + String.format("%.2f",
                hrsImpl.generateTotalSalaryByStatusEmployee(employees, Status.PERMANENT)));

    }


}