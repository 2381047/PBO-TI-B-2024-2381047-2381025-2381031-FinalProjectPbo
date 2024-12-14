package Marketplace.repositories;

import Marketplace.entities.Transaction;

import java.util.ArrayList;

public interface TransactionRepository {
    ArrayList<Transaction> fetchTransactions();
    void saveTransaction(Transaction transaction);
    Transaction getTransactionById(int id);
    void updateTransaction(Transaction transaction);
    void deleteTransaction(int id);
}