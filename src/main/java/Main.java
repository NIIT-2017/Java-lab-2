
import Automata.Automata;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Automata meAutomat = new Automata();
        meAutomat.on();
        System.out.println(meAutomat.getMenu());
        meAutomat.coin(1000);
        meAutomat.choice(3);
        try {
            TimeUnit.SECONDS.sleep(45);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        meAutomat.choice(1);
        System.out.println("Our surrender: " + meAutomat.cancel());
    }
}
