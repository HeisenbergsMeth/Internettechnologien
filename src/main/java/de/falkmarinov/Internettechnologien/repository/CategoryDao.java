package de.falkmarinov.Internettechnologien.repository;

import de.falkmarinov.Internettechnologien.database.DatabaseConnection;
import de.falkmarinov.Internettechnologien.model.Category;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Named("categoryDao")
@ApplicationScoped
public class CategoryDao implements Dao<Category> {

    @Inject
    private DatabaseConnection MARIADB;

    @Override
    public Optional<Category> get(int id) {
        return Optional.empty();
    }

    @Override
    public Collection<Category> getAll() {
        String sql = "SELECT * FROM Warengruppe";

        ResultSet resultSet = MARIADB.executeQuery(sql, "quickbook");

        List<Category> categories = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Long id = resultSet.getLong("WarengruppeID");
                String name = resultSet.getString("Bezeichnung");

                Category category = new Category();
                category.setId(id);
                category.setName(name);

                categories.add(category);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return categories;
    }

    @Override
    public void save(Category category) {
        String sql = String.format(
                "INSERT INTO Warengruppe(Bezeichnung) VALUE ('%s')",
                category.getName()
        );

        MARIADB.executeUpdate(sql, "quickbook");
    }

    @Override
    public int update(Category category) {
        return 0;
    }

    @Override
    public int delete(Category category) {
        return 0;
    }
}
