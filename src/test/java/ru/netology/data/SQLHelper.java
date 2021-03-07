package ru.netology.data;

import org.apache.commons.dbutils.QueryRunner;

import java.sql.*;

public class SQLHelper {
    private static String url = System.getProperty("db.url");
    private static String user = System.getProperty("db.user");
    private static String password = System.getProperty("db.password");

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                url, user, password);
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
        return "NO DATA";
    }

    public static String getPaymentOnCreditStatus() {
        String query =
                "SELECT *" + " " +
                "FROM order_entity INNER JOIN credit_request_entity ON order_entity.payment_id=credit_request_entity.bank_id" + " " +
                "WHERE order_entity.payment_id=credit_request_entity.bank_id" + " " +
                "ORDER BY credit_request_entity.created DESC" + " " +
                "LIMIT 1";
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getString("status");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return "NO DATA";
    }

    public static void clearDB() {
        String credit_request_entity = "DELETE FROM credit_request_entity";
        String order_entity = "DELETE FROM order_entity";
        String payment_entity = "DELETE FROM payment_entity";
        QueryRunner runner = new QueryRunner();
        try (Connection connection = getConnection()) {
            runner.update(connection, credit_request_entity);
            runner.update(connection, order_entity);
            runner.update(connection, payment_entity);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}