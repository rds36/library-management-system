package com.rds.librarymanagementsystem.repository;

import com.rds.librarymanagementsystem.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findByStatus(int status);
}