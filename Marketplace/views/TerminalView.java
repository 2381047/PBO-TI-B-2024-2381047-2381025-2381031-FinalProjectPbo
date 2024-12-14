package Marketplace.views;

import Marketplace.entities.GameAccount;
import Marketplace.entities.Transaction;
import Marketplace.services.GameAccountServicesImpl;
import Marketplace.services.TransactionServiceImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Component
public class TerminalView implements Menus {
    private Scanner input = new Scanner(System.in);

    private GameAccountServicesImpl gameAccountServices;
    private TransactionServiceImpl transactionServices;

    public TerminalView(GameAccountServicesImpl gameAccountServices, TransactionServiceImpl transactionServices) {
        this.gameAccountServices = gameAccountServices;
        this.transactionServices = transactionServices;
    }

    @Override
    public void run() {
        mainMenus();
    }

    private void mainMenus() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("-- GAME ACCOUNT MANAGEMENT SYSTEM --");
            System.out.println("1. Add Game Account");
            System.out.println("2. View All Game Accounts");
            System.out.println("3. Select Game Account");
            System.out.println("4. Add Transaction to Account");
            System.out.println("5. View Transaction History");
            System.out.println("6. Update Game Account");
            System.out.println("7. Delete Game Account");
            System.out.println("8. View Game Account Details");
            System.out.println("9. View Total Balance");
            System.out.println("10. Transaction Report");
            System.out.println("0. EXIT");
            System.out.print("Enter your choice: ");
            int selectedMenu = input.nextInt();
            input.nextLine();

