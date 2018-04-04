package automata;


import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by kortez on 04/04/18.
 */
public class AutomataTest {
    Automata automata;
    @org.junit.Before
    public void setUp() throws Exception {
        //создаем автомат
        automata = new Automata("drinks.txt","  ");
    }

    @org.junit.After
    public void tearDown() throws Exception {
        //удаляем автомат из памяти
        automata=null;
    }

    //включение из режима OFF
    @org.junit.Test
    public void onFromOFF() throws Exception {
        assertEquals(automata.getState(), Automata.States.OFF.toString());
        //включаем автомат
        automata.on();
        //должен перейти в ожидание
        assertEquals(automata.getState(), Automata.States.WAIT.toString());
    }
    //включение из режима WAIT
    @org.junit.Test
    public void onFromWait() throws Exception {
        onFromOFF();
        //включаем автомат
        automata.on();
        //должен остаться в Wait
        assertEquals(automata.getState(), Automata.States.WAIT.toString());
    }
    //включение из режима CHECK
    @org.junit.Test
    public void onFromCheck() throws Exception {
        onFromWait();
        //выбрали напиток и перевели в режим CHECK
        automata.choiceDrink(1);
        assertEquals(automata.getState(), Automata.States.CHECK.toString());
        //пытаемся включить автомат
        automata.on();
        //должен остаться в Wait
        assertEquals(automata.getState(), Automata.States.CHECK.toString());
    }
    //выключение из состояния OFF
    @org.junit.Test
    public void offFromOff() throws Exception {
        assertEquals(automata.getState(), Automata.States.OFF.toString());
        //выключаем автомат
        automata.off();
        //должен перейти в ожидание
        assertEquals(automata.getState(), Automata.States.OFF.toString());
    }
    //выключение из состояния WAIT
    @org.junit.Test
    public void offFromWait() throws Exception {
        //включаем автомат
        automata.on();
        //проверяем что включился
        assertEquals(automata.getState(), Automata.States.WAIT.toString());
        //выключаем автомат
        automata.off();
        assertEquals(automata.getState(), Automata.States.OFF.toString());
    }
    //выключение из состояния CHECK
    @org.junit.Test
    public void offFromCheck() throws Exception {
        //включаем автомат
        automata.on();
        //выбираем напиток
        automata.choiceDrink(1);
        assertEquals(automata.getState(), Automata.States.CHECK.toString());
        //выключаем автомат
        automata.off();
        assertEquals(automata.getState(), Automata.States.CHECK.toString());
    }
    //проверка выбора напитка из OFF
    @org.junit.Test
    public void choiceDrinkFromOff() throws Exception {
        automata.choiceDrink(1);
        assertEquals(automata.getState(),Automata.States.OFF.toString());
    }
    //проверка выбора напитка из WAIT
    @org.junit.Test
    public void choiceDrinkFromWait() throws Exception {
        automata.on();
        automata.choiceDrink(1);
        assertEquals(automata.getState(),Automata.States.CHECK.toString());
    }
    //проверка выбора напитка из CHECK
    @org.junit.Test
    public void choiceDrinkFromCheck() throws Exception {
        automata.on();
        automata.choiceDrink(1);
        assertEquals(automata.getState(),Automata.States.CHECK.toString());
        automata.choiceDrink(1);
        assertEquals(automata.getState(),Automata.States.CHECK.toString());
    }
    //выбор напитка с отрицательным значением
    @org.junit.Test(expected = NullPointerException.class)
    public void choiceDrinkNegative() throws Exception {
        automata.on();
        automata.choiceDrink(-1);
    }
    //вызов из состояния OFF
    @org.junit.Test
    public void coinFromOff() throws Exception {
        assertEquals(automata.getState(),Automata.States.OFF.toString());
        automata.coin(200.0f);
        //проверяем что остались в режиме off и не занесли деньги в автомат
        assertEquals(automata.getState(),Automata.States.OFF.toString());
        assertEquals("0.0",Double.toString(automata.getCash()));
    }

