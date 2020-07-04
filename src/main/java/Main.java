import java.util.ArrayList;

public class Main {
        public static void main(String[] args) {
            Automata auto = new Automata();
            auto.on();
            System.out.println("Состояние автомата" + auto.on());
            ArrayList<String> menu = auto.getMenu();
            int randomMoney = (int) (Math.random() *1000);
            int randomChoice = (int) (Math.random() * 8);
            System.out.println ("Меню:");
            for (String i: menu){
                System.out.println(i);
            }
            System.out.printf("Покупатель внес %.2f рублей", auto.coin(randomMoney));
            auto.choice(randomChoice);
            System.out.println("Состояние автомата: " + auto.getState());
            System.out.printf("Заберите напиток\n");
            System.out.printf("Счет: %.2f, состояние автомата: %s\n", auto.getCash(), auto.getState());
            auto.off();
            System.out.println("Автомат выключен " + auto.getState());
        }
}
