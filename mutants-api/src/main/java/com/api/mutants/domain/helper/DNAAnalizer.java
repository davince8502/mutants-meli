package com.api.mutants.domain.helper;


import com.api.mutants.exception.BadDataException;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

import static com.api.mutants.util.Constants.DEFAULT_SEQUENCE_SIZE_MUTANT;
import static com.api.mutants.util.Constants.*;

/**
 * DNA Analizer Mutants
 *
 * @author <a href="mailto:davince0285@gmail.com">Leonardo Hernandez</a>
 * @version 1.0.0
 * @sinse 07/15/2021 23:04:45
 */

@Component
public class DNAAnalizer {


    /**
     * Metodo encargado de evaluar si una cadena de ADN corresponde a un Humano normal o un Mutante.
     * @param dna: Arreglo de Secuencias de ADN a analizar
     * @return boolean : True si es Mutante. False si es Humano.
     */
    public Boolean isMutant(String ...dna) throws BadDataException {

        int repeatLength = DEFAULT_SEQUENCE_SIZE_MUTANT;

        validateDNA(dna, repeatLength);

        int maxStartRow = dna.length - repeatLength;

        int maxStartColumn = dna[0].length() - repeatLength;

        int numSequence = 0;

        for (int i = 0; i < dna.length; i++) {
            for (int j = 0; j < dna[i].length(); j++) {

                if (j <= (maxStartColumn + 1)) {
                    numSequence += evaluateSequence(dna, repeatLength, i, j, 1);
                }

                if (i <= maxStartRow) {
                    numSequence += evaluateSequence(dna, repeatLength, i, j, 2);
                }

                if ( i <= maxStartRow && j <= maxStartColumn ) {
                    numSequence += evaluateSequence(dna, repeatLength, i, j, 3);
                }

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

    /**+
     * Metodo encargado de validar la consistencia de las secuencias de ADN a evaluar.
     * @param dna : Arreglo de Secuencias de ADN a analizar
     * @param repeatLength :  tamaño de caracteres repetidos a buscar en cada secuencia de ADN
     * @throws BadDataException
     */


    public static void validateDNA( String[] dna, int repeatLength) throws BadDataException {

        final Pattern ADN_BASE_PATTERN = Pattern.compile("[atcg]+", Pattern.CASE_INSENSITIVE);

        if(dna.length < repeatLength){
            throw new BadDataException(ERROR_INVALID_NUM_SEQUENCES + repeatLength);
        }
        int sizeRow = dna[0].length();

        if (sizeRow < repeatLength) {
            throw new BadDataException(ERROR_INVALID_LENGTH_SEQUENCE + repeatLength);
        }

        for(String dnaRow : dna)
        {
            if (dnaRow.length() != sizeRow) {
                throw new BadDataException(ERROR_INVALID_NO_EQUAL_SEQUENCES);
            } else if (!ADN_BASE_PATTERN.matcher(dnaRow).matches()) {
                throw new BadDataException(ERROR_INVALID_BASES_ADN);
            }
        }

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