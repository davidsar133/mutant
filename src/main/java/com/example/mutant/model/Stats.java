package com.example.mutant.model;

public class Stats {

    private Long mutantCount;

    private Long humanCount;


    public Stats(Long mutantCount, Long nonMutantCount) {
        this.mutantCount = mutantCount;
        this.humanCount = nonMutantCount;
    }

    public Long getMutantCount() {
        return mutantCount;
    }

    public void setMutantCount(Long mutantCount) {
        this.mutantCount = mutantCount;
    }

    public Long getHumanCount() {
        return humanCount;
    }

    public void setHumanCount(Long humanCount) {
        this.humanCount = humanCount;
    }

    public Double getRatio(){
        if (this.humanCount == 0) {
            return 0.0;
        } else {
          return ((double) this.mutantCount / this.humanCount);
        }
    }
}
