package com.api.mutants.controller;

import com.api.mutants.MutantsApiApplication;
import com.api.mutants.kafka.MutantsConsumer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MutantsApiApplication.class)
@AutoConfigureMockMvc
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
@Testcontainers
class MutantControllerTest {


    public static final String URL_DNA_MUTANT_VERIFICATION ="/api/mutant";
    public static final String URL_GET_STATS ="/api/stats";

    public static final String DNA_HUMAN ="{\n" +
            "    \"dna\":[\"CTGCTA\",\"TAGTGC\",\"TTATGT\",\"AGAAGG\",\"TCCCTA\",\"GCACTG\"] \n" +
            "}";

    public static final String DNA_MUTANT ="{\n" +
            "    \"dna\":[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"] \n" +
            "}";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MutantsConsumer mutantsConsumer;


    @Test
    @DisplayName("DNA analizado es de un humano")
    void detectHuman() throws Exception {

        mutantsConsumer.getLatch().await(10000, TimeUnit.MILLISECONDS);

        mockMvc.perform(post(URL_DNA_MUTANT_VERIFICATION)
                .contentType(MediaType.APPLICATION_JSON)
                .content(DNA_HUMAN)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.status.message", is("Is Human")));;
    }

    @Test
    @DisplayName("DNA analizado es de un Mutante")
    void detectMutant() throws Exception {

        mutantsConsumer.getLatch().await(10000, TimeUnit.MILLISECONDS);


        mockMvc.perform(post("/api/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .content(DNA_MUTANT)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status.message", is("Is Mutant")));;
    }


    @Test
    @DisplayName("DNA analizado es de un Mutante")
    void getStats() throws Exception {

        mutantsConsumer.getLatch().await(10000, TimeUnit.MILLISECONDS);

        mockMvc.perform(post(URL_DNA_MUTANT_VERIFICATION)
                .contentType(MediaType.APPLICATION_JSON)
                .content(DNA_HUMAN)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.status.message", is("Is Human")));


            Thread.sleep(6000);


        mockMvc.perform( get(URL_GET_STATS )
                .accept(MediaType.APPLICATION_JSON))
                .andExpect( status().isOk() )
                .andExpect(jsonPath("$.countMutantDna", is(1)))
                .andExpect(jsonPath("$.countHumanDna", is(1)));
    }
}