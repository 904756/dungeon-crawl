
package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;


public class Sword extends Item {


    public Sword(Cell cell, CellType itemType) {
        super(cell, CellType.WEAPON);
    }

    @Override
    public String getTileName() {
        return "sword";
    }
}