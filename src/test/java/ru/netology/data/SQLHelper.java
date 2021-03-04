package ru.netology.data;

import java.sql.*;

public class SQLHelper {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/app", "app", "pass");
    }

    public static String getPaymentByCardStatus() {
        String query =
                "SELECT *" + " " +
                "FROM order_entity INNER JOIN payment_entity ON order_entity.payment_id=payment_entity.transaction_id" + " " +
                "WHERE order_entity.payment_id=payment_entity.transaction_id" + " " +
                "ORDER BY payment_entity.created DESC" + " " +
                "LIMIT 1";
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getString("status");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }
}
