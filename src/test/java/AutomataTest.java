import static org.junit.Assert.*;

public class AutomataTest {


    @org.junit.Test
    public void onTest() {
        Automata a = new Automata();
        a.on();
        assertEquals(Automata.STATES.WAIT, a.states);

    }

    @org.junit.Test
    public void offTest() {
        Automata a = new Automata();
        a.off();
        assertEquals(Automata.STATES.OFF, a.states);
    }

    @org.junit.Test
    public void coinTest() {
        Automata a = new Automata();
        a.coin(22);
        assertEquals(22, a.cash);
    }


    @org.junit.Test
    public void choiceTest() {
        Automata a = new Automata();
        a.cash = 25;
        a.choice("Черный чай");
        assertEquals(Automata.STATES.CHECK, a.states);
        assertEquals(a.menu[1], a.choice);
    }

    @org.junit.Test
    public void cancelTest() {
        Automata a = new Automata();
        a.cash = 99;
        a.cancel();
        assertEquals(0, a.cash);
    }

    @org.junit.Test
    public void cookTest() {
        Automata a = new Automata();
        a.states = Automata.STATES.CHECK;
        a.choice = "Молоко";
        a.cook();
        assertEquals(Automata.STATES.WAIT, a.states);
    }
}