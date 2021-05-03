package de.falkmarinov.Internettechnologien.validator;

import de.falkmarinov.Internettechnologien.model.Book;
import de.falkmarinov.Internettechnologien.validator.exception.BookValidatorException;

public interface BookValidator {

    void validate(Book book) throws BookValidatorException;
}
