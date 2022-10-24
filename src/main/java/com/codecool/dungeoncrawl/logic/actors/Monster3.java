package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Monster3 extends Actor {
    public Monster3(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "moster";
    }

}
