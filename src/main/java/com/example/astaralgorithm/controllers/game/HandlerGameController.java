package com.example.astaralgorithm.controllers.game;

import com.example.astaralgorithm.controllers.cell.CellController;
import com.example.astaralgorithm.math.AStar;
import com.example.astaralgorithm.objects.Arena;
import com.example.astaralgorithm.objects.cell.Cell;
import javafx.scene.Group;
import javafx.scene.input.KeyEvent;


public abstract class HandlerGameController extends Group {
    public CellController cellController;
    protected AStar aStar = null;

    public HandlerGameController(CellController cellController, Arena arena) {
        super();
        arena.getChildren().add(this);

        this.cellController = cellController;

        //this.setOnKeyPressed(this::handleAStarKeyPressed);
    }

    public void handleAStarKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case ENTER:
                this.startAStar();
                break;
        }
    }

    protected void startAStar() {
        if (this.aStar != null) {
            cellController.clearTimeDesignations();
        }

        if (cellController.startNode == null || cellController.finalNode == null) {
            // TODO: Logic if startNode or finalNode is not init
            return;
        }

        Cell[][] cellsInWindow = this.cellController.getCellsInWindow();

        if (cellsInWindow.length == 0) {
            // TODO: Logic if out of window
            return;
        }

        this.aStar = new AStar(cellsInWindow, cellController.startNode, cellController.finalNode);
        aStar.findPath();
    }

}
