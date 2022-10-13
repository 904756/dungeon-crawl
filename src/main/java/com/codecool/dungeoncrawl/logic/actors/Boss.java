package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import javafx.scene.input.KeyEvent;

public class Boss extends Actor {

    public CellType getType() {
        return type;
    }

    private CellType type;

    public Boss(Cell cell) {
        super(cell);
        this.type = CellType.BOSS;
    }


    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell.getType() == CellType.FLOOR) {
            cell.setBoss(null);
            nextCell.setBoss(this);
            cell = nextCell;
        }
//        private void onKeyPressed(KeyEvent keyEvent) {
//            switch (keyEvent.getCode()) {
//                map.getPlayer().move(0, -1);
//                map.getPlayer().move(0, 1);
//                map.getPlayer().move(-1, 0);
//                map.getPlayer().move(1, 0);
//                break;
//            }
    }

    @Override
    public String getTileName() {
        return "boss";
    }
}




