import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class AutomataTest {

    private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();

    Automata  testCoffeeMachine = new Automata();


    @Before
    public void setUp() throws Exception {
        System.setOut((new PrintStream(outStream)));
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(null);
    }

    @Test
    public void on() { // тест включения автомата
        /*проверка включения из OFF в WAIT*/
        assertEquals(Automata.STATES.OFF, testCoffeeMachine.getState()); // проверка начального состояния автомат выключен (OFF)
        assertEquals(1, testCoffeeMachine.on()); // включаем в режим ожидания (WAIT) из состояния выключен(OFF)
        assertEquals(Automata.STATES.WAIT, testCoffeeMachine.getState()); // проверка включения (WAIT)
        assertNotEquals(1, testCoffeeMachine.on()); // нельзя включить из состояния WAIT (только из OFF)
    }

    @Test
    public void off() { // тест выключения автомата
        /*проверка выключения из состояния WAIT в OFF*/
        assertEquals(Automata.STATES.OFF, testCoffeeMachine.getState()); // проверка начального состояния автомат выключен (OFF)
        assertNotEquals(1, testCoffeeMachine.off()); // нельзя выключить из состояния OFF (только из WAIT)
        assertEquals(1, testCoffeeMachine.on()); // включаем в режим ожидания (WAIT) из состояния выключен(OFF)
        assertEquals(Automata.STATES.WAIT, testCoffeeMachine.getState()); // проверка включения (WAIT)
        assertEquals(1, testCoffeeMachine.off()); // выключаем в состояние OFF из состояния WAIT
        assertEquals(Automata.STATES.OFF, testCoffeeMachine.getState()); // проверка выключения (OFF)
    }

    @Test
    public void coin() { // тест занесения денег пользователем
        assertNotEquals(1, testCoffeeMachine.coin(1)); // нельзя вводить деньги в состоянии OFF (либо из WAIT)
        assertNotEquals(2, testCoffeeMachine.coin(1)); // нельзя вводить деньги в состоянии OFF (либо из ACCEPT)
        assertEquals(0, testCoffeeMachine.coin(1));
        testCoffeeMachine.on();
        assertEquals(Automata.STATES.WAIT, testCoffeeMachine.getState()); // проверка включения (WAIT)
        /*общая сумма 1*/
        assertEquals(1, testCoffeeMachine.coin(1)); // начинаем вводить деньги из режима ожидания (WAIT) 1 возвращает только из WAIT
        assertEquals(Automata.STATES.ACCEPT, testCoffeeMachine.getState()); // после первого ввода денег должен перейти в состояние приёма денег (ACCEPT)
        /*общая сумма 3*/
        assertNotEquals(1,testCoffeeMachine.coin(2)); // из состояния ACCEPT должно возвращать 2
        /*общая сумма 5*/
        assertEquals(2,testCoffeeMachine.coin(2));
        /*общая сумма 10*/
        assertEquals(2,testCoffeeMachine.coin(5));
        /*общая сумма 20*/
        assertEquals(2,testCoffeeMachine.coin(10));
        /*общая сумма 70*/
        assertEquals(2,testCoffeeMachine.coin(50));
        /*общая сумма 170*/
        assertEquals(2,testCoffeeMachine.coin(100));

        assertEquals(170, testCoffeeMachine.cash);
    }

    @Test
    public void printMenu() { // тест отображения меню
        testCoffeeMachine.on();
        assertEquals("\n" +
                "Состояние: Автомат в режиме ожидания.\n" +
                "Добро пожаловать. Опустите в монетоприёмник 1, 2, 5, 10 рублей. Или в банкнотоприёмник 50 или 100 рублей. \n" +
                "Введите число соответствующее номеру напитка. Дождитесь пока автомат приготовит его.\n" +
                "Возьмите ваш напиток. Если ваша сумма превысит стоимость напитка, автомат выдаст вам сдачу.\n" +
                "Выберите напиток: \n" +
                "<1> эспрессо 50 мл. за 25 рублей.\n" +
                "<2> американо 100 мл. за 25 рублей.\n" +
                "<3> кофе с молоком 150 мл. за 30 рублей.\n" +
                "<4> капучино 150 мл. за 40 рублей.\n" +
                "<5> капучино с шоколадом 150 мл. за 40 рублей.\n" +
                "<6> мокачино 150 мл. за 40 рублей.\n" +
                "<7> чай чёрный 150 мл. за 20 рублей.\n" +
                "<8> горячий шоколад 150 мл. за 30 рублей.\n" +
                "<9> двойной шоколад 150 мл. за 40 рублей.\n" +
                "<10> молочный шоколад 150 мл. за 35 рублей.\n",outStream.toString());
    }

    @Test
    public void printSates() { // отображение текущего состояния для пользователя
        assertEquals(Automata.STATES.OFF,  testCoffeeMachine.getState());
        testCoffeeMachine.printStates();
        assertEquals("\nСостояние: Автомат выключен.\n",outStream.toString());
    }

    @Test
    public void choice() {
        assertNotEquals(1, testCoffeeMachine.choice(1)); // нельзя выбрать напиток в состоянии OFF (только ACCEPT)
        testCoffeeMachine.on();
        assertNotEquals(1, testCoffeeMachine.choice(1)); // нельзя выбрать напиток в состоянии WAIT (только ACCEPT)
        testCoffeeMachine.coin(100);
        assertEquals(Automata.STATES.ACCEPT,  testCoffeeMachine.getState());
        assertEquals(1, testCoffeeMachine.choice(3));
        assertEquals(70, testCoffeeMachine.cashBack); // выдача сдачи
    }

    @Test
    public void cancel() {
        assertNotEquals(1, testCoffeeMachine.cancel()); // нельзя вызвать из выключенного состояния (OFF)
        testCoffeeMachine.on();
        assertNotEquals(1, testCoffeeMachine.cancel()); // нельзя вызвать из состояния ожидания (WAIT)
        testCoffeeMachine.coin(1);
        assertEquals(Automata.STATES.ACCEPT, testCoffeeMachine.getState());
        assertEquals(1, testCoffeeMachine.cancel()); // отмена операции пользователем
        assertEquals(1, testCoffeeMachine.cashBack); // выдача сдачи
        assertEquals(Automata.STATES.WAIT, testCoffeeMachine.getState()); // возврат в режим ожидания

        testCoffeeMachine.coin(2);
        assertEquals(Automata.STATES.ACCEPT,  testCoffeeMachine.getState());
        testCoffeeMachine.choice(2); // не хватит денег
        assertEquals(Automata.STATES.ACCEPT, testCoffeeMachine.getState()); // после провала проверки, должен вернуться в состояние приёма денег (ACCEPT)
    }

    @Test
    public void check() { // косвенное тестирование check() на провал проверки
        /*недостаточно денег*/
        testCoffeeMachine.on();
        testCoffeeMachine.coin(5);
        testCoffeeMachine.choice(9);
        assertEquals("Внесённых денег 5 руб. не хватает для выбора двойной шоколад за 40 руб.",testCoffeeMachine.errorLog);
        testCoffeeMachine.cancel();
        testCoffeeMachine.off();
        assertEquals(Automata.STATES.OFF,  testCoffeeMachine.getState());

        /*кончилась вода*/
        testCoffeeMachine.setWater(0);
        testCoffeeMachine.on();
        testCoffeeMachine.coin(100);
        testCoffeeMachine.choice(9);
        assertEquals("Извините в автомате закончилась вода.",testCoffeeMachine.errorLog);
        testCoffeeMachine.cancel();
        testCoffeeMachine.off();
        assertEquals(Automata.STATES.OFF,  testCoffeeMachine.getState());

        /*кончились стаканчики*/
        testCoffeeMachine.setWater(1000);
        testCoffeeMachine.setDisposableCup(0);
        testCoffeeMachine.on();
        testCoffeeMachine.coin(100);
        testCoffeeMachine.choice(9);
        assertEquals("Извините в автомате закончились стаканчики.",testCoffeeMachine.errorLog);
        testCoffeeMachine.cancel();
        testCoffeeMachine.off();
        assertEquals(Automata.STATES.OFF,  testCoffeeMachine.getState());
    }
}