package com.bridgelabz.employee_payroll.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDTO {
    private String message;
    private Object data;
//    private HttpStatusCode statusCode;

//    public ResponseDTO() {}
//    public ResponseDTO(String message, Object data) {
//        this.message = message;
//        this.data = data;
//    }

//    public String getMessage() {
//        return message;
//    }
//
//    public Object getData() {
//        return data;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public void setData(Object data) {
//        this.data = data;
//    }
}
