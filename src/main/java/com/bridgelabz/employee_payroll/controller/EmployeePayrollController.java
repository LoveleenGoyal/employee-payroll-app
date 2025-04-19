package com.bridgelabz.employee_payroll.controller;

import com.bridgelabz.employee_payroll.dto.EmployeeDTO;
import com.bridgelabz.employee_payroll.dto.ResponseDTO;
import com.bridgelabz.employee_payroll.model.Employee;
import com.bridgelabz.employee_payroll.service.IEmployeePayrollService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payrollservice")
public class EmployeePayrollController {
    @Autowired
    private IEmployeePayrollService payrollService;

    @RequestMapping(value = {"","/","/get"})
    public ResponseEntity<ResponseDTO> getEmployeePayrollData(@RequestParam(defaultValue = "World") String name,
                                                              @RequestParam(defaultValue = "0.0") double salary) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        Employee employee = payrollService.createEmployeePayrollData(employeeDTO);
        ResponseDTO responseDTO = new ResponseDTO("Get call successful", employee);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/get/employees")
    public ResponseEntity<ResponseDTO> getEmployeesPayrollData() {
        List<Employee> employeeList = payrollService.getAllEmployeePayrollData();
        ResponseDTO responseDTO = new ResponseDTO("Get Call Successful", employeeList);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/get/{empId}")
    public ResponseEntity<ResponseDTO> getEmployeePayrollData(@PathVariable("empId") int empId) {
        Employee employee = payrollService.getEmployeePayrollDataById(empId);
        ResponseDTO responseDTO = new ResponseDTO("Get Call for ID successful", employee);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createEmployeePayrollData(@Valid @RequestBody EmployeeDTO employeeDTO) {
        Employee employee = payrollService.createEmployeePayrollData(employeeDTO);
        ResponseDTO responseDTO = new ResponseDTO("Created Employee payroll Data Successfully", employee);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updateEmployeePayrollData(@PathVariable("id") int id, @RequestBody EmployeeDTO employeeDTO) {
        Employee updatedEmployee = payrollService.updateEmployeePayrollData(id, employeeDTO);
        ResponseDTO responseDTO = new ResponseDTO("Updated Employee Payroll data for ID: " + id, updatedEmployee);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{empId}")
    public ResponseEntity<ResponseDTO> deleteEmployeePayrollData(@PathVariable("empId") int empId) {
        payrollService.deleteEmployeePayrollData(empId);
        ResponseDTO responseDTO = new ResponseDTO("Deleted Successfully", "Deleted id: " + empId);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
