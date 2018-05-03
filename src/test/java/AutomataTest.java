import org.junit.Test;

import static org.junit.Assert.*;

public class AutomataTest {

    @Test
    public void getCash() {
        Automata a1 = new Automata();
        int cash = 25;
        a1.setCash(cash);
        assertEquals(cash,a1.getCash());
    }

    @Test
    public void getState() {
        Automata a1 = new Automata();
        STATES st = STATES.WAIT;
        a1.setState(st);
        assertEquals(st,a1.getState());
    }

    @Test
    public void setCash() {
        Automata a1 = new Automata();
        int cash = 70;
        a1.setCash(cash);
        assertEquals(cash,a1.getCash());
    }

    @Test
    public void setState() {
        Automata a1 = new Automata();
        STATES st = STATES.ACCEPT;
        a1.setState(st);
        assertEquals(st,a1.getState());
    }

    @Test
    public void getMenuStr() {
        Automata a1 = new Automata();
        String[] menu = a1.getMenuStr();
        assertEquals("Automata menu: ",menu[0]);
        assertEquals("1. Tea, 15 rub.",menu[1]);
        assertEquals("2. Americano, 25 rub.",menu[2]);
        assertEquals("3. Espresso, 30 rub.",menu[3]);
    }

    @Test
    public void getStateStr() {
        Automata a1 = new Automata();
        STATES st = STATES.ACCEPT;
        a1.setState(st);
        assertEquals("Automata state: " + st,a1.getStateStr());
    }

    @Test
    public void getCashStr() {
        Automata a1 = new Automata();
        int cash = 25;
        a1.setCash(cash);
        assertEquals("Available cash: " + cash + " rub.",a1.getCashStr());
    }

    @Test
    public void on() {
        Automata a1 = new Automata();
        a1.on();
        assertEquals(STATES.WAIT,a1.getState());
        assertEquals(0,a1.getCash());
        a1.on();
        assertEquals(STATES.WAIT,a1.getState());
        assertEquals(0,a1.getCash());
    }

    @Test
    public void off() {
        Automata a1 = new Automata();
        a1.on();
        a1.off();
        assertEquals(STATES.OFF,a1.getState());
        assertEquals(0,a1.getCash());
        a1.off();
        assertEquals(STATES.OFF,a1.getState());
        assertEquals(0,a1.getCash());
    }

    @Test
    public void coin() {
        Automata a1 = new Automata();
        a1.on();
        int s1 = 10, s2 = 25;
        a1.coin(s1);
        assertEquals(STATES.ACCEPT,a1.getState());
        assertEquals(s1,a1.getCash());
        a1.coin(s2);
        assertEquals(STATES.ACCEPT,a1.getState());
        assertEquals(s1+s2,a1.getCash());
    }

    @Test
    public void cancel() {
        Automata a1 = new Automata();
        a1.on();
        int s1 = 12;
        a1.coin(s1);
        assertEquals(STATES.ACCEPT,a1.getState());
        assertEquals(s1,a1.getCash());
        assertEquals(s1,a1.cancel());
        assertEquals(STATES.WAIT,a1.getState());
        assertEquals(0,a1.getCash());
    }

    @Test
    public void choice() {
        Automata a1 = new Automata();
        a1.on();
        int s1 = 20;

        a1.coin(s1);
        assertEquals(STATES.ACCEPT,a1.getState());
        assertEquals(s1,a1.getCash());
        int[] res = a1.choice(1);
        assertEquals(STATES.WAIT,a1.getState());
        assertEquals(s1-15,res[0]);
        assertEquals(1,res[1]);

        a1.coin(s1);
        assertEquals(STATES.ACCEPT,a1.getState());
        assertEquals(s1,a1.getCash());
        int[] res1 = a1.choice(-1);
        assertEquals(STATES.WAIT,a1.getState());
        assertEquals(s1,res1[0]);
        assertEquals(3,res1[1]);

        s1 = 10;
        a1.coin(s1);
        assertEquals(STATES.ACCEPT,a1.getState());
        assertEquals(s1,a1.getCash());
        int[] res2 = a1.choice(2);
        assertEquals(STATES.WAIT,a1.getState());
        assertEquals(s1,res2[0]);
        assertEquals(2,res2[1]);
    }
}