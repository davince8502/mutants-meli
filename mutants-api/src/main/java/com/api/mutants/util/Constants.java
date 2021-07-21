package com.api.mutants.util;

public class Constants {

    /*Commons*/

    public static final String SUCCESS_MESSAGE = "successful";
    public static final String MESSAGE_ERROR = "Message Error: ";

    public static final String COMMA_SEPARATOR = ",";

    /*Validations Fields DTOs and Objects */

    public static final String ERROR_VALIDACION_CAMPO_NULO = "validacion.obj.field.null";
    public static final String ERROR_VALIDACION_CAMPO_REQUERIDO = "validacion.obj.field.requerid";
    public static final String ERROR_VALIDACION_CAMPO_TAMANIO = "javax.validation.constraints.Size.message";
    public static final String ERROR_VALIDACION_CAMPO_INT_POSITIVO = "validacion.obj.field.IntegerPositive";
    public static final String ERROR_VALIDACION_CAMPO_DOUBLE = "validacion.obj.field.Double";
    public static final String ERROR_VALIDACION_CAMPO_FECHA_FORMATO = "validacion.obj.field.Fecha.formato";
    public static final String ERROR_VALIDACION_CAMPO_EMAIL_FORMATO = "validacion.obj.field.Email.formato";

    /**************** BUSINESS ***************/

    /*Mutants*/

    public static final int DEFAULT_SEQUENCE_SIZE_MUTANT = 4;
    public static final int DEFAULT_COUNT_SEQUENCES_MATCH_MUTANT = 2;
    public static final String MESSAGE_IS_MUTANT = "Is Mutant";
    public static final String MESSAGE_IS_HUMAN = "Is Human";
    public static final String ERROR_INVALID_BASES_ADN = "A sequence (DNA) can only be made up of four nitrogenous bases " +
            "(cytosine (C), thymine (T), adenine (A) and guanine (G)). There are other letters that are not valid.";
    public static final String ERROR_INVALID_NO_EQUAL_SEQUENCES = "The length of the DNA sequences must be the same size";
    public static final String ERROR_INVALID_LENGTH_SEQUENCE = "The length of a DNA sequence must be greater than ";
    public static final String ERROR_INVALID_NUM_SEQUENCES = "DNA sequence array cannot be emptye and must be greater than ";
}
