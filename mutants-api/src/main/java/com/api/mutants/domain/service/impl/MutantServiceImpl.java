package com.api.mutants.domain.service.impl;


import com.api.mutants.domain.builder.ObjectBuilder;
import com.api.mutants.domain.dto.Stat;
import com.api.mutants.domain.helper.DNAAnalizer;
import com.api.mutants.domain.repository.DnaVerifiedRepository;
import com.api.mutants.domain.service.MutantService;
import com.api.mutants.exception.BadDataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Implementacion del Servicio {@link MutantService} para validaciones y consultas sobre mutantes.
 *
 * @author <a href="mailto:davince0285@gmail.com">Leonardo Hernandez</a>
 * @version 1.0.0
 * @sinse 07/15/2021 23:05:12
 */

@Slf4j
@Service
public class MutantServiceImpl implements MutantService {

    @Autowired
    private ObjectBuilder objectBuilder;

    @Autowired
    private DNAAnalizer dnaAnalizer;

    @Autowired
    private DnaVerifiedRepository dnaVerifiedRepository;


    @Override
    public boolean detectMutantDna(String... dna) throws BadDataException {

        return dnaAnalizer.isMutant(dna);

    }

    @Override
    public Stat getStats() {

        Stat stat = objectBuilder.map( dnaVerifiedRepository.getStats(), Stat.class);
        stat.setRatio((double)stat.getCountMutantDna()/ stat.getCountHumanDna());

        return  stat;

    }
}
