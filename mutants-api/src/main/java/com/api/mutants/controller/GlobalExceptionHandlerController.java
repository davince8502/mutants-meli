package com.api.mutants.controller;


import com.api.mutants.controller.dto.ResponseDTO;
import com.api.mutants.controller.dto.StatusDTO;
import com.api.mutants.exception.BadDataException;
import com.api.mutants.util.MessagesGestorUtil;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.api.mutants.util.Constants.COMMA_SEPARATOR;
import static com.api.mutants.util.Constants.MESSAGE_ERROR;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandlerController {



  @Autowired
  MessagesGestorUtil messagesGestorUtil;

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public final ResponseEntity<ResponseDTO> handleValidationExceptions(final MethodArgumentNotValidException e) {

    log.error(MESSAGE_ERROR, e);

    List<FieldError> errors = e.getBindingResult()
            .getAllErrors().stream()
            .map(o -> (FieldError)o)
            .collect(Collectors.toList());

    String error = errors.stream()
            .map(er -> messagesGestorUtil.addArgumentsToMenssage(er.getDefaultMessage(), er.getField()))
            .collect(Collectors.joining(COMMA_SEPARATOR.concat(StringUtils.SPACE)));

    ResponseDTO response = ResponseDTO.builder().status(new StatusDTO(HttpStatus.BAD_REQUEST.value(),  error)).build();
    return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ResponseDTO> handleException(HttpServletResponse res, Exception ex) throws IOException {
    log.error("Error: ", ex);
    ResponseDTO response = ResponseDTO.builder().status(new StatusDTO(HttpStatus.BAD_REQUEST.value(), "Something went wrong")).build();
    return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({BadDataException.class, MethodArgumentTypeMismatchException.class})
  public final ResponseEntity<ResponseDTO> handleAppRuntimeException(final Exception e) {
    log.error("Error: "  + e.getMessage());
    ResponseDTO response = ResponseDTO.builder().status(new StatusDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null)).build();
    return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
  }

}
