public class AutomataDemo {
    public static void main(String[] args) {


        Automata coffee = new Automata();

        coffee.on();
        coffee.getMenu();
        coffee.coin(10);
        coffee.coin(10);
        coffee.coin(10);
        coffee.coin(5);
        coffee.cancel();
        coffee.coin(10);
        coffee.coin(10);
        coffee.coin(5);
        coffee.coin(5);
        coffee.choice("Двойной шоколад");
        coffee.cancel();
        coffee.coin(10);
        coffee.coin(10);
        coffee.coin(5);
        coffee.coin(5);
        coffee.choice("Молоко");
        //coffee.cancel();
        coffee.cook();
        coffee.off();

    }
}
