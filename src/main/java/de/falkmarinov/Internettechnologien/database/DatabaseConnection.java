package de.falkmarinov.Internettechnologien.database;

import javax.enterprise.context.ApplicationScoped;
import java.sql.*;

@ApplicationScoped
public class DatabaseConnection {

    private final static String SUBPROTOCOL = "mariadb";
    private final static String HOST = "localhost";
    private final static String PORT = "3306";
    private final static String USER = "root";
    private final static String PASSWORD = "123456";

    private Connection connection;

    public ResultSet executeQuery(String sql, String database) {
        connect(database);

        ResultSet resultSet = null;

        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);
            } catch (SQLException exception) {
                printSQLException(exception);
            }
        }

        disconnect();

        return resultSet;
    }

    public int executeUpdate(String sql, String database) {

        int rows = -1;

        connect(database);

        if (connection != null) {
            try {
                rows = connection.createStatement().executeUpdate(sql);
            } catch (SQLException exception) {
                printSQLException(exception);
            }
        }

        disconnect();

        return rows;
    }

    private void connect(String database) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            String url = String.format("jdbc:%s://%s:%s/%s", SUBPROTOCOL, HOST, PORT, database);

            connection = DriverManager.getConnection(url, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException exception) {
            printSQLException(exception);
        }
    }

    private void disconnect() {
        if (this.connection != null) {
            try {
                connection.close();
            } catch (SQLException exception) {
                printSQLException(exception);
            }
        }
    }

    private void printSQLException(SQLException exception) {

        while (exception != null) {
            System.err.println("Code: " + exception.getErrorCode());
            System.err.println("State: " + exception.getSQLState());
            System.err.println(exception.getMessage());
            exception = exception.getNextException();
        }
    }
}