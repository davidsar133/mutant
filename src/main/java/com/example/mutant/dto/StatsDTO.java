package com.example.mutant.dto;

public class StatsDTO {


    private Long mutantCount;

    private Long humanCount;

    private Double ratio;

    public StatsDTO(Long mutantCount, Long humanCOunt, Double ratio) {
        this.mutantCount = mutantCount;
        this.humanCount = humanCOunt;
        this.ratio = ratio;
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

    public void setHumanCount(Long humanCOunt) {
        this.humanCount = humanCOunt;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }
}
