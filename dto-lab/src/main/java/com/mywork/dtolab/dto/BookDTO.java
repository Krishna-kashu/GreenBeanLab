package com.mywork.dtolab.dto;

import lombok.Data;

@Data
public class BookDTO {
    private String title;
    private String author;
    private String genre;
    private int yearPublished;
    private double price;
}
