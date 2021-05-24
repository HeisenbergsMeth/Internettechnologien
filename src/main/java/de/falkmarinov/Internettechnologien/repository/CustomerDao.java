package de.falkmarinov.Internettechnologien.repository;

import de.falkmarinov.Internettechnologien.database.DatabaseConnection;
import de.falkmarinov.Internettechnologien.model.Customer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
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
}
