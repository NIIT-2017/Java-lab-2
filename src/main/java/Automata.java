public class Automata {
    private enum My_States {ON, WAIT, ACCEPT, CHECK, COOK, OFF};
    private My_States States;
    private int cash;
    private String[] Menuout = new String[]{"Black Tea", "Latte", "Cappuccino", "Glace", "Green Tea",
            "Hot chocolate", "Espresso"};
    private int[] prices = new int[]{30, 45, 50, 60, 30, 40, 35};
    private long Timer = 0;

    public String getMenu() {
        String Out = "<---------MENU---------->\n";
        for (int i = 0; i < Menuout.length; i++) {
            int u = i + 1;
            Out = Out + "\\ " + u + " | " + Menuout[i] + "  | " + prices[i] + " /" + "\n";
        }
        Out = Out + "___________End___________\n";
        return Out;
    }

    public String On() {
        if (States != My_States.COOK) {
            States = My_States.WAIT;
        }
        return "CoffeeMaker on";
    }

    private void Cook(int choice) {
        States = States.COOK;
        Timer = System.currentTimeMillis();
    }

    public String Finish() {
        if (States == States.OFF) {
            return "CoffeeMaker off";
        }
        if ((System.currentTimeMillis() - Timer) > 5000) {
            States = States.WAIT;
            return "Your drink is ready, bro";
        } else {
            return "Your drink is cooking, wait, bro";
        }
    }

    public boolean Check(int choice) {
        if (prices[choice - 1] > cash) {
            return false;
        } else {
            return true;
        }
    }

    public String getState() { // - отображение текущего состояния для пользователя;
        return String.valueOf(States);
    }

    public String Choice(int choice) {
        if (States == States.OFF) {
            return "CoffeeMaker off";
        }
        if (choice <= 0 || choice > Menuout.length) {
            return "This drink was not find, select another.";
        } else {
            States = States.CHECK;
            if (!Check(choice)) {
                States = States.ACCEPT;
                return "Not enough cash, man, add yet " + (prices[choice - 1] - cash);
            } else {
                cash = cash - prices[choice - 1];
                Cook(choice);
                return "I understood and make for you " + Menuout[choice - 1];
            }
        }
    }

    public String off() {
        if (States == States.WAIT && cash == 0){
            States = States.OFF;
            return "CoffeeMaker off";
        }
        else if (States == States.WAIT && cash > 0){
            return "Before off, give your cash or choose drink, man.";
        }
        else{
            return "Wait, I am busy.";
        }
    }

    public String coin(int money) {
        if (States == States.OFF){
            return "CoffeeMaker off";
        }
        States = States.ACCEPT;
        if (money <= 0){
            return "What is this? Try again.";
        }
        cash = cash + money;
        cancel();
        return "I have "+cash+" coins.";
    }

    public void cancel(){
        States = States.WAIT;
    }

    public int getCoin(){
        return cash;
    }

    public String moneyBack(){
        if (States == States.OFF){
            return "CoffeeMaker off";
        }
        if (cash==0){
            return "I don't have your coins";
        }
        else {
            String text = "Give your payback " + cash;
            cash = 0;
            return text;
        }
    }
}