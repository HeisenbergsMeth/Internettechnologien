package de.falkmarinov.Internettechnologien.repository;

import de.falkmarinov.Internettechnologien.database.DatabaseConnection;
import de.falkmarinov.Internettechnologien.model.Category;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CategoryDaoImplementation implements CategoryDao {

    private final static String DATABASE = "quickbook";

    @Inject
    private DatabaseConnection MARIADB;

    @Override
    public void insertCategory(Category category) {
        String sql = String.format(
                "INSERT INTO Warengruppe VALUES (0, '%s')",
                category.getName()
        );

        MARIADB.executeUpdate(sql, DATABASE);
    }

    @Override
    public List<Category> fetchAllCategories() {
        String sql = "SELECT * FROM Warengruppe";

        ResultSet resultSet = MARIADB.executeQuery(sql, DATABASE);

        List<Category> result = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Long id = resultSet.getLong("WarengruppeID");
                String name = resultSet.getString("Bezeichnung");

                Category category = new Category();
                category.setId(id);
                category.setName(name);

                result.add(category);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return result;
    }
}
