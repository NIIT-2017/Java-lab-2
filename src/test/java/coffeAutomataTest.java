import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class coffeAutomataTest {

    Automata automataTester = new Automata();
    @org.junit.Test
    public void checkGetState(){
        assertEquals(Automata.States.OFF,automataTester.getState());
    }

    @org.junit.Test
    public void checkOn(){
        assertEquals(Automata.States.WAIT,automataTester.on());
    }

    @org.junit.Test
    public void checkOff(){
        assertEquals(Automata.States.OFF,automataTester.off());
    }

    @org.junit.Test
    public void checkGetMenu(){
        automataTester.on();
        ArrayList<String> testMenu = new ArrayList<>();
        testMenu.add("Espresso   60 coins.");
        testMenu.add("Americano   90 coins.");
        testMenu.add("Cappuccino   120 coins.");
        testMenu.add("Latte   150 coins.");
        testMenu.add("Tea   45 coins.");
        testMenu.add("Water   30 coins.");
        assertEquals(testMenu,automataTester.getMenu());
    }

}
