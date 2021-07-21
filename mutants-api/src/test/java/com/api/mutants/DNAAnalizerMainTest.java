package com.api.mutants;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.api.mutants.util.Constants.DEFAULT_SEQUENCE_SIZE_MUTANT;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DNAAnalizerMainTest {


    @Test
    @DisplayName("Confirmar ADN Mutante")
    public void testAdnMutante() {

        String[] dna = {"BTGCGA","CCGTGC","TTCGCT","AGGGGC","CGCTCA"," TCAAAA"};
        assertEquals(DNAAnalizerMain.isMutant(dna), true);
    }

    @Test
    @DisplayName("Confirmar ADN Humano")
    public void testAdnHumano() {

        String[] dna = {"TTGCGA","CAGTGC","TTCGCT","AGTCGG","CGATCA","TCACAA"};
        assertEquals(DNAAnalizerMain.isMutant(dna), false);
    }




    @Test
    @DisplayName("Evaluar una secuencia horizontal")
    public void testSecuenciasHorizontal() {

        String[] dna = {"BTGCGA","CCGTGC","TTCGCT","AGGGGC","CGCTCA"," TCAAAA"};

        int numSequence = 0;

        for (int i = 0; i < dna.length; i++) {
            for (int j = 0; j < dna[i].length(); j++) {

                if (j <= 3) {
                    numSequence += DNAAnalizerMain.evaluateSequence(dna, DEFAULT_SEQUENCE_SIZE_MUTANT, i, j, 1);
                }
            }
        }

        assertEquals(numSequence, 2);
    }


    @Test
    @DisplayName("Evaluar una secuencia vertical")
    public void testSecuenciasVertical() {

        String[] dna = {"BTGCGA","CCGTGC","TTCGCA","AGGGGA","CGCTCA"," TCAAAA"};

        int numSequence = 0;

        for (int i = 0; i < dna.length; i++) {
            for (int j = 0; j < dna[i].length(); j++) {

                if (i <= 2) {
                    numSequence += DNAAnalizerMain.evaluateSequence(dna, DEFAULT_SEQUENCE_SIZE_MUTANT, i, j, 2);
                }
            }
        }
        assertEquals(numSequence, 1);
    }


    @Test
    @DisplayName("Evaluar una secuencia en Diagonal Bajando de Izquierda a Derecha")
    public void testDiagonalBajandoIzquierdaADerecha() {

        String[] dna = {"BTGCGA","CCGTGC","TTCGCT","AGGCGG","CGCTCA"," TCACTA"};

        int numSequence = 0;

        for (int i = 0; i < dna.length; i++) {
            for (int j = 0; j < dna[i].length(); j++) {

                if ( i <= 2 && j <= 3 ) {
                    numSequence += DNAAnalizerMain.evaluateSequence(dna, DEFAULT_SEQUENCE_SIZE_MUTANT, i, j, 3);
                }
            }
        }

        assertEquals(numSequence, 1);
    }


    @Test
    @DisplayName("Evaluar dos secuencia en Diagonal Subiendo de Izquierda a Derecha")
    public void testDiagonalSubiendoIzquierdaADerecha() {

        String[] dna = {"BTGCGA","CAGTGC","TTCGCT","AGGCGG","AGGCGG"," TCACTA"};

        int numSequence = 0;

        for (int i = 0; i < dna.length; i++) {
            for (int j = 0; j < dna[i].length(); j++) {

                if (j <= 2 && i > 2) {
                    numSequence += DNAAnalizerMain.evaluateSequence(dna, DEFAULT_SEQUENCE_SIZE_MUTANT, i, j, 4);
                }
            }
        }

        assertEquals(numSequence, 1);

    }

}