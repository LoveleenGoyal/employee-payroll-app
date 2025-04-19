package com.bridgelabz.employee_payroll.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeDTO {

    @NotEmpty(message = "Employee name cannot be null")
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Employee name Invalid")
    private String name;

    @Min(value = 500, message = "Min Wage should be more than 500")
    private double salary;

    public String gender;
    public String startDate;
    public String note;
    public String profilePic;
    public List<String> department;

//    public EmployeeDTO() {}
//    public EmployeeDTO(String name, double salary) {
//        this.name = name;
//        this.salary = salary;
//    }

//    public String getName() {
//        return name;
//    }
//
//    public double getSalary() {
//        return salary;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setSalary(double salary) {
//        this.salary = salary;
//    }
//
//    public String toString() {
//        return "name : " + name + " salary: " + salary;
//    }
}
