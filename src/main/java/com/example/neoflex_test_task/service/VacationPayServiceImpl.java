package com.example.neoflex_test_task.service;

import com.example.neoflex_test_task.dto.VacationPayRequest;
import com.example.neoflex_test_task.dto.VacationPayResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@Service
public class VacationPayServiceImpl implements VacationPayService {
    private static final double AVERAGE_NUMBER_DAY_A_MONTH = 29.3;
    private static final double NDFL_PERCENT = 0.13;

    private static int thisYear = LocalDate.now().getYear();
    private static Set<LocalDate> holidays = Set.of(
            LocalDate.of(thisYear, Month.JANUARY, 1),
            LocalDate.of(thisYear, Month.JANUARY, 2),
            LocalDate.of(thisYear, Month.JANUARY, 3),
            LocalDate.of(thisYear, Month.JANUARY, 4),
            LocalDate.of(thisYear, Month.JANUARY, 5),
            LocalDate.of(thisYear, Month.JANUARY, 6),
            LocalDate.of(thisYear, Month.JANUARY, 7),
            LocalDate.of(thisYear, Month.JANUARY, 8),
            LocalDate.of(thisYear, Month.FEBRUARY, 23),
            LocalDate.of(thisYear, Month.MARCH, 8),
            LocalDate.of(thisYear, Month.MAY, 1),
            LocalDate.of(thisYear, Month.MAY, 9),
            LocalDate.of(thisYear, Month.JUNE, 12),
            LocalDate.of(thisYear, Month.NOVEMBER, 4)
            );

    @Override
    public VacationPayResponse calculate(VacationPayRequest request) {

        LocalDate startDay = request.getVacationStartDay();
        LocalDate endDay = request.getVacationEndDay();
        int numberOfPaidVacationDays = (int) ChronoUnit.DAYS.between(startDay, endDay);
        while (startDay.isBefore(endDay)) {
            if (holidays.contains(startDay)) {
                numberOfPaidVacationDays--;
                endDay.plusDays(1);
            }
            endDay.plusDays(1);
        }

        BigDecimal averageDailyEarnings = request.getAverageSalary()
                .divide(BigDecimal.valueOf(AVERAGE_NUMBER_DAY_A_MONTH));
        BigDecimal vacationPayWithoutNDFL = averageDailyEarnings.multiply(BigDecimal.valueOf(numberOfPaidVacationDays));
        BigDecimal amountNDFL = vacationPayWithoutNDFL.multiply(BigDecimal.valueOf(NDFL_PERCENT));
        BigDecimal vacationPay = vacationPayWithoutNDFL.subtract(amountNDFL).setScale(2, RoundingMode.HALF_UP);

        return new VacationPayResponse(request.getVacationStartDay(), endDay, vacationPay);
    }
}
