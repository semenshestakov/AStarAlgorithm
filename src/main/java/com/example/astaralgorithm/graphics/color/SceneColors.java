package com.example.astaralgorithm.graphics.color;

import com.example.astaralgorithm.graphics.type.SceneColorTypes;
import javafx.scene.paint.Color;

import java.util.HashMap;


public class SceneColors {
    static private final HashMap<SceneColorTypes, Color> typesColorHashMap = new HashMap<>();

    static {
        typesColorHashMap.put(SceneColorTypes.BACKGROUDN, Color.rgb(47, 47, 46));
    }

    static public Color get(SceneColorTypes key){
        return typesColorHashMap.get(key);
    }

}
