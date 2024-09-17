package com.example.astaralgorithm.controllers.game;


import com.example.astaralgorithm.controllers.cell.CellController;
import com.example.astaralgorithm.objects.Arena;
import com.example.astaralgorithm.utils.Updater;


public class GameController extends HandlerGameController implements Updater {
    public GameController(CellController cellController, Arena arena) {
        super(cellController, arena);
    }

    public void update() {}
}
