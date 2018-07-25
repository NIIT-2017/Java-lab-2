public class AutomataDemo {
    public static void main(String[] args) {
        Automata myAutomata = new Automata();
        myAutomata.on();
        myAutomata.coin(20);
        myAutomata.cancel();
        myAutomata.coin(50);
        myAutomata.choice(3);
        myAutomata.coin(80);
        myAutomata.choice(2);
        myAutomata.off();
    }
}

