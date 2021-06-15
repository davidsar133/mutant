package com.example.mutant.validator;

import com.example.mutant.util.DnaBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;


public class MutantValidator {


    private static final Logger LOGGER = LoggerFactory.getLogger(MutantValidator.class);



    public static boolean isMutant(List<String> dna) throws IllegalArgumentException {

        FormatValidator.validateFormat(dna);

        long numOfSeqs = validateColumns(dna) + validateRows(dna) + validateDiagonalUp(dna) + validateDiagonalDown(dna);
        LOGGER.info("Number of sequences: "+numOfSeqs);

        return numOfSeqs > 1;
    }

    private static Long validateColumns(final List<String> dna){
        LOGGER.info("validate columns");
        return dna.stream().filter(s -> s.matches(".*(AAAA|CCCC|GGGG|TTTT).*")).count();
    }

    private static Long validateRows(final List<String> dna){
        LOGGER.info("validate rows");
        List<String> verticalDna = DnaBuilder.buildStringsFromRows(dna);

        return verticalDna.stream().filter(s -> s.matches(".*(AAAA|CCCC|GGGG|TTTT).*")).count();
    }

    private static Long validateDiagonalUp(List<String> dna){
        LOGGER.info("validate diagonal up");
        List<String> diagonalUpStrings = DnaBuilder.buildStringsFromDiagonalUp(dna);

        return diagonalUpStrings.stream().filter(s -> s.matches(".*(AAAA|CCCC|GGGG|TTTT).*")).count();

    }

    private static Long validateDiagonalDown(List<String> dna){
        LOGGER.info("validate diagonal down");
        List<String> diagonalDownStrings = DnaBuilder.buildStringsFromDiagonalDown(dna);

        return diagonalDownStrings.stream().filter(s -> s.matches(".*(AAAA|CCCC|GGGG|TTTT).*")).count();
    }


}
