import static org.junit.Assert.*;

public class AutomataTest {

    @org.junit.Test
    public void on() {
        Automata a1=new Automata();
        a1.on();
        assertEquals("WAIT",a1.PrintState());
    }

    @org.junit.Test
    public void off() {
        Automata a1=new Automata();
        a1.off();
        assertEquals("OFF",a1.PrintState());
    }

    @org.junit.Test
    public void coin() {
        Automata a1=new Automata();
        a1.on();
        assertEquals(50,a1.coin(50));
        assertEquals(100,a1.coin(50));
        a1.cancel();
        assertEquals("WAIT",a1.PrintState());
        assertEquals(10,a1.coin(10));
        assertEquals("ACCEPT",a1.PrintState());
    }

    @org.junit.Test
    public void printState() {
        Automata a1=new Automata();
        assertEquals("OFF",a1.PrintState());
        a1.on();
        assertEquals("WAIT",a1.PrintState());
        a1.coin(20);
        assertEquals("ACCEPT",a1.PrintState());
        a1.choice(0);
        assertEquals("ACCEPT",a1.PrintState());
        a1.coin(50);
        assertEquals("ACCEPT",a1.PrintState());
        a1.choice(0);
        assertEquals("WAIT",a1.PrintState());
        a1.cancel();
        assertEquals("WAIT",a1.PrintState());
        a1.on();
        assertEquals("WAIT",a1.PrintState());
        a1.off();
        assertEquals("OFF",a1.PrintState());
    }

    @org.junit.Test
    public void choice() {
        Automata a1=new Automata();
        a1.on();
        a1.coin(100);
        a1.choice(2);
        assertEquals("WAIT",a1.PrintState());
        a1.coin(20);
        a1.choice(1);
        assertEquals("ACCEPT",a1.PrintState());
    }

    @org.junit.Test
    public void cancel() {
        Automata a1=new Automata();
        a1.on();
        a1.coin(100);

        assertEquals( a1.cancel(),100);
        assertEquals("WAIT",a1.PrintState());

        a1.coin(10);
        a1.coin(10);
        a1.choice(1);
        assertEquals( a1.cancel(),20);
        assertEquals("WAIT",a1.PrintState());
    }

    @org.junit.Test
    public void getSumMoney() {
        Automata a1=new Automata();
        a1.on();
        a1.coin(100);
        a1.cancel();
        assertEquals(0,a1.getSumMoney());
        a1.coin(300);
        a1.choice(1);
        assertEquals(20,a1.getSumMoney());
        a1.coin(100);
        a1.choice(0);
        assertEquals(70,a1.getSumMoney());
    }

}