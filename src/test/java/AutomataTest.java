import static org.junit.Assert.*;

public class AutomataTest {

    @org.junit.Test
    public void on() {
        Automata auto = new Automata();
        STATES actual = auto.on();
        STATES expected = STATES.WAIT;
        assertEquals(actual, expected);
    }

    @org.junit.Test
    public void off() {
        Automata auto = new Automata();
        STATES actual = auto.off();
        STATES expected = STATES.OFF;
        assertEquals(actual, expected);
    }

    @org.junit.Test
    public void coin() {
        Automata auto = new Automata();
        auto.on();
        auto.coin(50);
        int actual = auto.coin(50);
        int expected = 100;
        assertEquals(actual, expected);

    }

    @org.junit.Test
    public void getState() {
        Automata auto = new Automata();
        STATES actual = auto.off();
        STATES expected = STATES.OFF;
        assertEquals(actual, expected);
    }

    @org.junit.Test
    public void choice() {
        Automata auto = new Automata();
        auto.on();
        auto.getMenu();
        String actual = auto.choice(1);
        String expected = "1.Cappuccino";
        assertEquals(actual, expected);
    }

    @org.junit.Test
    public void check() {
        Automata auto = new Automata();
        auto.on();
        auto.coin(100);
        auto.getMenu();
        auto.choice(1);
        STATES actual = auto.check();
        STATES expected = STATES.ACCEPT;
        assertEquals(actual, expected);
    }

    @org.junit.Test
    public void cancel() {
        Automata auto = new Automata();
        auto.on();
        auto.coin(180);
        int actual = auto.cancel();
        int expected = 180;
        assertEquals(actual, expected);
    }

    @org.junit.Test
    public void cook() {
        Automata auto = new Automata();
        auto.on();
        auto.coin(100);
        auto.getMenu();
        auto.choice(1);
        auto.check();
        STATES actual = auto.cook();
        STATES expected = STATES.COOK;
        assertEquals(actual, expected);
    }
}