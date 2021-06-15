package com.example.mutant.util;

import com.example.mutant.dto.StatsDTO;
import com.example.mutant.model.Stats;

public class StatsMapper {

    public static StatsDTO mapStats(Stats stats){
        return new StatsDTO(stats.getMutantCount(), stats.getHumanCount(), stats.getRatio());
    }
}
