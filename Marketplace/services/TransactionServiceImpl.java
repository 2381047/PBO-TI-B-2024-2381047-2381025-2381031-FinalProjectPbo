package Marketplace.services;

import Marketplace.entities.Transaction;
import Marketplace.repositories.TransactionRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class TransactionServiceImpl implements TransactionServices {

    private TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        transactionRepository.saveTransaction(transaction);
    }

    @Override
    public void updateTransaction(Transaction transaction) {
        transactionRepository.updateTransaction(transaction);
    }

    @Override
    public void deleteTransaction(int id) {
        transactionRepository.deleteTransaction(id);
    }

    @Override
    public ArrayList<Transaction> getAllTransactions() {
        return transactionRepository.fetchTransactions();
    }

    @Override
    public Transaction getTransactionDetails(int id) {
        return transactionRepository.getTransactionById(id);
    }
}