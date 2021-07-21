package com.api.mutants.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ResponseDTO {

    private StatusDTO status;

    public static ResponseDTO apply(String response){
        return new ResponseDTO(new StatusDTO(response));
    }
}