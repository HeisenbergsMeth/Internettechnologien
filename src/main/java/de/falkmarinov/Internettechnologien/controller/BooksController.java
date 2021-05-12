package de.falkmarinov.Internettechnologien.controller;

import de.falkmarinov.Internettechnologien.config.ThymeleafConfig;
import de.falkmarinov.Internettechnologien.model.ShoppingCart;
import de.falkmarinov.Internettechnologien.service.BookService;
import de.falkmarinov.Internettechnologien.service.CategoryService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BooksController", value = "/book")
public class BooksController extends HttpServlet {

    @Inject
    private CategoryService categoryService;

    @Inject
    private BookService bookService;

    @Inject
    private ShoppingCart shoppingCart;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        WebContext context = new WebContext(request, response, request.getServletContext());
        TemplateEngine engine = (TemplateEngine) request.getServletContext().getAttribute(ThymeleafConfig.TEMPLATE_ENGINE_ATTR);

        context.setVariable("categories", categoryService.getAllCategories());
        context.setVariable("shoppingCartSize", shoppingCart.getPositionMapEntry().size());

        String categoryId = request.getParameter("c");

        if (categoryId == null) {
            context.setVariable("books", bookService.getAllBooks());
        } else {
            context.setVariable("books", bookService.getBooksByCategoryId(Long.valueOf(categoryId)));
        }

        engine.process("book.html", context, response.getWriter());
    }
}
