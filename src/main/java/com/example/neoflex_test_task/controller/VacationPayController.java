package com.example.neoflex_test_task.controller;

import com.example.neoflex_test_task.dto.VacationPayRequest;
import com.example.neoflex_test_task.dto.VacationPayResponse;
import com.example.neoflex_test_task.service.VacationPayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VacationPayController {
    private final VacationPayService service;

    @GetMapping("/calculate")
    public VacationPayResponse calculate(@RequestBody VacationPayRequest request) {
        return service.calculate(request);
    }
}
