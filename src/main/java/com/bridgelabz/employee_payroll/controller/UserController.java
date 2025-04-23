package com.bridgelabz.employee_payroll.controller;

import com.bridgelabz.employee_payroll.dto.AuthResponseDTO;
import com.bridgelabz.employee_payroll.dto.LoginDTO;
import com.bridgelabz.employee_payroll.dto.RegisterDTO;
import com.bridgelabz.employee_payroll.service.UserInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
public class UserController {
    @Autowired
    UserInterface userInterface;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO<String, String>> register(@Valid @RequestBody RegisterDTO registerDTO) {
        AuthResponseDTO<String, String> responseDTO = userInterface.registerUser(registerDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO<String, String>> login(@Valid @RequestBody LoginDTO loginDTO) {
        AuthResponseDTO<String,String> responseDTO = userInterface.loginUser(loginDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
