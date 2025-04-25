import implementations.EmployeeImpl;
import interfaces.IEmployee;

public class Main {
    public static void main(String[] args) {
        IEmployee empInf = new EmployeeImpl();

        var employees = empInf.initListEmployee();
        //
        //List<Programmer> programmers =
        //        employees.stream()
        //                 .filter(Programmer.class::isInstance)
        //                 .map(Programmer.class::cast)
        //                 .collect(Collectors.toList());

        //empInf.generateSalary(programmers);

        //empInf.displayEmployees(programmers);

        empInf.generateAllSalaries(employees);
        empInf.displayAllEmployees(employees);
    }
}