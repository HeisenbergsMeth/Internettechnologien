package de.falkmarinov.Internettechnologien.repository;

import de.falkmarinov.Internettechnologien.model.Book;

import java.util.List;

public interface BookDao {

    void insertBook(Book book);
    List<Book> fetchAllBooks();
}
