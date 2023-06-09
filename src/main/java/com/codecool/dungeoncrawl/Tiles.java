package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    private static final Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static final Map<String, Tile> tileMap = new HashMap<>();
    public static int TILE_WIDTH = 32;

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(19, 11));
        tileMap.put("floor", new Tile(0, 0));
        tileMap.put("player", new Tile(27, 0));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("door", new Tile(9, 25));
        tileMap.put("openDoor", new Tile(8, 10));
        tileMap.put("key", new Tile(16, 23));
        tileMap.put("ax", new Tile(9, 29));
        tileMap.put("bush", new Tile(3, 1));
        tileMap.put("water", new Tile(8, 5));
        tileMap.put("water<", new Tile(10, 5));
        tileMap.put("closedHole", new Tile(23, 27));
        tileMap.put("openedHole", new Tile(23, 26));
        tileMap.put("armor", new Tile(1, 23));
        tileMap.put("house", new Tile(0, 21));
        tileMap.put("potion", new Tile(25, 23));
        tileMap.put("wizard", new Tile(24, 2));
        tileMap.put("cabbage", new Tile(23, 22));
        tileMap.put("mushrooms", new Tile(15, 29));
        tileMap.put("windoor", new Tile(0, 9));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }

    public static class Tile {
        public final int x, y, w, h;

        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }
}
