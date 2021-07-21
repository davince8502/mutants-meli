package com.api.mutants.domain.repository;

import com.api.mutants.domain.entity.DnaVerified;
import com.api.mutants.domain.projection.StatView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DnaVerifiedRepository extends JpaRepository<DnaVerified, Long> {

    @Query(value = "SELECT  \n" +
            "COUNT(id) filter (where is_mutant = true) as countMutantDna, \n" +
            "count(id) filter (where is_mutant = false) as countHumanDna\n" +
            "FROM mutants_analytics.dna_verified", nativeQuery = true)
    StatView getStats();
}
