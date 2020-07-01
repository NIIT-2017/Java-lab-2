import java.util.ArrayList;

enum States {
    OFF, WAIT, ACCEPT, CHECK, COOK
}

public class Automata {

    ArrayList<String> drinks = new ArrayList<String>();
    ArrayList<Integer> prices = new ArrayList<Integer>();
    private int cash;
    private int oddMoney;
    private States state;

    Automata(){
        cash = 0;
        this.state = States.OFF;
    }

    public void on(){
        if (state == States.OFF ){
            state = States.WAIT;
        }
    }

    public void off(){
        if (state == States.WAIT ) {
            state = States.OFF;
        }
    }

    public ArrayList [] getMenu(){
        ArrayList menu [] = {drinks, prices};
        drinks.add("Espresso");
        drinks.add("Americano");
        drinks.add("Cappuccino");
        drinks.add("Latte");
        drinks.add("Tea");
        prices.add(20);
        prices.add(30);
        prices.add(40);
        prices.add(45);
        prices.add(15);
        return menu;
    }

    public void coin(int coins){
        if (state == States.WAIT || state == States.ACCEPT){
            state = States.ACCEPT;
            cash += coins;
        }
    }

    public States getState(){
        return state;
    }

    public void choice(int position){
        if (state == States.ACCEPT && check(position)){
            cash -= prices.get(position - 1);
            oddMoney = cash;
            cash = 0;
            cook();
        } else {
            cancel();
        }
    }

    public boolean check(int position){ // public - specifically for testing
        if (cash >= prices.get(position - 1)){
            state = States.CHECK;
            return true;
        }
        return false;
    }

    private int cancel(){
        state = States.WAIT;
        oddMoney = cash;
        cash = 0;
        return oddMoney;
    }

    private void cook(){
        state = States.COOK;
        finish();
    }

    public int getOddMoney(){
        return oddMoney;
    }

    private void finish(){
        if (state == States.COOK){
            state = States.WAIT;
            getOddMoney();
        }
    }
}