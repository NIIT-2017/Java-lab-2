import java.util.ArrayList;

public class coffeAutomata {
    public static void main(String[] args){
        Automata coffeeMachine = new Automata();
        coffeeMachine.on();
        System.out.println("State is "+ coffeeMachine.getState());
        for(String line:coffeeMachine.getMenu())
            System.out.println(line);
        coffeeMachine.coin(200);
        int drinkChoice = 3;
        coffeeMachine.choice(drinkChoice);
        System.out.println("You have chosen " + coffeeMachine.menu.get(drinkChoice));
        System.out.println("Take your " + coffeeMachine.menu.get(drinkChoice));
        System.out.println("Do you have any change left: " + coffeeMachine.getAnInt() + " coins");
        System.out.println("State is "+ coffeeMachine.getState());
        System.out.println("Thank you for your purchase! ");
        coffeeMachine.cancel();
        coffeeMachine.off();
        System.out.println("State is "+ coffeeMachine.getState());
    }
}
