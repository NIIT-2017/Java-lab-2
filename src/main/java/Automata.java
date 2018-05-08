import java.util.HashMap;
import java.util.Properties;

public class Automata {


    private int cash;
    private Properties menu;
    private STATES state;

//    creating sample data:

    static HashMap <String, String> prices = new HashMap<String, String>();
    static {
        prices.put("1", "Espresso,30");
        prices.put("2", "Capuccino,40");
    }

//creating constructor with params

    public Automata(int cash, Properties menu) {
        state = STATES.OFF;
        this.cash = cash;
        this.menu = menu;
    }
//    creating constructor without params

    public Automata() {
        state = STATES.OFF;
        this.cash = 0;
    }


//    creating getters and setters

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public STATES getState() {
        return state;
    }

    public void setState(STATES state) {
        this.state = state;
    }



    enum STATES {OFF, WAIT, ACCEPT, CHECK, COOK}

//creating logic methods

    public void on() {
        if (state == STATES.OFF) {
            state  = STATES.WAIT;

        }

    }

    public void off() {
        cancel();
        state  = STATES.OFF;

    }
    public void coin(int amount) {
        if (state == STATES.WAIT || state == STATES.ACCEPT) {
            state  = STATES.ACCEPT;
            cash +=amount;

        }

    }
    public void choice(String menuNum) {
        if (state != STATES.OFF) {
            if (check(menuNum)) {
                cook(menuNum);
            } else {
                cancel();
            }
        }
    }

    private boolean check(String menuNum) {
        state = STATES.CHECK;
        return Integer.parseInt(prices.get(menuNum).split(",")[1]) <= cash;

    }

    public int cancel() {
        int sum = cash;
        if (state == STATES.ACCEPT || state == STATES.CHECK ) {
            cash = 0;
            state  = STATES.WAIT;
        } else {
            sum = -1;
        }
        return sum;
    }

    public void printMenu(Properties menu){

    }
    public void printState() {
        System.out.println("current state is " + state);
    }
    private void cook (String menuNum){
        state = STATES.COOK;
//        берем меню и пишем что готовим
// здесь код отображения момента приготовления
        finish(menuNum);

    }


    private void finish(String menuNum){

        state = STATES.WAIT;
        cash -= Integer.parseInt(prices.get(menuNum).split(",")[1]);
    }



}
