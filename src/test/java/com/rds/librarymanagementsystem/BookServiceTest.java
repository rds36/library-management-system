package com.rds.librarymanagementsystem;

import com.rds.librarymanagementsystem.entity.Book;
import com.rds.librarymanagementsystem.exception.DateFormatNotValidException;
import com.rds.librarymanagementsystem.mapper.BookDataMapper;
import com.rds.librarymanagementsystem.model.create.CreateBookRequest;
import com.rds.librarymanagementsystem.model.update.UpdateBookDataRequest;
import com.rds.librarymanagementsystem.model.update.UpdateBookStatusDataRequest;
import com.rds.librarymanagementsystem.repository.BookRepository;
import com.rds.librarymanagementsystem.service.BookService;
import com.rds.librarymanagementsystem.service.BookServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookServiceTest {

    BookDataMapper bookDataMapper = new BookDataMapper();
    @Mock
    BookRepository bookRepository;
    @InjectMocks
    BookService bookService = new BookServiceImpl();

    UpdateBookDataRequest updateBookDataRequest;
    UpdateBookDataRequest updateBookDataRequestInvalidPublishDate;

    UpdateBookStatusDataRequest updateBookStatusDataRequest;
    UpdateBookStatusDataRequest updateBookStatusDataRequestInvalidBorrowedAt;

    CreateBookRequest createBookRequest;
    CreateBookRequest createBookRequestInvalidPublishDate;
    CreateBookRequest createBookRequestDuplicateBookId;

    Book book1;
    Book book2;
    Book book3;

    @BeforeEach
    void init() {
        List<Book> books = new ArrayList<>();

        book1 = new Book("A001", "Harry poter", "Rangga Dirga", "Gramedia", LocalDate.of(2023, 11, 11), "354", 0, null, null, null);
        book2 = new Book("A002", "Harry porter", "Rangga Dirga", "Gramedia", LocalDate.of(2023, 11, 11), "354", 0, null, null, null);
        book3 = new Book("A003", "Transporter", "Rangga Dirga", "Kompas", LocalDate.of(2023, 11, 11), "354", 0, null, null, null);

        updateBookDataRequest = new UpdateBookDataRequest("A001", "Spiderman", "Rangga", "Kompas", "2020-11-17", "200");
        updateBookDataRequestInvalidPublishDate = new UpdateBookDataRequest("A001", "Spiderman", "Rangga", "Kompas", "2020-11:1", "200");

        updateBookStatusDataRequest = new UpdateBookStatusDataRequest("A001", 1, "Rangga", "2023-07-18T10:11:00", "2023-07-20T00:00:00");
        updateBookStatusDataRequestInvalidBorrowedAt = new UpdateBookStatusDataRequest("A001", 1, "Rangga", "2023-07-18", "2023-07-20T00:00:00");

        createBookRequest = new CreateBookRequest("A0012", "Spiderman", "Rangga", "Gramedia", "2023-07-18", "10", 0);
        createBookRequestInvalidPublishDate = new CreateBookRequest("A0012", "Spiderman", "Rangga", "Gramedia", "2023-7-18", "10", 0);
        createBookRequestDuplicateBookId = new CreateBookRequest("A001", "Spiderman", "Rangga", "Gramedia", "2023-07-18", "10", 0);

        books.add(book1);
        books.add(book2);
        books.add(book3);

        Optional<Book> book1Result = Optional.of(book1);

        Mockito.lenient().when(bookRepository.findAll()).thenReturn(books);
        Mockito.lenient().when(bookRepository.findById(createBookRequestDuplicateBookId.getBookId())).thenReturn(book1Result);
    }

    @Test
    public void getAllBooksTest() {
        List<Book> getAllBooks = bookService.getAllBook();

        boolean isBookPresent = getAllBooks.stream().findFirst().isPresent();
        if (isBookPresent) {
            Assertions.assertEquals("A001", getAllBooks.get(0).getBookId());
            Assertions.assertEquals(3, getAllBooks.size());
        } else {
            System.out.println("Books not present.");
        }
    }

    @Test
    public void createBookTest() {
        DateFormatNotValidException dateFormatNotValidException = Assertions.assertThrows(DateFormatNotValidException.class,
                () -> bookDataMapper.createBookRequestToBookEntity(createBookRequestInvalidPublishDate));

        Assertions.assertEquals(
                createBookRequestInvalidPublishDate.getPublishDate() + " -> format is invalid. please use this referred format 'yyyy-MM-dd'",
                dateFormatNotValidException.getMessage()
        );

        DuplicateKeyException duplicateKeyException = Assertions.assertThrows(DuplicateKeyException.class,
                () -> bookService.createBook(createBookRequestDuplicateBookId));

        Assertions.assertEquals(
                "book with id " + createBookRequestDuplicateBookId.getBookId() + " is exist.",
                duplicateKeyException.getMessage()
        );

        Book book = bookDataMapper.createBookRequestToBookEntity(createBookRequest);
        Assertions.assertEquals(createBookRequest.getBookId(), book.getBookId());
    }

    @Test
    public void updateBookDataTest() {

        // Invalid Publish Date
        DateFormatNotValidException dateFormatNotValidException = Assertions.assertThrows(DateFormatNotValidException.class,
                () -> bookDataMapper.updateBookDataWithRequestedData(book1, updateBookDataRequestInvalidPublishDate));
        Assertions.assertEquals(
                updateBookDataRequestInvalidPublishDate.getPublishDate() + " -> format is invalid. please use this referred format 'yyyy-MM-dd'",
                dateFormatNotValidException.getMessage());

        // Valid Request
        Book updatedBook = bookDataMapper.updateBookDataWithRequestedData(book1, updateBookDataRequest);
        Assertions.assertEquals(updateBookDataRequest.getTitle(), updatedBook.getTitle());
    }

    @Test
    public void updateBookStatusDataTest() {
        // Invalid Borrowed at
        DateFormatNotValidException dateFormatNotValidException = Assertions.assertThrows(DateFormatNotValidException.class,
                () -> bookDataMapper.updateBookDataStatusWithRequestedData(book1, updateBookStatusDataRequestInvalidBorrowedAt));
        Assertions.assertEquals(
                updateBookStatusDataRequestInvalidBorrowedAt.getBorrowedAt() + " -> format is invalid. please use this referred format 'yyyy-MM-dd'T'hh:mm:ss'",
                dateFormatNotValidException.getMessage());

        // Valid Request
        Book updatedBook = bookDataMapper.updateBookDataStatusWithRequestedData(book1, updateBookStatusDataRequest);
        Assertions.assertEquals(updateBookStatusDataRequest.getStatus(), updatedBook.getStatus());
    }
}
