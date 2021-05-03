package de.falkmarinov.Internettechnologien.parser;

import de.falkmarinov.Internettechnologien.model.Book;
import de.falkmarinov.Internettechnologien.model.Category;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Named("bookParser")
@ApplicationScoped
public class BookParserImplementation implements Parser<Book> {

    @Override
    public Book parse(HttpServletRequest request) {

        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String description = request.getParameter("description");
        Integer edition = Integer.valueOf(request.getParameter("edition"));
        String date = request.getParameter("date");
        String company = request.getParameter("company");
        String isbn = request.getParameter("isbn");
        Double price = Double.valueOf(request.getParameter("price").replace(",", "."));
        String[] categories = request.getParameterValues("categories");

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setDescription(description);
        book.setEdition(edition);
        book.setDate(date);
        book.setCompany(company);
        book.setIsbn(isbn);
        book.setPrice(price);

        if (categories != null) {
            for (String category : categories) {
                book.addCategory(Long.valueOf(category));
            }
        }

        return book;
    }
}
