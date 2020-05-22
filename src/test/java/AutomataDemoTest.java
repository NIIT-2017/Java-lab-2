import org.junit.Test;

import static org.junit.Assert.*;

public class AutomataDemoTest {

    AutomataDemo amTest = new AutomataDemo();

    @Test
    public void on() {
        amTest.on();
        assertEquals("AWAIT",amTest.getState());
    }

    @Test
    public void off() {
        amTest.on();
        amTest.coin(50);
        amTest.off();
        assertEquals("AWAIT",amTest.getState());
        amTest.moneyBack();
        amTest.off();
        assertEquals("OFF",amTest.getState());
    }

    @Test
    public void coin() {
        amTest.on();
        assertEquals("Купюра не распознана, повторите попытку",amTest.coin(0));
        amTest.coin(50);
        assertEquals(50,amTest.getCoin());
    }

    @Test
    public void moneyBack() {
        amTest.on();
        assertEquals("Нет средств",amTest.moneyBack());
        amTest.coin(50);
        amTest.moneyBack();
        assertEquals(0,amTest.getCoin());
    }

    @Test
    public void choice() {
        amTest.on();
        amTest.coin(50);
        amTest.choice(1);
        assertEquals("COOK", amTest.getState());
    }
}