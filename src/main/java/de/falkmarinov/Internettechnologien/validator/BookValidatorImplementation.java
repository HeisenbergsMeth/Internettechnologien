package de.falkmarinov.Internettechnologien.validator;

import de.falkmarinov.Internettechnologien.model.Book;
import de.falkmarinov.Internettechnologien.validator.exception.BookValidatorException;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BookValidatorImplementation implements BookValidator {

    @Override
    public void validate(Book book) throws BookValidatorException {
        validateEdition(book.getEdition());
        validateISBN(book.getIsbn());
        validatePrice(book.getPrice());
    }

    private void validateEdition(Integer edition) throws BookValidatorException {
        if (edition < 1)
            throw new BookValidatorException("Die Auflage muss mindestens 1 oder größer sein");
    }

    public void validateISBN(String isbn) throws BookValidatorException {
        boolean matches = isbn.matches("[\\d]*");

        if (!(matches && (isbn.length() == 10 || isbn.length() == 13)))
            throw new BookValidatorException("ISBN muss 10 oder 13 Stellen lang sein und darf nur Zahlen beinhalten");
    }

    public void validatePrice(Double price) throws BookValidatorException {
        if (price < 0)
            throw new BookValidatorException("Der Preis darf nicht negativ sein");
    }
}
