package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Boss;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;

    public Item getIteml() {
        return item;
    }

    public void setIteml(Item iteml) {
        this.item = iteml;
    }

    private Item item;

    private Player player;

    private Boss boss;

    public Boss getBoss() {
        return boss;
    }

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setBoss(Boss boss) {
        this.boss = boss;
    }
}
