package Marketplace.entities;

import java.util.Date;

public class Transaction {
    private int id;
    private int gameAccountId; // Reference to the GameAccount
    private double amount; // Amount for top-up
    private Date transactionDate;
    private String transactionStatus; // e.g., Completed, Pending, Failed

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGameAccountId() {
        return gameAccountId;
    }

    public void setGameAccountId(int gameAccountId) {
        this.gameAccountId = gameAccountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
}