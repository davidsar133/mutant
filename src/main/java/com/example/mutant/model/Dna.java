package com.example.mutant.model;

import javax.persistence.*;

@Entity
@Table(name = "Dna")
public class Dna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dnaChain;

    private boolean isMutant;


    public Dna() {

    }

    public Dna(String dnaChain, boolean isMutant) {
        this.dnaChain = dnaChain;
        this.isMutant = isMutant;
    }

    public String getDnaChain() {
        return dnaChain;
    }

    public void setDnaChain(String dnaChain) {
        this.dnaChain = dnaChain;
    }

    public boolean isMutant() {
        return isMutant;
    }

    public void setMutant(boolean mutant) {
        isMutant = mutant;
    }
}
