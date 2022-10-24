package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;


public abstract class Item implements Drawable {
    private final Cell cell;

    public CellType getItemType() {
        return itemType;
    }

    private CellType itemType;


    public Item(Cell cell, CellType itemType) {
        this.cell = cell;
        this.itemType = itemType;
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