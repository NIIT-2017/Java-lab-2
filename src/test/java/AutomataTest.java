import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import org.junit.Test;

public class AutomataTest {
    ArrayList <String> menuTest = new ArrayList<String>(Arrays.asList("000","aaa","bbb","ccc"));
    ArrayList <Double> pricesTest = new ArrayList<Double>(Arrays.asList(0.0,10.0,20.0,30.0));
    Automata automataTest = new Automata(menuTest, pricesTest);
    ButtonsInDisplayOut bdioTest = new ButtonsInDisplayOut(automataTest);
    DecimalFormat df1 = new DecimalFormat("#0.0");

    @org.junit.Test
    public void on1() {
        automataTest.setBdio(bdioTest);
        automataTest.on();
        assertEquals(Automata.STATES.WAIT,automataTest.getState());
        assertEquals("0.0",df1.format(automataTest.getCash()));
        assertEquals("0.0",df1.format(automataTest.getChange()));
    }

    @org.junit.Test
    public void on2() {
        automataTest.setBdio(bdioTest);
        automataTest.setState(Automata.STATES.COOK);

        automataTest.on();
        assertEquals(Automata.STATES.COOK,automataTest.getState());
        assertEquals("0.0",df1.format(automataTest.getCash()));
        assertEquals("0.0",df1.format(automataTest.getChange()));
    }

    @org.junit.Test
    public void off1() {
        automataTest.setBdio(bdioTest);
        automataTest.off();
        assertEquals(Automata.STATES.OFF,automataTest.getState());
    }

    @org.junit.Test
    public void off2() {
        automataTest.setBdio(bdioTest);
        automataTest.setState(Automata.STATES.COOK);

        automataTest.off();
        assertEquals(Automata.STATES.COOK,automataTest.getState());
    }

    @org.junit.Test
    public void coin1() {
        automataTest.setBdio(bdioTest);
        automataTest.setState(Automata.STATES.WAIT);
        automataTest.setCash(0.0);
        automataTest.setChange(0.0);

        automataTest.coin(10.0);
        assertEquals(Automata.STATES.ACCEPT,automataTest.getState());
        assertEquals("10.0",df1.format(automataTest.getCash()));
        assertEquals("0.0",df1.format(automataTest.getChange()));
    }

    @org.junit.Test
    public void coin2() {
        automataTest.setBdio(bdioTest);
        automataTest.setState(Automata.STATES.WAIT);
        automataTest.setCash(10.0);
        automataTest.setChange(0.0);

        automataTest.coin(20.0);
        assertEquals(Automata.STATES.ACCEPT,automataTest.getState());
        assertEquals("30.0",df1.format(automataTest.getCash()));
        assertEquals("0.0",df1.format(automataTest.getChange()));
    }

   @org.junit.Test
    public void coin3() {
        automataTest.setBdio(bdioTest);
        automataTest.setState(Automata.STATES.COOK);
        automataTest.setCash(0.0);
        automataTest.setChange(0.0);

        automataTest.coin(20.0);
        assertEquals(Automata.STATES.COOK,automataTest.getState());
        assertEquals("0.0",df1.format(automataTest.getCash()));
        assertEquals("20.0",df1.format(automataTest.getChange()));
    }

    @org.junit.Test
    public void cancel1() {
        automataTest.setBdio(bdioTest);
        automataTest.setState(Automata.STATES.ACCEPT);
        automataTest.setCash(20.0);
        automataTest.setChange(0.0);

        automataTest.cancel();
        assertEquals(Automata.STATES.WAIT,automataTest.getState());
        assertEquals("0.0",df1.format(automataTest.getCash()));
        assertEquals("20.0",df1.format(automataTest.getChange()));

    }

