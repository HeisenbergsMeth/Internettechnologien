package de.falkmarinov.Internettechnologien.parser;

import de.falkmarinov.Internettechnologien.model.Category;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named("categoryParser")
@ApplicationScoped
public class CategoryParserImplementation implements Parser<Category> {

    @Override
    public Category parse(HttpServletRequest request) {
        Category category = new Category();

        String name = request.getParameter("newCategory");
        category.setName(name);

        return category;
    }
}
