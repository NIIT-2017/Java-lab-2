import org.junit.jupiter.api.Disabled;

import static java.util.logging.Level.OFF;
import static org.junit.jupiter.api.Assertions.*;

class AutomataTest {
    Automata testA = new Automata();

    @org.junit.jupiter.api.Test
    public void on() {
        assertEquals(Automata.STATES.WAIT, testA.on());
    }

    @org.junit.jupiter.api.Test
    public void off() {
        assertEquals(Automata.STATES.OFF, testA.off());
    }

    @org.junit.jupiter.api.Test
    public void coin() {
        testA.on();
        assertEquals(528, testA.coin(528));
    }

    @org.junit.jupiter.api.Test
    public void coin1() {
        testA.on();
        assertEquals(77, testA.coin(77));
    }

    @org.junit.jupiter.api.Test
    public void coin2() {
        testA.on();
        assertEquals(333, testA.coin(333));
    }

    @org.junit.jupiter.api.Test
    public void choice() {
        testA.on();
        testA.readMenu();
        assertEquals(90, testA.choice("Hot chocolate"));
    }

    @org.junit.jupiter.api.Test
    public void choice1() {
        testA.on();
        testA.readMenu();
        assertEquals(110, testA.choice("Americano big"));
    }

    @org.junit.jupiter.api.Test
    public void choice2() {
        testA.on();
        testA.readMenu();
        assertEquals(50, testA.choice("Black tea standard"));
    }

    @org.junit.jupiter.api.Test
    public void check() {
        testA.on();
        testA.readMenu();
        testA.coin(148);
        testA.choice("Americano");
        assertEquals(78, testA.check());
    }

    @org.junit.jupiter.api.Test
    public void check1() {
        testA.on();
        testA.readMenu();
        testA.coin(500);
        testA.choice("Hot chocolate big");
        assertEquals(340, testA.check());
    }

    @org.junit.jupiter.api.Test
    public void check2() {
        testA.on();
        testA.readMenu();
        testA.coin(58);
        testA.choice("Latte large");
        assertEquals(58, testA.check());
    }

    @org.junit.jupiter.api.Test
    public void check3() {
        testA.on();
        testA.readMenu();
        testA.coin(35);
        testA.choice("Black tea standard");
        assertEquals(35, testA.check());
    }

    @org.junit.jupiter.api.Test
    public void getState() {
        testA.on();
        testA.readMenu();
        testA.coin(35);
        assertEquals(Automata.STATES.ACCEPT, testA.getState());
        System.out.println(testA.getState());
    }

    @org.junit.jupiter.api.Test
    public void getState1() {
        testA.on();
        testA.readMenu();
        testA.coin(35);
        testA.choice("Black tea standard");
        assertEquals(Automata.STATES.CHECK, testA.getState());
        System.out.println(testA.getState());
    }

    @org.junit.jupiter.api.Test
    public void getState2() {
        testA.on();
        testA.readMenu();
        testA.coin(200);
        testA.choice("Latte large");
        testA.check();
        testA.cook();
        assertEquals(Automata.STATES.WAIT, testA.getState());
        System.out.println(testA.getState());
    }

    @org.junit.jupiter.api.Test
    public void cancel() {
        testA.on();
        testA.readMenu();
        testA.coin(5);
        testA.choice("Green tea standard");
        testA.check();
        assertEquals(Automata.STATES.WAIT, testA.cancel());
    }

    @org.junit.jupiter.api.Test
    public void cancel1() {
        testA.on();
        testA.readMenu();
        testA.coin(120);
        testA.choice("Cappuccino large");
        testA.check();
        assertEquals(Automata.STATES.WAIT, testA.cancel());
    }

    @org.junit.jupiter.api.Test
    public void cancel2() {
        testA.on();
        testA.readMenu();
        testA.coin(100);
        testA.choice("Americano big");
        testA.check();
        assertEquals(Automata.STATES.WAIT, testA.cancel());
    }
}