package com.example.astaralgorithm.objects.cell;

import java.util.HashSet;

public class CellTypes {
    public enum Type {
        DEFAULT,
        START,
        FINISH,
        WALL,
        FINDED,
        INVESTIGATED,
    }

    static public HashSet<Type> tempType = new HashSet<>();

    static {
        tempType.add(Type.FINDED);
        tempType.add(Type.INVESTIGATED);
    }
}
