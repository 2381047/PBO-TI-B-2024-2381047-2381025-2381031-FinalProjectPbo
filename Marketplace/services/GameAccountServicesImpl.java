package Marketplace.services;

import Marketplace.entities.GameAccount;
import Marketplace.repositories.GameAccountRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class GameAccountServicesImpl implements GameAccountServices {

    private GameAccountRepository gameAccountRepository;

    public GameAccountServicesImpl(GameAccountRepository gameAccountRepository) {
        this.gameAccountRepository = gameAccountRepository;
    }

    @Override
    public void registerGameAccount(GameAccount gameAccount) {
        gameAccountRepository.saveGameAccount(gameAccount);
    }

    @Override
    public void updateGameAccount(GameAccount gameAccount) {
        gameAccountRepository.updateGameAccount(gameAccount);
    }

    @Override
    public void deleteGameAccount(int id) {
        gameAccountRepository.deleteGameAccount(id);
    }

    @Override
    public ArrayList<GameAccount> getAllGameAccounts() {
        return gameAccountRepository.fetchGameAccounts();
    }

    @Override
    public GameAccount getGameAccountDetails(int id) {
        return gameAccountRepository.getGameAccountById(id);
    }
}