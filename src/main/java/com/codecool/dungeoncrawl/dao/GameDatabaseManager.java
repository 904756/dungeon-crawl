package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.PlayerModel;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class GameDatabaseManager {
    private PlayerDao playerDao;

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        playerDao = new PlayerDaoJdbc(dataSource);
    }

    public void savePlayer(Player player) {
        PlayerModel model = new PlayerModel(player);
        playerDao.add(model);
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
}
