package com.example.mutant.dto;

import java.util.List;

public class DnaDTO {

    private List<String> dna;

    public DnaDTO () {

    }

    public DnaDTO(List<String> dna) {
        this.dna = dna;
    }

    public List<String> getDna() {
        return dna;
    }

    public void setDna(List<String> dna) {
        this.dna = dna;
    }
}
