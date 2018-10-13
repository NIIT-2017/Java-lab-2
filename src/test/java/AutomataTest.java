import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AutomataTest extends Automata {
    final static String testMenu = "____MENU____\n" +
            "Russiano 146\n" +
            "Tea 120\n" +
            "Espresso 80\n" +
            "Mocha 120\n" +
            "Latte 100\n" +
            "Macchiato 120\n" +
            "Cappuccino 100\n";
    //static Automata test = new Automata();

    @Test
    /*
     * Checks the menu of powered off machine
     */
    public void generateMenuTest001() {
        Automata test = new Automata();
        assertEquals("Unexpected menu. The received one is: " + test.getMenu(), "Machine is powered off", test.getMenu());
    }

    @Test
    /*
     * Checks the menu of running machine
     */
    public void generateMenuTest002() {
        Automata test = new Automata();
        test.powerOn();
        assertEquals("Unexpected menu. The received one is: " + test.getMenu(), testMenu, test.getMenu());
        test.powerOff();
    }

    @Test
    /*
     * Checks the default state
     * of machine after it is created
     */
    public void defaultStateTest001() {
        Automata test = new Automata();
        assertEquals("Default state of machine is not OFF, actual default state is: " + test.getState(),
                "OFF", test.getState());
        test.powerOff();
    }

    @Test
    /*
     * Checks powering on and state after power on
     */
    public void powerOnTest001() {
        Automata test = new Automata();
        test.powerOn();
        assertEquals("Unexpected state after power on, actual state is: " + test.getState(),
                "WAITING_FOR_CHOICE", test.getState());
        test.powerOff();
    }

    @Test
    /*
     * Checks powering off and state after power off
     */
    public void powerOffTest001() {
        Automata test = new Automata();
        test.powerOff();
        assertEquals("Unexpected state after power off, actual state is: " + test.getState(),
                "OFF", test.getState());
    }

    @Test
    /*
     * Checks state after setting a choice
     */
    public void setChoiceTest001() {
        Automata test = new Automata();
        test.powerOn();
        test.setChoice("Russiano");
        assertEquals("Unexpected state after choice setting, actual state is: " + test.getState(),
                "WAITING_FOR_CASH", test.getState());
        test.powerOff();
        //TODO: tests for out-of-menu choices
    }

    @Test
    /*
     * Checks state after payment, that is enough to pay for the choice
     */
    public void setCurrentPaymentTest001() {
        Automata test = new Automata();
        test.powerOn();
        test.setCurrentPayment(146);
        assertEquals("Unexpected state after choice setting, actual state is: " + test.getState(),
                "WAITING_FOR_CASH", test.getState());
        test.powerOff();
    }

    @Test
    /*
     * Checks state after payment, that is not enough to pay for the choice
     */
    public void setCurrentPaymentTest002() {
        Automata test = new Automata();
        test.powerOn();
        test.setCurrentPayment(1);
        assertEquals("Unexpected state after choice setting, actual state is: " + test.getState(),
                "WAITING_FOR_CASH", test.getState());
        test.powerOff();
    }

    @Test
    /*
     * Checks cooking positive case
     */
    public void cookTest001() {
        Automata test = new Automata();
        test.powerOn();
        test.setCurrentPayment(150);
        test.setChoice("Cappuccino");
        test.cook(false);
        assertEquals("Unexpected state after cooking, actual state is: " + test.getState(),
                "WAITING_FOR_CASH", test.getState());
        test.powerOff();
    }

    @Test
    public void checkCoinsTest001() {
        Automata test = new Automata();
        test.powerOn();
        test.setCurrentPayment(150);
        test.setChoice("Cappuccino");
        test.checkCoins();
        test.powerOff();
    }

    @Test
    public void cancelTest001() {
        Automata test = new Automata();
        test.powerOn();
        test.setCurrentPayment(150);
        test.cancel(false);
        assertEquals("Unexpected state after canceling, actual state is: " + test.getState(),
                "WAITING_FOR_CASH", test.getState());
        test.powerOff();
    }
}
