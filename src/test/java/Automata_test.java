import org.junit.Test;

import static org.junit.Assert.*;

public class Automata_test {

    Automata Test = new Automata();

    @Test
    public void on() {
        Test.On();
        assertEquals("WAIT",Test.getState());
    }

    @Test
    public void off() {
        Test.On();
        Test.coin(40);
        Test.off();
        assertEquals("WAIT",Test.getState());
        Test.moneyBack();
        Test.off();
        assertEquals("OFF",Test.getState());
    }

    @Test
    public void coin() {
        Test.On();
        assertEquals("What is this? Try again.",Test.coin(0));
        Test.coin(50);
        assertEquals(50,Test.getCoin());
    }

    @Test
    public void moneyBack() {
        Test.On();
        assertEquals("I don't have your coins",Test.moneyBack());
        Test.coin(50);
        Test.moneyBack();
        assertEquals(0,Test.getCoin());
    }

    @Test
    public void choice() {
        Test.On();
        Test.coin(50);
        Test.Choice(1);
        assertEquals("COOK", Test.getState());
    }
}