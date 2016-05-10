package org.maochen.playground;

import com.google.common.collect.ImmutableList;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by mguan on 5/10/16.
 */
public class BDDTest {

    static class Ticket {
        public boolean orderedSnacks = false;
        public boolean orderedBeverage = false;

        public void setOrderedSnacks(boolean orderedSnacks) {
            this.orderedSnacks = orderedSnacks;
        }

        public List<String> beverageOptions() {
            return ImmutableList.of("coke", "sprite", "tea");
        }

        public List<String> getBeverageSizes(String beverage) {
            return ImmutableList.of("Small", "Medium", "Large");

        }
    }


    private BDDTest GIVEN = this, WHEN = this, THEN = this, AND = this;
    private Ticket ticket = null;

    //    private static WebDriver webDriver;
    //    @BeforeClass
    //    public static void setup() {
    //        webDriver = new ChromeDriver();
    //    }

    @Test
    public void test_beverage_selection_returns_option() {
        GIVEN.ticket_purchase_engine();
        WHEN.a_user_order_a_movie_ticket();
        AND.a_user_ordered_a_beverage();
        THEN.show_me_beverage_options("coke");
    }

    private void a_user_ordered_a_beverage() {
        ticket.setOrderedSnacks(true);
        Assert.assertTrue(ticket.orderedSnacks);
    }

    private void show_me_beverage_options(String str) {
        Assert.assertTrue(ticket.beverageOptions().contains(str));
    }

    private void a_user_order_a_movie_ticket() {
        Assert.assertNotNull(ticket);
    }

    private void ticket_purchase_engine() {
        ticket = new Ticket();
    }


}
