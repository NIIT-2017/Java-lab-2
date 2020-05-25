import java.net.URISyntaxException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class AutomataTest
{
    @org.junit.Test
    public void start() throws URISyntaxException
    {
        Automata test = new Automata("menu.json");
        Automata.STATES compare = test.getState();
        boolean ifOff = compare.equals(Automata.STATES.OFF);
        assertTrue(ifOff);
    }
    @org.junit.Test
    public void on() throws URISyntaxException
    {
        Automata test = new Automata("menu.json");
        test.on();
        Automata.STATES compare = test.getState();
        boolean ifWait = compare.equals(Automata.STATES.WAIT);
        assertTrue(ifWait);
    }
    @org.junit.Test
    public void coinState() throws URISyntaxException
    {
        Automata test = new Automata("menu.json");
        test.on();
        test.coin(10);
        Automata.STATES compare = test.getState();
        boolean ifAccept = compare.equals(Automata.STATES.ACCEPT);
        assertTrue(ifAccept);
    }
    @org.junit.Test
    public void coinCash() throws URISyntaxException
    {
        Automata test = new Automata("menu.json");
        test.on();
        test.coin(10);
        assertEquals(10, test.getCash());
    }
    @org.junit.Test
    public void choice() throws URISyntaxException
    {
        Automata test = new Automata("menu.json");
        test.on();
        test.coin(30);
        test.choice(2);
        Automata.STATES compare = test.getState();
        boolean ifWait = compare.equals(Automata.STATES.WAIT);
        assertTrue(ifWait);
    }
    @org.junit.Test
    public void checkNotEnoughCash() throws URISyntaxException
    {
        Automata test = new Automata("menu.json");
        test.on();
        test.coin(10);
        test.choice(2);
        assertEquals(0, test.getCash());
        assertEquals(10, test.getChange());
    }
    @org.junit.Test
    public void checkEnoughCash() throws URISyntaxException
    {
        Automata test = new Automata("menu.json");
        test.on();
        test.coin(35);
        test.choice(2);
        assertEquals(0, test.getCash());
        assertEquals(10, test.getChange());
    }
    @org.junit.Test
    public void cancelFromAccept() throws URISyntaxException
    {
        Automata test = new Automata("menu.json");
        test.on();
        test.coin(10);
        test.cancel();
        Automata.STATES compare = test.getState();
        boolean ifWait = compare.equals(Automata.STATES.WAIT);
        assertTrue(ifWait);
        assertEquals(0, test.getCash());
        assertEquals(10, test.getChange());
    }
    @org.junit.Test
    public void off() throws URISyntaxException
    {
        Automata test = new Automata("menu.json");
        test.on();
        test.coin(30);
        test.choice(2);
        test.off();
        Automata.STATES compare = test.getState();
        boolean ifOff = compare.equals(Automata.STATES.OFF);
        assertTrue(ifOff);
    }
}