import static org.junit.Assert.*;

public class AutomataTest {
    Automata CoffeeMashine = new Automata();

    @org.junit.Test
    public void on() {
    }

    @org.junit.Test
    public void off() {
    }

    @org.junit.Test
    public void getState() {
        CoffeeMashine.on();
        assertEquals(Automata.State.WAIT, CoffeeMashine.getState());
    }

    @org.junit.Test
    public void getMenu() {
        assertEquals("1. coffee", CoffeeMashine.getMenu()[0].get(0));
    }

    @org.junit.Test
    public void coin() {
        CoffeeMashine.on();
        CoffeeMashine.coin(5);
        CoffeeMashine.coin(10);
        assertEquals(Automata.State.ACCEPT, CoffeeMashine.getState());
        assertEquals(15,CoffeeMashine.getCash(),0.01 );
    }

    @org.junit.Test
    public void getCash() {
    }

    @org.junit.Test
    public void cancel() {
        CoffeeMashine.on();
        CoffeeMashine.coin(50);
        assertEquals(50,CoffeeMashine.cancel(),0.01 );
        assertEquals(Automata.State.WAIT, CoffeeMashine.getState());
    }

    @org.junit.Test
    public void choice() {
        CoffeeMashine.on();
        CoffeeMashine.coin(60);
        assertEquals(10,CoffeeMashine.choice(0),0.01 );
        assertEquals(Automata.State.WAIT, CoffeeMashine.getState());
    }
}