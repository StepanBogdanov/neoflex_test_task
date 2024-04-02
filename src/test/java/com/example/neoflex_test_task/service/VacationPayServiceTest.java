package com.example.neoflex_test_task.service;

import com.example.neoflex_test_task.NeoflexTestTaskApplication;
import com.example.neoflex_test_task.dto.VacationPayRequest;
import com.example.neoflex_test_task.dto.VacationPayResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class VacationPayServiceTest {

    @Autowired
    private VacationPayService service;

    @Test
    public void calculateWithoutHoliday() {
        var request = new VacationPayRequest(
                LocalDate.of(2024, 7, 1),
                LocalDate.of(2024, 7, 14),
                BigDecimal.valueOf(60000));
        var response = new VacationPayResponse(
                LocalDate.of(2024, 7, 1),
                LocalDate.of(2024,7, 14),
                BigDecimal.valueOf(24941.96)
        );
        assertThat(service.calculate(request)).isEqualTo(response);
    }

    @Test
    public void calculateWithHoliday() {
        var request = new VacationPayRequest(
                LocalDate.of(2024, 3, 1),
                LocalDate.of(2024, 3, 14),
                BigDecimal.valueOf(60000));
        var response = new VacationPayResponse(
                LocalDate.of(2024, 3, 1),
                LocalDate.of(2024,3, 15),
                BigDecimal.valueOf(23160.39)
        );
        assertThat(service.calculate(request)).isEqualTo(response);
    }

}