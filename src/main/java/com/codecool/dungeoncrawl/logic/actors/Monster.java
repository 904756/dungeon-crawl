package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Monster extends Actor {
    @Override
    public int getHealth() {
        return health;
    }

    public int getStrenght() {
        return strenght;
    }

    private int health;
    private int strenght;

    public Monster(Cell cell) {
        super(cell);
        this.health = 10;
        this.strenght = 10;
    }


    @Override
    public String getTileName() {
        return "monster";
    }


}