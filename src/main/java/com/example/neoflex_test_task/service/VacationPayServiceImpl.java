package com.example.neoflex_test_task.service;

import com.example.neoflex_test_task.dto.VacationPayRequest;
import com.example.neoflex_test_task.dto.VacationPayResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.temporal.ChronoUnit;

@Service
public class VacationPayServiceImpl implements VacationPayService {
    private static final double AVERAGE_NUMBER_DAY_A_MONTH = 29.3;
    private static final double NDFL_PERCENT = 0.13;

    @Override
    public VacationPayResponse calculate(VacationPayRequest request) {

        BigDecimal averageDailyEarnings = request.getAverageSalary()
                .divide(BigDecimal.valueOf(AVERAGE_NUMBER_DAY_A_MONTH));
        long numberOfVacationDays = ChronoUnit.DAYS.between(request.getVacationStartDay(), request.getVacationEndDay());
        BigDecimal vacationPayWithoutNDFL = averageDailyEarnings.multiply(BigDecimal.valueOf(numberOfVacationDays));
        BigDecimal amountNDFL = vacationPayWithoutNDFL.multiply(BigDecimal.valueOf(NDFL_PERCENT));
        BigDecimal vacationPay = vacationPayWithoutNDFL.subtract(amountNDFL).setScale(2, RoundingMode.HALF_UP);

        return new VacationPayResponse(request.getVacationStartDay(), request.getVacationEndDay(), vacationPay);
    }
}
