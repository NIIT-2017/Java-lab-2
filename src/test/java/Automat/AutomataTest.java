package Automat;

import java.util.ArrayList;

import static Automat.STATES.*;
import static org.junit.Assert.*;


public
class AutomataTest {

    @org.junit.Test
    public
    void on () {
        Automata test = new Automata("drinks.txt");
        test.on();
        assertEquals(WAIT, test.getState());
    }

    @org.junit.Test
    public
    void off () {
        Automata test = new Automata("drinks.txt");
        test.off();
        assertEquals(OFF, test.getState());
    }

    @org.junit.Test
    public
    void setCash () {
        Automata test = new Automata("drinks.txt");
        test.setCash(30);
        assertEquals(30, test.getCash());
    }

    @org.junit.Test
    public
    void setYourChoice () {
        Automata test = new Automata("drinks.txt");
        test.setYourChoice(3);
        String s = new String("3. hot chocolate");
        assertEquals(s, test.getYourChoice());
    }


    @org.junit.Test
    public
    void getMenu () {
        Automata test = new Automata("drinks.txt");
        String s = new String("4. americana price: 25");
        ArrayList<String>testList = test.getMenu();
        assertEquals(s, testList.get(3));
    }

    @org.junit.Test
    public
    void coin () {
        Automata test = new Automata("drinks.txt");
        test.on();
        test.coin(230);
        assertEquals(230, test.getCash());
        assertEquals(ACCEPT, test.getState());
    }

    @org.junit.Test
    public
    void choice () {
        Automata test = new Automata("drinks.txt");
        test.on();
        test.choice(3);
        assertEquals(WAIT, test.getState());
    }

}