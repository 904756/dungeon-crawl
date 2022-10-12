package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.LinkedList;

public class Inventory {

    LinkedList<CellType> inventory = new LinkedList<>();

    public LinkedList<CellType> getItems() {
        return inventory;
    }

    public void addToInventory(CellType item) {
        inventory.add(item);
    }

    public void removeItem(String name) {
        inventory.removeIf(item -> item.getTileName().equals(name));
    }

    public boolean contains(String name) {
        return inventory.stream().anyMatch(item -> item.getTileName().equals(name));
    }
}
