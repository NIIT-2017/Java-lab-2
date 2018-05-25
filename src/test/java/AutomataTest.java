import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class AutomataTest {
    private final ByteArrayOutputStream outStream=new ByteArrayOutputStream();
    private String []outPutString;

    Automata automata=new Automata();
    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outStream));
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(null);
    }

    @Test
    public void cancel() {
        assertEquals(automata.getCash(), automata.cancel(automata.getCash()));
    }

    @Test
    public void coin() {
        assertEquals(30,automata.coin(30));
    }

    @Test
    public void choice() throws InterruptedException {
        assertEquals(Automata.STATES.WAIT, automata.choice(50));
    }

    @Test
    public void returnChange() {
        assertEquals(40,automata.returnChange(100,60));
    }


}