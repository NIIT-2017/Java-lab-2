import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AutomataTest {
    Automata testAutomat = new Automata();

    @Test
    public void on() {
        testAutomat.on();
        Automata.States actual = testAutomat.getState();
        Automata.States expected = Automata.States.WAIT;
        assertEquals(expected, actual);
    }

    @Test
    public void off() {
        testAutomat.on();
        testAutomat.off();
        Automata.States actual = testAutomat.getState();
        Automata.States expected = Automata.States.OFF;
        assertEquals(expected, actual);
    }

    @Test
    public void getMenu() {
        ArrayList<String> actual = testAutomat.getMenu();
        ArrayList<String> expected = new ArrayList<>();
        expected.add("espresso");
        expected.add("tea");
        expected.add("americano");
        expected.add("cappuccino");
        expected.add("latte");
        expected.add("cocoa");
        assertEquals(expected, actual);
    }

    @Test
    public void getPrice() {
        ArrayList<Integer> actual = testAutomat.getPrice();
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(30);
        expected.add(10);
        expected.add(40);
        expected.add(50);
        expected.add(80);
        expected.add(20);
        assertEquals(expected, actual);
    }

    @Test
    public void coin1() {   //state test
        testAutomat.on();
        testAutomat.coin(10);
        Automata.States actual = testAutomat.getState();
        Automata.States expected = Automata.States.ACCEPT;
        assertEquals(Automata.States.ACCEPT, testAutomat.getState());
    }

    @Test
    public void coin2() {   //test of money deposited
        testAutomat.on();
        testAutomat.coin(15);
        testAutomat.coin(30);
        int actual = testAutomat.getCash();
        int expected = 45;
        assertEquals(expected, actual);
    }

    @Test
    public void choice1_1() {   //state test. After a successful attempt to buy a drink
        testAutomat.on();
        testAutomat.coin(100);
        testAutomat.choice(4);
        Automata.States actual = testAutomat.getState();
        Automata.States expected = Automata.States.WAIT;
        assertEquals(expected, actual);
    }

    @Test
    public void choice1_2() {  //Information after a successful attempt to buy a drink
        testAutomat.on();
        testAutomat.coin(80);    //price of cappuccino - 50 rub
        testAutomat.choice(3);
        String actual = testAutomat.getInfo();
        String expected = "take your drink and don’t forget the change";
        assertEquals(expected, actual);
    }

    @Test
    public void choice1_3() {  //change test. After a successful attempt to buy a drink
        testAutomat.on();
        testAutomat.coin(20);
        testAutomat.coin(50);
        testAutomat.choice(0);  //price of espresso - 30 rub
        int actual = testAutomat.getChange();
        int expected = 40;
        assertEquals(expected, actual);
    }

    @Test
    public void choice2_1() {  //state test. After incorrect user actions
        testAutomat.on();
        testAutomat.choice(4);
        Automata.States actual = testAutomat.getState();
        Automata.States expected = Automata.States.WAIT;
        assertEquals(expected, actual);
    }

    @Test
    public void choice2_2() {  //Information after incorrect user actions
        testAutomat.on();
        testAutomat.choice(4);
        String actual = testAutomat.getInfo();
        String expected = "give money first";
        assertEquals(expected, actual);
    }

    @Test
    public void choice3_1() {  //state test. After insufficient amount
        testAutomat.on();
        testAutomat.coin(20);
        testAutomat.coin(20);
        testAutomat.choice(4);   //price of latte - 80 rub
        Automata.States actual = testAutomat.getState();
        Automata.States expected = Automata.States.ACCEPT;
        assertEquals(expected, actual);
    }

    @Test
    public void choice3_2() {  //Information after insufficient amount
        testAutomat.on();
        testAutomat.coin(20);
        testAutomat.coin(20);
        testAutomat.choice(4);
        String actual = testAutomat.getInfo();
        String expected = "not enough money, need to deposit more";
        assertEquals(expected, actual);
    }

    @Test
    public void choice4_1() {  //state test. After the wrong choice of drink
        testAutomat.on();
        testAutomat.coin(500);
        testAutomat.choice(15);
        Automata.States actual = testAutomat.getState();
        Automata.States expected = Automata.States.ACCEPT;
        assertEquals(expected, actual);
    }

    @Test
    public void choice4_2() {  //Information after the wrong choice of drink
        testAutomat.on();
        testAutomat.coin(500);
        testAutomat.choice(15);
        String actual = testAutomat.getInfo();
        String expected = "the drink does not exist";
        assertEquals(expected, actual);
    }

    @Test
    public void choice4_3() {  //deposit test. After the wrong choice of drink
        testAutomat.on();
        testAutomat.coin(500);
        testAutomat.choice(15);
        int actual = testAutomat.getCash();
        int expected = 500;
        assertEquals(expected, actual);
    }


    @Test
    public void cancel1() {  //state test. After cancel
        testAutomat.on();
        testAutomat.coin(100);
        testAutomat.cancel();
        Automata.States actual = testAutomat.getState();
        Automata.States expected = Automata.States.WAIT;
        assertEquals(expected, actual);
    }

    @Test
    public void cancel2() { //change test. After cancel
        testAutomat.on();
        testAutomat.coin(100);
        testAutomat.cancel();
        int actual = testAutomat.getChange();
        int expected = 100;
        assertEquals(expected, actual);
    }


    @Test
    public void cancel3() {  //deposit test. After cancel
        testAutomat.on();
        testAutomat.coin(100);
        testAutomat.cancel();
        int actual = testAutomat.getCash();
        int expected = 0;
        assertEquals(expected, actual);
    }
}
