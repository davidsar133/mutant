package com.example.mutant.controller;

import com.example.mutant.dto.StatsDTO;
import com.example.mutant.model.Stats;
import com.example.mutant.service.IStatsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StatsControllerTest {

    @Mock
    IStatsService statsService;

    @InjectMocks
    StatsController controller;

    Stats stats;

    @BeforeEach
    void setup(){
        stats = new Stats(1L, 1L);
    }

    @Test
    void shouldReturnStats(){
        when(statsService.getStats()).thenReturn(stats);

        ResponseEntity<StatsDTO> result = controller.getStats();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(1L, result.getBody().getHumanCount());
        assertEquals(1L, result.getBody().getMutantCount());
        assertEquals(1.0, result.getBody().getRatio());

        verify(statsService, times(1)).getStats();

    }
}
