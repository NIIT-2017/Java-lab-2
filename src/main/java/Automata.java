import java.util.ArrayList;
import java.lang.*;
enum STATES {OFF, WAIT, ACCEPT, CHECK, COOK}
public class Automata {
    private int cash;
    private ArrayList<String> menu = new ArrayList<String>();
    private ArrayList<Integer> prices = new ArrayList<Integer>();
    private STATES state;
    int change;

    Automata(){
        this.state=STATES.OFF;
        this.cash = 0;
        this.change = 0;

    }

    public STATES on(){
        if(state == STATES.OFF)
            state = STATES.WAIT;
        return state;
    }
    public STATES off(){
        if(state == STATES.WAIT)
            state = STATES.OFF;
        return  state;
    }
    public int coin(int c){
        if (state == STATES.WAIT)
                 cash+=c;
        return cash;
    }
    public ArrayList [] getMenu(){
        ArrayList buf [] = {menu, prices};
       if (state == STATES.WAIT){
            menu.add("1.Cappuccino");
            menu.add("2.Americana");
            menu.add("3.Latte");
            menu.add("4.Tea");
            prices.add(100);
            prices.add(80);
            prices.add(150);
            prices.add(50);
            }
       return buf;
    }

    public STATES getState(){
        return state;

    }

    public String choice(int num){
        if (state == STATES.WAIT){
            state = STATES.CHECK;
            int sum = prices.get(num-1);
            prices.clear();
            prices.add(sum);
        }
        return menu.get(num-1);
    }
    public STATES check(){
        if (state == STATES.CHECK) {
            if (prices.get(0) <= cash){
                state = STATES.ACCEPT;
            }else{
                cancel();
            }
        }
        return state;
    }
    public int cancel(){
        state = STATES.WAIT;
        change = cash;
        cash=0;
        return change;
    }
    public STATES cook(){
        if(state == STATES.ACCEPT)
            state = STATES.COOK;
        return state;
    }
    public String finish(){
        if(state == STATES.COOK) {
            state = STATES.WAIT;
            if (cash>prices.get(0)) {
                change = cash - prices.get(0);
            }else {
                change = 0;
            }
        }
        return "Your change is: " + change+ " coins.\n" +"Your drink is waiting for you!";
    }
}
