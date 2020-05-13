import java.net.URISyntaxException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws URISyntaxException {
        Automata a = new Automata("menu.json");
        Scanner in = new Scanner(System.in);
        a.on();
        System.out.println("Current state: " + a.getState());
        System.out.println("INSERT MONEY");
        System.out.println("Balance is " + a.coin(in.nextInt()) + " RUB");
        System.out.println("Current state: " + a.getState());
        System.out.println("MENU");
        System.out.println("*");
        for (int i = 0; i < a.getMenuLength(); i++)
            System.out.println(i + ": " + a.getMenu(i));
        System.out.println("*");
        System.out.println("CHOOSE NUMBER OF DRINK");
        System.out.println(a.choice(in.nextInt()));
        System.out.println("Current state: " + a.getState());
        System.out.println("INSERT MONEY");
        System.out.println("Balance is " + a.coin(in.nextInt()) + " RUB");
        System.out.println("Operation cancelled. Take your money " + a.cancel() + " RUB");
        System.out.println("INSERT MONEY");
        System.out.println("Balance is " + a.coin(in.nextInt()) + " RUB");
        System.out.println("Current state: " + a.getState());
        System.out.println("CHOOSE NUMBER OF DRINK");
        System.out.println(a.choice(in.nextInt()));
        System.out.println("Current state: " + a.getState());
        System.out.println("Your change is " + a.getChange() + " RUB");
        a.off();
    }
}
