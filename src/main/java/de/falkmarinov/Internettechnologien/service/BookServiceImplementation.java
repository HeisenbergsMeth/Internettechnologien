package de.falkmarinov.Internettechnologien.service;

import de.falkmarinov.Internettechnologien.model.Book;
import de.falkmarinov.Internettechnologien.repository.Dao;
import de.falkmarinov.Internettechnologien.validator.BookValidator;
import de.falkmarinov.Internettechnologien.validator.exception.BookValidatorException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class BookServiceImplementation implements BookService {

    @Inject
    @Named("bookDao")
    private Dao<Book> bookDao;

    @Inject
    private BookValidator bookValidator;

    @Override
    public void addBook(Book book) throws BookValidatorException {
        bookValidator.validate(book);
        bookDao.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return (ArrayList<Book>) bookDao.getAll();
    }
}
