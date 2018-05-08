public class Main {
    public static void main(String[] args) {
        Automata nescafe = new Automata();
        nescafe.on();
        nescafe.printState();
        nescafe.coin(10);
        nescafe.coin(20);
        nescafe.printState();
        nescafe.choice("1");
        nescafe.printState();
        nescafe.off();

    }
}
