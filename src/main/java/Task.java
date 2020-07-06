import java.util.ArrayList;

public class Task {
    public static void main(String[] args){
        Automata coffeeMachine = new Automata();
        coffeeMachine.on();
        System.out.println("State is "+ coffeeMachine.getState());
        ArrayList menu [] = coffeeMachine.getMenu();
        for (int i =0; i < menu[0].size(); i++) {
            System.out.println((i+1) + ". " + menu[0].get(i) + " : " + menu[1].get(i) + " rub.");
        }
        coffeeMachine.coin(20);
        coffeeMachine.coin(100);
        System.out.println("Select drink");
        int position = 4;
        coffeeMachine.choice(position);
        System.out.println("Take your " + menu[0].get(position-1));
        System.out.println("Take your odd money!");
        System.out.println("State is "+ coffeeMachine.getState());
        coffeeMachine.off();
        System.out.println("State is "+ coffeeMachine.getState());
    }
}
