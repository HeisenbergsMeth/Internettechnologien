package de.falkmarinov.Internettechnologien.repository;

import de.falkmarinov.Internettechnologien.database.DatabaseConnection;
import de.falkmarinov.Internettechnologien.model.Book;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class BookDaoImplementation implements BookDao {

    private final static String DATABASE = "quickbook";

    @Inject
    private DatabaseConnection MARIADB;

    @Override
    public void insertBook(Book book) {
        long id = 0L;
        String author = book.getAuthor();
        String title = book.getTitle();
        String description = book.getDescription();
        String company = book.getCompany();
        Integer edition = book.getEdition();
        String isbn = book.getIsbn();
        String price = String.valueOf(book.getPrice()).replace(',', '.');
        String date = book.getDate();
        List<Long> categories = book.getCategories();

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

        MARIADB.executeUpdate(sql, DATABASE);

        if (categories.size() > 0) {
            sql = String.format(
                    "SELECT BuchID FROM Buch WHERE ISBN='%s' AND Auflage=%d",
                    isbn,
                    edition
            );

            ResultSet resultSet = MARIADB.executeQuery(sql, DATABASE);

            try {
                resultSet.first();
                id = resultSet.getLong("BuchID");
            } catch (SQLException exception) {
                exception.printStackTrace();
            }

            for (Long category : categories) {
                sql = String.format(
                        "INSERT INTO Warengruppe_Buch_beinhaltet VALUES(0, %d, %d)",
                        category,
                        id
                );

                MARIADB.executeUpdate(sql, DATABASE);
            }
        }
    }

    @Override
    public List<Book> fetchAllBooks() {
        String sql = "SELECT * FROM Buch";

        ResultSet resultSet = MARIADB.executeQuery(sql, DATABASE);

        List<Book> books = new ArrayList<>();

        try {
            while(resultSet.next()) {
                
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }
}