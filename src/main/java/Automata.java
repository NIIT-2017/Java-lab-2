public class Automata {
    public enum STATES {OFF,WAIT,ACCEPT,CHECK,COOK}
    private STATES state;
    private int cash;
    private int choice;
    private boolean checked;

    public Automata() {
        state=STATES.OFF;
        cash=0;
        choice=0;
        checked=false;
    }

    public int getChoice() {
        return choice;
    }

    public boolean getChecked() {
        return checked;
    }


    private static String[] drinkNames = {"Зеленый чай", "Чёрный кофе", "Капучино", "Латте", "Горячий шоколад"};
    private static int[] drinkCosts = {20, 40, 45, 55, 50};

    public void on(){
        if (state==STATES.OFF){
            state=STATES.WAIT;
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
            choice = 0;
            checked = false;
        }
        if (state==STATES.CHECK){
            state=STATES.WAIT;
            cash = 0;
            choice = 0;
            checked = false;
        }
        cash=0;

    }

    public void choice(int i){
        if (state==STATES.ACCEPT){
            if (i>=0 && i<=4){
                choice=i;
                state=STATES.CHECK;
            }

        }

    }

    public void check(){
        if (cash >= drinkCosts[choice])
            checked = true;

    }

    public void cook(){
        if (state==STATES.CHECK && checked) {
            state = STATES.COOK;
            cash -= drinkCosts[choice];
            checked = false;
        }
    }

    public void finish(){
        if (state==STATES.COOK){
            state=STATES.WAIT;
            cash=0;
            choice=0;
        }
    }
}
