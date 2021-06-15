package com.example.mutant.repository;

import com.example.mutant.model.Dna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DnaRepository extends JpaRepository<Dna, Long> {

    Optional<Dna> findByDnaChain(final String dnaChain);

    Long countByIsMutant(Boolean isMutant);

}
