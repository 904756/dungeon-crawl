package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    MONSTER("monster"),
    KEY("key"),
    WEAPON ("sword"),
    DOOR_CLOSED ("closed door"),
    DOOR_OPEN ("open door");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
