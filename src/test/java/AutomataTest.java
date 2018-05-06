import static org.junit.Assert.*;

public class AutomataTest {

    @org.junit.Test
    public void on() {
        Automata coffee= new Automata();
        coffee.on();
        assertEquals(STATES.WAIT, coffee.getState());
    }

    @org.junit.Test
    public void off() {
        Automata coffee= new Automata();
        coffee.off();
        assertEquals(STATES.OFF, coffee.getState());
    }

    @org.junit.Test
    public void coin1() {
        Automata coffee= new Automata();
        coffee.on();
        coffee.coin(10);
        assertEquals(10, coffee.getCash());
    }

    @org.junit.Test
    public void coin2() {
        Automata coffee= new Automata();
        coffee.on();
        coffee.coin(10);
        coffee.coin(50);
        assertEquals(60, coffee.getCash());
    }

    @org.junit.Test
    public void cancel1() {
        Automata coffee= new Automata();
        coffee.on();
        coffee.coin(10);
        coffee.coin(50);
        coffee.cancel();
        assertEquals(STATES.WAIT, coffee.getState());

    }

    @org.junit.Test
    public void cancel2() {
        Automata coffee= new Automata();
        coffee.on();
        coffee.coin(10);
        coffee.coin(50);
        assertEquals(60,coffee.cancel());

    }

    @org.junit.Test
    public void printMenu() {
        String[]result = {"1:Americano-20","2:Cappucino-30","3:Tea-15","4:Latte-35"};
        Automata coffee= new Automata();
        coffee.on();
        assertEquals(result, coffee.printMenu());
    }

    @org.junit.Test
    public void printState() {
        Automata coffee= new Automata();
        coffee.on();
        assertEquals("WAIT", coffee.printState());
    }

    @org.junit.Test
    public void choise() {
        Automata coffee= new Automata();
        coffee.on();
        coffee.coin(10);
        coffee.coin(50);
        assertEquals(30,coffee.choise(2));
    }
}