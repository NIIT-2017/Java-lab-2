import org.junit.Test;

import static org.junit.Assert.*;

public class AutomateTest {

    @Test
    public void getAutomatePositive() {
        assertNotNull(getInstanceAutomate());
    }

    @Test
    public void getAutomateNegative() {
        String[] menuNull = {};
        int[] pricesNull = {};
        String[] menu = {"Tea", "Coffee", "Chocolate"};
        int[] prices = {40, 35, 20};
        int[] pricesOneItem = {40};

        assertNull(Automate.getAutomate(menuNull, pricesNull));
        assertNull(Automate.getAutomate(menu, pricesNull));
        assertNull(Automate.getAutomate(menuNull, prices));
        assertNull(Automate.getAutomate(menu, pricesOneItem));
    }

    @Test
    public void onPositive() {
        Automate automate = getInstanceAutomate();
        assertTrue(automate.on());
    }

    @Test
    public void onNegativeWait() {
        Automate automate = getInstanceAutomate();
        automate.on();
        assertFalse(automate.on());
    }

    @Test
    public void onNegativeAccept() {
        Automate automate = getInstanceAutomate();
        automate.on();
        automate.coin(10);
        assertFalse(automate.on());
    }

    @Test
    public void offPositive() {
        Automate automate = getInstanceAutomate();
        automate.on();
        assertTrue(automate.off());
    }

    @Test
    public void offNegativeOff() {
        Automate automate = getInstanceAutomate();
        assertFalse(automate.off());
    }

    @Test
    public void offNegativeAccept() {
        Automate automate = getInstanceAutomate();
        automate.on();
        automate.coin(10);
        assertFalse(automate.off());
    }

    @Test
    public void coinPositiveWait() {
        Automate automate = getInstanceAutomate();
        automate.on();
        int money = 10;
        assertEquals(money, automate.coin(money), 0.00001);
    }

    @Test
    public void coinPositiveAccept() {
        Automate automate = getInstanceAutomate();
        automate.on();
        int money = 10;
        automate.coin(money);
        assertEquals(money + money, automate.coin(money), 0.00001);
    }

    @Test
    public void coinNegative() {
        Automate automate = getInstanceAutomate();
        assertEquals(-1, automate.coin(10), 0.00001);
    }

    @Test
    public void printMenuPositiveWait() {
        Automate automate = getInstanceAutomate();
        automate.on();
        assertNotNull(automate.printMenu());
    }

    @Test
    public void printMenuPositiveAccept() {
        Automate automate = getInstanceAutomate();
        automate.on();
        automate.coin(10);
        assertNotNull(automate.printMenu());
    }

    @Test
    public void printMenuNegative() {
        Automate automate = getInstanceAutomate();
        assertNull(automate.printMenu());
    }

    @Test
    public void printStatePositiveWait() {
        Automate automate = getInstanceAutomate();
        automate.on();
        assertNotNull(automate.printState());
    }

    @Test
    public void printStatePositiveAccept() {
        Automate automate = getInstanceAutomate();
        automate.on();
        automate.coin(10);
        assertNotNull(automate.printState());
    }

    @Test
    public void printStateNegative() {
        Automate automate = getInstanceAutomate();
        assertNull(automate.printState());
    }

    @Test
    public void choicePositiveNotMoney() {
        Automate automate = getInstanceAutomate();
        automate.on();
        automate.coin(40);
        assertEquals(0, automate.choice(1), 0.00001);
    }

    @Test
    public void choicePositiveMoney() {
        Automate automate = getInstanceAutomate();
        automate.on();
        automate.coin(50);
        assertEquals(10, automate.choice(1), 0.00001);
    }

    @Test
    public void choiceNegativeOff() {
        Automate automate = getInstanceAutomate();
        assertNull(automate.choice(1));
    }

    @Test
    public void choiceNegativeWait() {
        Automate automate = getInstanceAutomate();
        automate.on();
        assertNull(automate.choice(1));
    }

    @Test
    public void choiceNegativeAccept() {
        Automate automate = getInstanceAutomate();
        automate.on();
        automate.coin(10);
        assertEquals(-30, automate.choice(1), 0.0001);
    }

    @Test
    public void choiceNegative() {
        Automate automate = getInstanceAutomate();
        automate.on();
        automate.coin(10);
        assertNull(automate.choice(0));
        assertNull(automate.choice(-1));
        assertNull(automate.choice(Integer.MAX_VALUE));
    }

    @Test
    public void cancelPositiveCoin() {
        Automate automate = getInstanceAutomate();
        automate.on();
        int money = 10;
        automate.coin(money);
        assertEquals(money, automate.cancel(), 0.00001);
    }

    @Test
    public void cancelPositiveDoubleCoin() {
        Automate automate = getInstanceAutomate();
        automate.on();
        int money = 10;
        automate.coin(money);
        automate.coin(money);
        assertEquals(money + money, automate.cancel(), 0.00001);
    }

    @Test
    public void cancelPositiveAfterChoice() {
        Automate automate = getInstanceAutomate();
        automate.on();
        int money = 10;
        automate.coin(money);
        automate.choice(1);
        assertEquals(money, automate.cancel(), 0.00001);
    }

    @Test
    public void cancelNegativeOff() {
        Automate automate = getInstanceAutomate();
        assertEquals(-1, automate.cancel(), 0.00001);
    }

    @Test
    public void cancelNegativeWait() {
        Automate automate = getInstanceAutomate();
        automate.on();
        assertEquals(-1, automate.cancel(), 0.00001);
    }

    private Automate getInstanceAutomate(){
        String[] menu = {"Tea", "Coffee", "Chocolate"};
        int[] prices = {40, 35, 20};
        return Automate.getAutomate(menu, prices);
    }
}