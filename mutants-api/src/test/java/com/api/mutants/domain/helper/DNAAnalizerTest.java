package com.api.mutants.domain.helper;

import com.api.mutants.MutantsApiApplication;
import com.api.mutants.exception.BadDataException;
import org.junit.Before;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.api.mutants.util.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MutantsApiApplication.class)
@AutoConfigureMockMvc
class DNAAnalizerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;


    @Autowired
    private DNAAnalizer dNAAnalizer;


    @Test
    @DisplayName("ADN es mutante")
    void isMutant() throws BadDataException {

        String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        assertEquals(dNAAnalizer.isMutant(dna), true);
    }

    @Test
    @DisplayName("ADN es Humano")
    public void testAdnHumano() throws BadDataException  {

        String[] dna = {"TTGCGA","CAGTGC","TTCGCT","AGTCGG","CGATCA","TCACAA"};
        assertEquals(dNAAnalizer.isMutant(dna), false);
    }

    @Test
    @DisplayName("Base ADN no corresponde añ tipo base nitrogenada  ")
    public void testADNBaseNitrogenada() {

        try {
            String[] dna = {"ZTGCGA","CAGTGC","TTCGCT","AGTCGG","CGATCA","TCACAA"};
            dNAAnalizer.validateDNA(dna, DEFAULT_SEQUENCE_SIZE_MUTANT);

        }catch (BadDataException b){
            assertEquals(b.getMessage(), ERROR_INVALID_BASES_ADN);
        }
    }

    @Test
    @DisplayName("Secuecnias de ADN no tienen la misma longitud ")
    public void testSecuenciasADNDiferenteLongitud() {

        try {
            String[] dna = {"TTGCGA","CAGTGC","TTCGCT","AGTCGG","CGATCA"," TCACAA"};
            dNAAnalizer.validateDNA(dna, DEFAULT_SEQUENCE_SIZE_MUTANT);

        }catch (BadDataException b){
            assertEquals(b.getMessage(), ERROR_INVALID_NO_EQUAL_SEQUENCES);
        }
    }



    @Test
    @DisplayName("Arreglo Secuecnias de ADN no tiene el tamaño adecuada ")
    public void testSecuenciasADNLongitud() {

        try {
            String[] dna = {"TTG","CAGTGC","TTCGCT","AGTCGG","CGATCA","CGATCA"};
            dNAAnalizer.validateDNA(dna, DEFAULT_SEQUENCE_SIZE_MUTANT);

        }catch (BadDataException b){
            assertEquals(b.getMessage(), ERROR_INVALID_LENGTH_SEQUENCE + DEFAULT_SEQUENCE_SIZE_MUTANT);
        }
    }



    @Test
    @DisplayName("Arreglo Secuecnias de ADN no tiene el tamaño adecuada ")
    public void testArregloSecuenciasADNLongitud() {

        try {
            String[] dna = {"TTGCGA","CAGTGC","TTCGCT"};
            dNAAnalizer.validateDNA(dna, DEFAULT_SEQUENCE_SIZE_MUTANT);

        }catch (BadDataException b){
            assertEquals(b.getMessage(), ERROR_INVALID_NUM_SEQUENCES + DEFAULT_SEQUENCE_SIZE_MUTANT);
        }
    }
}