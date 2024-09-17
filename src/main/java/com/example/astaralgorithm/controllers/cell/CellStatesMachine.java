package com.example.astaralgorithm.controllers.cell;

import com.example.astaralgorithm.objects.cell.CellTypes.Type;
import javafx.scene.input.KeyEvent;


public class CellStatesMachine {
    private Type currentState;

    public CellStatesMachine(Type state) {
        this.currentState = state;
    }

    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case BACK_SLASH:        // "\"
                this.currentState = Type.DEFAULT;
                break;

            case CLOSE_BRACKET:     // "]"
                this.currentState = Type.WALL;
                break;

            case P:                 // "P" - start
                this.currentState = Type.START;
                break;

            case O:                 // "O" - landmark (finish)
                this.currentState = Type.FINISH;
                break;
        }
    }

    public Type getCurrentState() {
        return this.currentState;
    }
}
