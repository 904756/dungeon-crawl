package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import javafx.scene.input.KeyEvent;

public class Boss extends Actor {
    private int health;
    private int strenght;

    public Boss(Cell cell) {
        super(cell);
        cell.setType(CellType.BOSS);
        this.health = 10;
        this.strenght = 10;
    }

    @Override
    public String getTileName() {
        return "boss";
    }
}





