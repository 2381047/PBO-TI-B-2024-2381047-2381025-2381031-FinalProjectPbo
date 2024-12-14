package Marketplace.services;

import Marketplace.entities.GameAccount;
import java.util.ArrayList;


public interface GameAccountServices {
    void registerGameAccount(GameAccount gameAccount);
    void updateGameAccount(GameAccount gameAccount);
    void deleteGameAccount(int id);
    ArrayList<GameAccount> getAllGameAccounts();
    GameAccount getGameAccountDetails(int id);
}