package marketplace.repositories;

import marketplace.entities.GameAccount;

import java.util.ArrayList;

public interface GameAccountRepository {
    ArrayList<GameAccount> fetchGameAccounts();
    void saveGameAccount(GameAccount gameAccount);
    GameAccount getGameAccountById(int id);
    void updateGameAccount(GameAccount gameAccount);
    void deleteGameAccount(int id);
}