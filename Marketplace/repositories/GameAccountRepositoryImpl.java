package Marketplace.repositories;

import Marketplace.config.Database;
import Marketplace.entities.GameAccount;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class GameAccountRepositoryImpl implements GameAccountRepository {
    private  Database database;

    public GameAccountRepositoryImpl(Database database) {
        this.database = database;
    }

    @Override
    public ArrayList<GameAccount> fetchGameAccounts() {
        Connection connection = database.getConnection();
        String sqlStatement = "SELECT * FROM game_accounts";
        ArrayList<GameAccount> gameAccounts = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                GameAccount gameAccount = new GameAccount();
                gameAccount.setId(resultSet.getInt("id"));
                gameAccount.setUsername(resultSet.getString("username"));
                gameAccount.setGameName(resultSet.getString("game_name"));
                gameAccount.setAccountStatus(resultSet.getString("account_status"));
                gameAccount.setBalance(resultSet.getDouble("balance"));
                gameAccount.setPaymentMethod(resultSet.getString("payment_method"));
                gameAccounts.add(gameAccount);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching game accounts: " + e.getMessage());
        }

        return gameAccounts;
    }

    @Override
    public void saveGameAccount(GameAccount gameAccount) {
        Connection connection = database.getConnection();
        String sqlStatement = "INSERT INTO game_accounts(username, game_name, account_status, balance, payment_method) VALUES(?,?,?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            preparedStatement.setString(1, gameAccount.getUsername());
            preparedStatement.setString(2, gameAccount.getGameName());
            preparedStatement.setString(3, gameAccount.getAccountStatus());
            preparedStatement.setDouble(4, gameAccount.getBalance());
            preparedStatement.setString(5, gameAccount.getPaymentMethod());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Game account saved successfully!");
            }
        } catch (SQLException e) {
            System.err.println("Error saving game account: " + e.getMessage());
        }
    }

    @Override
    public GameAccount getGameAccountById(int id) {
        Connection connection = database.getConnection();
        String sqlStatement = "SELECT * FROM game_accounts WHERE id = ?";
        GameAccount gameAccount = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    gameAccount = new GameAccount();
                    gameAccount.setId(resultSet.getInt("id"));
                    gameAccount.setUsername(resultSet.getString("username"));
                    gameAccount.setGameName(resultSet.getString("game_name"));
                    gameAccount.setAccountStatus(resultSet.getString("account_status"));
                    gameAccount.setBalance(resultSet.getDouble("balance"));
                    gameAccount.setPaymentMethod(resultSet.getString("payment_method"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching game account by ID: " + e.getMessage());
        }

        return gameAccount;
    }

    @Override
    public void updateGameAccount(GameAccount gameAccount) {
        Connection connection = database.getConnection();
        String sqlStatement = "UPDATE game_accounts SET username = ?, game_name = ?, account_status = ?, balance = ?, payment_method = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            preparedStatement.setString(1, gameAccount.getUsername());
            preparedStatement.setString(2, gameAccount.getGameName());
            preparedStatement.setString(3, gameAccount.getAccountStatus());
            preparedStatement.setDouble(4, gameAccount.getBalance());
            preparedStatement.setString(5, gameAccount.getPaymentMethod());
            preparedStatement.setInt(6, gameAccount.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Game account updated successfully!");
            }
        } catch (SQLException e) {
            System.err.println("Error updating game account: " + e.getMessage());
        }
    }

    @Override
    public void deleteGameAccount(int id) {
        Connection connection = database.getConnection();
        String sqlStatement = "DELETE FROM game_accounts WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Game account deleted successfully!");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting game account: " + e.getMessage());
        }
    }
}