package com.hkarabakla.rest_consumer_demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class Runner implements CommandLineRunner {

    private final RestTemplate restTemplate;

    public Runner(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.rootUri("http://localhost:8080/api").build();
    }

    @Override
    public void run(String... args) throws Exception {

        readAllBooks();
        BookDto savedBook = saveBook();
        readAllBooks();

        getBooksByAuthorName(savedBook.getAuthors().stream().findFirst().get().getName());
    }

    private void getBooksByAuthorName(String author) throws JsonProcessingException {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("author", author);
        parameters.put("page", "0");
        parameters.put("size", "10");
        BookDto[] bookDtos = restTemplate.getForObject("/books?page={page}&size={size}&author={author}", BookDto[].class, parameters);
        List<BookDto> books = Arrays.asList(bookDtos);
        System.out.println("+++++++++getBooksByAuthorName++++++++++++");
        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(books));
    }

    private BookDto saveBook() throws JsonProcessingException {
        AuthorDto a = new AuthorDto();
        a.setName("Uncle Bob");
        BookDto b = new BookDto();
        b.setName("Clean Code");
        b.setPrice(50);
        b.setAuthors(Collections.singleton(a));

        BookDto savedBook = restTemplate.postForObject("/books", b, BookDto.class);
        System.out.println("+++++++++saveBook++++++++++++");
        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(savedBook));
        return savedBook;
    }

    private void readAllBooks() throws JsonProcessingException {
        Map<String, Integer> parameters = new HashMap<>();
        parameters.put("page", 0);
        parameters.put("size", 10);
        BookDto[] bookDtos = restTemplate.getForObject("/books?page={page}&size={size}", BookDto[].class, parameters);
        List<BookDto> books = Arrays.asList(bookDtos);

        System.out.println("+++++++++readAllBooks++++++++++++");
        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(books));
    }
}
