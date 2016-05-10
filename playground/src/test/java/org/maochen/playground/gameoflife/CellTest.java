package org.maochen.playground.gameoflife;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by mguan on 5/10/16.
 */
public class CellTest {

    @Test
    public void testNewCellNotNull() {
        Cell c = new Cell(3, 4);
        Assert.assertNotNull(c);
    }

    @Test
    public void testCellReproduceFewerThan2LiveThenDies() {
        Cell c = new Cell(3, 4);
        c.setAlive(true);

        setNeighborsAndReproduce(c, i -> i == 0);
        Assert.assertFalse(c.isAlive());
    }

    @Test
    public void testCellReproduce2Or3LiveThenLives() {
        Cell c = new Cell(3, 4);
        c.setAlive(true);

        setNeighborsAndReproduce(c, i -> i == 0 || i == 1);
        Assert.assertTrue(c.isAlive());
    }

    @Test
    public void testCellReproduceMoreThan3LivesThenDies() {
        Cell c = new Cell(3, 4);
        c.setAlive(true);

        setNeighborsAndReproduce(c, i -> true);
        Assert.assertFalse(c.isAlive());
    }

    private void setNeighborsAndReproduce(Cell c, Predicate<Integer> predicate) {
        List<Cell> neighs = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            Cell nighCell = new Cell(2, 4);
            nighCell.setAlive(predicate.test(i));
            neighs.add(nighCell);
        }

        c.setNeighbors(neighs);
        c.reproduce();
    }

}
