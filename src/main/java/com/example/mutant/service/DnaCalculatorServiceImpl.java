package com.example.mutant.service;


import com.example.mutant.validator.MutantValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DnaCalculatorServiceImpl implements IDnaCalculatorService {

    IDbaService dbaService;

    @Autowired
    public DnaCalculatorServiceImpl(IDbaService dbaService) {
        this.dbaService = dbaService;
    }

    @Override
    public boolean isMutant(List<String> dna) {

        final boolean isMutant = MutantValidator.isMutant(dna);
        dbaService.saveDna(dna, isMutant);

        return isMutant;
    }
}
