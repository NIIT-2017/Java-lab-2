public class AutomataDemo {

public static void main(String[] args) {

    Automata  coffeeMachine = new Automata();

    coffeeMachine.on();
    coffeeMachine.coin(5);
    coffeeMachine.coin(10);
    coffeeMachine.coin(100);
    coffeeMachine.choice(5);
    coffeeMachine.off();
}
}

