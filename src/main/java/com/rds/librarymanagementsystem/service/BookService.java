package com.rds.librarymanagementsystem.service;

import com.rds.librarymanagementsystem.entity.Book;
import com.rds.librarymanagementsystem.model.create.CreateBookRequest;
import com.rds.librarymanagementsystem.model.update.UpdateBookDataRequest;
import com.rds.librarymanagementsystem.model.update.UpdateBookStatusDataRequest;

import java.util.List;

public interface BookService {
    public List<Book> getAllBook(int status);
    public List<Book> getAllBook();
    public void createBook(CreateBookRequest createBookRequest); // POJO CreateBookRequest
    public void updateBookData(UpdateBookDataRequest updateBookDataRequest); // POJO: UpdateBookDataRequest, UpdateBookDataResponse
    public void updateBookStatusData(UpdateBookStatusDataRequest updateBookStatusDataRequest); // POJO: UpdateBookStatusDataRequest, UpdateBookStatusDataResponse
    public void deleteBook(String bookId);
}
