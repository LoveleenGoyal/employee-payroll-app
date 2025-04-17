package com.bridgelabz.employee_payroll.service;

import com.bridgelabz.employee_payroll.dto.EmployeeDTO;
import com.bridgelabz.employee_payroll.model.Employee;

import java.util.List;

public interface IEmployeePayrollService {
    Employee getEmployeePayrollDataById(int empId);
    Employee createEmployeePayrollData(EmployeeDTO employeeDTO);
    List<Employee> getAllEmployeePayrollData();
    Employee updateEmployeePayrollData(int empId, EmployeeDTO employeeDTO);

    void deleteEmployeePayrollData(int empId);
}
