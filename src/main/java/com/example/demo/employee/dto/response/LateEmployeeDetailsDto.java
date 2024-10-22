package com.example.demo.employee.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LateEmployeeDetailsDto {
    private String employeeName;
    private LocalDate date;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime checkInTime;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime checkOutTime;
}
