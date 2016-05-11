package org.maochen.playground.gameoflife;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;

import java.util.List;
import java.util.Random;

/**
 * Created by mguan on 5/10/16.
 */
public class LifeEngine {

    Table<Integer, Integer, Cell> board = HashBasedTable.create();

    private Random generator;

    private int cols = 0;
    private int rows = 0;

    private static final long SEED = 17L;

    private boolean genState() {
        double num = generator.nextDouble();
        return num > 0.5;
    }

    private List<Cell> findNeighbors(Cell c) {
        if (c == null || c.row < 0 || c.col < 0) {
            throw new IllegalArgumentException();
        }

        int row = c.row;
        int col = c.col;

        Cell topLeft = board.values().stream()
                .filter(cell -> (cell.row == row - 1) && (cell.col == col - 1))
                .findFirst().orElse(null);
        Cell topCenter = board.values().stream()
                .filter(cell -> (cell.row == row - 1) && cell.col == col)
                .findFirst().orElse(null);
        Cell topRight = board.values().stream()
                .filter(cell -> (cell.row == row - 1) && cell.col == col + 1)
                .findFirst().orElse(null);
        Cell left = board.values().stream()
                .filter(cell -> (cell.row == row) && cell.col == col - 1)
                .findFirst().orElse(null);
        Cell right = board.values().stream()
                .filter(cell -> (cell.row == row) && cell.col == col + 1)
                .findFirst().orElse(null);
        Cell btmLeft = board.values().stream()
                .filter(cell -> (cell.row == row + 1) && cell.col == col - 1)
                .findFirst().orElse(null);

        Cell btm = board.values().stream()
                .filter(cell -> (cell.row == row + 1) && cell.col == col)
                .findFirst().orElse(null);

        Cell btmRight = board.values().stream()
                .filter(cell -> (cell.row == row + 1) && cell.col == col + 1)
                .findFirst().orElse(null);

        List<Cell> neighbors = Lists.newArrayList(topLeft, topCenter, topRight, left, right, btmLeft, btm, btmRight);

        return neighbors;
    }

    public void init() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Cell cell = new Cell(row, col);
                cell.setAlive(genState());
                board.put(row, col, cell);
            }
        }

        board.values().stream().forEach(cell -> cell.setNeighbors(findNeighbors(cell)));
    }

    public void runOneGeneration() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Cell cell = board.get(i, j);
                cell.reproduce();
            }
        }
    }

    public void runNGenerations(int ngenerations) {
        for (int i = 0; i < ngenerations; i++) {
            runOneGeneration();
        }
    }

    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                strBuilder.append(board.get(i, j).isAlive()).append("\t");
            }
            strBuilder.append(System.lineSeparator());
        }

        return strBuilder.toString();
    }

    public LifeEngine(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException();
        }
        this.rows = rows;
        this.cols = cols;
        this.generator = new Random(SEED);
    }

}
