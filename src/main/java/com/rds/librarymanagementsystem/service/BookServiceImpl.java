package com.rds.librarymanagementsystem.service;

import com.rds.librarymanagementsystem.entity.Book;
import com.rds.librarymanagementsystem.mapper.BookDataMapper;
import com.rds.librarymanagementsystem.model.create.CreateBookRequest;
import com.rds.librarymanagementsystem.model.update.UpdateBookDataRequest;
import com.rds.librarymanagementsystem.model.update.UpdateBookStatusDataRequest;
import com.rds.librarymanagementsystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookDataMapper bookDataMapper;
    @Autowired
    BookRepository bookRepository;

    public BookServiceImpl() {
    }

    @Override
    public List<Book> getAllBook(int status) {
        List<Book> books = bookRepository.findByStatus(status);
        if (books.isEmpty()) {
            throw new EntityNotFoundException("No books found.");
        }
        return books;
    }

    @Override
    public List<Book> getAllBook() {
        List<Book> books = bookRepository.findAll();
        if (books.isEmpty()) {
            throw new EntityNotFoundException("No books found.");
        }
        return books;
    }

    @Override
    public void createBook(CreateBookRequest createBookRequest) {
        checkExistingBook(createBookRequest);
        Book bookEntity = bookDataMapper.createBookRequestToBookEntity(createBookRequest);
        bookEntity.validate();
        bookRepository.save(bookEntity);
    }

    @Override
    public void updateBookData(UpdateBookDataRequest updateBookDataRequest) {
        Optional<Book> currentBook = bookRepository.findById(updateBookDataRequest.getBookId());
        if (currentBook.isEmpty()) {
            throw new EntityNotFoundException("Book with id " + updateBookDataRequest.getBookId() + " not found");
        }
        Book updatedBook = bookDataMapper.updateBookDataWithRequestedData(currentBook.get(), updateBookDataRequest);
        bookRepository.save(updatedBook);
    }

    @Override
    public void updateBookStatusData(UpdateBookStatusDataRequest updateBookStatusDataRequest) {
        Optional<Book> currentBook = bookRepository.findById(updateBookStatusDataRequest.getBookId());
        if (currentBook.isEmpty()) {
            throw new EntityNotFoundException("Book with id " + updateBookStatusDataRequest.getBookId() + " not found");
        }
        Book updatedBook = bookDataMapper.updateBookDataStatusWithRequestedData(currentBook.get(), updateBookStatusDataRequest);
        bookRepository.save(updatedBook);
    }

    @Override
    public void deleteBook(String bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isEmpty()){
            throw new EntityNotFoundException("Book with id " + bookId + " is not found");
        }
        bookRepository.delete(book.get());
    }

    private void checkExistingBook(CreateBookRequest createBookRequest) {
        Optional<Book> book = bookRepository.findById(createBookRequest.getBookId());
        if (book.isPresent()) {
            throw new DuplicateKeyException("book with id " + createBookRequest.getBookId() + " is exist.");
        }
    }
}
