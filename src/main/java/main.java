class main {
    public static void main(String[] args) {
        Automata automata = new Automata();
        automata.on();
        automata.printState();
        automata.printMenu();
        automata.coin(50);
        automata.printState();
        automata.choice("5");
        automata.printState();
        automata.off();
    }
}