            switch (selectedMenu) {
                case 1:
                    addGameAccount();
                    break;
                case 2:
                    showAllGameAccounts();
                    break;
                case 3:
                    showAllGameAccounts();
                    selectGameAccount();
                    break;
                case 4:
                    showAllGameAccounts();
                    addTransactionToAccount();
                    break;
                case 5:
                    showTransactionHistory();
                    break;
                case 6:
                    showAllGameAccounts();
                    updateGameAccount();
                    break;
                case 7:
                    showAllGameAccounts();
                    deleteGameAccount();
                    break;
                case 8:
                    showAllGameAccounts();
                    viewGameAccountDetails();
                    break;
                case 9:
                    showTotalBalance();
                    break;
                case 10:
                    transactionReport();
                    break;
                case 0:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid menu option!");
            }
        }
    }

    private void viewGameAccountDetails(){
        System.out.print("Enter Game Account ID: ");
        int id = input.nextInt();
        input.nextLine();
        GameAccount gameAccount = gameAccountServices.getGameAccountDetails(id);
        if(gameAccount == null){
            System.out.println("Game account not found.");
        } else {
            System.out.println("Game Account Details:");
            System.out.println("ID: " + gameAccount.getId());
            System.out.println("Username: " + gameAccount.getUsername());
            System.out.println("Game Name: " + gameAccount.getGameName());
            System.out.println("Status: " + gameAccount.getAccountStatus());
            System.out.println("Balance: " + gameAccount.getBalance());
            System.out.println("Payment Method: " + gameAccount.getPaymentMethod());
        }
    }

    private void addGameAccount(){
        GameAccount gameAccount = new GameAccount();
        System.out.print("Enter Username: ");
        gameAccount.setUsername(input.nextLine());
        System.out.print("Enter Game Name: ");
        gameAccount.setGameName(input.nextLine());
        System.out.print("Enter Account Status: ");
        gameAccount.setAccountStatus(input.nextLine());
        System.out.print("Enter Balance: ");
        gameAccount.setBalance(input.nextDouble());
        input.nextLine();
        System.out.print("Enter Payment Method: ");
        gameAccount.setPaymentMethod(input.nextLine());

        gameAccountServices.registerGameAccount(gameAccount);
        System.out.println("Game account created");
    }

    private void updateGameAccount(){
        System.out.print("Enter Game Account ID to update: ");
        int id = input.nextInt();
        input.nextLine();

        GameAccount gameAccount = gameAccountServices.getGameAccountDetails(id);
        if(gameAccount == null){
            System.out.println("Game account not found.");
        } else {
            System.out.print("Enter new Username (leave blank to keep current): ");
            String newUsername = input.nextLine();
            if (!newUsername.isEmpty()) {
                gameAccount.setUsername(newUsername);
            }

            System.out.print("Enter new Game Name (leave blank to keep current): ");
            String newGameName = input.nextLine();
            if (!newGameName.isEmpty()) {
                gameAccount.setGameName(newGameName);
            }

            System.out.print("Enter new Account Status (leave blank to keep current): ");
            String newStatus = input.nextLine();
            if (!newStatus.isEmpty()) {
                gameAccount.setAccountStatus(newStatus);
            }

            System.out.print("Enter new Balance (leave blank to keep current): ");
            String newBalanceStr = input.nextLine();
            if (!newBalanceStr.isEmpty()) {
                gameAccount.setBalance(Double.parseDouble(newBalanceStr));
            }

            System.out.print("Enter new Payment Method (leave blank to keep current): ");
            String newPaymentMethod = input.nextLine();
            if (!newPaymentMethod.isEmpty()) {
                gameAccount.setPaymentMethod(newPaymentMethod);
            }
            gameAccountServices.updateGameAccount(gameAccount);
            System.out.println("Game account updated!");

        }

    }

    private void deleteGameAccount(){
        System.out.print("Enter Game Account ID to delete: ");
        int id = input.nextInt();
        input.nextLine();
        GameAccount gameAccount = gameAccountServices.getGameAccountDetails(id);
        if(gameAccount == null){
            System.out.println("Game account not found.");
        } else {
            gameAccountServices.deleteGameAccount(id);
            System.out.println("Game account deleted.");
        }
    }
    private void selectGameAccount() {
        System.out.print("Enter Game Account ID to select: ");
        int id = input.nextInt();
        input.nextLine();

        GameAccount gameAccount = gameAccountServices.getGameAccountDetails(id);
        if (gameAccount != null) {
            System.out.println("Selected Game Account: " + gameAccount.getUsername() + ", Game: " + gameAccount.getGameName());
        } else {
            System.out.println("Game account not found with ID: " + id);
        }
    }

    private void addTransactionToAccount() {
        System.out.print("Enter Game Account ID to add transaction to: ");
        int gameAccountId = input.nextInt();
        input.nextLine();

        GameAccount gameAccount = gameAccountServices.getGameAccountDetails(gameAccountId);
        if (gameAccount == null) {
            System.out.println("Game account not found!");
            return;
        }


        System.out.print("Enter transaction amount: ");
        double amount = input.nextDouble();
        input.nextLine();
        System.out.print("Enter transaction status: ");
        String status = input.nextLine();
        Transaction transaction = new Transaction();
        transaction.setGameAccountId(gameAccountId);
        transaction.setAmount(amount);
        transaction.setTransactionStatus(status);
        transaction.setTransactionDate(new java.util.Date());

        transactionServices.addTransaction(transaction);

        System.out.println("Transaction added for account: " + gameAccount.getUsername());
    }
    private void showTransactionHistory(){
        System.out.print("Enter Game Account ID to view transactions: ");
        int gameAccountId = input.nextInt();
        input.nextLine();

        ArrayList<Transaction> transactionList = transactionServices.getAllTransactions();

        boolean found = false;
        for (Transaction i : transactionList) {
            if (i.getGameAccountId() == gameAccountId) {
                System.out.println("Transaction ID: " + i.getId());
                System.out.println("Amount: " + i.getAmount());
                System.out.println("Date : " + i.getTransactionDate());
                System.out.println("Status: " + i.getTransactionStatus());
                System.out.println("--------------------");
                found = true;
            }
        }

        if(!found){
            System.out.println("Transaction not found for account ID : " + gameAccountId);
        }
    }

    private void showTotalBalance() {
        double totalBalance = 0.0;
        ArrayList<GameAccount> gameAccounts = gameAccountServices.getAllGameAccounts();
        for (GameAccount account : gameAccounts) {
            totalBalance += account.getBalance();
        }
        System.out.println("Total Balance across all accounts: " + totalBalance);
    }

    private void transactionReport(){
        Map<String,Integer> transactionStatus = new HashMap<>();
        ArrayList<Transaction> transactionList = transactionServices.getAllTransactions();
        for(Transaction i : transactionList){
            if (transactionStatus.containsKey(i.getTransactionStatus())){
                int tmp = transactionStatus.get(i.getTransactionStatus());
                tmp++;
                transactionStatus.put(i.getTransactionStatus(),tmp);
            } else {
                transactionStatus.put(i.getTransactionStatus(),1);
            }
        }
        System.out.println("Transaction Report");
        for(Map.Entry<String,Integer> i : transactionStatus.entrySet()){
            System.out.println("Transaction Status : " + i.getKey() + ", Transaction Count : " + i.getValue());
        }

    }

    private void showAllGameAccounts() {
        int nums = 1;
        ArrayList<GameAccount> gameAccountList = gameAccountServices.getAllGameAccounts();
        System.out.printf("%-5s %-15s %-15s %-15s %-15s %-15s%n", "No.", "Username", "Game Name", "Status", "Balance", "Payment");
        if (gameAccountList.isEmpty()) {
            System.out.println("No Game Accounts found!");
            return;
        }
        for (GameAccount i : gameAccountList) {
            System.out.printf("%-5d %-15s %-15s %-15s %-15f %-15s%n", nums, i.getUsername(), i.getGameName(), i.getAccountStatus(), i.getBalance(), i.getPaymentMethod());
            nums++;
        }


    }
}