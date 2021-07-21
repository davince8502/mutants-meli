package com.api.mutants;

import java.util.Arrays;

import static com.api.mutants.util.Constants.DEFAULT_COUNT_SEQUENCES_MATCH_MUTANT;
import static com.api.mutants.util.Constants.DEFAULT_SEQUENCE_SIZE_MUTANT;

public class DNAAnalizerMain {

    public static void main(String ... args) {

        String[] dna1 = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        Boolean result = isMutant(dna1);
        System.out.println("Analysis Sequence --> " + Arrays.toString(dna1) +" ===>  Result : " + ((result)? "Is Mutant" : "Is Human") );

         /*
        B T G C G A
        C C G T G C
        T T A T G T
        A G A A G G
        C C C C A A
        T C A C T A
        */

        String[] dna2 = {"BTGCGA","CCGTGC","TTCGCT","AGGCGG","CGCTCA"," TCACTA"};
        result = isMutant(dna2);
        System.out.println("Analysis Sequence --> " + Arrays.toString(dna2) +" ===> Result : " + ((result)? "Is Mutant" : "Is Human") );
        /*
        B T G C G A
        C C G T G C
        T T C G C T
        A G G C G G
        C G C T C A
        T C A C T A
        */

        String[] dna3 = {"TTGCGA","CAGTGC","TTCGCT","AGTCGG","CGATCA","TCACAA"};
        result = isMutant(dna3);
        System.out.println("Analysis Sequence --> " + Arrays.toString(dna3) +" ===> Result : " + ((result)? "Is Mutant" : "Is Human") );
        /*
        T T G C G A
        C A G T G C
        T T C G C T
        A G T C G G
        C G A T C A
        T C A C A A
        */
    }

    /**
     * Metodo encargado de evaluar si una cadena de ADN corresponde a un Humano normal o un Mutante.
     * @param dna: Arreglo de Secuencias de ADN a analizar
     * @return boolean : True si es Mutante. False si es Humano.
     */
    public static boolean isMutant(String[] dna) {
        // tamaño de caracteres repetidos en una secuencia de ADN
        int repeatLength = DEFAULT_SEQUENCE_SIZE_MUTANT;

        // fila maxima a evaluar
        int maxStartRow = dna.length - repeatLength;

        // columna maxima a evaluar
        int maxStartColumn = dna[0].length() - repeatLength;

        int numSequence = 0;

        for (int i = 0; i < dna.length; i++) {
            for (int j = 0; j < dna[i].length(); j++) {

                // Revisar Secuencia de forma horizontal
                if (j <= (maxStartColumn + 1)) {
                    numSequence += evaluateSequence(dna, repeatLength, i, j, 1);
                }

                // Revisar Secuencia en forma Vertical
                if (i <= maxStartRow) {
                    numSequence += evaluateSequence(dna, repeatLength, i, j, 2);
                }

                // Revisar Secuencia de forma Diagonal, Derecha a izquierda & Descendente
                if ( i <= maxStartRow && j <= maxStartColumn ) {
                    numSequence += evaluateSequence(dna, repeatLength, i, j, 3);
                }
                // Revisar Secuencia de forma Diagonal, Izquierda a derecha & Ascendente
                if (j <= maxStartColumn && i > maxStartRow) {
                    numSequence += evaluateSequence(dna, repeatLength, i, j, 4);
                }

                if (numSequence >= DEFAULT_COUNT_SEQUENCES_MATCH_MUTANT) {
                    return true;
                }
            }
        }

        return false;
    }


    /**
     * Metodo encargado de evaluar una fila, columna o diagonal en una matriz formada por una secuencia de ADN,
     * segun el Modo de busqueda seleccionado. <br><br>
     * Ejemplo: String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"}<br><br>
     *
     *     B T G C G A <br>
     *     C C G T G C <br>
     *     T T A T G T <br>
     *     A G A A G G <br>
     *     C C C C A A <br>
     *     T C A C T A <br>
     *
     * @param dna: Arreglo de Secuencias de ADN a analizar
     * @param repeatLength: tamaño de caracteres repetidos a buscar en cada secuencia de ADN
     * @param row: fila maxima a evaluar en la matriz
     * @param col: columna maxima a evaluar en la matriz
     * @param mode: Modo de busqueda
     * <ul>
     *     <li>1 : Horizontal </li>
     *     <li>2 : Vertcal </li>
     *     <li>3 : Diagonal, Derecha a izquierda & Descendente </li>
     *     <li>4 : Diagonal, Derecha a izquierda & Ascendente </li>
     * </ul>
     * @return boolean : True contiene elementos repetidos. False no contiene elementos en secuencia repetidos.
     */
    public static int evaluateSequence( String[] dna , int repeatLength, int row, int col, int mode) {

        boolean allMatch = true;

        char[] sequence = new char[repeatLength];

        sequence[0] = dna[row].charAt(col);

        for (int counter = 1; counter < repeatLength && allMatch; counter++) {
            int offsetV = ((mode ==1) ? 0 : (mode == 2 || mode == 3 ) ? counter : (mode ==4) ? - counter : 0);
            int offsetH = ((mode ==1 || mode == 3 || mode == 4) ? counter : (mode == 2 ) ? 0 : 0);

            sequence[counter]= dna[row + offsetV ].charAt(col + offsetH);
            allMatch &= (sequence[0] == sequence[counter]);
        }

        return (allMatch) ? 1 : 0;
    }

}