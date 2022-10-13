package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Player extends Actor {
    private int health;
    private int strenght;
    private int defence;

    public Player(Cell cell, String name) {
        super(cell);
        this.health = 50;
        this.strenght = 25;
        this.defence = 40;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private String name;


    public String getTileName() {
        return "player";
    }
}
