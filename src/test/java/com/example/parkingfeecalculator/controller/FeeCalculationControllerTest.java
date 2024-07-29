package com.example.parkingfeecalculator.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
public class FeeCalculationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldGet200WithLowestCarPark() throws Exception {
        ResultActions result = mockMvc.perform(get(
                "/api/feecalculator/getLowestCarpark")
                .param("startTime", "2021-01-01T10:00:00")
                .param("endTime", "2021-01-01T11:00:00")
                .param("vehicleType", "car"));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Plaza Singapura Car Park"))
                .andExpect(jsonPath("$.fee").value("1.95"));
    }

    @Test
    public void shouldGet400IfVehicleTypeIsInvalid() throws Exception {
        ResultActions result = mockMvc.perform(get(
            "/api/feecalculator/getLowestCarpark")
            .param("startTime", "2021-01-01T10:00:00")
            .param("endTime","2021-01-01T11:00:00")
            .param("vehicleType", "car1"));

        // Assert
        result.andExpect(status().isBadRequest());
    }
}
