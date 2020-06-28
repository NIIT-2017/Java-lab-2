import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AutomataDemoTest {

    AutomataDemo testA = new AutomataDemo();

    @Test
    void on() {
        assertEquals(AutomataDemo.STATES.WAIT, testA.on());
    }

    @Test
    void off() {
        assertEquals(AutomataDemo.STATES.OFF, testA.off());
    }

    @Test
    void coin() {
        testA.on();
        assertEquals(528, testA.coin(528));
    }

    @Test
    void coin1() {
        testA.on();
        assertEquals(77, testA.coin(77));

    @Test
    void choice() {
            testA.on();
            testA.readMenu();
            assertEquals(90, testA.choice("Hot chocolate"));
    }

    @Test
    void check() {
            testA.on();
            testA.readMenu();
            testA.coin(148);
            testA.choice("Americano");
            assertEquals(78, testA.check());
        }
    }

    @Test
    void getState() {
        testA.on();
        testA.readMenu();
        testA.coin(35);
        assertEquals(AutomataDemo.STATES.ACCEPT, testA.getState());
        System.out.println(testA.getState());
    }

    @Test
    void setState() {
        testA.on();
        testA.readMenu();
        testA.coin(200);
        testA.choice("Latte large");
        testA.check();
        testA.cook();
        assertEquals(AutomataDemo.STATES.WAIT, testA.getState());
        System.out.println(testA.getState());
    }

    @Test
    void cancel() {
        testA.on();
        testA.readMenu();
        testA.coin(5);
        testA.choice("Green tea standard");
        testA.check();
        assertEquals(AutomataDemo.STATES.WAIT, testA.cancel());
    }

    @Test
    void cook() {
    }

    @Test
    void finish() {
    }
}