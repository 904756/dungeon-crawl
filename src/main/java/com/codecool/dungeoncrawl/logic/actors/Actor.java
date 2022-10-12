package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.App;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.Inventory;
import com.codecool.dungeoncrawl.Main;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health = 10;


    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    private Inventory inventory;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if ( (nextCell.getType() == CellType.FLOOR) || (nextCell.getType() == CellType.WEAPON) || (nextCell.getType() == CellType.KEY)){
//        || ((nextCell.getType() == CellType.DOOR_CLOSED)&& inventory.contains("key"))){
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
        }
        if ((nextCell.getType() == CellType.DOOR_CLOSED)&& inventory.contains("key")){
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    public int getHealth() {
        return health;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }
}
