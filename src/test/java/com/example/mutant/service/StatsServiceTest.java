package com.example.mutant.service;

import com.example.mutant.model.Stats;
import com.example.mutant.repository.DnaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class StatsServiceTest {

    @Mock
    DnaRepository dnaRepository;

    @InjectMocks
    StatsServiceImpl statsService;

    Stats stats;

    @BeforeEach
    void setUp(){
        stats = new Stats(1L, 1L);
    }


    @Test
    void shouldReturnStats(){
        when(dnaRepository.count()).thenReturn(1L);
        when(dnaRepository.countByIsMutant(anyBoolean())).thenReturn(1L);

        Stats result = statsService.getStats();

        assertNotNull(result);
        assertEquals(result.getHumanCount(), 1L);
        assertEquals(result.getMutantCount(), 1L);
        assertEquals(result.getRatio(), 1.0);

        verify(dnaRepository, times(1)).count();
        verify(dnaRepository, times(1)).countByIsMutant(true);
    }



}
