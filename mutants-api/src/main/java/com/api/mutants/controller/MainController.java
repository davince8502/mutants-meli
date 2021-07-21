package com.api.mutants.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Hidden
public class MainController {

    @RequestMapping("/")
    @Operation(summary = "Mensaje descriptivo del api")
    public String home() {
        return "Mutants API!";
    }
}
