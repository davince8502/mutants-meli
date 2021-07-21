package com.api.mutants.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatusDTO {

    private Integer code;
    private String message;
    private String detailMessageError;

    public StatusDTO(Integer code, String message,String detailMessageError) {
        this.code = code;
        this.message = message;
        this.detailMessageError = detailMessageError;
    }

    public StatusDTO(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.detailMessageError = null;
    }

    public StatusDTO(String message) {
        this.code = HttpStatus.OK.value();
        this.message = message;
        this.detailMessageError = null;
    }
}
