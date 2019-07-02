import java.util.ArrayList;


public class Automata {
    enum State {OFF, WAIT, ACCEPT, CHECK, COOK}

    private State state;
    private float cash;
    private float price;

    public Automata() {
        this.cash = 0;
        this.state = State.OFF;
    }

    public void on() {
        if (state == State.OFF) {
            state = State.WAIT;
        }
    }

    public void off() {
        if (state == State.WAIT) {
            state = State.OFF;
        }
    }

    public State getState() {
        return state;
    }

    public ArrayList[] getMenu() {
        ArrayList menuDrink = new ArrayList<String>();
        ArrayList menuPrice = new ArrayList<Float>();
        menuDrink.add("tea");
        menuDrink.add("coffee");
        menuDrink.add("cocoa");
        menuDrink.add("milk");
        menuPrice.add(40);
        menuPrice.add(50);
        menuPrice.add(60);
        menuPrice.add(70);
        ArrayList[] Menu = {menuDrink, menuPrice};
        return Menu;
    }

    public void coin(float money) {
        if (state == State.WAIT || state == State.ACCEPT) {
            state = State.ACCEPT;
            cash += money;
        }
    }

    public float getCash() {
        return cash;
    }

    private boolean check(float price) {
        return (state == State.CHECK) && (cash >= price);
    }

    public float cancel() {
        float change = 0;
        if (state == State.CHECK || state == State.ACCEPT) {
            state = State.WAIT;
            change = cash;
            cash = 0;
        }
        return change;
    }

    private void cook(int typeDrink) {
        if (state == State.CHECK) {
            state = State.COOK;
        }
    }

    private void finish() {
        if (state == State.COOK) {
            state = State.WAIT;
            System.out.println("the drink is ready");
        }
    }


    public float choice(int typeDrink) {
        float change = 0;

        try {
            if (state == State.ACCEPT) {
                state = State.CHECK;
                ArrayList[] menu = getMenu();
                float price = (Float) menu[1].get(typeDrink);
                if (check(price)) {
                    change = cash -= price;
                    cash = 0;
                    cook(typeDrink);
                    finish();
                } else {
                    change = cancel();
                }
            }
        } catch (Exception E1) {
            System.out.println("This drink isn`t in the menu" + E1);
            change = cancel();
            System.out.println("Take the change:" + change);
        }
        return change;
    }
}



