package com.example.neoflex_test_task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class VacationPayRequest {
    LocalDate vacationStartDay;
    LocalDate vacationEndDay;
    BigDecimal averageSalary;
}

