package de.falkmarinov.Internettechnologien.controller;

import de.falkmarinov.Internettechnologien.model.Book;
import de.falkmarinov.Internettechnologien.parser.Parser;
import de.falkmarinov.Internettechnologien.service.BookService;
import de.falkmarinov.Internettechnologien.validator.exception.BookValidatorException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddBookController", value = "/book/add")
public class AddBookController extends HttpServlet {

    @Inject
    @Named("bookParser")
    private Parser<Book> bookParser;

    @Inject
    private BookService bookService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Book book = bookParser.parse(request);

        String message;

        try {
            bookService.addBook(book);
            message = "Buch erfolgreich hinzugefügt: " + book.getTitle();
        } catch (BookValidatorException e) {
            message = e.getMessage();
        }

        request.setAttribute("message", message);
        request.getRequestDispatcher("/admin.jsp").forward(request, response);
    }
}
