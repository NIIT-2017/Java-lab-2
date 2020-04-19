import java.util.ArrayList;

public class Automata {
    enum STATES {OFF,WAIT,ACCEPT,CHECK,COOK};
    private STATES state;
    private double cash;
    private double change;
    private int menuNumber;
    private ArrayList<String> menu;
    private ArrayList<Double> prices;
    private ButtonsInDisplayOut bdio;

//constructor
    Automata(ArrayList<String> menu_in, ArrayList<Double> prices_in) {
        this.cash = 0.0;
        this.state = STATES.OFF;
        this.menu = menu_in;
        this.prices = prices_in;
    }

//getters
    ArrayList<String> getMenu(){
        return this.menu;
    }
    STATES getState(){
        return this.state;
    }
    double getCash() { return this.cash; }
    double getChange(){ return this.change; }

//setters
    void setState(STATES state){ this.state = state; }
    void setCash(double cash){ this.cash = cash; }
    void setChange(double change){ this.change = change; }
    void setMenuNumber(int menuNumber){ this.menuNumber = menuNumber;}
    void setBdio (ButtonsInDisplayOut bdio){ this.bdio = bdio;}

//methods
    void on(){
        if (this.state == STATES.OFF) {
            this.state = STATES.WAIT;
            this.change = 0.0;
            this.cash = 0.0;
            this.bdio.displayOut("Automata is ready to work", ButtonsInDisplayOut.VARIANTS.BREAK);
            this.bdio.displayOut("CASH: "+this.cash+" "+"CHANGE: "+this.change,ButtonsInDisplayOut.VARIANTS.BREAK);
        }
    }

    void off(){
        if (this.state == STATES.WAIT){
            this.state = STATES.OFF;
            this.bdio.displayOut("Automata is off",ButtonsInDisplayOut.VARIANTS.BREAK);
        }
    }

    void coin(double coin_in){
        if (this.state == STATES.WAIT && coin_in > 0){
            this.state = STATES.ACCEPT;
            this.change = 0.0;
            this.cash += coin_in;
            this.bdio.displayOut("Coin accepted!",ButtonsInDisplayOut.VARIANTS.BREAK);
            this.bdio.displayOut("CASH: "+this.cash+" "+"CHANGE: "+this.change,ButtonsInDisplayOut.VARIANTS.BREAK);
        }
        else if (this.state == STATES.ACCEPT && coin_in > 0){
            this.cash += coin_in;
            this.bdio.displayOut("Coin accepted!",ButtonsInDisplayOut.VARIANTS.BREAK);
            this.bdio.displayOut("CASH: "+this.cash+" "+"CHANGE: "+this.change,ButtonsInDisplayOut.VARIANTS.BREAK);
        }
        else{
            this.change = coin_in; //return the coin if the state isn't match
            this.bdio.displayOut("Automata isn't ready to accept a coin",ButtonsInDisplayOut.VARIANTS.BREAK);
            this.bdio.displayOut("CASH: "+this.cash+" "+"CHANGE: "+this.change,ButtonsInDisplayOut.VARIANTS.BREAK);
        }
    }

    void cancel(){
        if (this.state == STATES.ACCEPT || this.state == STATES.CHECK){
            this.state = STATES.WAIT;
            this.change = this.cash;
            this.cash = 0.0;
            this.bdio.displayOut("Operation has been canceled",ButtonsInDisplayOut.VARIANTS.BREAK);
            this.bdio.displayOut("Automata is ready to work",ButtonsInDisplayOut.VARIANTS.BREAK);
            this.bdio.displayOut("CASH: "+this.cash+" "+"CHANGE: "+this.change,ButtonsInDisplayOut.VARIANTS.BREAK);
        }
    }

    void choice(int menuNumber_in){
        if (this.state == STATES.ACCEPT) {
            this.state = STATES.CHECK;
            this.menuNumber = menuNumber_in;
            this.check();
        }
    }

    private void check(){
        //checking for the state isn't needed here
        // because the check() starts from the choice() with the state "CHECK" only
        if (this.cash < prices.get(this.menuNumber))
            this.cancel();
        else
            this.cook();
    }

    private void cook(){
        //checking for the state isn't needed here
        // because the cook() starts from the check() with the state "CHECK" only
        this.state = STATES.COOK;
        this.bdio.displayOut("Cooking...",ButtonsInDisplayOut.VARIANTS.BREAK);
        for (int i = 0; i <= 10; i++) //animation of cooking process
        {
            this.bdio.displayOut("#",ButtonsInDisplayOut.VARIANTS.NOBREAK);
            try {Thread.sleep(500);}
            catch(InterruptedException ex) {}
        }
        this.bdio.displayOut("",ButtonsInDisplayOut.VARIANTS.BREAK);
        this.bdio.displayOut("Take the drink!",ButtonsInDisplayOut.VARIANTS.BREAK);
        this.finish();
    }

    void finish(){
        if (this.state == STATES.COOK) {
            this.state = STATES.WAIT;
            this.change = this.cash - prices.get(this.menuNumber);
            this.cash = 0.0;
            this.bdio.displayOut("Automata is ready to work",ButtonsInDisplayOut.VARIANTS.BREAK);
            this.bdio.displayOut("CASH: "+this.cash+" "+"CHANGE: "+this.change,ButtonsInDisplayOut.VARIANTS.BREAK);
        }
    }
}


