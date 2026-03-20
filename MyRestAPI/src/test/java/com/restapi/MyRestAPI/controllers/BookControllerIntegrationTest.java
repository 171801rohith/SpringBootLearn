package com.restapi.MyRestAPI.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restapi.MyRestAPI.TestDataUtil;
import com.restapi.MyRestAPI.domain.dto.BookDTO;
import com.restapi.MyRestAPI.domain.entities.BookEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class BookControllerIntegrationTest {
    private ObjectMapper objectMapper;
    private MockMvc mockMvc;

    @Autowired
    public BookControllerIntegrationTest(ObjectMapper objectMapper, MockMvc mockMvc) {
        this.objectMapper = objectMapper;
        this.mockMvc = mockMvc;
    }

    @Test
    public void TestThatCreateBookRespondsWithCorrectStatus201() throws Exception {
        BookDTO testBook = TestDataUtil.createBookDTOTest(null);
        String bookJson = objectMapper.writeValueAsString(testBook);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/" + testBook.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void TestThatCreateBookRespondsWithCorrectBook() throws Exception {
        BookDTO testBook = TestDataUtil.createBookDTOTest(null);
        String bookJson = objectMapper.writeValueAsString(testBook);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/" + testBook.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").isString()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").isString()
        );
    }
}
