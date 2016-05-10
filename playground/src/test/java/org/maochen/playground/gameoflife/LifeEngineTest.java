package org.maochen.playground.gameoflife;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by mguan on 5/10/16.
 */
public class LifeEngineTest {

    @Test(expected = IllegalArgumentException.class)
    public void testRowLength() {
        new LifeEngine(-2, -9);
    }

    @Test
    public void testInitBoardPos() {
        LifeEngine lifeEngine = new LifeEngine(5, 5);
        lifeEngine.init();

        int posCount = 0;
        for (Cell c : lifeEngine.board.values()) {
            if (c.isAlive()) {
                posCount++;
            }
        }

        Assert.assertTrue(posCount > 0);
    }


    @Test
    public void testRunOneGenerationAllDead() {
        LifeEngine lifeEngine = new LifeEngine(1, 3);
        lifeEngine.init();
        lifeEngine.board.get(0, 0).setAlive(true);
        lifeEngine.board.get(0, 1).setAlive(false);
        lifeEngine.board.get(0, 2).setAlive(true);

        lifeEngine.runOneGeneration();

        int deadCount = (int) lifeEngine.board.values().stream().filter(cell -> !cell.isAlive()).count();
        Assert.assertEquals(3, deadCount);
    }

    @Test
    public void testRunNGeneration() {


        LifeEngine lifeEngine = new LifeEngine(3, 3);
        lifeEngine.init();
        lifeEngine.board.get(0, 0).setAlive(true);
        lifeEngine.board.get(0, 1).setAlive(false);
        lifeEngine.board.get(0, 2).setAlive(true);

        lifeEngine.board.get(1, 0).setAlive(false);
        lifeEngine.board.get(1, 1).setAlive(false);
        lifeEngine.board.get(1, 2).setAlive(true);
        lifeEngine.board.get(2, 0).setAlive(true);
        lifeEngine.board.get(2, 1).setAlive(true);
        lifeEngine.board.get(2, 2).setAlive(false);
        lifeEngine.runNGenerations(1);

        Assert.assertFalse(lifeEngine.board.get(0, 0).isAlive());
    }

    // TODO: change to assert format
    @Test
    public void testPrint() {
        LifeEngine engine = new LifeEngine(3, 4);
        engine.init();

        System.out.println("[LifeEngineTest.testPrint]: ");
        System.out.println(engine.toString());
    }
}
