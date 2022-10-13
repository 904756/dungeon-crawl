package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Player extends Actor {
    @Override
    public int getHealth() {
        return health;
    }

    public int getStrenght() {
        return strenght;
    }

    public int getDefence() {
        return defence;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setStrenght(int strenght) {
        this.strenght = strenght;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

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
