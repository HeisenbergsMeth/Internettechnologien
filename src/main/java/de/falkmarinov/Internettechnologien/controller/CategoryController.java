package de.falkmarinov.Internettechnologien.controller;

import de.falkmarinov.Internettechnologien.model.Category;
import de.falkmarinov.Internettechnologien.parser.Parser;
import de.falkmarinov.Internettechnologien.service.CategoryService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CategoryController", value = "/category")
public class CategoryController extends HttpServlet {

    @Inject
    @Named("categoryParser")
    private Parser<Category> categoryParser;

    @Inject
    private CategoryService categoryService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Category category = categoryParser.parse(request);

        categoryService.addCategory(category);
        categoryService.updateCategoriesInContext(getServletContext());

        String message = "Kategorie hinzugefügt: " + category.getName();

        request.setAttribute("message", message);
        request.getRequestDispatcher("/admin.jsp").forward(request, response);
    }
}
