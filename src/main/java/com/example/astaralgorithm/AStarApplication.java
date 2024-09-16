package com.example.astaralgorithm;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class AStarApplication extends Application {

    private Circle player;

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 600, 400, Color.BLACK);

        player = new Circle(20, Color.BLUE);
        player.setTranslateX(300);
        player.setTranslateY(200);
        root.getChildren().add(player);

        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateGame();
                renderGame();
            }
        };

        gameLoop.start();

        primaryStage.setTitle("Game Loop Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateGame() {
        // Логика обновления состояния игры (например, перемещение игрока)
        player.setTranslateX(player.getTranslateX() + 1); // Двигаем игрока вправо
    }

    private void renderGame() {
        // Здесь можно добавить код для отрисовки объектов игры,
        // но в данном случае JavaFX автоматически обновляет сцену.
    }

    public static void main(String[] args) {
        launch(args);
    }
}