package Marketplace.services;

import Marketplace.entities.Transaction;
import java.util.ArrayList;

public interface TransactionServices {
    void addTransaction(Transaction transaction);
    void updateTransaction(Transaction transaction);
    void deleteTransaction(int id);
    ArrayList<Transaction> getAllTransactions();
    Transaction getTransactionDetails(int id);
}