public class Automata {
    public enum STATES {OFF,WAIT,ACCEPT,CHECK,COOK}
    private STATES state;
    private int cash;
    private int drink;
    private boolean checked;
    public Automata() {
            state=STATES.OFF;
            cash=0;
            drink=0;
            checked=false;
    }
    public int getChoice() {
        return drink;
    }
    public boolean getChecked() {
            return checked;
    }
    private static String[] drinkNames = {"Americano", "Espresso", "Capuccino", "Latte", "Moccacino","Tea"};
    private static int[] drinkPrice = {30, 40, 45, 45, 50, 25};

    public void on() {
        if (state == STATES.OFF) {
            state = STATES.WAIT;
        }
    }
    public STATES getState(){
            return state;
    }
    public void off(){
        if (state==STATES.WAIT){
            state=STATES.OFF;
        }
    }

    public void coin(int userCoin){
            if (state==STATES.ACCEPT){
                cash+=userCoin;
            }
            if (state==STATES.CHECK){
                cash+=userCoin;
            }
            if (state==STATES.WAIT){
                state=STATES.ACCEPT;
                cash+=userCoin;
            }
    }

    public int getCash(){
            return cash;
    }
    public void cancel(){
        if (state==STATES.ACCEPT){
                state=STATES.WAIT;
                cash = 0;
                drink = 0;
                checked = false;
        }
        if (state==STATES.CHECK){
                state=STATES.WAIT;
                cash = 0;
                drink = 0;
                checked = false;
        }
        cash=0;
    }
    public void choice(int drinkNumber){
        if (state==STATES.ACCEPT){
            if (drinkNumber>=0 && drinkNumber<=5){
                drink=drinkNumber;
                state=STATES.CHECK;
            }
        }
    }
    public void check(){
        if (cash >= drinkPrice[drink])
            checked = true;
    }
    public void cook(){
        if (state==STATES.CHECK && checked) {
            state = STATES.COOK;
            cash -= drinkPrice[drink];
            checked = false;
            }
    }
    public void finish(){
        if (state==STATES.COOK){
            state=STATES.WAIT;
            cash=0;
            drink=0;
        }
    }
}

