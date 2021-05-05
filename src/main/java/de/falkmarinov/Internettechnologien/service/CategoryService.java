package de.falkmarinov.Internettechnologien.service;

import de.falkmarinov.Internettechnologien.model.Category;

import javax.servlet.ServletContext;
import java.util.List;

public interface CategoryService {

    void addCategory(Category category);

    void updateCategoriesInContext(ServletContext servletContext);

    List<Category> getAllCategories();
}
