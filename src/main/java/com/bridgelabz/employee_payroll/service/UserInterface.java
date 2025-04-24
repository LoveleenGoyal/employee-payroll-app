package com.bridgelabz.employee_payroll.service;

import com.bridgelabz.employee_payroll.dto.AuthResponseDTO;
import com.bridgelabz.employee_payroll.dto.LoginDTO;
import com.bridgelabz.employee_payroll.dto.RegisterDTO;
import com.bridgelabz.employee_payroll.model.User;

import java.util.Optional;

public interface UserInterface {
    AuthResponseDTO<String, String> registerUser(RegisterDTO registerDTO);

    AuthResponseDTO<String, String> loginUser(LoginDTO loginDTO);

    boolean matchPassword(String rawPassword, String encodedPassword);

    boolean existsByEmail(String email);

    Optional<User> getUserByEmail(String email);

    AuthResponseDTO<String, String> forgotPassword(String email);

    AuthResponseDTO<String, String> resetPassword(String token, String newPassword);
}
