import java.util.ArrayList;

public class Automata {
    private int cash;
    private ArrayList<String> menu = new ArrayList<String>();
    private ArrayList<Integer> prices = new ArrayList<Integer>();

    private enum States {
        off,
        wait,
        accept,
        check,
        cook
    }
    States state;

    public Automata() {
        this.cash = 0;
        this.menu.add("coffee");
        this.menu.add("tea");
        this.menu.add("cacao");
        this.menu.add("soup");
        this.prices.add(75);
        this.prices.add(55);
        this.prices.add(45);
        this.prices.add(40);
        this.state = States.off;
    }

    public void on() {                                         //включение автомата;
        if (state == States.off)
            state = States.wait;
        else
            System.out.println("Automata is" + state + "!");
    }

    private void off() {                                       //выключение автомата;
        if (state == States.wait && cash == 0)
            state = States.off;
    }

    public void coin(int EntCash) {                             //занесение денег на счёт пользователем;
        if (state == States.wait || state == States.accept) {
            cash = cash + EntCash;
            state = States.accept;
            System.out.println("Sum is " + cash);
        }
    }

    public void getMenu() {                                    //отображение меню с напитками и ценами для пользователя;
        if (state == States.wait || state == States.accept)
            for (int i = 0; i < menu.size(); i++)
                System.out.println((i + 1) + ". " + menu.get(i) + " - " + prices.get(i) + ".");
    }

    public void getState() {                                   //отображение текущего состояния для пользователя;
        if (state != States.off)
            System.out.println(state);
    }

    public boolean choice(int i) {                              //выбор напитка пользователем;
        System.out.println("Choose a number of drink!");
        if ((state == States.accept || state == States.wait) && (i == 1 || i == 2 || i == 3 || i == 4)) {
            System.out.println("Your choice is a " + menu.get(i - 1));
            state = States.check;
        }
        else
            System.out.println("Error!");

        if (check(i-1) == true) {
            cash = cash - prices.get(i - 1);
            cook(i - 1);
            return true;
        }

        else
            return false;
    }

    private boolean check(int choice) {                              //проверка наличия необходимой суммы;
        if (state == States.check && (cash - prices.get(choice)) >= 0) {
            System.out.println("Check is over!");
            state = States.cook;
            return true;
        }
        else {
            System.out.println("Not enough money!");
            state = States.accept;
            return false;
        }
    }

    public void cancel() {                                        //отмена сеанса обслуживания пользователем;
        System.out.println("To return money push!");
        if (state == States.accept && cash != 0) {
            System.out.println("Return money " + cash + "!");
            cash = 0;
            state = States.wait;
        }
    }

    private void cook(int choice) {                                //имитация процесса приготовления напитка;
        if (state == States.cook) {
            System.out.println("Cooking a " + menu.get(choice));
            state = States.check;
            finish();
        }
    }

    private void finish() {                                        // завершение обслуживания пользователя.
        if (cash != 0) {
            System.out.println("Return money " + cash + "!");
            cash = 0;
        }

        System.out.println("Your order is over! Take a drink!\nThank you!");
        state = States.wait;
    }
}