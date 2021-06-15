package com.example.mutant.service;


import com.example.mutant.model.Dna;
import com.example.mutant.repository.DnaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DbaServiceTest {

    @Mock
    DnaRepository repository;

    @InjectMocks
    DbaServiceImpl dbaService;

    Dna dna;
    List<String> dnaChain = Arrays.asList("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG");
    String dnaChainTest = "ATGCGACAGTGCTTATGTAGAAGGCCCCTATCACTG";
    @BeforeEach
    void setUp(){
        dna = new Dna(dnaChainTest, true);
    }

    @Test
    void shouldSaveInDb() throws ExecutionException, InterruptedException {
        when(repository.save(any())).thenReturn(dna);
        CompletableFuture<Dna> result = dbaService.saveDna(dnaChain, true);
        assertEquals(result.get().getDnaChain(), dnaChainTest);
        assertTrue(result.get().isMutant());
    }

}
