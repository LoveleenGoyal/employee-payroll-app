package com.bridgelabz.employee_payroll.model;

import com.bridgelabz.employee_payroll.dto.EmployeeDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int employeeId;
    private String name;
    private double salary;
    private String gender;
    private LocalDate startDate;
    private String note;
    private String profilePic;
    private List<String> departments;
//    public Employee() {}

    public Employee(int i, EmployeeDTO employeeDTO) {
        this.employeeId = i;
        this.name = employeeDTO.getName();
        this.salary = employeeDTO.getSalary();
        this.gender = employeeDTO.getGender();
        this.note = employeeDTO.getNote();
        this.startDate = LocalDate.parse(employeeDTO.startDate);
        this.profilePic = employeeDTO.getProfilePic();
        this.departments = employeeDTO.department;
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
