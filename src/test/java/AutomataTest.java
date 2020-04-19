import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class AutomataTest {
    ArrayList <String> menuTest = new ArrayList<String>(Arrays.asList("000","aaa","bbb","ccc"));
    ArrayList <Double> pricesTest = new ArrayList<Double>(Arrays.asList(0.0,10.0,20.0,30.0));
    Automata autumataTest = new Automata(menuTest, pricesTest);

    @org.junit.jupiter.api.Test
    public void on1() {
        autumataTest.on();
        assertEquals(Automata.STATES.WAIT,autumataTest.getState());
        assertEquals(0.0,autumataTest.getCash());
        assertEquals(0.0,autumataTest.getChange());
    }

    @org.junit.jupiter.api.Test
    public void on2() {
        autumataTest.setState(Automata.STATES.COOK);

        autumataTest.on();
        assertEquals(Automata.STATES.COOK,autumataTest.getState());
        assertEquals(0.0,autumataTest.getCash());
        assertEquals(0.0,autumataTest.getChange());
    }

    @org.junit.jupiter.api.Test
    public void off1() {
        autumataTest.off();
        assertEquals(Automata.STATES.OFF,autumataTest.getState());
    }

    @org.junit.jupiter.api.Test
    public void off2() {
        autumataTest.setState(Automata.STATES.COOK);

        autumataTest.off();
        assertEquals(Automata.STATES.COOK,autumataTest.getState());
    }

    @org.junit.jupiter.api.Test
    public void coin1() {
        autumataTest.setState(Automata.STATES.WAIT);
        autumataTest.setCash(0.0);
        autumataTest.setChange(0.0);

        autumataTest.coin(10.0);
        assertEquals(Automata.STATES.ACCEPT,autumataTest.getState());
        assertEquals(10.0,autumataTest.getCash());
        assertEquals(0.0,autumataTest.getChange());
    }

    @org.junit.jupiter.api.Test
    public void coin2() {
        autumataTest.setState(Automata.STATES.WAIT);
        autumataTest.setCash(10.0);
        autumataTest.setChange(0.0);

        autumataTest.coin(20.0);
        assertEquals(Automata.STATES.ACCEPT,autumataTest.getState());
        assertEquals(30.0,autumataTest.getCash());
        assertEquals(0.0,autumataTest.getChange());
    }

    @org.junit.jupiter.api.Test
    public void coin3() {
        autumataTest.setState(Automata.STATES.COOK);
        autumataTest.setCash(0.0);
        autumataTest.setChange(0.0);

        autumataTest.coin(20.0);
        assertEquals(Automata.STATES.COOK,autumataTest.getState());
        assertEquals(0.0,autumataTest.getCash());
        assertEquals(20.0,autumataTest.getChange());
    }

    @org.junit.jupiter.api.Test
    public void cancel1() {
        autumataTest.setState(Automata.STATES.ACCEPT);
        autumataTest.setCash(20.0);
        autumataTest.setChange(0.0);

        autumataTest.cancel();
        assertEquals(Automata.STATES.WAIT,autumataTest.getState());
        assertEquals(0.0,autumataTest.getCash());
        assertEquals(20.0,autumataTest.getChange());

    }

    @org.junit.jupiter.api.Test
    public void cancel2() {
        autumataTest.setState(Automata.STATES.CHECK);
        autumataTest.setCash(10.0);
        autumataTest.setChange(0.0);

        autumataTest.cancel();
        assertEquals(Automata.STATES.WAIT,autumataTest.getState());
        assertEquals(0.0,autumataTest.getCash());
        assertEquals(10.0,autumataTest.getChange());
    }

    @org.junit.jupiter.api.Test
    public void cancel3() {
        autumataTest.setState(Automata.STATES.COOK);
        autumataTest.setCash(10.0);
        autumataTest.setChange(0.0);

        autumataTest.cancel();
        assertEquals(Automata.STATES.COOK,autumataTest.getState());
        assertEquals(10.0,autumataTest.getCash());
        assertEquals(0.0,autumataTest.getChange());
    }

    @org.junit.jupiter.api.Test
    public void choice1() {
        autumataTest.setState(Automata.STATES.ACCEPT);
        autumataTest.setCash(10.0);
        autumataTest.setChange(0.0);

        autumataTest.choice(1); //cook expected
        assertEquals(Automata.STATES.COOK,autumataTest.getState());
    }

    @org.junit.jupiter.api.Test
    public void choice2() {
        autumataTest.setState(Automata.STATES.ACCEPT);
        autumataTest.setCash(10.0);
        autumataTest.setChange(0.0);

        autumataTest.choice(2); // cancel expected
        assertEquals(Automata.STATES.WAIT,autumataTest.getState());
        assertEquals(0.0,autumataTest.getCash());
        assertEquals(10.0,autumataTest.getChange());
    }

    @org.junit.jupiter.api.Test
    public void choice3() {
        autumataTest.setState(Automata.STATES.ACCEPT);
        autumataTest.setCash(30.0);
        autumataTest.setChange(0.0);

        autumataTest.choice(2); // cookl expected
        assertEquals(Automata.STATES.COOK,autumataTest.getState());
    }

    @org.junit.jupiter.api.Test
    public void choice4() {
        autumataTest.setState(Automata.STATES.WAIT);
        autumataTest.setCash(0.0);
        autumataTest.setChange(0.0);

        autumataTest.choice(2);
        assertEquals(Automata.STATES.WAIT,autumataTest.getState());
        assertEquals(0.0,autumataTest.getCash());
        assertEquals(0.0,autumataTest.getChange());
    }

    @org.junit.jupiter.api.Test
    public void finish1() {
        autumataTest.setState(Automata.STATES.COOK);
        autumataTest.setCash(20.0);
        autumataTest.setChange(0.0);
        autumataTest.setMenuNumber(1);

        autumataTest.finish();
        assertEquals(Automata.STATES.WAIT,autumataTest.getState());
        assertEquals(0.0,autumataTest.getCash());
        assertEquals(10.0,autumataTest.getChange());
    }

    @org.junit.jupiter.api.Test
    public void finish2() {
        autumataTest.setState(Automata.STATES.COOK);
        autumataTest.setCash(20.0);
        autumataTest.setChange(0.0);
        autumataTest.setMenuNumber(2);

        autumataTest.finish();
        assertEquals(Automata.STATES.WAIT,autumataTest.getState());
        assertEquals(0.0,autumataTest.getCash());
        assertEquals(0.0,autumataTest.getChange());
    }

    @org.junit.jupiter.api.Test
    public void finish3() {
        autumataTest.setState(Automata.STATES.ACCEPT);
        autumataTest.setCash(20.0);
        autumataTest.setChange(0.0);

        autumataTest.finish();
        assertEquals(Automata.STATES.ACCEPT,autumataTest.getState());
        assertEquals(20.0,autumataTest.getCash());
        assertEquals(0.0,autumataTest.getChange());
    }
}