import java.util.HashMap;

public class Automata {

    enum STATES {
        OFF, WAIT, ACCEPT, CHECK, COOK
    }

    private int cash;
    private STATES state;

    static HashMap <String, String> prices = new HashMap<String, String>();
    static {
        prices.put("1", "Espresso,30");
        prices.put("2", "Capuccino,40");
        prices.put("3", "Latte,50");
        prices.put("4", "Americano,30");
        prices.put("5", "Moccacino,40");
    }

    public Automata() {
        state = STATES.OFF;
        this.cash = 0;
    }

    public STATES getState() {
        return state;
    }

    public void setState(STATES state) {
        this.state = state;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    //Switching on the automata
    public void on() {
        if (state == STATES.OFF) {
            state  = STATES.WAIT;
            System.out.println("You turned on the coffee machine!");
        }
        else {
            System.out.println("The machine is already on!");
        }
    }

    //Switching off the automata
    public void off() {
        if (state  == STATES.WAIT) {
            System.out.println("You turned off the coffee machine!");
            state = STATES.OFF;
        }
        else {
            System.out.print("Automat is already off!\n");
        }
    }

    //Depositing money by user
    public void coin(int amount) {
        if (state == STATES.WAIT || state == STATES.ACCEPT) {
            state  = STATES.ACCEPT;
            cash +=amount;
        }
    }

    //Choice of drink
    public void choice(String menuNum) {
        if (state != STATES.OFF) {
            if (check(menuNum)) {
                cook(menuNum);
            } else {
                cancel();
            }
        }
    }

    //Choice of drink
    private boolean check(String menuNum) {
        state = STATES.CHECK;
        return Integer.parseInt(prices.get(menuNum).split(",")[1]) <= cash;
    }
    
    //Print the current state for the user
    public void printState() {
        System.out.println("Current state is " + state);
    }

    //print keys and values(amazing lambda)
    public void printMenu(){
        prices.forEach((k,v) -> System.out.println(" "+k+" "+v));
    }

    //Imitation of the process of cooking
    private void cook (String menuNum){
        state = STATES.COOK;
        finish(menuNum);
    }

    //Cancel of the session
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

    //End of service 
     private void finish(String menuNum){
        state = STATES.WAIT;
        cash -= Integer.parseInt(prices.get(menuNum).split(",")[1]);
    }
}
