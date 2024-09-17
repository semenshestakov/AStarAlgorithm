package com.example.astaralgorithm.graphics.color;


import com.example.astaralgorithm.objects.cell.CellTypes.Type;
import javafx.scene.paint.Color;

import java.util.HashMap;


public class CellColors {
    static private final HashMap<Type, Color> typesColorHashMap = new HashMap<>();

    static {
        typesColorHashMap.put(Type.DEFAULT, Color.rgb(61, 60, 59));
        typesColorHashMap.put(Type.START, Color.BLUE);
        typesColorHashMap.put(Type.FINISH, Color.YELLOW);
        typesColorHashMap.put(Type.WALL, Color.RED);
        typesColorHashMap.put(Type.FINDED, Color.GREEN);
        typesColorHashMap.put(Type.INVESTIGATED, Color.WHITESMOKE);
    }

    static public Color get(Type key){
        return typesColorHashMap.get(key);
    }
}
