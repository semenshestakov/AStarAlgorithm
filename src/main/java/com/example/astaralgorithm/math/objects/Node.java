package com.example.astaralgorithm.math.objects;

import com.example.astaralgorithm.objects.cell.Cell;
import com.example.astaralgorithm.objects.cell.CellTypes.Type;

/**
 * Node Class
 */
public class Node {
    
    public double G, F, H;
    public int row, col;
    public Node parent;
    public Cell graphicalRepresentation;

    public Node(int row, int col, Cell cell) {
        super();
        this.row = row;
        this.col = col;
        this.graphicalRepresentation = cell;
    }

    public void calculateHeuristic(Node finalNode) {
        this.H = Math.abs(finalNode.row - row) + Math.abs(finalNode.col - col);
    }

    public void setNodeData(Node currentNode, double cost) {
        parent = currentNode;
        this.G = currentNode.G + cost;
        calculateFinalCost();
    }

    public boolean checkBetterPath(Node currentNode, double cost) {
        double gCost = currentNode.G + cost;
        if (gCost < G) {
            setNodeData(currentNode, cost);
            return true;
        }
        return false;
    }

    private void calculateFinalCost() {
        this.F = this.G + this.H;
    }

    @Override
    public boolean equals(Object arg0) {
        Node other = (Node) arg0;
        return this.row == other.row && this.col == other.col;
    }

    public boolean isBlock() {
        return this.graphicalRepresentation.isBlock();
    }

    public void finded() {
        this.graphicalRepresentation.setType(Type.FINDED);
    }
}