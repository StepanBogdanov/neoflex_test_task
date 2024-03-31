package com.example.neoflex_test_task.service;

import com.example.neoflex_test_task.dto.VacationPayRequest;
import com.example.neoflex_test_task.dto.VacationPayResponse;

public interface VacationPayService {
    public VacationPayResponse calculate(VacationPayRequest request);
}
