package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Player;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
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
import javafx.stage.WindowEvent;

public class Main extends Application {
    Button pickUp = new Button("Pick Up");
    Button attack = new Button("ATTACK");
    public Label playerNameLabel = new Label();

    public Inventory getPlayerInventory() {
        return playerInventory;
    }

    public Inventory playerInventory = new Inventory();
    GameMap map = MapLoader.loadMap();
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label StrenghtLabel = new Label();
    Label DefenceLabel = new Label();
    VBox inventory = new VBox();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        playerNameLabel.setText(map.getPlayer().getName());
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(5));
        ui.add(new Label("Player: "+ playerNameLabel.getText().toUpperCase()),0,1);
        ui.add(new Label("Health: "), 0, 2);
        ui.add(new Label("Strenght: "), 0, 3);
        ui.add(new Label("Defence: "), 0, 4);
        ui.add(new Label("Inventory: "), 0, 5);
        ui.add(DefenceLabel, 1, 4);
        ui.add(StrenghtLabel, 1, 3);
        ui.add(healthLabel, 1, 2);
        ui.add(inventory, 1,5 );
        ui.add(pickUp, 10, 0);
        setPickUpButtonActive(false);
        pickUp.setDefaultButton(true);
        pickUp.setOnAction( actionEvent -> {
            Cell cell = map.getCell(map.getPlayer().getX(), map.getPlayer().getY());
            playerInventory.addToInventory(cell.getType());
            map.getPlayer().setInventory(playerInventory);
            if ( cell.getTileName()=="sword" ) map.getPlayer().setStrenght(+50);
            cell.setType(CellType.FLOOR);
            setPickUpButtonActive(false);
        });

        ui.add(attack, 10, 0);
        setAButtonActive(false);
        attack.setDefaultButton(true);
        attack.setOnAction( actionEvent -> {
            Cell cell = map.getCell(map.getPlayer().getX(), map.getPlayer().getY());;
            if ( cell.getTileName()=="skeleton" ) map.getPlayer().setHealth(map.getPlayer().getHealth() - 5);
            if ( cell.getTileName()=="skeleton1" ) map.getPlayer().setHealth(map.getPlayer().getHealth() - 10);
            if ( cell.getTileName()=="skeleton2" ) map.getPlayer().setHealth(map.getPlayer().getHealth() - 15);
            if ( map.getPlayer().getTileName() == map.getBoss().getTileName() ) map.getPlayer().setHealth(map.getPlayer().getHealth() - 30);map.setBoss(null);

            cell.setType(CellType.FLOOR);
            setAButtonActive(false);
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
        if (map.getCell(map.getPlayer().getX(), map.getPlayer().getY()).getType() == CellType.STAIR){
            primaryStage.hide();
        }
    }
    public void handle(WindowEvent t) {
        Platform.exit();
        System.exit(0);
    }

    private void PickUpButtonActivity() {
        if (isItem()) {
            setPickUpButtonActive(true);
            canvas.setFocusTraversable(true);
        } else {
            setPickUpButtonActive(false);
        }
    }

    private void AttackUpButtonActivity() {
        if (isMonster()) {
            setAButtonActive(true);
            canvas.setFocusTraversable(true);
        } else {
            setAButtonActive(false);
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
                map.getCell(map.getPlayer().getX(), map.getPlayer().getY()).getType() == CellType.KEY ||
                map.getCell(map.getPlayer().getX(), map.getPlayer().getY()).getType() == CellType.KEY_YELLOW);
    }

    private boolean isMonster() {
        return (map.getCell(map.getPlayer().getX(), map.getPlayer().getY()).getType() == CellType.MONSTER ||
                map.getCell(map.getPlayer().getX(), map.getPlayer().getY()).getType() == CellType.MONSTER2 ||
                map.getCell(map.getPlayer().getX(), map.getPlayer().getY()).getType() == CellType.MONSTER3 ||
                map.getCell(map.getPlayer().getX(), map.getPlayer().getY()) == map.getCell(map.getBoss().getX(), map.getBoss().getY())  );
    }

    private void setPickUpButtonActive(boolean isActive) {
        pickUp.setDisable(!isActive);
        pickUp.setVisible(isActive);
    }

    private void setAButtonActive(boolean isActive) {
        attack.setDisable(!isActive);
        attack.setVisible(isActive);
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1);
                refresh();
                if ( map.getBoss() != null) map.getBoss().moveboss(0,-1);
                refresh();
                refresh();
                PickUpButtonActivity();
                AttackUpButtonActivity();

                refreshInventory();
                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                refresh();
                if ( map.getBoss() != null) map.getBoss().move(-1,0);
                refresh();
                PickUpButtonActivity();
                AttackUpButtonActivity();
                refresh();
                refreshInventory();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                refresh();
                if ( map.getBoss() != null) map.getBoss().move(0,1);
                refresh();
                PickUpButtonActivity();
                AttackUpButtonActivity();
                refresh();
                refreshInventory();

                break;
            case RIGHT:
                map.getPlayer().move(1,0);
                refresh();
                if ( map.getBoss() != null) map.getBoss().move(1,0);
                refresh();
                PickUpButtonActivity();
                AttackUpButtonActivity();
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
        StrenghtLabel.setText("" + map.getPlayer().getStrenght());
        DefenceLabel.setText("" + map.getPlayer().getDefence());
    }
}
