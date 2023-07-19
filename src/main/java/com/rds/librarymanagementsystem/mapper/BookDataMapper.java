package com.rds.librarymanagementsystem.mapper;

import com.rds.librarymanagementsystem.entity.Book;
import com.rds.librarymanagementsystem.exception.DateFormatNotValidException;
import com.rds.librarymanagementsystem.model.create.CreateBookRequest;
import com.rds.librarymanagementsystem.model.update.UpdateBookDataRequest;
import com.rds.librarymanagementsystem.model.update.UpdateBookStatusDataRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@Component
public class BookDataMapper {

    public Book createBookRequestToBookEntity(CreateBookRequest createBookRequest) {

        return Book.builder()
                .bookId(createBookRequest.getBookId())
                .author(createBookRequest.getAuthor())
                .publisher(createBookRequest.getPublisher())
                .publishDate(stringDateToLocalDate(createBookRequest.getPublishDate()))
                .title(createBookRequest.getTitle())
                .status(0)
                .totalPage(createBookRequest.getTotalPage())
                .build();
    }

    public Book updateBookDataWithRequestedData(Book book, UpdateBookDataRequest updateBookDataRequest) {
        if (updateBookDataRequest.getTitle() != null && !updateBookDataRequest.getTitle().isBlank()){
            book.setTitle(updateBookDataRequest.getTitle());
        }
        if (updateBookDataRequest.getAuthor() != null && !updateBookDataRequest.getAuthor().isBlank()){
            book.setAuthor(updateBookDataRequest.getAuthor());
        }
        if (updateBookDataRequest.getPublisher() != null && !updateBookDataRequest.getPublisher().isBlank()){
            book.setPublisher(updateBookDataRequest.getPublisher());
        }
        if (updateBookDataRequest.getPublishDate() != null && !updateBookDataRequest.getPublishDate().isBlank()){
            book.setPublishDate(stringDateToLocalDate(updateBookDataRequest.getPublishDate()));
        }
        if (updateBookDataRequest.getTotalPage() != null && !updateBookDataRequest.getTotalPage().isBlank()){
            book.setTotalPage(updateBookDataRequest.getTotalPage());
        }
        return book;
    }

    public Book updateBookDataStatusWithRequestedData(Book book, UpdateBookStatusDataRequest updateBookStatusDataRequest) {
        if (updateBookStatusDataRequest.getStatus() != null){
            book.setStatus(updateBookStatusDataRequest.getStatus());
        }
        if (updateBookStatusDataRequest.getBorrowedBy() != null && !updateBookStatusDataRequest.getBorrowedBy().isBlank()){
            book.setBorrowedBy(updateBookStatusDataRequest.getBorrowedBy());
        }
        if (updateBookStatusDataRequest.getBorrowedAt() != null && !updateBookStatusDataRequest.getBorrowedAt().isBlank()){
            book.setBorrowedAt(stringDateToLocalDateTime(updateBookStatusDataRequest.getBorrowedAt()));
        }
        if (updateBookStatusDataRequest.getReturnedAt() != null && !updateBookStatusDataRequest.getReturnedAt().isBlank()){
            book.setReturnedAt(stringDateToLocalDateTime(updateBookStatusDataRequest.getReturnedAt()));
        }
        return book;
    }

    private LocalDateTime stringDateToLocalDateTime(String stringDate) {
        LocalDateTime localDateTime;

        try {
            localDateTime = LocalDateTime.parse(stringDate);
        } catch (DateTimeParseException ex){
            throw new DateFormatNotValidException(stringDate + " -> format is invalid. please use this referred format 'yyyy-MM-dd'T'hh:mm:ss'");
        }

        return localDateTime;
    }
    private LocalDate stringDateToLocalDate(String stringDate) {
        LocalDate localDate;

        try {
            localDate = LocalDate.parse(stringDate);
        } catch (DateTimeParseException ex){
            throw new DateFormatNotValidException(stringDate + " -> format is invalid. please use this referred format 'yyyy-MM-dd'");
        }

        return localDate;
    }
}
