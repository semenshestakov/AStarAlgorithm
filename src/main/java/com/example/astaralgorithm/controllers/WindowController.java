package com.example.astaralgorithm.controllers;


public class WindowController {
    static private final int cellSize = 10;

    static private int width = 600;
    static private int height = 600;

    static {
        width = calcSize(width);
        height = calcSize(height);
    }

    private static int calcSize(int value){
        return (value / cellSize) * cellSize;
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }
    public static void setWidthHeight(int currentWidth, int currentHeight) {
        if (currentHeight != WindowController.getHeight()) {
            height = currentHeight;
        }
        if (currentWidth != WindowController.getWidth()) {
            width = currentWidth;
        }
    }

    public static int getCellSize() {
        return cellSize;
    }

    public static int getNumColumns() {
        return width / cellSize;
    }

    public static int getNumRow() {
        return height / cellSize;
    }
} // End WindowController
