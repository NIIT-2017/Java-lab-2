import org.junit.Test;

import static org.junit.Assert.*;

public class AutomataTest {

    @Test
    public void on(){
        Automata tester = new Automata();
        States exp = States.WAIT;
        tester.on();
        States act = tester.getState();
        assertEquals(exp, act);
    }

    @Test
    public void getMenu(){
        Automata tester = new Automata();
        tester.on();
        assertEquals("Cappuccino", tester.getMenu()[0].get(2));
        assertEquals(30, tester.getMenu()[1].get(1));
        assertEquals(15, tester.getMenu()[1].get(4));
    }

    @Test
    public void getState(){
        Automata tester = new Automata();
        States exp = States.OFF;
        States act = tester.getState();
        tester.on();
        States actual = tester.getState();
        States expected = States.WAIT;
        assertEquals(exp, act);
        assertEquals(expected, actual);
    }

    @Test
    public void check() {
        Automata tester = new Automata();
        tester.on();
        tester.coin(30);
        tester.getMenu();
        tester.check(1);
        States exp = States.CHECK;
        States act = tester.getState();
        assertEquals(exp, act);
        assertEquals(true, tester.check(1));
        assertEquals(false, tester.check(4));
    }
}