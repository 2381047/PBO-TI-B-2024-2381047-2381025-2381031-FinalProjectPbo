package marketplace.Repositories.Impl;

import marketplace.config.Database;
import marketplace.entities.Transaction;
import marketplace.repositories.TransactionRepository;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Component
public class TransactionRepositoryImpl implements TransactionRepository {
    private final Database database;

    public TransactionRepositoryImpl(Database database) {
        this.database = database;
    }

    @Override
    public ArrayList<Transaction> fetchTransactions() {
        Connection connection = database.getConnection();
        String sqlStatement = "SELECT * FROM transactions";
        ArrayList<Transaction> transactions = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setId(resultSet.getInt("id"));
                transaction.setGameAccountId(resultSet.getInt("game_account_id"));
                transaction.setAmount(resultSet.getDouble("amount"));
                transaction.setTransactionDate(resultSet.getDate("transaction_date"));
                transaction.setTransactionStatus(resultSet.getString("transaction_status"));
                transactions.add(transaction);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return transactions;
    }

    @Override
    public void saveTransaction(Transaction transaction) {
        Connection connection = database.getConnection();
        String sqlStatement = "INSERT INTO transactions(game_account_id, amount, transaction_date, transaction_status) VALUES(?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, transaction.getGameAccountId());
            preparedStatement.setDouble(2, transaction.getAmount());
            preparedStatement.setDate(3, new java.sql.Date(transaction.getTransactionDate().getTime()));
            preparedStatement.setString(4, transaction.getTransactionStatus());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Transaction saved successfully!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Transaction getTransactionById(int id) {
        Connection connection = database.getConnection();
        String sqlStatement = "SELECT * FROM transactions WHERE id = ?";
        Transaction transaction = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                transaction = new Transaction();
                transaction.setId(resultSet.getInt("id"));
                transaction.setGameAccountId(resultSet.getInt("game_account_id"));
                transaction.setAmount(resultSet.getDouble("amount"));
                transaction.setTransactionDate(resultSet.getDate("transaction_date"));
                transaction.setTransactionStatus(resultSet.getString("transaction_status"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return transaction;
    }

    @Override
    public void updateTransaction(Transaction transaction) {
        Connection connection = database.getConnection();
        String sqlStatement = "UPDATE transactions SET game_account_id = ?, amount = ?, transaction_date = ?, transaction_status = ? WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, transaction.getGameAccountId());
            preparedStatement.setDouble(2, transaction.getAmount());
            preparedStatement.setDate(3, new java.sql.Date(transaction.getTransactionDate().getTime()));
            preparedStatement.setString(4, transaction.getTransactionStatus());
            preparedStatement.setInt(5, transaction.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Transaction updated successfully!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteTransaction(int id) {
        Connection connection = database.getConnection();
        String sqlStatement = "DELETE FROM transactions WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Transaction deleted successfully!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}