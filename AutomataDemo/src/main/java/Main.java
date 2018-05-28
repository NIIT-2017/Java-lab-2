import automata.Automata;
import automata.Goods;
import automata.ListenerAutomata;

import java.util.concurrent.TimeUnit;

public class Main implements ListenerAutomata {
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

    public void getGoods(Goods goods) {
        System.out.println(goods.toString());
    }

    public void getStatus(Automata.STATES states) {
        System.out.println("Automat is " + states);
    }
}