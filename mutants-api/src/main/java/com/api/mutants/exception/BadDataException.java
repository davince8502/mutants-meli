package com.api.mutants.exception;

/**
 * Excepcion Usa para devolver mensajes de error cuando se envian parametros erroneos
 *
 * @author <a href="mailto:davince0285@gmail.com">Leonardo Hernandez</a>
 * @version 1.0.0
 * @sinse 07/15/2021 20:23:45
 */
public class BadDataException extends Exception {

    public BadDataException() { super(); }
    public BadDataException(String message) { super(message); }
    public BadDataException(String message, Throwable cause) { super(message, cause); }
    public BadDataException(Throwable cause) { super(cause); }
}