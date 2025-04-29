package employee;

import constants.Status;
import salary.Salary;

import java.time.LocalDate;
import java.util.List;

public interface Employee {
    void addNewSalary(Salary salary);

    List<Salary> getSalaries();

    Long getEmpNo();

    void setEmpNo(Long empNo);

    String getFullName();

    void setFullName(String fullName);

    LocalDate getHireDate();

    void setHireDate(LocalDate hireDate);

    Status getStatus();

    void setStatus(Status status);

    double getSalary();

    void setSalary(double salary);
}
