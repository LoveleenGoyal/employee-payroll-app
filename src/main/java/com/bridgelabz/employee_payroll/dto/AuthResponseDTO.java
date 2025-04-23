package com.bridgelabz.employee_payroll.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDTO<T1, T2>{
    private T1 message;
    private T2 messageData;
}
