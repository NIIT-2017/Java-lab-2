import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class AutomatTest {

    Automat automatTest = new Automat();

    @org.junit.Test
    public void checkGetState() {
        assertEquals(Automat.STATES.OFF,automatTest.getState());
    }

    @org.junit.Test
    public void checkOn() {
        assertEquals(Automat.STATES.WAIT,automatTest.on());
    }

    @org.junit.Test
    public void checkGetCash() {
        automatTest.on(); // for STATES.WAIT
        automatTest.coin(15);
        automatTest.coin(35);
        assertEquals(50,automatTest.getCash());
    }

    @org.junit.Test
    public void checkGetMenu() {
        automatTest.on(); // for STATES.WAIT
        ArrayList<String> canBeMenu = new ArrayList<String>();
        canBeMenu.add("  Expresso   30 rub.");
        canBeMenu.add(" Americano   35 rub.");
        canBeMenu.add("Cappuccino   45 rub.");
        canBeMenu.add(" Mokachino   40 rub.");
        canBeMenu.add("   Dalgona   45 rub.");

        assertEquals(canBeMenu,automatTest.getMenu());
    }

    @org.junit.Test
    public void checkCoin() {
        automatTest.on(); // for STATES.WAIT
        assertEquals(20,automatTest.coin(20));
    }

    @org.junit.Test
    public void checkChoiceTrue() {
        automatTest.on(); // for STATES.WAIT
        automatTest.coin(40); //for STATES.ACCEPT and cash=40
        //coffee№1  has price=35, go to cook(), finish() -> return true;
        assertEquals(true,automatTest.choice(1));
    }

    @org.junit.Test
    public void checkChoiceFalse() {
        automatTest.on(); // for STATES.WAIT
        automatTest.coin(15); //for STATES.ACCEPT and cash=15
        //coffee№4  has price=45, cash<price, go to cancel() -> return false;
        assertEquals(false,automatTest.choice(4));
    }

    @org.junit.Test
   public void checkCancelTrue() {
        automatTest.on(); // for STATES.WAIT
        automatTest.coin(15); //for STATES.ACCEPT and cash=15

        assertEquals(15,automatTest.cancel(true));
    }

    @org.junit.Test
    public void checkCancelFalse() {
        automatTest.on(); // for STATES.WAIT
        automatTest.coin(15); //for STATES.ACCEPT and cash=15
        //for surrender

        assertEquals(0,automatTest.cancel(false));
    }

    @org.junit.Test
    public void checkProtectedMethods() {
        automatTest.on(); // for STATES.WAIT
        automatTest.coin(50); //STATES.ACCEPT and cash=50
        automatTest.choice(4); //STATES.CHECK
        //in choice() -> check()
        //coffee№4  has price=45, cash=50>price=45 check():STATES.CHECK, cash=50-45=5 return true;
        // go to cook(): STATES.COOK, -> go to finish()
        //in finish(): cash=5  - surrender -> cash=0 and STATES.WAIT

        assertEquals(0,automatTest.getCash());
    }

    @org.junit.Test
    public void checkOff() {
        automatTest.on(); // for STATES.WAIT
        assertEquals(Automat.STATES.OFF,automatTest.off());
    }

}