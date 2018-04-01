package automata;

/**
 * Created by kortez on 01/04/18.
 */
public class Program {
    public static void main(String[] args) {
        Automata automata = new Automata("drinks.txt");
        System.out.println(automata.getState());
        automata.on();
        System.out.println(automata.getState());
        automata.coin(10.0);
        System.out.println(automata.getCash());
        System.out.println(automata.getState());
        automata.getMenu();
    }
}
