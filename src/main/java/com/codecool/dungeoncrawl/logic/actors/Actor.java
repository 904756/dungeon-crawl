package com.codecool.dungeoncrawl.logic.actors;
import com.codecool.dungeoncrawl.App;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.Inventory;
import com.codecool.dungeoncrawl.Main;

public abstract class Actor implements Drawable {
    protected Cell cell;
    private int health;
    private int strenght;
    private int defence;


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
        if ((nextCell.getType() == CellType.FLOOR) ||
                (nextCell.getType() == CellType.WEAPON)  ||
                (nextCell.getType() == CellType.KEY)  ||
                (nextCell.getType() == CellType.KEY_YELLOW) ||
                (nextCell.getType() == CellType.DOOR_OPEN_YELLOW) ||
                (nextCell.getType() == CellType.MONSTER) ||
                (nextCell.getType() == CellType.MONSTER2) ||
                (nextCell.getType() == CellType.MONSTER3) ||
                (nextCell.getType() == CellType.STAIR) ||
                (nextCell.getType() == CellType.BOSS))
        {
        cell.setActor(null);
        cell.setBoss(null);
        nextCell.setActor(this);
        cell = nextCell;
        }

        if ((nextCell.getType() == CellType.DOOR_CLOSED) && inventory.contains("key")){
            cell.setActor(null);
            cell.setBoss(null);
            nextCell.setActor(this);
            cell = nextCell;
            cell.setType(CellType.DOOR_OPEN);
        }
        if ((nextCell.getType() == CellType.DOOR_CLOSED_YELLOW) && inventory.contains("keyYellow")){
            cell.setActor(null);
            cell.setBoss(null);
            nextCell.setActor(this);
            cell = nextCell;
            cell.setType(CellType.DOOR_OPEN_YELLOW);
        }
    }

    public void moveboss(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if ((nextCell.getType() == CellType.FLOOR) ||
                (nextCell.getType() == CellType.WEAPON)  ||
                (nextCell.getType() == CellType.KEY)  ||
                (nextCell.getType() == CellType.KEY_YELLOW) ||
                (nextCell.getType() == CellType.DOOR_OPEN_YELLOW) ||
                (nextCell.getType() == CellType.MONSTER) ||
                (nextCell.getType() == CellType.MONSTER2) ||
                (nextCell.getType() == CellType.MONSTER3) ||
                (nextCell.getType() == CellType.STAIR) ||
                (nextCell.getType() == CellType.BOSS))
        {
            cell.setBoss(null);
            cell.setType(CellType.FLOOR);
            nextCell.setBoss(this);
            cell = nextCell;
            cell.setType(CellType.FLOOR);
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
