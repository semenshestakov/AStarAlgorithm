package com.example.astaralgorithm.math;

import com.example.astaralgorithm.math.objects.Node;
import com.example.astaralgorithm.objects.cell.Cell;
import com.example.astaralgorithm.objects.cell.CellTypes;

import java.util.*;

/**
 * A Star Algorithm
 */
public class AStar {

    private final double hvCost = 1.0, diagonalCost = Math.sqrt(2.0);

    private final Node startNode, finalNode;
    private final Node[][] searchArea;
    public List<Node> findedPath = null;

    private final Set<Node> closedSet = new HashSet<>();
    private final PriorityQueue<Node> openList = new PriorityQueue<>(Comparator.comparingDouble(node0 -> node0.F));

    public AStar(Cell[][] cellMap, Cell start, Cell finish) {
        int[] indexStart = start.indexsByWhindow(cellMap);
        int[] indexFinish = finish.indexsByWhindow(cellMap);

        this.startNode = new Node(indexStart[0], indexStart[1], start);
        this.finalNode = new Node(indexFinish[0], indexFinish[1], finish);

        this.searchArea = new Node[cellMap.length][cellMap[0].length];

        for (int i = 0; i < this.searchArea.length; i++) {
            for (int j = 0; j < this.searchArea[0].length; j++) {
                Node node = new Node(i, j, cellMap[i][j]);
                node.calculateHeuristic(this.finalNode);

                this.searchArea[i][j] = node;
            }
        }
    }

    public void findPath() {
        if (this.findedPath != null)
            return;

        openList.add(startNode);
        while (!openList.isEmpty()) {
            Node currentNode = openList.poll();
            closedSet.add(currentNode);
            currentNode.graphicalRepresentation.setType(CellTypes.Type.INVESTIGATED);

            if (currentNode.equals(finalNode)) {
                this.findedPath = getPath(currentNode);
                return;
            } else {
                addAdjacentUpperRow(currentNode);
                addAdjacentMiddleRow(currentNode);
                addAdjacentLowerRow(currentNode);
            }
        }
    }

    private List<Node> getPath(Node currentNode) {
        List<Node> path = new ArrayList<Node>();
        path.add(currentNode);
        Node parent;
        while ((parent = currentNode.parent) != null) {
            path.add(0, parent);
            currentNode = parent;
        }

        for (Node node : path) {
            node.finded();
        }

        return path;
    }

    private void addAdjacentLowerRow(Node currentNode) {
        int row = currentNode.row, col = currentNode.col;
        int lowerRow = row + 1;

        if (lowerRow < this.searchArea.length) {
            if (col - 1 >= 0 && isRoute(currentNode, col - 1, lowerRow)) {
                checkNode(currentNode, col - 1, lowerRow, diagonalCost);
            }
            if (col + 1 < this.searchArea[0].length && isRoute(currentNode, col + 1, lowerRow)) {
                checkNode(currentNode, col + 1, lowerRow, diagonalCost);
            }
            checkNode(currentNode, col, lowerRow, this.hvCost);
        }
    }

    private void addAdjacentMiddleRow(Node currentNode) {
        int row = currentNode.row, col = currentNode.col;
        int middleRow = row;

        if (col - 1 >= 0) {
            checkNode(currentNode, col - 1, middleRow, this.hvCost);
        }

        if (col + 1 < this.searchArea[0].length) {
            checkNode(currentNode, col + 1, middleRow, this.hvCost);
        }
    }

    private void addAdjacentUpperRow(Node currentNode) {
        int row = currentNode.row, col = currentNode.col;
        int upperRow = row - 1;

        if (upperRow >= 0) {
            if (col - 1 >= 0 && isRoute(currentNode, col - 1, upperRow)) {
                checkNode(currentNode, col - 1, upperRow, diagonalCost);
            }
            if (col + 1 < this.searchArea[0].length && isRoute(currentNode, col + 1, upperRow)) {
                checkNode(currentNode, col + 1, upperRow, diagonalCost);
            }

            checkNode(currentNode, col, upperRow, this.hvCost);
        }
    }

    private void checkNode(Node currentNode, int col, int row, double cost) {
        Node adjacentNode = this.searchArea[row][col];
        if (adjacentNode.isBlock() || this.closedSet.contains(adjacentNode))
            return;

        if (!this.openList.contains(adjacentNode)) {
            adjacentNode.setNodeData(currentNode, cost);
            this.openList.add(adjacentNode);
        } else if (adjacentNode.checkBetterPath(currentNode, cost)) {
            this.openList.remove(adjacentNode);
            this.openList.add(adjacentNode);
        }
    }

    private boolean isRoute(Node currentNode, int col, int row) {
        return !searchArea[row][currentNode.col].isBlock() && !searchArea[currentNode.row][col].isBlock();
    }
} // End AStar