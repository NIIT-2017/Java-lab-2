import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AutomataDemoTest extends Automata {
    static String testMenu = "____MENU____\n" +
            "Espresso 80\n" +
            "Mocha 120\n" +
            "Latte 100\n" +
            "Cappuccino 100\n";

    @Test

    //Проверка дефалтового состояния автомата после создания
    public void defaultTest() {
        Automata test = new Automata();
        assertEquals("Default state of machine is not OFF, actual default state is: " + test.getState(),
                "OFF", test.getState());
        test.powerOff();
    }

    @Test
    //Проверка состояния автомата после включения

    public void powerTest() {
        Automata test = new Automata();
        test.powerOn();
        assertEquals("Unexpected state after power on, actual state is: " + test.getState(),
                "WAITING_FOR_CHOICE", test.getState());
        test.powerOff();
    }
}

