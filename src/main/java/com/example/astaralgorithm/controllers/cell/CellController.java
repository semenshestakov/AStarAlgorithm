package com.example.astaralgorithm.controllers.cell;

import com.example.astaralgorithm.objects.Arena;
import com.example.astaralgorithm.objects.cell.Cell;
import com.example.astaralgorithm.utils.Updater;

/**
* Class CellController -> HandleCellController -> BaseCellController -> Pane(JavaFX)
*/
public class CellController extends HandlerCellController implements Updater {

    public CellController(Arena arena) {
        super(arena);
    }

    @Override
    protected void createCell(int i, int j, int _i, int _j, int cellSize) {
        cells[i][j] = new Cell(_i * cellSize, _j * cellSize , this);
    }

    public void update() {}
} // End CellController
