import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class AutomataTest {

    Automata testAut = new Automata("testmenu.json");

    @Test
    public void on() {
        assertEquals("WAIT",testAut.on());
    }

    @Test
    public void on1() {
        testAut.on();
        testAut.coin(25);
        assertNotSame("WAIT",testAut.on());
    }

    @Test
    public void off() {
        assertEquals("OFF",testAut.off());
    }

    @Test
    public void off1() {
        assertNotSame("WAIT",testAut.off());
    }

    @Test
    public void coin() {
        testAut.on();
        assertEquals("100",testAut.coin(100));
        assertEquals("125",testAut.coin(25));
        assertEquals("225",testAut.coin(100));
    }

    @Test
    public void coin1() {
        testAut.on();
        assertNotSame("-100",testAut.coin(-100));
    }

    @Test
    public void coin2() {
        assertEquals("Операция невозможна!",testAut.coin(100));
    }

    @Test
    public void getMenu() {
        testAut.on();
        testAut.coin(100);
        testAut.cancel();
        testAut.off();
        ArrayList<String> testmenu = new ArrayList<>();
        testmenu.add("Операция невозможна!");
        assertEquals(testmenu,testAut.getMenu());
    }

    @Test
    public void getState() {
        testAut.on();
        testAut.coin(100);
        assertEquals("ACCEPT",testAut.getState());
    }
    @Test
    public void getState1() {
        testAut.on();
        testAut.coin(100);
        testAut.cancel();
        assertEquals("WAIT",testAut.getState());
    }
    @Test
    public void getState2() {
        assertNotSame("WAIT",testAut.getState());
    }

    @Test
    public void getState3() {
        testAut.on();
        testAut.coin(100);
        testAut.choise(0);
        assertEquals("COOK",testAut.getState());
    }

    @Test
    public void finish() {
        testAut.on();
        testAut.coin(100);
        testAut.choise(0);
        assertEquals(Integer.toString(testAut.checkcash()),testAut.finish("YES"));
    }

    @Test
    public void finish2() {
        testAut.on();
        testAut.coin(101);
        testAut.choise(0);
        assertEquals("Благодарим за покупку!",testAut.finish("YES"));
    }

    @Test
    public void finish3() {
        testAut.on();
        testAut.coin(101);
        assertEquals("Операция невозможна!",testAut.finish("YES"));
    }

    @Test
    public void choise() {
        testAut.on();
        testAut.coin(100);
        assertEquals("80",testAut.choise(0));

    }
    @Test
    public void choise1() {
        testAut.on();
        assertEquals("Операция невозможна!",testAut.choise(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void choise2() throws IndexOutOfBoundsException{
        testAut.on();
        testAut.coin(100);
        testAut.choise(3);
    }


    @Test
    public void cancel() {
        testAut.on();
        testAut.coin(100);
        testAut.choise(0);
        assertEquals("WAIT",testAut.cancel());
    }
    @Test
    public void cancel2() {
        testAut.on();
        assertEquals("Операция невозможна!",testAut.cancel());
    }

    @Test
    public void checkcash() {
        testAut.on();
        testAut.coin(1000000);
        assertEquals(1000000,testAut.checkcash());
    }
}