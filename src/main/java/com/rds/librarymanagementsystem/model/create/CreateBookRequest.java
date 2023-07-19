package com.rds.librarymanagementsystem.model.create;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateBookRequest {

    @NotBlank(message = "Book id cannot be null")
    private String bookId;
    @NotBlank(message = "Title cannot be null")
    private String title;
    @NotBlank(message = "Author cannot be null")
    private String author;
    @NotBlank(message = "Publisher cannot be null")
    private String publisher;
    @NotNull(message = "Publish date cannot be null")
    private String publishDate;
    @NotNull(message = "Total Page cannot be null")
    private String totalPage;

    public CreateBookRequest(String bookId, String title, String author, String publisher, String publishDate, String totalPage, int status) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.totalPage = totalPage;
    }

    public CreateBookRequest(){}

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public String getTotalPage() {
        return totalPage;
    }



}
