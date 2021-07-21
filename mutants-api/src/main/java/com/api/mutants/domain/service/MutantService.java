package com.api.mutants.domain.service;


import com.api.mutants.domain.dto.Stat;
import com.api.mutants.exception.BadDataException;

/**
 * Servicio para validaciones y consultas sobre mutantes.
 *
 * @author <a href="mailto:davince0285@gmail.com">Leonardo Hernandez</a>
 * @version 1.0.0
 * @sinse 07/15/2021 23:05:12
 */

public interface MutantService {

    boolean detectMutantDna(String ...dna) throws BadDataException;

    Stat getStats();

}