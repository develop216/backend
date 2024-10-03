package com.proxiad.controller;


import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.proxiad.controller.ProduitController;
import com.proxiad.service.ProduitService;

@SpringBootTest
@AutoConfigureMockMvc
public class ProduitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProduitService produitService;

    @Test
    public void testGetProduit() throws Exception {
        mockMvc.perform(get("/produit"))
            .andExpect(status().isOk());
    }

}