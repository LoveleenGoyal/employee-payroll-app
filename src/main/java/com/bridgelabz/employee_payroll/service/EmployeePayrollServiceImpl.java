package com.bridgelabz.employee_payroll.service;

import com.bridgelabz.employee_payroll.dto.EmployeeDTO;
import com.bridgelabz.employee_payroll.exceptions.EmployeePayrollException;
import com.bridgelabz.employee_payroll.model.Employee;
import com.bridgelabz.employee_payroll.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeePayrollServiceImpl implements IEmployeePayrollService{
    @Autowired
    private EmployeeRepository repository;

    @Override
    public Employee getEmployeePayrollDataById(int empId) {
        Optional<Employee> employeeOptional = repository.findById(empId);
        return employeeOptional.orElseThrow(() -> new EmployeePayrollException("Employee not found"));
    }

    @Override
    public Employee createEmployeePayrollData(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());
        return repository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployeePayrollData() {
        return repository.findAll();
    }

    @Override
    public Employee updateEmployeePayrollData(int id, EmployeeDTO employeeDTO) {
        Optional<Employee> employeeOptional = repository.findById(id);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employee.setName(employeeDTO.getName());
            employee.setSalary(employeeDTO.getSalary());
            return repository.save(employee);
        }
        return null;
    }

    @Override
    public void deleteEmployeePayrollData(int empId) {
        repository.deleteById(empId);
    }
}
