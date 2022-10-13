package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Monster2 extends Actor {
    public MOnster2(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "moster";
    }

}