    @org.junit.Test
    public void cancel2() {
        automataTest.setBdio(bdioTest);
        automataTest.setState(Automata.STATES.CHECK);
        automataTest.setCash(10.0);
        automataTest.setChange(0.0);

        automataTest.cancel();
        assertEquals(Automata.STATES.WAIT,automataTest.getState());
        assertEquals("0.0",df1.format(automataTest.getCash()));
        assertEquals("10.0",df1.format(automataTest.getChange()));
    }

    @org.junit.Test
    public void cancel3() {
        automataTest.setBdio(bdioTest);
        automataTest.setState(Automata.STATES.COOK);
        automataTest.setCash(10.0);
        automataTest.setChange(0.0);

        automataTest.cancel();
        assertEquals(Automata.STATES.COOK,automataTest.getState());
        assertEquals("10.0",df1.format(automataTest.getCash()));
        assertEquals("0.0",df1.format(automataTest.getChange()));
    }

    @org.junit.Test
    public void choice1() {
        automataTest.setBdio(bdioTest);
        automataTest.setState(Automata.STATES.ACCEPT);
        automataTest.setCash(10.0);
        automataTest.setChange(0.0);

        automataTest.choice(1); //cook expected
        assertEquals(Automata.STATES.WAIT,automataTest.getState());
    }

    @org.junit.Test
    public void choice2() {
        automataTest.setBdio(bdioTest);
        automataTest.setState(Automata.STATES.ACCEPT);
        automataTest.setCash(10.0);
        automataTest.setChange(0.0);

        automataTest.choice(2); // cancel expected
        assertEquals(Automata.STATES.WAIT,automataTest.getState());
        assertEquals("0.0",df1.format(automataTest.getCash()));
        assertEquals("10.0",df1.format(automataTest.getChange()));
    }

    @org.junit.Test
    public void choice3() {
        automataTest.setBdio(bdioTest);
        automataTest.setState(Automata.STATES.ACCEPT);
        automataTest.setCash(30.0);
        automataTest.setChange(0.0);

        automataTest.choice(2); // cookl expected
        assertEquals(Automata.STATES.WAIT,automataTest.getState());
    }

    @org.junit.Test
    public void choice4() {
        automataTest.setBdio(bdioTest);
        automataTest.setState(Automata.STATES.WAIT);
        automataTest.setCash(0.0);
        automataTest.setChange(0.0);

        automataTest.choice(2);
        assertEquals(Automata.STATES.WAIT,automataTest.getState());
        assertEquals("0.0",df1.format(automataTest.getCash()));
        assertEquals("0.0",df1.format(automataTest.getChange()));
    }

    @org.junit.Test
    public void finish1() {
        automataTest.setBdio(bdioTest);
        automataTest.setState(Automata.STATES.COOK);
        automataTest.setCash(20.0);
        automataTest.setChange(0.0);
        automataTest.setMenuNumber(1);

        automataTest.finish();
        assertEquals(Automata.STATES.WAIT,automataTest.getState());
        assertEquals("0.0",df1.format(automataTest.getCash()));
        assertEquals("10.0",df1.format(automataTest.getChange()));
    }

    @org.junit.Test
    public void finish2() {
        automataTest.setBdio(bdioTest);
        automataTest.setState(Automata.STATES.COOK);
        automataTest.setCash(20.0);
        automataTest.setChange(0.0);
        automataTest.setMenuNumber(2);

        automataTest.finish();
        assertEquals(Automata.STATES.WAIT,automataTest.getState());
        assertEquals("0.0",df1.format(automataTest.getCash()));
        assertEquals("0.0",df1.format(automataTest.getChange()));
    }

    @org.junit.Test
    public void finish3() {
        automataTest.setBdio(bdioTest);
        automataTest.setState(Automata.STATES.ACCEPT);
        automataTest.setCash(20.0);
        automataTest.setChange(0.0);

        automataTest.finish();
        assertEquals(Automata.STATES.ACCEPT,automataTest.getState());
        assertEquals("20.0",df1.format(automataTest.getCash()));
        assertEquals("0.0",df1.format(automataTest.getChange()));
    }
}
