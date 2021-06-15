package com.example.mutant.service;

import com.example.mutant.model.Dna;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IDbaService {

    CompletableFuture<Dna> saveDna(List<String> dnaChain, boolean isMutant);
}
