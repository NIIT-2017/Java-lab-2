import static org.junit.Assert.*;

public class AutomataTest {

    @org.junit.Test
    public void on() {
        Automata test = new Automata();
        test.on();
        assertEquals(Automata.States.WAIT, test.state);
    }

    @org.junit.Test
    public void off() {
        Automata test = new Automata();
        assertEquals(Automata.States.OFF, test.state);
    }

    @org.junit.Test
    public void coin() {
        Automata test = new Automata();
        test.on();
        test.coin (10);
        test.coin (15);
        assertEquals(25, test.cash);
    }

    @org.junit.Test
    public void getMenu() {
        String expStr = "Latte=30\n" +
                "Coffe=20\n" +
                "Cappuccino=30\n" +
                "Tea=15\n" +
                "Tea with lemon=15\n";
        Automata test = new Automata();
        test.on();
        assertEquals (expStr, test.getMenu());
    }

    @org.junit.Test
    public void getState() {
        Automata test = new Automata();
        test.on();
        test.coin (10);
        assertEquals("ACCEPT", test.getState());

    }

    @org.junit.Test
    public void choice() {
        Automata test = new Automata();
        test.on();
        test.coin (40);
        assertEquals(true, test.choice(2));
    }

    @org.junit.Test
    public void cancel() {
        Automata test = new Automata();
        test.on();
        test.coin (40);
        test.cancel();
        assertEquals(Automata.States.WAIT, test.state);
    }


    @org.junit.Test
    public void getChange() {
        Automata test = new Automata();
        test.on();
        test.coin (40);
        assertEquals(40, test.getChange());
    }
}