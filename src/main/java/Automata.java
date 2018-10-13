import java.util.HashMap;

public class Automata {
    static final HashMap<String, Integer> menu = new HashMap<String, Integer>();
    static states current = states.OFF;
    private static int cashInTheMachine = 0;
    private static int currentPayment = 0;
    private static int change = 0;
    private static String currentChoice = "";

    public enum states {
        OFF,
        WAITING_FOR_CHOICE,
        WAITING_FOR_CASH,
        CHECKING_CASH,
        COOKING,
        CANCELING
    }

    static void generateMenu() {
        menu.put("Russiano", 146);
        menu.put("Cappuccino", 100);
        menu.put("Latte", 100);
        menu.put("Espresso", 80);
        menu.put("Mocha", 120);
        menu.put("Macchiato", 120);
        menu.put("Tea", 120);
    }

    //    Returns the current state of the machine
    public String getState() {
        return current.toString();
    }

    //    Returns the menu of available drinks
    public String getMenu() {
        if (current.equals(states.OFF)) {
            return "Machine is powered off";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("____MENU____\n");
            for (HashMap.Entry<String, Integer> entry : menu.entrySet()) {
                sb.append(entry.getKey() + " " + entry.getValue() + "\n");
            }
            return sb.toString();
        }
    }

    //    Sets power of machine to OFF
    public void powerOff() {
        current = states.OFF;
    }

    //    Sets power of machine to ON if it's turned OFF
    public void powerOn() {
        if (current.equals(states.OFF)) {
            generateMenu();
            current = states.WAITING_FOR_CHOICE;
        }
    }

    //    Sets customers drink of choice, gets the name of a chosen drink
    public void setChoice(String choice) {
        currentChoice = choice;
        current = states.WAITING_FOR_CASH;
    }

    //    Sets amount of cash given by customer for a chosen drink, gets coins from customer
    public String setCurrentPayment(int coins) {
        currentPayment = coins;
        try {
            return checkCoins();
        } catch (NullPointerException e) {
            return "Payment first";
        }
    }

    //    Cooking
    String cook(boolean withChange) {
        current = states.COOKING;
        String result = "Thanks, your " + currentChoice + "is on the way!";
        if (withChange) {
            result += "\nHere is your change " + (currentPayment - menu.get(currentChoice)) + ".";
        }
        current = states.WAITING_FOR_CASH;
        return result;
    }

    //    checks if currentPayment is enough for currentChoice
    String checkCoins() {
        /*payment = price, starts cooking*/
        current = states.CHECKING_CASH;
        if (currentPayment == menu.get(currentChoice)) {
            cashInTheMachine += menu.get(currentChoice);
            return cook(false);
        }
//        payment < price, cancels the order
        else if (currentPayment < menu.get(currentChoice)) {
            return cancel(true);
        }
//        payment > price
        else {
            current = states.COOKING;
            cashInTheMachine += menu.get(currentChoice);
            return cook(true);
        }
    }

    //    Cancels the order, returns currentChoice to null, returns currentPayment to customer if there is
    public String cancel(boolean paid) {
        current = states.CANCELING;
        String result = "Bye!";
        if (paid) {
            result = "Sorry, your " + currentChoice + " cost a bit more. Here is your " + currentPayment;
        }
        currentChoice = "";
        current = states.WAITING_FOR_CASH;
        return result;
    }
}
