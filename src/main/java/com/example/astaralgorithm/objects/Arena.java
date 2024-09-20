package com.example.astaralgorithm.objects;

import com.example.astaralgorithm.utils.Updater;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.HashMap;

import static javafx.scene.input.KeyCode.*;


public class Arena  extends Group implements Updater {
    private static final HashMap<KeyCode, Boolean> statesByKeyCode = new HashMap<>();

    protected static double scalingSpeed = 0.01;
    protected static double movementSpeed = 2.0;

    private static final KeyCode[] handleKeys = {EQUALS, MINUS, UP, RIGHT, DOWN, LEFT};
    static {
        for (KeyCode key : handleKeys) {
            statesByKeyCode.put(key, false);
        }
    }

    public Arena() {
        super();

        this.setOnKeyReleased(this::handleKeyReleased);
    }

    public void handleKeyPressed(KeyEvent event) {
        KeyCode key = event.getCode();
        if (!statesByKeyCode.containsKey(key)) {
            return;
        }
        statesByKeyCode.put(key, true);
    }

    public void handleKeyReleased(KeyEvent event) {
        KeyCode key = event.getCode();
        if (!statesByKeyCode.containsKey(key)) {
            return;
        }
        statesByKeyCode.put(key, false);
    }

    public void update() {
        for(KeyCode key : handleKeys) {
            if (statesByKeyCode.get(key))
                this.updateValueByKey(key);
        }
    }
    
    protected void updateValueByKey(KeyCode key) {
        switch (key) {
            case EQUALS:                                                // Approach "+"
                this.updateScale(Arena.scalingSpeed);
                break;

            case MINUS:                                                // Department "-"
                this.updateScale(-Arena.scalingSpeed);
                break;

            case UP:                                                    // Step up "+"
                this.setTranslateY(this.getTranslateY() + movementSpeed);
                break;

            case DOWN:                                                  // Step down "+"
                this.setTranslateY(this.getTranslateY() - movementSpeed);
                break;

            case RIGHT:                                                 // Step right
                this.setTranslateX(this.getTranslateX() - movementSpeed);
                break;

            case LEFT:                                                  // Step left "+"
                this.setTranslateX(this.getTranslateX() + movementSpeed);
                break;
        }
    }

    protected void updateScale(double speed) {
        double currentScaleX = this.getScaleX();
        double currentScaleY = this.getScaleY();

        double newScaleX = currentScaleX + speed;
        double newScaleY = currentScaleY + speed;

        this.setScaleX(newScaleX);
        this.setScaleY(newScaleY);
    }
} // End Arena

