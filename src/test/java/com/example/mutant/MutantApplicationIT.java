package com.example.mutant;

import com.example.mutant.dto.DnaDTO;
import com.example.mutant.model.Dna;
import com.example.mutant.repository.DnaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MutantApplicationIT {

    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(APPLICATION_JSON.getType(),
            APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
    private static final MediaType TEXT_PLAIN_UTF8 = new MediaType(MediaType.TEXT_PLAIN.getType(),
            MediaType.TEXT_PLAIN.getSubtype(), Charset.forName("utf8"));
    private final List<String> testDnaHuman = Arrays.asList("ATGTGT", "TAGTGC", "TTATGT", "AGACGG", "GAGTCA", "TCACTG");
    private final List<String> testDnaMutant = Arrays.asList("ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG");
    @Autowired
    private DnaRepository dnaRepository;
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testEndpoints() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.get("/stats"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mutantCount").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.humanCount").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ratio").value(0))
                .andExpect(MockMvcResultMatchers.content().contentType(APPLICATION_JSON))
                .andReturn();

        DnaDTO dnaDTO = new DnaDTO(testDnaMutant);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String mutantRequestJson = ow.writeValueAsString(dnaDTO);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/mutant")
                .header("Content-Type", "application/json")
                .content(mutantRequestJson))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(TEXT_PLAIN_UTF8))
                .andExpect(MockMvcResultMatchers.content().string("Ok"));

        // wait a 0.1 seconds since the persistence is an async event
        Thread.sleep(100);

        List<Dna> allDnas = dnaRepository.findAll();
        assertEquals(1, allDnas.size());

        DnaDTO humanDnaDTO = new DnaDTO(testDnaHuman);
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        String humanRequestJson = ow.writeValueAsString(humanDnaDTO);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/mutant")
                .header("Content-Type", "application/json")
                .content(humanRequestJson))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isForbidden());

        Thread.sleep(100);

        allDnas = dnaRepository.findAll();
        assertEquals(2, allDnas.size());

        this.mockMvc.perform(MockMvcRequestBuilders.get("/stats"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mutantCount").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.humanCount").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ratio").value(0.5))
                .andExpect(MockMvcResultMatchers.content().contentType(APPLICATION_JSON))
                .andReturn();
    }

}