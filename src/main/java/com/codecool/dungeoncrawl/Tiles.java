package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();
    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(19, 11));
        tileMap.put("floor", new Tile(0, 0));
        tileMap.put("player", new Tile(27, 0));
        tileMap.put("skeleton", new Tile(24, 8));
        tileMap.put("skeleton1", new Tile(26, 8));
        tileMap.put("skeleton2", new Tile(28, 8));
        tileMap.put("key", new Tile(17 , 23));
        tileMap.put("keyYellow", new Tile(16 , 23));
        tileMap.put("sword", new Tile(0, 31));
        tileMap.put("open door", new Tile(2, 9));
        tileMap.put("closed door", new Tile(0, 9));
        tileMap.put("open door yellow", new Tile(16, 22));
        tileMap.put("closed door yellow", new Tile(9, 25));
        tileMap.put("boss", new Tile(22, 23));
        tileMap.put("fir", new Tile(1, 1));
        tileMap.put("full fir", new Tile(0, 1));
        tileMap.put("cactus1", new Tile(6, 1));
        tileMap.put("cactus2", new Tile(7, 1));
        tileMap.put("next_level", new Tile(12, 23));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}