package HomeworkLab2;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
public class AutomataTest {
    Automata vendor=null;
    @Before
    public void createAutaomata()
    {
        vendor = new Automata("Menu.json");
        assertNotEquals(vendor,null);
    }
    @Test
    public void procedureTest()
    {
        assertEquals(Automata.STATES.Off,vendor.getState());
        vendor.on();
        assertEquals(Automata.STATES.Wait,vendor.getState());
        vendor.coin(5);
        assertEquals(Automata.STATES.Accept,vendor.getState());
        vendor.choice("Tea");
        assertEquals(Automata.STATES.Check,vendor.getState());
        vendor.cancel();
        assertEquals(Automata.STATES.Wait,vendor.getState());
        vendor.coin(5);
        assertEquals(Automata.STATES.Accept,vendor.getState());
        vendor.choice("Tea");
        assertEquals(Automata.STATES.Check,vendor.getState());
        vendor.cook();
        assertEquals(Automata.STATES.Cook,vendor.getState());
        vendor.finish();
        assertEquals(Automata.STATES.Wait,vendor.getState());
    }
    @Test
    public  void onTest()
    {
        Automata vendor=new Automata("Menu.txt");
        vendor.on();
        assertEquals(Automata.STATES.Wait,vendor.getState());
    }
    @Test
    public  void offTest()
    {
        Automata vendor=new Automata("Menu.txt");
        vendor.on();
        vendor.off();
        assertEquals(Automata.STATES.Off,vendor.getState());
    }
    @Test
    public void acceptTest()
    {
        Automata vendor=new Automata("Menu.txt");
        vendor.on();
        vendor.coin(5);
        assertEquals(Automata.STATES.Accept,vendor.getState());
    }
    @Test
    public void checkTest()
    {
        Automata vendor=new Automata("Menu.txt");
        vendor.on();
        vendor.coin(5);
        vendor.choice("Tea");
        assertEquals(Automata.STATES.Check,vendor.getState());
    }
    @Test
    public void cancelTest()
    {
        Automata vendor=new Automata("Menu.txt");
        vendor.on();
        vendor.coin(5);
        vendor.choice("Tea");
        vendor.cancel();
        assertEquals(Automata.STATES.Wait,vendor.getState());
    }
    @Test
    public void cookTest()
    {
        Automata vendor=new Automata("Menu.txt");
        vendor.on();
        vendor.coin(5);
        vendor.choice("Tea");
        vendor.cancel();
        vendor.coin(5);
        vendor.choice("Tea");
        vendor.cook();
        assertEquals(Automata.STATES.Cook,vendor.getState());
    }
    @Test
    public void endTest()
    {
        Automata vendor=new Automata("Menu.txt");
        vendor.on();
        vendor.coin(5);
        vendor.choice("Tea");
        vendor.cancel();
        vendor.coin(5);
        vendor.choice("Tea");
        vendor.cook();
        vendor.finish();
        assertEquals(Automata.STATES.Wait,vendor.getState());
    }
}