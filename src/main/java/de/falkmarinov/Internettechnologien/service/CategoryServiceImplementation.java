package de.falkmarinov.Internettechnologien.service;

import de.falkmarinov.Internettechnologien.config.CategoryConfig;
import de.falkmarinov.Internettechnologien.model.Category;
import de.falkmarinov.Internettechnologien.repository.Dao;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import java.util.List;

@ApplicationScoped
public class CategoryServiceImplementation implements CategoryService {

    @Inject
    @Named("categoryDao")
    private Dao<Category> categoryDao;

    @Override
    public void addCategory(Category category) {
        categoryDao.save(category);
    }

    @Override
    public void updateCategoriesInContext(ServletContext servletContext) {
        List<Category> categories = (List<Category>) categoryDao.getAll();
        servletContext.setAttribute(CategoryConfig.CATEGORY_ATTR, categories);
    }

    @Override
    public List<Category> getAllCategories() {
        return (List<Category>) categoryDao.getAll();
    }
}
