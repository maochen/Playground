package org.maochen.playground.gameoflife;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by mguan on 5/10/16.
 */
public class Cell {

    private boolean isAlive = false;
    private List<Cell> neighbors = new ArrayList<>();

    public int row;
    public int col;

    public void reproduce() {
        int count = (int) neighbors.stream().filter(Objects::nonNull).filter(x -> x.isAlive).count();

        if (isAlive) {
            if (count < 2 || count > 3) {
                isAlive = false;
            }

            //else (count == 2 && count == 3) {
            // DO NOTHING
            //}
        } else {
            if (count == 3) {
                isAlive = true;
            }
        }
    }

    public List<Cell> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(List<Cell> neighbors) {
        this.neighbors = neighbors;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

}
