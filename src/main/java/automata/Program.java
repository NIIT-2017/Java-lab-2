package automata;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by kortez on 01/04/18.
 */
public class Program {
    public static void main(String[] args) {
        Automata automata = new Automata("drinks.txt","  ");
        System.out.println("state = "+automata.getState());
        System.out.println("automata.getCash() = " + automata.getCash());
        System.out.println("automata.getMoney() = " + automata.getMoney());
        System.out.println();

        System.out.println("on");
        automata.on();
        System.out.println("state = "+automata.getState());
        System.out.println("automata.getCash() = " + automata.getCash());
        System.out.println("automata.getMoney() = " + automata.getMoney());
        System.out.println();

        ArrayList<String> menu = automata.getMenu();
        for (Iterator<String> iterator = menu.iterator(); iterator.hasNext(); ) {
            System.out.println(iterator.next());
        }
        System.out.println();

        System.out.println("choice drink 2");
        automata.choiceDrink(2);
        System.out.println("state = "+automata.getState());
        System.out.println("automata.getCash() = " + automata.getCash());
        System.out.println("automata.getMoney() = " + automata.getMoney());
        System.out.println();

        System.out.println("coin 10.0");
        automata.coin(10.0);
        System.out.println("state = "+automata.getState());
        System.out.println("automata.getCash() = " + automata.getCash());
        System.out.println("automata.getMoney() = " + automata.getMoney());
        System.out.println();

        System.out.println("coin 200.0");
        automata.coin(200.0);
        System.out.println("automata.getCash() = " + automata.getCash());
        System.out.println("state ="+automata.getState());
        System.out.println("automata.getMoney() = " + automata.getMoney());
        System.out.println();

        System.out.println("return money");
        System.out.println("state ="+automata.getState());
        System.out.println("automata.getMoney() = " + automata.getMoney());
        System.out.println("automata.returnMoney() = " + automata.returnMoney());
        System.out.println();

        System.out.println("off");
        automata.off();
        System.out.println("state ="+automata.getState());
        System.out.println("automata.getMoney() = " + automata.getMoney());
        System.out.println();
    }
}
