package com.example.neoflex_test_task.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class VacationPayRequest {
    LocalDate vacationStartDay;
    LocalDate vacationEndDay;
    BigDecimal averageSalary;
}

