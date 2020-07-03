import static org.junit.Assert.*;
import org.junit.Test;

import java.io.File;


public class AutomataTest {
    ClassLoader classLoader = getClass().getClassLoader();
    File file = new File(classLoader.getResource("menu.json").getFile());
    Automata test = new Automata("menu.json");
    @org.junit.Test
    public void start() {
        Automata.STATES compare = test.getState();
        boolean ifOff = compare.equals(Automata.STATES.OFF);
        assertTrue(ifOff);
    }
    @org.junit.Test
    public void on() {
        test.on();
        Automata.STATES compare = test.getState();
        boolean ifWait = compare.equals(Automata.STATES.WAIT);
        assertTrue(ifWait);
    }

    @org.junit.Test
    public void coinState() {
        test.on();
        test.coin(10);
        Automata.STATES compare = test.getState();
        boolean ifAccept = compare.equals(Automata.STATES.ACCEPT);
        assertTrue(ifAccept);
    }

    @org.junit.Test
    public void coinCash() {
        test.on();
        test.coin(10);
        assertEquals(10, test.getCash());
    }

    @org.junit.Test
    public void choice() {
        test.on();
        test.coin(30);
        test.choice(2);
        Automata.STATES compare = test.getState();
        boolean ifWait = compare.equals(Automata.STATES.WAIT);
        assertTrue(ifWait);
    }

    @org.junit.Test
    public void checkNotEnoughCash() {
        test.on();
        test.coin(10);
        test.choice(2);
        assertEquals(0, test.getCash());
        assertEquals(10, test.getChange());
    }

    @org.junit.Test
    public void checkEnoughCash() {
        test.on();
        test.coin(35);
        test.choice(2);
        assertEquals(0, test.getCash());
        assertEquals(10, test.getChange());
    }

    @org.junit.Test
    public void cancelFromAccept() {
        test.on();
        test.coin(10);
        test.cancel();
        Automata.STATES compare = test.getState();
        boolean ifWait = compare.equals(Automata.STATES.WAIT);
        assertTrue(ifWait);
        assertEquals(0, test.getCash());
        assertEquals(10, test.getChange());
    }
    @org.junit.Test
    public void off() {
        test.on();
        test.coin(30);
        test.choice(2);
        test.off();
        Automata.STATES compare = test.getState();
        boolean ifOff = compare.equals(Automata.STATES.OFF);
        assertTrue(ifOff);
    }
}