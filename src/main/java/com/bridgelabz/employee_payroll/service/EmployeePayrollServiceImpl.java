package com.bridgelabz.employee_payroll.service;

import com.bridgelabz.employee_payroll.dto.EmployeeDTO;
import com.bridgelabz.employee_payroll.exceptions.EmployeePayrollException;
import com.bridgelabz.employee_payroll.model.Employee;
import com.bridgelabz.employee_payroll.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeePayrollServiceImpl implements IEmployeePayrollService{
    @Autowired
    private EmployeeRepository repository;

    @Override
    public Employee getEmployeePayrollDataById(int empId) {
        log.info("Fetching employee by ID: {}", empId);
        Optional<Employee> employeeOptional = repository.findById(empId);
        if(employeeOptional.isEmpty()) {
            log.error("No employee found with ID: {}", empId);
            throw new EmployeePayrollException("Employee not found");
        }

        Employee employee = employeeOptional.get();
        log.info("Employee found with ID: {}", empId);
        return employee;
    }

    @Override
    public Employee createEmployeePayrollData(EmployeeDTO employeeDTO) {
        Employee employee = new Employee(employeeDTO);
        log.debug("Employee Data: " + employee);
        return repository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployeePayrollData() {
        log.info("Fetching all employee records from database");
        return repository.findAll();
    }

    @Override
    public List<Employee> getEmployeesByDepartment(String department) {
        log.info("Fetching employees records by department");
        return repository.findEmployeesByDepartment(department);
    }

    @Override
    public Employee updateEmployeePayrollData(int id, EmployeeDTO employeeDTO) {
        Optional<Employee> employeeOptional = repository.findById(id);
        if (employeeOptional.isPresent()) {
            Employee emp = employeeOptional.get();

            if (employeeDTO.getName() != null) {
                log.debug("Updating name for employee ID {}: {}", id, employeeDTO.getName());
                emp.setName(employeeDTO.getName());
            }

            if (employeeDTO.getSalary() > 500) {
                log.debug("Updating salary for employee ID {}: {}", id, employeeDTO.getSalary());
                emp.setSalary(employeeDTO.getSalary());
            }

            if (employeeDTO.getGender() != null) {
                log.debug("Updating gender for employee ID {}: {}", id, employeeDTO.getGender());
                emp.setGender(employeeDTO.getGender());
            }

            if (employeeDTO.getStartDate() != null) {
                log.debug("Updating start date for employee ID {}: {}", id, employeeDTO.getStartDate());
                emp.setStartDate(LocalDate.parse(employeeDTO.getStartDate().toString()));
            }

            if (employeeDTO.getProfilePic() != null) {
                log.debug("Updating profile picture for employee ID {}: {}", id, employeeDTO.getProfilePic());
                emp.setProfilePic(employeeDTO.getProfilePic());
            }

            if (employeeDTO.getDepartment() != null && !employeeDTO.getDepartment().isEmpty()) {
                log.debug("Updating departments for employee ID {}: {}", id, employeeDTO.getDepartment());
                emp.setDepartments(employeeDTO.getDepartment());
            }

            if (employeeDTO.getNote() != null && !employeeDTO.getNote().isEmpty()) {
                log.debug("Updating note for employee ID {}: {}", id, employeeDTO.getNote());
                emp.setNote(employeeDTO.getNote());
            }
            log.info("Successfully updated employee with ID: {}", id);
            return repository.save(emp);
        } else {
            log.warn("Update failed. No employee found with ID: {}", id);
            throw new EmployeePayrollException("Employee not found with ID: " + id);
        }
    }

    @Override
    public void deleteEmployeePayrollData(int empId) {
        if (!repository.existsById(empId)) {
            throw new EmployeePayrollException("Employee not found with ID: " + empId);
        }
        log.info("Successfully deleted employee with ID: {}", empId);
        repository.deleteById(empId);
    }
}
