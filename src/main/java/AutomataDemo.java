public class AutomataDemo {
    public static void main(String[] args) {
        Automata myAutomata = new Automata();
        myAutomata.on();
        myAutomata.coin(10);
        myAutomata.cancel();
        myAutomata.coin(50);
        myAutomata.choice(2);
        myAutomata.coin(100);
        myAutomata.choice(2);
        myAutomata.off();
    }
}
