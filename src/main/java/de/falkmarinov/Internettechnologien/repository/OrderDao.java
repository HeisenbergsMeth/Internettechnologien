package de.falkmarinov.Internettechnologien.repository;

import de.falkmarinov.Internettechnologien.database.DatabaseConnection;
import de.falkmarinov.Internettechnologien.model.Book;
import de.falkmarinov.Internettechnologien.model.Order;
import de.falkmarinov.Internettechnologien.model.ShoppingCart;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Named("orderDao")
@ApplicationScoped
public class OrderDao implements Dao<Order> {

    @Inject
    private DatabaseConnection MARIADB;

    @Inject
    private ShoppingCart shoppingCart;

    @Override
    public Optional<Order> get(Long id) {
        return Optional.empty();
    }

    @Override
    public Collection<Order> getAll() {
        return null;
    }

    @Override
    public void save(Order order) {
        Long customerId = order.getCustomer().getId();
        String street = order.getStreet();
        String zip = order.getZip();
        String place = order.getPlace();
        String paymentMethod = order.getPaymentMethod();
        String orderDate = order.getOrderDate();
        Map<Long, Integer> positions = order.getPositions();

        String sql = String.format(
                "INSERT INTO Bestellung VALUES (0, %d, '%s', '%s', '%s', '%s', '%s')",
                customerId,
                street,
                zip,
                place,
                paymentMethod,
                orderDate
        );

        MARIADB.executeUpdate(sql, "quickbook");

        if (positions.size() > 0) {
            sql = String.format(
                    "SELECT BestellungID FROM Bestellung WHERE KundenID=%d AND Bestelldatum='%s'",
                    customerId,
                    orderDate
            );

            ResultSet resultSet = MARIADB.executeQuery(sql, "quickbook");

            long bestellungID = 0L;

            try {
                resultSet.last();
                bestellungID = resultSet.getLong("BestellungID");
            } catch (SQLException exception) {
                exception.printStackTrace();
            }

            for (Map.Entry<Long, Integer> entry : positions.entrySet()) {
                sql = String.format(
                        "INSERT INTO Bestellung_Buch_beinhaltet VALUES(0, %d, %d, %d)",
                        entry.getKey(),
                        bestellungID,
                        entry.getValue()
                );

                MARIADB.executeUpdate(sql, "quickbook");
            }
        }
    }

    @Override
    public int update(Order order) {
        return 0;
    }

    @Override
    public int delete(Order order) {
        return 0;
    }
}
