import static org.junit.Assert.*;

public class AutomataTest {

    @org.junit.Test
    public void on() {
        Automata testAutomata = new Automata();
        testAutomata.on();
        assertEquals(Automata.STATES.WAIT, testAutomata.getState());
        assertEquals(0, testAutomata.getCash());
    }

    @org.junit.Test
    public void off() {
        Automata testAutomata = new Automata();
        testAutomata.on();
        testAutomata.coin(10);
        testAutomata.off();
        assertEquals(Automata.STATES.OFF, testAutomata.getState());
        assertEquals(0, testAutomata.getCash());
    }

    @org.junit.Test
    public void coin() {
        Automata testAutomata = new Automata();
        testAutomata.on();
        testAutomata.coin(10);
        testAutomata.coin(20);
        assertEquals(30, testAutomata.getCash());
    }

    @org.junit.Test
    public void choice() {
        Automata testAutomata = new Automata();
        testAutomata.on();
        testAutomata.coin(10);
        assertEquals(10, testAutomata.getCash());
        testAutomata.choice("1");
        assertEquals(0, testAutomata.getCash());
        testAutomata.coin(40);
        testAutomata.choice("1");
        assertEquals(10, testAutomata.getCash());
    }

    @org.junit.Test
    public void cancel() {
        Automata testAutomata = new Automata();
        testAutomata.on();
        testAutomata.coin(10);
        assertEquals(10, testAutomata.getCash());
        testAutomata.cancel();
        assertEquals(0, testAutomata.getCash());
    }

}