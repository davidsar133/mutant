package com.example.mutant.validator;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MutantValidatorTest {

    List<String> nullList = null;
    List<String> nonSquaredMatrix = Arrays.asList("ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA");
    List<String> nonValidCharacter = Arrays.asList("ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","GCGTCO");
    List<String> mutant = Arrays.asList("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG");
    List<String> nonMutant = Arrays.asList("ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG");



    @Test
    void shouldThrowIllegalArgumentExceptionForNull(){
       IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> MutantValidator.isMutant(nullList));
       assertEquals("Dna is null", ex.getMessage());
    }

    @Test
    void shoulkdThrowExceptionNonSquaredMatrix(){
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> MutantValidator.isMutant(nonSquaredMatrix));
        assertEquals("Dna is not a square matrix", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionNonValidCharacter(){
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> MutantValidator.isMutant(nonValidCharacter));
        assertEquals("The Dna must be conformed by the values A, T, C or G only.", ex.getMessage());
    }

    @Test
    void shouldBeMutant(){
        assertTrue(MutantValidator.isMutant(mutant));
    }

    @Test
    void shouldBeNonMutant(){
        assertFalse(MutantValidator.isMutant(nonMutant));
    }
}
