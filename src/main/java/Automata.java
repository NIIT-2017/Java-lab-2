import java.util.HashMap;
public class Automata {

    enum STATES {
        OFF, WAIT, ACCEPT, CHECK, COOK
    }

    private int cash;
    private STATES state;

    static HashMap <String, String> prices = new HashMap<String, String>();
    static {
        prices.put("1", "Coffee with milk,30");
        prices.put("2", "Green Tea,40");
        prices.put("3", "Cappuchino,50");
        prices.put("4", "Latthe,30");
        prices.put("5", "Orange Juice,40");
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

    public void on() {
        if (state == STATES.OFF) {
            state  = STATES.WAIT;
        }
    }

    public void off() {
        state = STATES.OFF;
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

    public void printState() {
        System.out.println("Current state is " + state);
    }

    public void printMenu(){
        prices.forEach((k,v) -> System.out.println(" "+k+" "+v));
    }

    private void cook (String menuNum){
        state = STATES.COOK;
        finish(menuNum);
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

    private void finish(String menuNum){
        state = STATES.WAIT;
        cash -= Integer.parseInt(prices.get(menuNum).split(",")[1]);
    }
}
