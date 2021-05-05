package de.falkmarinov.Internettechnologien.service;

import de.falkmarinov.Internettechnologien.model.Book;
import de.falkmarinov.Internettechnologien.validator.exception.BookValidatorException;

public interface AddBookService {

    void addBook(Book book) throws BookValidatorException;
}
