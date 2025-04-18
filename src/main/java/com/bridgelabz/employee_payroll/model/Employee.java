package com.bridgelabz.employee_payroll.model;

import com.bridgelabz.employee_payroll.dto.EmployeeDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int employeeId;
    private String name;
    private double salary;

//    public Employee() {}

    public Employee(int i, EmployeeDTO employeeDTO) {
        this.employeeId = i;
        this.name = employeeDTO.getName();
        this.salary = employeeDTO.getSalary();
    }

//    public long getEmployeeId() {
//        return employeeId;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public double getSalary() {
//        return salary;
//    }
//
//    public void setEmployeeId(int employeeId) {
//        this.employeeId = employeeId;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setSalary(double salary) {
//        this.salary = salary;
//    }
}
