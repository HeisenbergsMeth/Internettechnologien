package de.falkmarinov.Internettechnologien.repository;

import de.falkmarinov.Internettechnologien.database.DatabaseConnection;
import de.falkmarinov.Internettechnologien.model.Book;
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
        String sql = "SELECT * FROM Buch";

        ResultSet resultSet = MARIADB.executeQuery(sql, "quickbook");

        List<Book> books = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Long bookId = resultSet.getLong("BuchID");
                String author = resultSet.getString("Autor");
                String title = resultSet.getString("Titel");
                String description = resultSet.getString("Beschreibung");
                Integer edition = resultSet.getInt("Auflage");
                String date = resultSet.getString("Erscheinungsdatum");
                String company = resultSet.getString("Verlag");
                String isbn = resultSet.getString("ISBN");
                Double price = resultSet.getDouble("Preis");

                Book book = new Book();
                book.setId(bookId);
                book.setAuthor(author);
                book.setTitle(title);
                book.setDescription(description);
                book.setEdition(edition);
                book.setDate(date);
                book.setCompany(company);
                book.setIsbn(isbn);
                book.setPrice(price);

                books.add(book);
            }

            for (Book book : books) {
                sql = String.format(
                        "SELECT W.WarengruppeID, Bezeichnung " +
                                "FROM Warengruppe_Buch_beinhaltet WBB, Warengruppe W " +
                                "WHERE WBB.BuchID = %d AND WBB.WarengruppeID = W.WarengruppeID",
                        book.getId()
                );

                resultSet = MARIADB.executeQuery(sql, "quickbook");

                while (resultSet.next()) {
                    Category category = new Category();
                    category.setId(resultSet.getLong("W.WarengruppeID"));
                    category.setName(resultSet.getString("Bezeichnung"));
                    book.addCategory(category);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return books;
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
