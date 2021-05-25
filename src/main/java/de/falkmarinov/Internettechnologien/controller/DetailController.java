package de.falkmarinov.Internettechnologien.controller;

import de.falkmarinov.Internettechnologien.config.ThymeleafConfig;
import de.falkmarinov.Internettechnologien.handler.UserSessionHandler;
import de.falkmarinov.Internettechnologien.model.Book;
import de.falkmarinov.Internettechnologien.model.Category;
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
import java.util.List;

@WebServlet(name = "DetailController", value = "/detail")
public class DetailController extends HttpServlet {

    @Inject
    private CategoryService categoryService;

    @Inject
    private BookService bookService;

    @Inject
    private ShoppingCart shoppingCart;

    @Inject
    private UserSessionHandler loginHandler;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        WebContext context = new WebContext(request, response, request.getServletContext());
        TemplateEngine engine = (TemplateEngine) request.getServletContext().getAttribute(ThymeleafConfig.TEMPLATE_ENGINE_ATTR);

        List<Category> categories = categoryService.getAllCategories();
        context.setVariable("categories", categories);

        context.setVariable("shoppingCartSize", shoppingCart.getPositionEntryList().size());
        context.setVariable("loggedInCustomer", loginHandler.getLoggedInCustomer());

        Long bookId = Long.valueOf(request.getParameter("id"));
        Book book = bookService.getBookById(bookId);
        context.setVariable("book", book);

        engine.process("detail.html", context, response.getWriter());
    }
}