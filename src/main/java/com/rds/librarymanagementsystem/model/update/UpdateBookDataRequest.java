package com.rds.librarymanagementsystem.model.update;

import javax.validation.constraints.NotNull;

public class UpdateBookDataRequest {

    @NotNull(message = "Book Id cannot be null")
    private String bookId;
    private String title;
    private String author;
    private String publisher;
    private String publishDate;
    private String totalPage;

    public UpdateBookDataRequest(String bookId, String title, String author, String publisher, String publishDate, String totalPage) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.totalPage = totalPage;
    }

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
