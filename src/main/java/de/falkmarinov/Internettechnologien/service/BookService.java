package de.falkmarinov.Internettechnologien.service;

import de.falkmarinov.Internettechnologien.model.Book;
import de.falkmarinov.Internettechnologien.validator.exception.BookValidatorException;

import java.util.List;

public interface BookService {

    void addBook(Book book) throws BookValidatorException;
    List<Book> getAllBooks();
}