    //вызов из состояния Wait
    @org.junit.Test
    public void coinFromWait() throws Exception {
        automata.on();
        assertEquals(automata.getState(),Automata.States.WAIT.toString());
        automata.coin(200.0f);
        //проверяем что остались в режиме wait и не занесли деньги в автомат
        assertEquals(automata.getState(),Automata.States.WAIT.toString());
        assertEquals("0.0",Double.toString(automata.getCash()));
    }
    //вызов из состояния CHECK
    @org.junit.Test
    public void coinFromCheck() throws Exception {
        automata.on();
        automata.choiceDrink(1);
        assertEquals(automata.getState(),Automata.States.CHECK.toString());
        automata.coin(10.0f);
        //проверяем что остались в режиме check и не занесли деньги в автомат
        assertEquals(automata.getState(),Automata.States.CHECK.toString());
        assertEquals("10.0",Double.toString(automata.getCash()));
    }
    //вызов с отрицательным значением
    @org.junit.Test
    public void coinNegative() throws Exception {
        automata.on();
        automata.choiceDrink(1);
        assertEquals(automata.getState(),Automata.States.CHECK.toString());
        automata.coin(-10.0f);
        //проверяем что остались в режиме check и не занесли деньги в автомат
        assertEquals(automata.getState(),Automata.States.CHECK.toString());
        assertEquals("0.0",Double.toString(automata.getCash()));
    }
    //вызов из OFF
    @org.junit.Test
    public void returnMoneyFromOff() throws Exception {
        assertEquals(automata.getState(),Automata.States.OFF.toString());
        assertEquals("0.0",Double.toString(automata.returnMoney()));
    }
    //вызов из Wait
    @org.junit.Test
    public void returnMoneyFromWait() throws Exception {
        automata.on();
        assertEquals(automata.getState(),Automata.States.WAIT.toString());
        assertEquals("0.0",Double.toString(automata.returnMoney()));
    }
    //вызов из CHECK
    @org.junit.Test
    public void returnMoneyFromCheck() throws Exception {
        automata.on();
        automata.choiceDrink(1);
        assertEquals(automata.getState(),Automata.States.CHECK.toString());
        assertEquals("0.0",Double.toString(automata.returnMoney()));
    }
    //вызов из WAIT после COOK
    @org.junit.Test
    public void returnMoneyFromWaitAfterCook() throws Exception {
        automata.on();
        automata.choiceDrink(1);
        automata.coin(210.0f);
        Thread.sleep(11000);
        assertEquals(automata.getState(),Automata.States.WAIT.toString());
        assertEquals("110.0",Double.toString(automata.returnMoney()));
    }

    //проверка что деньги после приготовления остались в автомате
    @org.junit.Test
    public void getMoney() throws Exception {
        returnMoneyFromWaitAfterCook();
        assertEquals("100.0",Double.toString(automata.getMoney()));
    }
    //проверка что наличные приняты и остаток правильно расчитался
    @org.junit.Test
    public void getCash() throws Exception {
        automata.on();
        automata.choiceDrink(1);
        automata.coin(10.0f);
        //проверка что деньги приняты
        assertEquals("10.0",Double.toString(automata.getCash()));
        automata.coin(200.0f);
        Thread.sleep(11000);
        assertEquals(automata.getState(),Automata.States.WAIT.toString());
        //запрос остатка
        assertEquals("110.0",Double.toString(automata.getCash()));
    }

    @org.junit.Test
    public void getState() throws Exception {
        //проверка выкоюченого состояния
        assertEquals(automata.getState(),Automata.States.OFF.toString());
        automata.on();
        assertEquals(automata.getState(),Automata.States.WAIT.toString());
        automata.choiceDrink(1);
        assertEquals(automata.getState(),Automata.States.CHECK.toString());
        automata.coin(100);
        Thread.sleep(11000);
        assertEquals(automata.getState(),Automata.States.WAIT.toString());
    }
    //проверка вывода меню
    @org.junit.Test
    public void getMenu() throws Exception {
        ArrayList<String> check=new ArrayList<String>();
        check.add("Доступные напитки:");
        check.add("1 Drink1 100");
        check.add("2 Drink2 200");
        check.add("3 Drink3 50");
        ArrayList<String> menu = automata.getMenu();
        for (int i = 0; i < menu.size(); i++) {
            assertEquals(menu.get(i),check.get(i));
        }
    }
    @org.junit.Test
    public void Work() throws Exception{
        //начинаем из выключенного состояния
        assertEquals(automata.getState(),Automata.States.OFF.toString());
        //включаем
        automata.on();
        assertEquals(automata.getState(),Automata.States.WAIT.toString());
        //выбираем напиток
        automata.choiceDrink(2);
        assertEquals(automata.getState(),Automata.States.CHECK.toString());
        //вносим деньги
        automata.coin(210.0f);
        //задержка пока готовиться
        Thread.sleep(11000);
        assertEquals(automata.getState(),Automata.States.WAIT.toString());
        //остаток от введенной суммы
        assertEquals("10.0",Double.toString(automata.getCash()));
        //возврат сдачи
        assertEquals("10.0",Double.toString(automata.returnMoney()));
        //сколько денег осталось в автомате
        assertEquals("200.0",Double.toString(automata.getMoney()));
        //какой в итоге напиток приготовился
        assertEquals(2,automata.getCompliteDrinkIndex());
        //выключение автомата
        automata.off();
        assertEquals(automata.getState(),Automata.States.OFF.toString());
    }
}