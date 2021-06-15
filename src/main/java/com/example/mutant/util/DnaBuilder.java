package com.example.mutant.util;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DnaBuilder {

    public static List<String> buildStringsFromRows(List<String> dna){
        List<String> verticalDna = new ArrayList<>();

        for(int i = 0; i < dna.size(); i++){
            StringBuilder newChain = new StringBuilder(dna.get(0).length());
            for (String protein: dna){
                newChain.append(protein.charAt(i));
            }
            verticalDna.add(newChain.toString());
        }

        return verticalDna;
    }

    public static List<String> buildStringsFromDiagonalUp(List<String> dna){

        List<String> diagonalDnaString = new ArrayList<>();

        // Diagonal processing orientation from top to bottom
        for (int i = 0; i < dna.size() / 2; i++) {

            StringBuilder diagonalArrangeUpper = new StringBuilder(dna.size());
            StringBuilder diagonalArrangeLower = new StringBuilder(dna.size());

            for (int j = 0; j < dna.size() - i; j++) {
                diagonalArrangeUpper.append(dna.get(j).charAt(j + i));

                if (i != 0) {
                    diagonalArrangeLower.append(dna.get(i + j).charAt(j));
                }
            }

            if (diagonalArrangeUpper.length() > 0) {
                diagonalDnaString.add(diagonalArrangeUpper.toString());
            }

            if (diagonalArrangeLower.length() > 0) {
                diagonalDnaString.add(diagonalArrangeLower.toString());
            }
        }

        return diagonalDnaString;
    }


    public static List<String> buildStringsFromDiagonalDown(List<String> dna) {

        List<String> diagonalDnaString = new ArrayList<>();

        // Diagonal processing orientation from bottom to top
        for (int i = 0; i < dna.size() / 2; i++) {

            StringBuilder diagonalArrangeUpper = new StringBuilder(dna.size());
            StringBuilder diagonalArrangeLower = new StringBuilder(dna.size());

            for (int j = dna.size() - 1; j >= i; j--) {
                diagonalArrangeUpper.append(dna.get(j).charAt(i + dna.size() - 1 - j));

                if (i != 0) {
                    diagonalArrangeLower.append(dna.get(j - i).charAt(dna.size() - 1 - j));
                }
            }

            if (diagonalArrangeUpper.length() > 0) {
                diagonalDnaString.add(diagonalArrangeUpper.toString());
            }

            if (diagonalArrangeLower.length() > 0) {
                diagonalDnaString.add(diagonalArrangeLower.toString());
            }
        }

        return diagonalDnaString;

    }



}
