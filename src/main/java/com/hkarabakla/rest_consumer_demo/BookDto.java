package com.hkarabakla.rest_consumer_demo;

import java.util.Set;
import java.util.UUID;


public class BookDto {

    private UUID id;
    private String name;
    private double price;
    private Set<AuthorDto> authors;

    public BookDto(UUID id, String name, double price, Set<AuthorDto> authors) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.authors = authors;
    }

    public BookDto() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<AuthorDto> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<AuthorDto> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", authors=" + authors +
                '}';
    }
}
