package de.falkmarinov.Internettechnologien.repository;

import de.falkmarinov.Internettechnologien.model.Category;

import java.util.List;

public interface CategoryDao {

    void insertCategory(Category category);

    List<Category> fetchAllCategories();
}
