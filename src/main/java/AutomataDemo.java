public class AutomataDemo{
    public static void main(String[] args){
        System.out.println("The automata is currently " + " " + Automata.current);
        Automata.powerOn();
        System.out.println("The automata is currently " + " " + Automata.current);
        Automata.getMenu();
        System.out.println(Automata.getMenu());
        Automata.setChoice("Latte");
        Automata.checkCoins(90);
        System.out.println(Automata.setCurrentPayment(90));
        Automata.powerOff();
    }
}