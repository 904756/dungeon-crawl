package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    MONSTER("monster"),
    MONSTER2("skeleton1"),
    MONSTER3("skeleton2"),
    KEY("key"),
    KEY_YELLOW("keyYellow"),
    WEAPON ("sword"),
    DOOR_CLOSED ("closed door"),
    DOOR_OPEN ("open door"),
    DOOR_CLOSED_YELLOW ("closed door yellow"),
    DOOR_OPEN_YELLOW ("open door yellow"),
    BOSS ("boss"),
    FIR("fir"),
    FULL_FIR("full fir"),
    CACTUS1("cactus1"),
    CACTUS2("cactus2");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
