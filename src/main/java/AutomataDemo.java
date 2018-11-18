import java.util.ArrayList;

public class AutomataDemo {
    public static void main(String[] args){
        //Automata A1 = new Automata(new ArrayList<String[]>());
    }
}

class Automata {

    int cash = 0;
    int sum = 0;
    ArrayList<String[]> menu = new ArrayList<String[]>();
    States state;

    enum States {
        OFF, WAIT, ACCEPT, CHECK, COOK
    }

    Automata(ArrayList<String[]> menuNew) {
        menu = menuNew;
        state = States.OFF;
    }

    void ON() {
        state = States.WAIT;
    }

    void OFF() {
        state = States.OFF;
    }

    void coin(int cash1) {
        cash += cash1;
        state = States.ACCEPT;
        check();
    }

    void printMenu() {

        for (String[] food : menu) {
            System.out.println(menu.indexOf(food) + " " + food[0] + " " + food[1]);
        }
    }

    void printState() {
        System.out.println(state);
    }

    void choice(int food) {
        String[] str = menu.get(food);
        sum += Integer.parseInt(str[1]);
        state = States.CHECK;
    }

    void check() {

        int balance = cash - sum;
        state = States.CHECK;

        if (balance < 0) {
            System.out.println("Не хвататет " + (balance * -1));
        } else {
            System.out.println("Достаточно");
        }
    }

    void cancel() {
        System.out.println("Сдача " + cash);
        cash = 0;
        sum = 0;
        state = States.WAIT;
    }

    void cook() throws InterruptedException {
        state = States.COOK;
        Thread.sleep(1000);
        state = States.WAIT;

        System.out.println("Сдача " + (cash - sum));
        cash = 0;
        sum = 0;
    }
}
