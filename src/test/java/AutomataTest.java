import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AutomataTest {

    @Test
    void choice() {
        Automata TestAutomata = new Automata();
        TestAutomata.on();
        TestAutomata.coin(150);

        assertEquals(true, TestAutomata.choice(3));
        assertEquals(false,TestAutomata.choice(6));
    }
}