package com.jackson.marshal;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jackson.marshal.domain.Book;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JacksonObjTest {
    @Test
    public void testThatObjectMapperCanCreateJsonFromObject() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Book book = Book.builder()
                .isbn("B1")
                .title("Dead End")
                .yearPublished("2026")
                .authorName("Rohith")
                .build();

        String result = mapper.writeValueAsString(book);
        assertThat(result).isEqualTo("{\"isbn\":\"B1\",\"title\":\"Dead End\",\"authorName\":\"Rohith\",\"yearPublished\":\"2026\"}");
    }

    @Test
    public void testThatObjectMapperCanCreateJavaObjectFromJson() throws JsonProcessingException {
        Book book = Book.builder()
                .isbn("B1")
                .title("Dead End")
                .yearPublished("2026")
                .authorName("Rohith")
                .build();

        String json = "{\"ajskld\":\"ghabjkdl\", \"isbn\":\"B1\",\"title\":\"Dead End\",\"authorName\":\"Rohith\",\"year\":\"2026\"}";
//        String json = "{\"isbn\":\"B1\",\"title\":\"Dead End\",\"authorName\":\"Rohith\",\"year\":\"2026\"}";
        ObjectMapper mapper = new ObjectMapper();

        Book result = mapper.readValue(json, Book.class);

        assertThat(result).isEqualTo(book);
    }
}

