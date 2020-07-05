import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AutomataDemoTest {
    AutomataDemo a = new AutomataDemo();

    @Test
    void on() {
        a.on();
        assertEquals(AutomataDemo.STATES.WAIT,a.getState());
    }

    @Test
    void off() {
        a.off();
        assertEquals(AutomataDemo.STATES.OFF,a.getState());
    }

    @Test
    void coin() {
        a.on();
        assertEquals(12,a.coin(12));
        assertEquals(212,a.coin(200));
        a.cancel();
        assertEquals(0,a.coin(0));
        assertEquals(AutomataDemo.STATES.ACCEPT,a.getState());
    }
    @Test
    void choice() {
        a.on();
        assertEquals(20, a.choice(2));
        assertEquals(15, a.choice(1));
        assertEquals(AutomataDemo.STATES.WAIT,a.getState());
    }
}