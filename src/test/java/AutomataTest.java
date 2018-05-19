import static org.junit.Assert.*;

public class AutomataTest {

    @org.junit.Test
    public void on() {
        Automata testAutomata = new Automata();
        assertEquals("OFF", testAutomata.getState().toString());
        testAutomata.on();
        assertEquals("WAIT", testAutomata.getState().toString());
    }

    @org.junit.Test
    public void off() {
        Automata testAutomata = new Automata();
        testAutomata.off();
        assertEquals("OFF", testAutomata.getState().toString());
    }

    @org.junit.Test
    public void coin() {
        Automata testAutomata = new Automata();
        testAutomata.on();
        testAutomata.coin(50);
        assertEquals(50, testAutomata.getCash());
        testAutomata.coin(100);
        assertEquals(150, testAutomata.getCash());
        assertEquals("ACCEPT", testAutomata.getState().toString());
    }

    @org.junit.Test
    public void printState() {
        Automata testAutomata = new Automata();
        assertEquals("Automata current state: OFF", testAutomata.printState());

        testAutomata.on();
        assertEquals("Automata current state: WAIT", testAutomata.printState());

        testAutomata.coin(25);
        assertEquals("Automata current state: ACCEPT", testAutomata.printState());

        testAutomata.choice(1);         //ситуация с нехваткой денег
        assertEquals("Automata current state: ACCEPT", testAutomata.printState());

        testAutomata.choice(9);         //денег достаточно, напиток приготовлен, аппарат снова ждет
        assertEquals("Automata current state: WAIT", testAutomata.printState());

        testAutomata.off();
        assertEquals("Automata current state: OFF", testAutomata.printState());
    }

    @org.junit.Test
    public void choice() {
        Automata testAutomata = new Automata();
        testAutomata.on();
        testAutomata.coin(100);
        testAutomata.choice(6);         //недостаточно денег
        assertEquals("ACCEPT", testAutomata.getState().toString());
        testAutomata.coin(50);
        testAutomata.choice(6);         //достаточно денег
        assertEquals("WAIT", testAutomata.getState().toString());
        testAutomata.coin(100);
        testAutomata.choice(4);         //вносим еще денег, заказываем новый напиток
        assertEquals("WAIT", testAutomata.getState().toString());
    }

    @org.junit.Test
    public void cancel() {
        Automata testAutomata = new Automata();
        testAutomata.on();
        testAutomata.coin(10);
        testAutomata.cancel();                     //отмена после добавления денег
        assertEquals("WAIT", testAutomata.getState().toString());

        testAutomata.coin(50);
        int testCashback = testAutomata.cancel();
        assertEquals(50, testCashback);    //проверка на количество сдачи после отмены

        testAutomata.choice(6);
        testAutomata.cancel();                     //отмена после выбора напитка
        assertEquals("WAIT", testAutomata.getState().toString());

    }
}