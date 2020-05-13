import org.junit.Test;

import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class AutomataTest {

    @Test
    public void on() throws URISyntaxException {
        Automata test = new Automata("menu.json");
        test.on();
        assertEquals(States.WAIT, test.getState());
    }

    @Test
    public void off() throws URISyntaxException {
        Automata test = new Automata("menu.json");
        test.off();
        assertEquals(States.OFF, test.getState());
    }

    @Test
    public void coin() throws URISyntaxException {
        Automata test = new Automata("menu.json");
        test.on();
        assertEquals(0, test.coin(0));
        test.coin(100);
        assertEquals(200, test.coin(100));
    }

    @Test
    public void check() throws URISyntaxException {
        Automata test = new Automata("menu.json");
        test.on();
        assertEquals(-30, test.check(0));
        test.coin(100);
        assertEquals(10, test.check(6));
    }

    @Test
    public void choice() throws URISyntaxException {
        Automata test = new Automata("menu.json");
        test.on();
        test.coin(50);
        assertEquals("Add enough money to buy a drink", test.choice(7));
        assertEquals("Take your Lemon Tea", test.choice(2));
    }

    @Test
    public void finish() throws URISyntaxException {
        Automata test = new Automata("menu.json");
        test.on();
        test.coin(50);
        test.finish();
        assertEquals(States.WAIT, test.getState());
        assertEquals(0, test.getCash());
    }

    @Test
    public void cancel() throws URISyntaxException {
        Automata test = new Automata("menu.json");
        test.on();
        test.coin(90);
        assertEquals(90, test.cancel());
        test.choice(5);
        assertEquals(0, test.cancel());
    }

    @Test
    public void returnChange() throws URISyntaxException {
        Automata test = new Automata("menu.json");
        test.on();
        test.coin(90);
        test.choice(1);
        assertEquals(50, test.getChange());
    }

    @Test
    public void getMenu() throws URISyntaxException {
        Automata test = new Automata("menu.json");
        test.on();
        assertEquals("Espresso - 60 RUB", test.getMenu(3));
        assertEquals("Latte - 90 RUB", test.getMenu(6));
    }

    @Test
    public void getPrice() throws URISyntaxException {
        Automata test = new Automata("menu.json");
        test.on();
        assertEquals(50, test.getPrice(2));
        assertEquals(80, test.getPrice(5));
    }

    @Test
    public void getDrink() throws URISyntaxException {
        Automata test = new Automata("menu.json");
        test.on();
        assertEquals("Lemon Tea", test.getDrink(2));
        assertEquals("Capuccino", test.getDrink(5));
    }

    @Test
    public void getMenuLength() throws URISyntaxException {
        Automata test = new Automata("menu.json");
        test.on();
        assertEquals(8, test.getMenuLength());
    }

}