import java.awt.*;

public class Automata_Demo {
    public static void main (String[] args) throws InterruptedException {
        Automata work = new Automata();
        System.out.println("Hello, my hungry friend!");
        System.out.println(work.On());
        System.out.println(work.getMenu());
        System.out.println(work.Choice(0));
        System.out.println(work.coin(15));
        System.out.println(work.Choice(3));
        System.out.println(work.coin(50));
        System.out.println(work.Choice(3));
        System.out.println(work.Finish());
        Thread.sleep(6000);
        System.out.println(work.Finish());
        System.out.println(work.off());
        System.out.println(work.moneyBack());
        System.out.println(work.off());
        System.out.println(work.getState());


    }
}
