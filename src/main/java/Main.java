public class Main {
    public static void main(String[] args) {
        Automata automata = new Automata();
        automata.on();
        automata.coin(5);
        automata.choice(1);
        automata.coin(100);
        automata.choice(1);
        automata.off();
    }

}