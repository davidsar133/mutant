package com.example.mutant.service;

import com.example.mutant.model.Stats;
import com.example.mutant.repository.DnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsServiceImpl implements IStatsService{

    DnaRepository dnaRepository;

    @Autowired
    public StatsServiceImpl(DnaRepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }


    @Override
    public Stats getStats() {
        long humans = dnaRepository.count();
        long mutants = dnaRepository.countByIsMutant(true);

        return new Stats(mutants, humans);
    }
}
