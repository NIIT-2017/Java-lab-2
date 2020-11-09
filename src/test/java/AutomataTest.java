import static org.junit.Assert.*;

import static org.junit.Assert.*;

public class AutomataTest {

    @org.junit.Test
    public void on() {
        Automata a = new Automata();
        a.on();
        assertEquals(Automata.STATES.WAIT, a.getState());
        assertEquals(0, a.getCash());
    }

    @org.junit.Test
    public void off() {
        Automata a = new Automata();
        a.on();
        a.off();
        assertEquals(Automata.STATES.OFF, a.getState());
    }

    @org.junit.Test
    public void coin() {
        Automata a = new Automata();
        a.on();
        a.coin(20);
        a.coin(20);
        assertEquals(40, a.getCash());
    }

    @org.junit.Test
    public void choice() {
        Automata a = new Automata();
        a.on();
        a.coin(50);
        assertEquals(50, a.getCash());
        a.choice("3");
        assertEquals(0, a.getCash());
        a.coin(40);
        a.choice("4");
        assertEquals(10, a.getCash());
    }

    @org.junit.Test
    public void cancel() {
        Automata testAutomata = new Automata();
        testAutomata.on();
        testAutomata.coin(50);
        assertEquals(50, testAutomata.getCash());
        testAutomata.cancel();
        assertEquals(0, testAutomata.getCash());
    }
}
