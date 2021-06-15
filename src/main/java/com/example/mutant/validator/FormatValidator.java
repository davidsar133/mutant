package com.example.mutant.validator;

import java.util.List;

public class FormatValidator {

    public static void validateFormat(List<String> dna) throws IllegalArgumentException {
        if (dna == null){
            throw new IllegalArgumentException("Dna is null");
        }

        int size = dna.size();

        if (dna.stream().anyMatch(row -> row.length() != size)) {
            throw new IllegalArgumentException("Dna is not a square matrix");
        }

        if (dna.parallelStream().anyMatch(dnaRow -> !dnaRow.matches("^[ATCG]*$"))) {
            throw new IllegalArgumentException("The Dna must be conformed by the values A, T, C or G only.");
        }

    }
}
