package de.falkmarinov.Internettechnologien.service;

import de.falkmarinov.Internettechnologien.model.Category;
import de.falkmarinov.Internettechnologien.repository.CategoryDao;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import java.util.List;

@ApplicationScoped
public class CategoryServiceImplementation implements CategoryService {

    @Inject
    private CategoryDao categoryDao;

    @Override
    public void addCategory(Category category) {
        categoryDao.insertCategory(category);
    }

    @Override
    public void updateCategoriesInContext(ServletContext servletContext) {
        List<Category> categories = categoryDao.fetchAllCategories();
        servletContext.setAttribute("categories", categories);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryDao.fetchAllCategories();
    }
}
