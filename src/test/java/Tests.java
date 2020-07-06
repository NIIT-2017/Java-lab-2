import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class Tests {

    Automata automata = new Automata();

    @Test
    public void testGetState() {
        assertEquals(Automata.STATES.OFF, automata.getStates());
    }

    @Test
    public void testGetOne() {
        automata.on();
        assertEquals(Automata.STATES.WAIT, automata.getStates());
    }

    @Test
    public void testGetCash() {
        automata.on();
        automata.coin(5);
        automata.coin(25);
        assertEquals(30,automata.getCash());
    }

    @Test
    public void testGetMenu() {
        automata.on();
        ArrayList<String> menu = new ArrayList<String>();
        menu.add("Latte");
        menu.add("Espresso");
        menu.add("Cappuccino");
        assertEquals(menu, automata.getMenu2());
    }

    @Test
    public void testGetPrices() {
        automata.on();
        ArrayList<Integer> prices = new ArrayList<Integer>();
        prices.add(30);
        prices.add(45);
        prices.add(30);
        assertEquals(prices, automata.getPrices());
    }

    @Test
    public void testCoin() {
        automata.on();
        automata.coin(20);
        assertEquals(20, automata.getCash());
    }

    @Test
    public void testChoiceTrue() {
        automata.on();
        automata.coin(45);
        automata.choice(2);
        assertEquals(Automata.STATES.WAIT, automata.getStates());
    }

    @Test
    public void testChoiceFalse() {
        automata.on();
        automata.coin(45);
        automata.choice(1);
        assertEquals(Automata.STATES.CHECK, automata.getStates());
    }

    @Test
    public void checkOff() {
        automata.on();
        automata.off();
        assertEquals(Automata.STATES.OFF,automata.getStates());
    }

}
