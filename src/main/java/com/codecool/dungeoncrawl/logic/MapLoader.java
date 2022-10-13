package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.*;


import java.io.InputStream;
import java.util.Scanner;

public class    MapLoader {
    public static GameMap loadMap() {
        InputStream is = MapLoader.class.getResourceAsStream("/map.txt");
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();
        scanner.nextLine();
        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                            cell.setType(CellType.MONSTER);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell, "KING"));
                            break;
                        case 'W':
                            cell.setType(CellType.WEAPON);
                            break;
                        case 'K':
                            cell.setType(CellType.KEY);
                            break;
                        case 'D':
                            cell.setType(CellType.DOOR_CLOSED);
                            break;
                        case 'f':
                            cell.setType(CellType.MONSTER3);
                            break;
                        case 'g':
                            cell.setType(CellType.MONSTER2);
                            break;
                        case 'B':
                            map.setBoss(new Boss(cell));
                            cell.setType(CellType.BOSS);
                            break;
                        case 'L':
                            cell.setType(CellType.KEY_YELLOW);
                            break;
                        case 'E':
                            cell.setType(CellType.DOOR_CLOSED_YELLOW);
                            break;
                        case 'b':
                            cell.setType(CellType.FIR);
                            break;
                        case 'F':
                            cell.setType(CellType.FULL_FIR);
                            break;
                        case 'G':
                            cell.setType(CellType.CACTUS1);
                            break;
                        case 'H':
                            cell.setType(CellType.CACTUS2);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }
}
