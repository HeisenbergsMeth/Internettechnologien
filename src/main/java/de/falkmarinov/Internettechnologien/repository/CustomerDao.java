package de.falkmarinov.Internettechnologien.repository;

import de.falkmarinov.Internettechnologien.database.DatabaseConnection;
import de.falkmarinov.Internettechnologien.model.Customer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

@Named("customerDao")
@ApplicationScoped
public class CustomerDao implements Dao<Customer> {

    @Inject
    private DatabaseConnection MARIADB;

    @Override
    public Optional<Customer> get(Long id) {
        return Optional.empty();
    }

    @Override
    public Collection<Customer> getAll() {
        return null;
    }

    @Override
    public void save(Customer customer) {
        String name = customer.getName();
        String surname = customer.getSurname();
        String email = customer.getEmail();
        String password = customer.getPassword();

        String sql = String.format(
                "INSERT INTO Kunde VALUES (0, '%s', '%s', '%s', '%s')",
                name,
                surname,
                email,
                password
        );

        MARIADB.executeUpdate(sql, "quickbook");
    }

    @Override
    public int update(Customer customer) {
        return 0;
    }

    @Override
    public int delete(Customer customer) {
        return 0;
    }

    public Optional<Customer> findUserByEmail(String email) {
        String sql = String.format(
                "SELECT * FROM Kunde WHERE Email='%s'",
                email
        );

        ResultSet resultSet = MARIADB.executeQuery(sql, "quickbook");

        Customer customer = new Customer();
        Optional<Customer> optionalCustomer = Optional.empty();

        try {
            if (resultSet.next()) {
                Long id = resultSet.getLong("KundenID");
                String name = resultSet.getString("Vorname");
                String surname = resultSet.getString("Nachname");
                String customerEmail = resultSet.getString("Email");
                String password = resultSet.getString("Passwort");

                customer.setId(id);
                customer.setName(name);
                customer.setSurname(surname);
                customer.setEmail(customerEmail);
                customer.setPassword(password);

                optionalCustomer = Optional.of(customer);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return optionalCustomer;
    }
}
