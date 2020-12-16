package model.astar;

import java.awt.*;

public class Node {

    private int g;
    private int f;
    private int h;
    private int row;
    private int col;
    private boolean isBlock;
    private Node parent;

    /**
     * Constructeur
     * @param row ligne
     * @param col colonne
     */
    public Node(int row, int col) {
        super();
        this.row = row;
        this.col = col;
    }

    /**
     * calcule l'heuristique
     * @param finalNode
     */
    public void calculateHeuristic(Node finalNode) {
       // this.h = Math.abs(finalNode.getRow() - getRow()) + Math.abs(finalNode.getCol() - getCol());
        this.h = Math.abs(finalNode.getRow() - getRow() +finalNode.getCol() - getCol());
    }

    /**
     *
     * @param currentNode
     * @param cost
     */
    public void setNodeData(Node currentNode, int cost) {
        int gCost = currentNode.getG() + cost;
        setParent(currentNode);
        setG(gCost);
        calculateFinalCost();
    }

    /**
     * verifie si c'est le meilleur chemin
     * @param currentNode
     * @param cost
     * @return
     */
    public boolean checkBetterPath(Node currentNode, int cost) {
        int gCost = currentNode.getG() + cost;
        if (gCost < getG()) {
            setNodeData(currentNode, cost);
            return true;
        }
        return false;
    }

    /**
     * calcule le cost final
     */
    private void calculateFinalCost() {
        int finalCost = getG() + getH();
        setF(finalCost);
    }

    @Override
    public boolean equals(Object arg0) {
        Node other = (Node) arg0;
        return this.getRow() == other.getRow() && this.getCol() == other.getCol();
    }

    @Override
    public String toString() {
        return "Node [row=" + row + ", col=" + col + "]";
    }

    public Point getNextPosition(){
        return new Point(col, row) ;
    }
    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public boolean isBlock() {
        return isBlock;
    }

    public void setBlock(boolean isBlock) {
        this.isBlock = isBlock;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
