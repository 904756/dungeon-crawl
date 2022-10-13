package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Boss;
import com.codecool.dungeoncrawl.logic.items.Item;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Cell implements Drawable {
    private CellType type;
    private Actor actor;
    private GameMap gameMap;
    private int x, y;
    private Item item;
    private Boss boss;

    public GameMap getGameMap() {
        return gameMap;
    }

    public Boss getBoss() {
        return boss;
    }

    public Cell(GameMap gameMap, int x, int y, CellType type) {
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public CellType getType() {
        return type;
    }

    public EventHandler<ActionEvent> setType(CellType type) {
        this.type = type;
        return null;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }
    public void setBoss(Boss boss) {
        this.boss = boss;
    }

    public Actor getActor() {
        return actor;
    }

    public Cell getNeighbor(int dx, int dy) {
        return gameMap.getCell(x + dx, y + dy);
    }

    @Override
    public String getTileName() {
        return type.getTileName();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public void setItem(Item item) {
        this.item = item;
    }
    public Item getItem() {
        return item;
    }
}
