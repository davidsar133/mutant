package com.example.mutant.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DnaCalculatorServiceTest {

    @Mock
    IDbaService dbaService;

    @InjectMocks
    DnaCalculatorServiceImpl dnaCalculatorService;

    List<String> dnaChainTrue = Arrays.asList("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG");
    List<String> dnaChainFalse = Arrays.asList("ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG");


    @Test
    void shouldReturnMutant(){
        assertTrue(dnaCalculatorService.isMutant(dnaChainTrue));
        verify(dbaService, times(1)).saveDna(dnaChainTrue, true);
    }

    @Test
    void shouldReturnNonMutant(){
        assertFalse(dnaCalculatorService.isMutant(dnaChainFalse));
        verify(dbaService, times(1)).saveDna(dnaChainFalse, false);
    }

}
