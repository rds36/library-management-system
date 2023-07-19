package com.rds.librarymanagementsystem.entity;

import com.rds.librarymanagementsystem.exception.EntityNotValidException;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "book")
public class Book {
    @Id
    private String bookId;
    private String title;
    private String author;
    private String publisher;
    private LocalDate publishDate;
    private String totalPage;
    private Integer status;
    private String borrowedBy;
    private LocalDateTime borrowedAt;
    private LocalDateTime returnedAt;

    public static Builder builder() {
        return new Builder();
    }
    public Book(
            String bookId,
            String title,
            String author,
            String publisher,
            LocalDate publishDate,
            String totalPage,
            Integer status,
            String borrowedBy,
            LocalDateTime borrowedAt,
            LocalDateTime returnedAt
    ) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.totalPage = totalPage;
        this.status = status;
        this.borrowedBy = borrowedBy;
        this.borrowedAt = borrowedAt;
        this.returnedAt = returnedAt;
    }

    public Book() {
    }

    private Book(Builder builder) {
        setBookId(builder.bookId);
        setTitle(builder.title);
        setAuthor(builder.author);
        setPublisher(builder.publisher);
        setPublishDate(builder.publishDate);
        setTotalPage(builder.totalPage);
        setStatus(builder.status);
        setBorrowedBy(builder.borrowedBy);
        setBorrowedAt(builder.borrowedAt);
        setReturnedAt(builder.returnedAt);
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public String getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getBorrowedBy() {
        return borrowedBy;
    }

    public void setBorrowedBy(String borrowedBy) {
        this.borrowedBy = borrowedBy;
    }

    public LocalDateTime getBorrowedAt() {
        return borrowedAt;
    }

    public void setBorrowedAt(LocalDateTime borrowedAt) {
        this.borrowedAt = borrowedAt;
    }

    public LocalDateTime getReturnedAt() {
        return returnedAt;
    }

    public void setReturnedAt(LocalDateTime returnedAt) {
        this.returnedAt = returnedAt;
    }

    public void validate(){
        if (bookId == null || author == null || status == null){
            throw new EntityNotValidException("Book with id: " + bookId);
        }
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId='" + bookId + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publishDate=" + publishDate +
                ", totalPage='" + totalPage + '\'' +
                ", status=" + status +
                ", borrowedBy='" + borrowedBy + '\'' +
                ", borrowedAt=" + borrowedAt +
                ", returnedAt=" + returnedAt +
                '}';
    }

    public static final class Builder {
        private String bookId;
        private String title;
        private String author;
        private String publisher;
        private LocalDate publishDate;
        private String totalPage;
        private Integer status;
        private String borrowedBy;
        private LocalDateTime borrowedAt;
        private LocalDateTime returnedAt;

        private Builder() {
        }

        public Builder bookId(String val) {
            bookId = val;
            return this;
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder author(String val) {
            author = val;
            return this;
        }

        public Builder publisher(String val) {
            publisher = val;
            return this;
        }

        public Builder publishDate(LocalDate val) {
            publishDate = val;
            return this;
        }

        public Builder totalPage(String val) {
            totalPage = val;
            return this;
        }

        public Builder status(Integer val) {
            status = val;
            return this;
        }

        public Builder borrowedBy(String val) {
            borrowedBy = val;
            return this;
        }

        public Builder borrowedAt(LocalDateTime val) {
            borrowedAt = val;
            return this;
        }

        public Builder returnedAt(LocalDateTime val) {
            returnedAt = val;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }
}
