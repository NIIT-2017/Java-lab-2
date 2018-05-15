import static org.junit.Assert.*;

public class AutomataTest {

    @org.junit.Test
    public void on() {
        Automata automata = new Automata();
        assertEquals("OFF",automata.PrintState());
        automata.on();
        assertEquals("WAIT",automata.PrintState());
    }

    @org.junit.Test
    public void off() {
        Automata automata = new Automata();
        automata.off();
        assertEquals("OFF",automata.PrintState());
    }

    @org.junit.Test
    public void coin() {
        Automata automata = new Automata();
        automata.on();
        assertEquals(5, automata.coin(5));
        assertEquals(15, automata.coin(10));
        assertEquals(20, automata.coin(5));
        assertEquals("ACCEPT",automata.PrintState());
    }

    @org.junit.Test
    public void printState() {
        Automata automata = new Automata();
        automata.on();
        assertEquals("WAIT",automata.PrintState());
        automata.coin(5);
        assertEquals("ACCEPT",automata.PrintState());
        automata.choice(1);
        assertEquals("ACCEPT",automata.PrintState());
        automata.coin(100);
        assertEquals("ACCEPT",automata.PrintState());
        automata.choice(1);
        assertEquals("WAIT",automata.PrintState());
        automata.cancel();
        assertEquals("WAIT",automata.PrintState());
        automata.on();
        assertEquals("WAIT",automata.PrintState());
        automata.off();
        assertEquals("OFF",automata.PrintState());
    }

    @org.junit.Test
    public void choice() {
        Automata automata = new Automata();
        automata.on();
        automata.coin(5);
        automata.choice(1);
        assertEquals("ACCEPT",automata.PrintState());
        automata.coin(100);
        automata.choice(1);
        assertEquals("WAIT",automata.PrintState());
        automata.coin(100);
        automata.choice(4);
        assertEquals("WAIT",automata.PrintState());
    }

    @org.junit.Test
    public void cancel() {
        Automata automata = new Automata();
        automata.on();
        automata.coin(5);
        automata.cancel();
        assertEquals("WAIT",automata.PrintState());
        automata.coin(5);
        automata.choice(1);
        automata.cancel();
        assertEquals("WAIT",automata.PrintState());
    }

}