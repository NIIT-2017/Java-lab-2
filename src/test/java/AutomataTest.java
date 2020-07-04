import org.junit.Test;

import static org.junit.Assert.*;

public class AutomataTest {

    @Test
    public void on() {
        Automata test = new Automata();
        test.on();
        assertEquals(test.getState(), Automata.States.WAIT);
    }

    @Test
    public void off() {
        Automata test = new Automata();
        test.off();
        assertEquals(test.getState(), Automata.States.OFF);
    }

    @Test
    public void coin() {
        Automata test = new Automata();
        test.coin(100);
        assertEquals(test.getState(), Automata.States.OFF);
        Float cash = test.getCash();
        assertEquals(cash.equals(0.0f), true);
    }
   @Test
    public void choice() {
        Automata test = new Automata();

        test.choice(1);
        assertEquals(test.getState(),  Automata.States.OFF);
        Float cash = test.getCash();
        assertEquals(cash.equals(0.0f), true);
    }
}