package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Player;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    Button pickUp = new Button("Pick Up");

    public Inventory playerInventory = new Inventory();
    GameMap map = MapLoader.loadMap();
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    VBox inventory = new VBox();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 2, 0);
        ui.add(new Label("Inventory: "), 0, 2);
        ui.add(inventory, 2,2 );
        ui.add(pickUp, 10, 0);
        setPickUpButtonActive(false);
        pickUp.setDefaultButton(true);
        pickUp.setOnAction( actionEvent -> {
            Cell cell = map.getCell(map.getPlayer().getX(), map.getPlayer().getY());
            playerInventory.addToInventory(cell.getType());
            cell.setType(CellType.FLOOR);
            setPickUpButtonActive(false);
        });

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }
    private void PickUpButtonActivity() {
        if (isItem()) {
            setPickUpButtonActive(true);
            canvas.setFocusTraversable(true);
        } else {
            setPickUpButtonActive(false);
        }
    }

    private void refreshInventory() {
        inventory.getChildren().removeAll(inventory.getChildren());
        for (CellType item : playerInventory.getItems()) {
            inventory.getChildren().add(new Label(item.getTileName()));
        }
    }

    private boolean isItem() {
        return (map.getCell(map.getPlayer().getX(), map.getPlayer().getY()).getType() == CellType.WEAPON ||
                map.getCell(map.getPlayer().getX(), map.getPlayer().getY()).getType() == CellType.KEY );
    }
    private void setPickUpButtonActive(boolean isActive) {
        pickUp.setDisable(!isActive);
        pickUp.setVisible(isActive);
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1);
                PickUpButtonActivity();
                refresh();
                refreshInventory();

                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                PickUpButtonActivity();
                refresh();
                refreshInventory();

                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                PickUpButtonActivity();
                refresh();
                refreshInventory();

                break;
            case RIGHT:
                map.getPlayer().move(1,0);
                PickUpButtonActivity();
                refresh();
                refreshInventory();

                break;
        }
    }

    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
    }
}
