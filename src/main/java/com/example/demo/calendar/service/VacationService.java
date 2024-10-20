package com.example.demo.calendar.service;

import com.example.demo.calendar.dto.CalendarEventDto;
import com.example.demo.calendar.entity.Vacation;
import com.example.demo.calendar.enums.VacationStatus;
import com.example.demo.calendar.enums.VacationType;
import com.example.demo.calendar.repository.VacationRepository;
import com.example.demo.common.exception.HttpException;
import com.example.demo.employee.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VacationService {
    private final VacationRepository vacationRepository;

    public Vacation vacationRequest(CalendarEventDto calendarEventDto, VacationType vacationType, Employee requester) {
        LocalDate start = calendarEventDto.getStart();
        LocalDate end = calendarEventDto.getEnd().minusDays(1);
        List<Vacation> vacations = vacationRepository.findOverlappingVacations(requester, start, end);

        if(!vacations.isEmpty()){ // 올라간 휴가가 있을 경우
            Vacation vacation = vacations.get(0);
            switch(vacation.getVacationStatus()){
                case PENDING -> throw new HttpException(400, "%d일에 결재가 올라가있습니다.".formatted(vacation.getStart().getDayOfMonth()));
                case APPROVED -> throw new HttpException(400, "휴가가 올라간 일자가 포함되어 있습니다.");
                case REJECTED -> { // 다시 펜딩 상태로 변경
                    vacation.setVacationStatus(VacationStatus.PENDING);
                    return vacationRepository.save(vacation);
                }
            }
        }
        // TODO 이벤트 삭제 시 연차라면 vacation 도 삭제해야함.

        Vacation vacation = Vacation.builder()
                .vacationType(vacationType)
                .reason(calendarEventDto.getTitle())
                .start(start)
                .end(end)
                .vacationStatus(VacationStatus.PENDING)
                .build();
        return vacationRepository.save(vacation);
    }


}
