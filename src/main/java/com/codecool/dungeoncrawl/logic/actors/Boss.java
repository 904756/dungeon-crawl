package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import javafx.scene.input.KeyEvent;

public class Boss extends Actor {

    public Boss(Cell cell) {
        super(cell);
    }

//    public void move(int dx, int dy) {
//        Cell nextCell = cell.getNeighbor(dx, dy);
//            cell.setBoss(null);
//            nextCell.setBoss(this);
//            cell = nextCell;

//    }
    @Override
    public String getTileName() {
        return "boss";
    }
}





