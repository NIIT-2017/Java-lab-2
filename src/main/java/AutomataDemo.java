public class AutomataDemo extends Automata {
    public static void main(final String[] args) {
        Automata test = new Automata();

        System.out.println(test.getState());
        test.powerOn();
        System.out.println(test.getState());
        System.out.println(test.getMenu());
        test.setChoice("Espresso");
        System.out.println(test.getState());
        System.out.println(test.setCurrentPayment(85));
        System.out.println(test.getState());
        test.powerOff();


        System.out.println(test.getState());
        test.powerOn();
        System.out.println(test.getState());
        System.out.println(test.getMenu());
        test.setChoice("Russiano");
        System.out.println(test.getState());
        System.out.println(test.setCurrentPayment(85));
        System.out.println(test.getState());
        test.powerOff();

        System.out.println(test.getState());
        test.powerOn();
        System.out.println(test.getState());
        System.out.println(test.getMenu());
        System.out.println(test.setCurrentPayment(85));
        System.out.println(test.getState());
        test.setChoice("Russiano");
        System.out.println(test.getState());
        test.powerOff();
    }
}
