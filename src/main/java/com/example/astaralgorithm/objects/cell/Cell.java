package com.example.astaralgorithm.objects.cell;

import com.example.astaralgorithm.controllers.WindowController;
import com.example.astaralgorithm.controllers.cell.CellController;
import com.example.astaralgorithm.graphics.color.CellColors;
import com.example.astaralgorithm.objects.cell.CellTypes.Type;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Cell extends Rectangle {
    protected Type type;
    protected CellController owner;

    public Cell(int x, int y, CellController owner) {
        this(x, y, WindowController.getCellSize(), Type.DEFAULT, owner);
    }

    public Cell(int x, int y, int cellSize, Type type, CellController owner) {
        super(x, y, cellSize, cellSize);
        owner.getChildren().add(this);

        this.type = type;
        this.owner = owner;

        this.setFill(CellColors.get(type));
        this.setArcWidth(15);
        this.setArcWidth(15);
        this.setArcWidth(1);
        this.setArcHeight(1);
        this.setStroke(Color.BLACK);
        this.setStrokeWidth(1);

        this.setOnMousePressed(owner::handleMousePressed);
        this.setOnMouseReleased(owner::handleMouseReleased);
        this.setOnMouseDragged(owner::handleMouseDragged);
    }

    public void setType(Type newType) {
        if (!owner.handleCellType(this, newType))
            return;

        if (newType == type)
            return;

        if (newType == Type.FINDED && (type == Type.START || type == Type.FINISH))
            return;

        if (newType == Type.INVESTIGATED && this.type != Type.DEFAULT)
            return;

        this.type = newType;
        this.setFill(CellColors.get(this.type));
    }

    public Type getType() {
        return this.type;
    }

    public boolean isBlock() {
        return this.type == Type.WALL;
    }

    public int[] indexsByWhindow(Cell[][] window) {
        int[] result = {-1, -1};

        for (int i = 0; i < window.length; i++) {
            for (int j = 0; j < window[0].length; j++) {
                if (window[i][j] == this) {
                    result[0] = i;
                    result[1] = j;
                    break;
                }
            }
        }

        return result;
    }
}
