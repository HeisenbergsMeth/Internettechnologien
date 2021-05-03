package de.falkmarinov.Internettechnologien.service;

import de.falkmarinov.Internettechnologien.model.Book;
import de.falkmarinov.Internettechnologien.repository.BookDao;
import de.falkmarinov.Internettechnologien.validator.BookValidator;
import de.falkmarinov.Internettechnologien.validator.exception.BookValidatorException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class BookServiceImplementation implements BookService {

    @Inject
    private BookDao bookDao;
    @Inject
    private BookValidator bookValidator;

    @Override
    public void addBook(Book book) throws BookValidatorException {
        bookValidator.validate(book);
        bookDao.insertBook(book);
    }
}
