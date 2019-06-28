import java.util.ArrayList;
public class TaskAutomata {
    public static void main (String[] args) {
        Automata CofMash = new Automata();
        CofMash.on();
        System.out.println(CofMash.getState());
        ArrayList[] menu = CofMash.getMenu();
        for (int i = 0; i < menu[0].size(); i++) {
            System.out.println(menu[0].get(i) + "  " + menu[1].get(i));
        }
        CofMash.coin(60);
        System.out.println("The deposit equals: " + CofMash.getCash());
        float change = CofMash.choice(0);
        System.out.println("Take the change: " + change);
        System.out.println("The deposit equals: " + CofMash.getCash());
        System.out.println(CofMash.getState());
        CofMash.off();
        System.out.println(CofMash.getState());

    }
}


