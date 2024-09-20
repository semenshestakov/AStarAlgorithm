package com.example.astaralgorithm.controllers.cell;

import com.example.astaralgorithm.controllers.WindowController;
import com.example.astaralgorithm.objects.cell.Cell;
import com.example.astaralgorithm.objects.cell.CellTypes;
import com.example.astaralgorithm.objects.cell.CellTypes.Type;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.layout.Pane;


/**
* Manipulations with cells
*/
public abstract class BaseCellController extends Pane {
    protected static final int additionalSquares = 25;
    protected Cell[][] cells;

    /**
     * Initialize the inner and outer cells
     */
    public BaseCellController() {
        super();
        int numColumns = WindowController.getNumColumns() + additionalSquares * 2;
        int numRow = WindowController.getNumRow() + additionalSquares * 2;

        this.cells = new Cell[numColumns][numRow];

        int cellSize = WindowController.getCellSize();
        for (int _i=-additionalSquares; _i < WindowController.getNumColumns() + additionalSquares; _i++) {
            int i = _i + additionalSquares;

            for (int _j=-additionalSquares; _j < WindowController.getNumRow() + additionalSquares; _j++) {
                int j = _j + additionalSquares;

                this.createCell(i, j, _i, _j, cellSize);
            }
        }
    }

    abstract protected void createCell(int i, int j, int _i ,int  _j, int cellSize);

    /**
     * Set default type for all cells
     */
    public void resetCommand() {
        for(Cell[] rowCells : this.cells) {
            for(Cell cell : rowCells) {
                cell.setType(Type.DEFAULT);
            }
        }
    }

    /**
     * The cells that are on the screen
     */
    public Cell[][] getCellsInWindow() {
        Group group = (Group)this.getParent();

        int cellSize = WindowController.getCellSize();
        int width = WindowController.getWidth();
        int height = WindowController.getHeight();

        Point2D point0 = group.sceneToLocal(0, 0);
        Point2D point1 = group.sceneToLocal(width, height);

        int rangei1 = -1;
        int rangei2 = -1;
        for (int i = 0; i < cells.length; i++) {
            double x1 = cells[i][0].getX();
            double x2 = x1 + cellSize;

            if (point0.getX() <= x1 && x2 <= point1.getX()) {
                if (rangei1 == -1) {
                    rangei1 = i;
                }
                rangei2 = i;
            } else if (rangei1 != -1) {
                break;
            }
        }

        int rangej1 = -1;
        int rangej2 = -1;
        for (int j = 0; j < cells[0].length; j++) {
            double y1 = cells[0][j].getY();
            double y2 = y1 + cellSize;

            if (point0.getY() <= y1 && y2 <= point1.getY()) {
                if (rangej1 == -1) {
                    rangej1 = j;
                }
                rangej2 = j;
            } else if (rangej1 != -1) {
                break;
            }
        }
        if (rangei2 == -1 || rangej2 == -1) {
            return new Cell[0][0];
        }

        Cell[][] copyCells = new Cell[rangei2 - rangei1 + 1][rangej2 - rangej1 + 1];

        for (int i = 0; i < copyCells.length; i++) {
            System.arraycopy(this.cells[i + rangei1], rangej1, copyCells[i], 0, copyCells[0].length);
        }

        return copyCells;
    }

    public void clearTimeDesignations() {
        for(Cell[] rowCells : this.cells) {
            for(Cell cell : rowCells) {
                if (CellTypes.tempType.contains(cell.getType())) {
                    cell.setType(Type.DEFAULT);
                }
            }
        }
    }
} // End BaseCellController
