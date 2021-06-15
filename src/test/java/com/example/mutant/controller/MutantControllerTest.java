package com.example.mutant.controller;

import com.example.mutant.dto.DnaDTO;
import com.example.mutant.service.IDnaCalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MutantControllerTest {

    @Mock
    IDnaCalculatorService calculatorService;

    @InjectMocks
    MutantController controller;

    DnaDTO dnaMutantDTO;
    DnaDTO dnaNonMutantDTO;

    List<String> dnaMutant = Arrays.asList("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG");
    List<String> dnaNonMutant = Arrays.asList("ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG");

    @BeforeEach
    void setUp(){
        dnaMutantDTO = new DnaDTO(dnaMutant);
        dnaNonMutantDTO = new DnaDTO(dnaNonMutant);
    }


    @Test
    void shouldReturntMutant(){

        when(calculatorService.isMutant(any())).thenReturn(true);
        ResponseEntity<String> result = controller.validateMutant(dnaMutantDTO);

        assertEquals(result.getStatusCode(), HttpStatus.OK);

        verify(calculatorService, times(1)).isMutant(dnaMutant);

    }

    @Test
    void shouldReturntNonMutant(){

        when(calculatorService.isMutant(any())).thenReturn(false);
        ResponseEntity<String> result = controller.validateMutant(dnaNonMutantDTO);

        assertEquals(result.getStatusCode(), HttpStatus.FORBIDDEN);

        verify(calculatorService, times(1)).isMutant(dnaNonMutant);

    }




}
