package com.example.astaralgorithm;

import com.example.astaralgorithm.controllers.WindowController;
import com.example.astaralgorithm.controllers.cell.CellController;
import com.example.astaralgorithm.controllers.game.GameController;
import com.example.astaralgorithm.graphics.color.SceneColors;
import com.example.astaralgorithm.graphics.type.SceneColorTypes;
import com.example.astaralgorithm.objects.Arena;
import com.example.astaralgorithm.utils.Updater;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class AStarApplication extends Application implements Updater {
    private Arena arena;
    private CellController cellController;
    private Scene scene;
    private GameController gameController;
    private boolean isInit = false;

    @Override
    public void start(Stage primaryStage) {
        this.initComponents();

        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                renderUpdate();
            }
        };

        gameLoop.start();

        primaryStage.setTitle("AStarAlgorithm");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void update() {
        if (!this.isInit)
            return;

        this.arena.update();
        this.cellController.update();
    }

    private void renderUpdate() {
        if (!this.isInit)
            return;

        int currentHeight = (int)this.scene.getHeight();
        int currentWidth = (int)this.scene.getWidth();

        WindowController.setWidthHeight(currentWidth, currentHeight);
    }
    
    private void initComponents() {
        this.arena = new Arena();

        Color colorBackground = SceneColors.get(SceneColorTypes.BACKGROUDN);
        this.scene = new Scene(arena, WindowController.getWidth(), WindowController.getHeight(), colorBackground);

        this.cellController = new CellController(arena);
        this.gameController = new GameController(this.cellController, this.arena);
        arena.setOnKeyPressed(event -> {
            arena.handleKeyPressed(event);
            gameController.handleAStarKeyPressed(event);
        });

        this.isInit = true;
    }

    /**
     * Start Program
     */
    public static void main(String[] args) {
        launch(args);
    }
} // End AStarApplication
