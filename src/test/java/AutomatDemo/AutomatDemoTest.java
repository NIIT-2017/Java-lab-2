package AutomatDemo;

import org.junit.Assert;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class AutomatDemoTest {

    AutomatDemo automat;

    @org.junit.Before
    public void setUp() throws Exception {
        automat = new AutomatDemo();
        automat.on();
    }

    @org.junit.Test
    public void consoleInputCoin() throws Exception {
        InputStream in = new ByteArrayInputStream(String.valueOf(10).getBytes());
        automat.consoleInputCoin(new InputIntReader(in));
        int rez = automat.getCash();
        Assert.assertEquals(10, rez);
    }

    @org.junit.Test
    public void textFieldInputCoin() throws Exception {
        automat.textFieldInputCoin(5);
        int rez = automat.getCash();
        Assert.assertEquals(5, rez);
    }

    @org.junit.Test
    public void choice() throws Exception {
        automat.setState(States.ACCEPT);
        InputStream in = new ByteArrayInputStream(String.valueOf(1).getBytes());
        automat.choice(new InputIntReader(in));
        int rez = automat.getCurrentDrinkPrice();
        Assert.assertEquals(25, rez);
    }
}