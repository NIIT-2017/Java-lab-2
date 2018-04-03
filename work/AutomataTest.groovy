class AutomataTest extends GroovyTestCase {
    void testOn() {
        Automata testAutomata = new Automata();
        testAutomata.on();
        assertEquals(Automata.STATES.WAIT, testAutomata.getState());
        assertEquals(0, testAutomata.getCash());
    }

    void testOff() {
        Automata testAutomata = new Automata();
        testAutomata.on();
        testAutomata.coin(10);
        testAutomata.off();
        assertEquals(Automata.STATES.OFF, testAutomata.getState());
        assertEquals(0, testAutomata.getCash());

    }

    void testCoin() {
        Automata testAutomata = new Automata();
        testAutomata.on();
        testAutomata.coin(10);
        testAutomata.coin(20);
        assertEquals(30, testAutomata.getCash());
    }

    void testChoice() {
        Automata testAutomata = new Automata();
        testAutomata.on();
        testAutomata.coin(10);
        assertEquals(10, testAutomata.getCash());
        testAutomata.choice("1");
        assertEquals(0, testAutomata.getCash());
        testAutomata.coin(40);
        testAutomata.choice("1");
        assertEquals(10, testAutomata.getCash());
    }

    void testCancel() {
        Automata testAutomata = new Automata();
        testAutomata.on();
        testAutomata.coin(10);
        assertEquals(10, testAutomata.getCash());
        testAutomata.cancel();
        assertEquals(0, testAutomata.getCash());

    }
}
