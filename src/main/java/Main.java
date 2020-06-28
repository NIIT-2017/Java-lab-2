public class Main {
    public static void main(String[] args) {
        AutomataDemo auto = new AutomataDemo();
        //This variable provides a random number of coin, which buyer have threw into the Automata
        int buyersCoin = (int) (Math.random() *550);
        //This variable provides a random number of drink, which buyer have chosen
        int randomI = (int) (Math.random() * 10);
        auto.setState(AutomataDemo.STATES.OFF);
        System.out.println(auto.on());
        auto.readMenu();
        auto.coin(buyersCoin);
        auto.getDrinks();
        String randomDrink = auto.getDrinks().get(randomI);
        auto.choice(randomDrink);
        System.out.println(auto.getState());
        auto.check();
        System.out.println(auto.getState());
        auto.cook();
        System.out.println(auto.getState());
    }
}
