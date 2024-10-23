package com.example.demo.calendar.dto;

import com.example.demo.calendar.enums.VacationStatus;
import com.example.demo.calendar.enums.VacationType;
import com.example.demo.common.dto.CommonDto;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VacationDto extends CommonDto {
    private VacationStatus vacationStatus;
    private VacationType vacationType;
    private LocalDate start;
    private LocalDate end;
    private String reason;
    private String approvedBy;
    private LocalDateTime approvedAt;
}
