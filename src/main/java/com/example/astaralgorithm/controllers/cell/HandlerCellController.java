package com.example.astaralgorithm.controllers.cell;

import com.example.astaralgorithm.controllers.WindowController;
import com.example.astaralgorithm.objects.Arena;
import com.example.astaralgorithm.objects.cell.Cell;
import com.example.astaralgorithm.objects.cell.CellTypes;
import com.example.astaralgorithm.objects.cell.CellTypes.Type;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;


/*
* Class handles keystroke events, mouse control
* */
public abstract class HandlerCellController extends BaseCellController {

    public Cell startNode = null, finalNode = null;
    public CellStatesMachine statesMachine;
    public boolean isMousePressed = false;

    public HandlerCellController(Arena arena) {
        super();
        arena.getChildren().add(this);

        statesMachine = new CellStatesMachine(Type.DEFAULT);
        this.setOnKeyPressed(event -> {
            statesMachine.handleKeyPress(event);
            this.handleKeyPress(event);
        });
        this.requestFocus();
    }

    public void handleMousePressed(MouseEvent event) {
        if (event.getButton() != MouseButton.PRIMARY)
            return;

        Cell source = (Cell)event.getSource();
        Type type = this.statesMachine.getCurrentState();

        this.isMousePressed = true;

        source.setType(type);
    }

    public void handleMouseReleased(MouseEvent event) {
        if (event.getButton() != MouseButton.PRIMARY)
            return;

        this.isMousePressed = false;
    }

    public void handleMouseDragged(MouseEvent event) {
        if (!this.isMousePressed || event.getButton() != MouseButton.PRIMARY)
            return;

        double mouseX = event.getX();
        double mouseY = event.getY();

        int col = (int) (mouseX / WindowController.getCellSize());
        int row = (int) (mouseY / WindowController.getCellSize());

        Cell source = cells[col + additionalSquares][row + additionalSquares];
        Type type = this.statesMachine.getCurrentState();


        source.setType(type);
    }

    protected void devSetWall() {
        Cell[][] cellInWinow = this.getCellsInWindow();
        for(Cell[] rowCells : cellInWinow) {
            for(Cell cell : rowCells) {
                cell.setType(Type.WALL);
            }
        }
    }

    protected void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case BACK_SPACE:
                this.resetCommand();
                break;
            case T:
                this.devSetWall();
                break;
        }
    }

    public boolean handleCellType(Cell source, Type type) {
        if (CellTypes.tempType.contains(type)) {
            return true;
        }

        if (type == Type.START) {
            if (this.startNode == null) {
                this.startNode = source;
                return true;
            }
            return false;
        }

        if (type == Type.FINISH) {
            if (this.finalNode == null) {
                this.finalNode = source;
                return true;
            }
            return false;
        }

        if (source == this.startNode) {
            this.startNode = null;
        } else if (source == this.finalNode)
            this.finalNode = null;

        return true;
    }
} // End HandleCellController
