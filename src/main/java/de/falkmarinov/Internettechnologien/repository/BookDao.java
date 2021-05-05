package de.falkmarinov.Internettechnologien.repository;

import de.falkmarinov.Internettechnologien.database.DatabaseConnection;
import de.falkmarinov.Internettechnologien.model.Book;
import de.falkmarinov.Internettechnologien.model.Category;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Named("bookDao")
@ApplicationScoped
public class BookDao implements Dao<Book> {

    @Inject
    private DatabaseConnection MARIADB;

    @Override
    public Optional<Book> get(int id) {
        return Optional.empty();
    }

    @Override
    public Collection<Book> getAll() {
        return null;
    }

    @Override
    public void save(Book book) {
        long id = 0L;
        String author = book.getAuthor();
        String title = book.getTitle();
        String description = book.getDescription();
        String company = book.getCompany();
        Integer edition = book.getEdition();
        String isbn = book.getIsbn();
        String price = String.valueOf(book.getPrice()).replace(',', '.');
        String date = book.getDate();
        List<Category> categories = book.getCategories();

        String sql = String.format(
                "INSERT INTO Buch VALUES (0, '%s', '%s', '%s', %d, '%s', '%s', '%s', %s)",
                author,
                title,
                description,
                edition,
                date,
                company,
                isbn,
                price
        );

        MARIADB.executeUpdate(sql, "quickbook");

        if (categories.size() > 0) {
            sql = String.format(
                    "SELECT BuchID FROM Buch WHERE ISBN='%s' AND Auflage=%d",
                    isbn,
                    edition
            );

            ResultSet resultSet = MARIADB.executeQuery(sql, "quickbook");

            try {
                resultSet.first();
                id = resultSet.getLong("BuchID");
            } catch (SQLException exception) {
                exception.printStackTrace();
            }

            for (Category category : categories) {
                sql = String.format(
                        "INSERT INTO Warengruppe_Buch_beinhaltet VALUES(0, %d, %d)",
                        category.getId(),
                        id
                );

                MARIADB.executeUpdate(sql, "quickbook");
            }
        }
    }

    @Override
    public int update(Book book) {
        return 0;
    }

    @Override
    public int delete(Book book) {
        return 0;
    }
}
