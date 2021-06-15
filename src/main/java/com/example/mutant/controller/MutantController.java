package com.example.mutant.controller;

import com.example.mutant.dto.DnaDTO;
import com.example.mutant.service.IDnaCalculatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class MutantController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MutantController.class);

    IDnaCalculatorService dnaCalculatorService;

    @Autowired
    public MutantController(IDnaCalculatorService dnaCalculatorService) {
        this.dnaCalculatorService = dnaCalculatorService;
    }

    @RequestMapping(value = "/mutant", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> validateMutant(@RequestBody DnaDTO dnaDTO) throws IllegalArgumentException {
        LOGGER.info("validate dna");
        if (dnaCalculatorService.isMutant(dnaDTO.getDna())) {
            LOGGER.info("Mutant Dna");
            return ResponseEntity.ok("Ok");
        } else {
            LOGGER.info("Not mutant");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
