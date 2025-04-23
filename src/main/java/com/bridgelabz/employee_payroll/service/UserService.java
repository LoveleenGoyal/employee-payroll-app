package com.bridgelabz.employee_payroll.service;

import com.bridgelabz.employee_payroll.dto.AuthResponseDTO;
import com.bridgelabz.employee_payroll.dto.LoginDTO;
import com.bridgelabz.employee_payroll.dto.RegisterDTO;
import com.bridgelabz.employee_payroll.model.User;
import com.bridgelabz.employee_payroll.repository.UserRepository;
import com.bridgelabz.employee_payroll.utility.JwtUtility;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Slf4j
@Service
public class UserService implements UserInterface{
    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    EmailService emailService;

    @Autowired
    JwtUtility jwtUtility;

//    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Override
    public AuthResponseDTO<String, String> registerUser(RegisterDTO registerDTO) {
        log.info("Register User: {}", registerDTO.getEmail());
        AuthResponseDTO<String, String> res = new AuthResponseDTO<>();
        if (existsByEmail(registerDTO.getEmail())) {
            log.warn("Registration failed: User already exists");
            res.setMessage("error");
            res.setMessageData("User already exists");
            return res;
        }
        User user = new User();
        user.setFullName(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        log.info("User {} registered successfully!", user.getEmail());
        emailService.sendMail(user.getEmail(),"Registered in Employee Payroll App", "Hi..."+ "\n You have been successfully registered");

        res.setMessage("message");
        res.setMessageData("User registered successfully");
        return res;
    }

    @Override
    public AuthResponseDTO<String, String> loginUser(LoginDTO loginDTO) {
        log.info("Login attempt for User: {}", loginDTO.getEmail());
        AuthResponseDTO<String, String> res = new AuthResponseDTO<>();
        Optional<User> userExists = getUserByEmail(loginDTO.getEmail());
        if(userExists.isPresent()) {
            User user = userExists.get();
            if (matchPassword(loginDTO.getPassword(), user.getPassword())) {
                String token = jwtUtility.generateToken(user.getEmail());
//                user.setToken(token);
//                userRepository.save(user);
                log.debug("Login successful for User: {} - Token generated", user.getEmail());
                emailService.sendMail(user.getEmail(), "Logged in Employee Payroll App", "Hi.." +
                        "\n You have been Successfully logged in!\n Token: " + token);
                res.setMessage("message");
                res.setMessageData("user Logged in successfully: " + token);
            } else {
                log.warn("Invalid credentials for user: {}", loginDTO.getEmail());
                res.setMessage("error");
                res.setMessageData("Invalid Credentials");
            }
        } else {
            log.error("User not found with email: {}", loginDTO.getEmail());
            res.setMessage("error");
            res.setMessageData("User Not found");
        }
        return res;
    }

    @Override
    public boolean matchPassword(String rawPassword, String encodedPassword) {
        log.debug("Matching password for login attempt");
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    @Override
    public boolean existsByEmail(String email){
        log.debug("Checking if user exists by email: {}", email);
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        log.debug("Fetching user by email: {}", email);
        return userRepository.findByEmail(email);
    }
}
