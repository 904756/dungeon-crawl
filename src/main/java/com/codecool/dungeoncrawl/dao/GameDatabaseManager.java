package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.InventoryModel;
import com.codecool.dungeoncrawl.model.PlayerModel;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.SQLException;
import java.util.LinkedList;

public class GameDatabaseManager {
    private GameState gameState;
    private PlayerModel playerModel;
    private PlayerDao playerDao;
    private InventoryDao inventoryDao;
    private GameStateDao gameStateDao;

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        playerDao = new PlayerDaoJdbc(dataSource);
        inventoryDao = new InventoryDaoJdbc(dataSource);
        gameStateDao = new GameStateDaoJdbc(dataSource);
    }

    public void savePlayer(Player player, boolean update) {
        playerModel = new PlayerModel(player);
        if (update) playerDao.add(playerModel, player.getPlayerId());
        else playerDao.add(playerModel);
    }


    public void saveGameState(String savedName, boolean update) {
        Date savedAtTime = new Date(System.currentTimeMillis());
        gameState = new GameState(savedName, "/map.txt", savedAtTime, playerModel);
        if (update) gameStateDao.add(gameState, savedName);
        else gameStateDao.add(gameState);
    }

    public void saveInventory(LinkedList<Item> inventory, boolean update) {
        if (update) {
            inventory.forEach(item -> inventoryDao.delete(new InventoryModel(item.getTileName(), gameState.getId())));
        }
        inventory.forEach(item -> inventoryDao.add(new InventoryModel(item.getTileName(), gameState.getId())));
    }

    public boolean ifSaveNameExist(String saveName) {
        return gameStateDao.ifSaveNameExist(saveName);
    }

    public enum ServerInfo {
        DB("dungen_crawl"),
        HOST("109.97.198.35"),

        PORT("5432"),

        URL("jdbc:postgresql://"),
        USER("postgres"),
        PASSWORD("admin");

        private final String info;

        ServerInfo(String info) {
            this.info = info;
        }
    }

    static final String URL = "jdbc:postgresql://109.97.198.35:5432/dungeon_crawl";
    private DataSource connect() throws SQLException {

        PGSimpleDataSource source = new PGSimpleDataSource();
        source.setServerName(ServerInfo.HOST.info);
        source.setDatabaseName(ServerInfo.DB.info);
        source.setUser(ServerInfo.USER.info);
        source.setPassword(ServerInfo.PASSWORD.info);
        source.setPortNumber(Integer.parseInt(ServerInfo.PORT.info));

        System.out.println("Connecting...!");
        source.getConnection().close();
        System.out.println("Connecting is ready!");
        return source;
    }

    public int getNextPlayerId() {
        return playerDao.getPlayerNextId();
    }

    public void saveGame(String savedName, Player player, LinkedList<Item> inventoryContainer) {
        savePlayer(player, false);
        saveGameState(savedName, false);
        saveInventory(inventoryContainer, false);
    }

    public void updateGame(String savedName, Player player, LinkedList<Item> inventoryContainer) {
        saveGameState(savedName, true);
        int playerPreviousId = gameStateDao.getPlayerId(savedName);
        player.setPlayerId(playerPreviousId);
        savePlayer(player, true);
        saveInventory(inventoryContainer, true);
    }
}