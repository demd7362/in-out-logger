package com.example.demo.employee.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDto {
    private String name;
    private String email;
    private String password;
    private String confirmPassword;
}
