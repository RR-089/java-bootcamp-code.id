package hr;


import enums.Roles;
import interfaces.ISalary;
import salary.Overtime;
import salary.Transportasi;

import java.time.LocalDate;


public class Programmer extends Employee implements ISalary {

    private Transportasi transport;
    private Overtime overtime;

    public Programmer(int empId, String fullName, LocalDate hireDate,
                      double salary, Transportasi transport, Overtime overtime) {
        super(empId, fullName, hireDate, Roles.PROGRAMMER, salary);
        this.transport = transport;
        this.overtime = overtime;
    }

    @Override
    public void calculateTotalSalary() {
        setTotalSalary(getSalary() + transport.getBensin()
                + transport.getSpj() + transport.getTransportasi()
                + overtime.getMeal());
    }
}