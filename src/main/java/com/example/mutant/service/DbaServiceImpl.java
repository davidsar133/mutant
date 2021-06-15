package com.example.mutant.service;

import com.example.mutant.model.Dna;
import com.example.mutant.repository.DnaRepository;
import com.example.mutant.validator.MutantValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class DbaServiceImpl implements IDbaService {

    DnaRepository dnaRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(DbaServiceImpl.class);

    @Autowired
    public DbaServiceImpl(DnaRepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }

    @Async
    @Override
    public CompletableFuture<Dna> saveDna(List<String> dnaChain, boolean isMutant) {

        StringBuilder builder = new StringBuilder();
        for(String chain: dnaChain){
            builder.append(chain);
        }

        String formedDnaChain = builder.toString();

        Dna dnaChainDb = dnaRepository.findByDnaChain(formedDnaChain).orElse(null);

        if(dnaChainDb != null){
            LOGGER.info("Dna found");
        } else {
            dnaChainDb = new Dna(formedDnaChain, MutantValidator.isMutant(dnaChain));

            dnaChainDb = dnaRepository.save(dnaChainDb);

        }

        return CompletableFuture.completedFuture(dnaChainDb);
    }
}
