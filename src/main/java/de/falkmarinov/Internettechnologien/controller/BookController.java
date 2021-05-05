package de.falkmarinov.Internettechnologien.controller;

import de.falkmarinov.Internettechnologien.config.ThymeleafConfig;
import de.falkmarinov.Internettechnologien.model.Book;
import de.falkmarinov.Internettechnologien.model.Category;
import de.falkmarinov.Internettechnologien.service.BookService;
import de.falkmarinov.Internettechnologien.service.CategoryService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BookController", value = "/book")
public class BookController extends HttpServlet {

    @Inject
    private CategoryService categoryService;

    @Inject
    private BookService bookService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        WebContext context = new WebContext(request, response, request.getServletContext());
        TemplateEngine engine = (TemplateEngine) request.getServletContext().getAttribute(ThymeleafConfig.TEMPLATE_ENGINE_ATTR);

        context.setVariable("categories", categoryService.getAllCategories());
        context.setVariable("books", bookService.getAllBooks());

        response.setCharacterEncoding("UTF-8");

        engine.process("book.html", context, response.getWriter());
    }

    private List<Book> generateBookDemo() {
        Book b1 = new Book();
        b1.setId(1L);
        b1.setTitle("Buch 1");
        b1.setAuthor("Autor 1");
        b1.setIsbn("1234567890");
        b1.setPrice(20.99);
        b1.setCompany("Verlag 1");
        b1.setDate("2020-04-10");
        b1.setDescription("Ein tolles Buch, das man einfach nur lesen muss. Ich kann nichts schlechtes dazu sagen.");
        b1.setEdition(1);
        b1.addCategory(50L);

        Book b2 = new Book();
        b2.setId(2L);
        b2.setTitle("Buch 2");
        b2.setAuthor("Autor 2");
        b2.setIsbn("1234567890");
        b2.setPrice(20.99);
        b2.setCompany("Verlag 2");
        b2.setDate("2020-04-11");
        b2.setDescription("Ein tolles Buch");
        b2.setEdition(1);
        b2.addCategory(50L);

        Book b3 = new Book();
        b3.setId(3L);
        b3.setTitle("Buch 3");
        b3.setAuthor("Autor 3");
        b3.setIsbn("1234567890");
        b3.setPrice(20.99);
        b3.setCompany("Verlag 1");
        b3.setDate("2020-04-02");
        b3.setDescription("Ein tolles Buch");
        b3.setEdition(1);
        b3.addCategory(50L);

        Book b4 = new Book();
        b4.setId(4L);
        b4.setTitle("Buch 4");
        b4.setAuthor("Autor 4");
        b4.setIsbn("1234567890");
        b4.setPrice(20.99);
        b4.setCompany("Verlag 1");
        b4.setDate("2020-04-03");
        b4.setDescription("Ein tolles Buch");
        b4.setEdition(1);
        b4.addCategory(50L);

        Book b5 = new Book();
        b5.setId(5L);
        b5.setTitle("Buch 5");
        b5.setAuthor("Autor 5");
        b5.setIsbn("1234567890");
        b5.setPrice(20.99);
        b5.setCompany("Verlag 1");
        b5.setDate("2020-04-23");
        b5.setDescription("Ein tolles Buch");
        b5.setEdition(1);
        b5.addCategory(50L);

        List<Book> result = new ArrayList<>();
        result.add(b1);
        result.add(b2);
        result.add(b3);
        result.add(b4);
        result.add(b5);

        return result;
    }

    private List<Category> generateCategoryDemo() {
        Category c1 = new Category();
        c1.setId(1L);
        c1.setName("Krimi");

        Category c2 = new Category();
        c2.setId(2L);
        c2.setName("Roman");

        Category c3 = new Category();
        c3.setId(3L);
        c3.setName("Action");

        Category c4 = new Category();
        c4.setId(4L);
        c4.setName("Sachbücher");

        Category c5 = new Category();
        c5.setId(5L);
        c5.setName("Drama");

        List<Category> result = new ArrayList<>();

        result.add(c1);
        result.add(c2);
        result.add(c3);
        result.add(c4);
        result.add(c5);

        return result;
    }
}
