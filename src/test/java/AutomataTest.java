import org.junit.Test;

import static org.junit.Assert.*;

public class AutomataTest {

    Automata playTest = new Automata();

    @Test
    public void on() {
        playTest.on();
        assertEquals("WAIT", playTest.setState());
    }

    @Test
    public void off() {
        playTest.off();
        assertEquals("OFF", playTest.setState());
    }

    @Test
    public void coin() {
        playTest.on();
        assertEquals("Баланс 0руб", playTest.coin(0));
        playTest.coin(40);
        assertEquals(40, playTest.getCash());
        playTest.coin(0);
        assertEquals("ACCEPT", playTest.setState());
    }

    @Test
    public void choice() {
        playTest.on();
        playTest.coin(40);
        playTest.choice(2);
        assertEquals("COOK", playTest.setState());
    }
}