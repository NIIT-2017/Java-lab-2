package automata;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class AutomataTest {
    /**
     * Тестирование функции включения автомата
     */
    @org.junit.Test
    public void on() {
        Automata meAutomata = new Automata();
        assertEquals(meAutomata.getState(), Automata.STATES.OFF);
        meAutomata.on();
        assertEquals(meAutomata.getState(), Automata.STATES.WAIT);
    }

    /**
     * Тестирование функции выключения автомата
     */
    @org.junit.Test
    public void off() {
        Automata meAutomata = new Automata();
        assertEquals(meAutomata.getState(), Automata.STATES.OFF);
        meAutomata.on();
        assertNotEquals(meAutomata.getState(), Automata.STATES.OFF);
        meAutomata.coin(100);
        meAutomata.off();
        assertNotEquals(meAutomata.getState(), Automata.STATES.OFF);
        meAutomata.cancel();
        meAutomata.off();
        assertEquals(meAutomata.getState(), Automata.STATES.OFF);
    }

    /**
     * Тестирование внесения средств, возврата средств и получения сдачи
     */
    @org.junit.Test
    public void coinAndCancel() {
        Automata meAutomata = new Automata();
        assertFalse(meAutomata.coin(100));
        assertEquals(meAutomata.cancel(), 0);
        assertTrue(meAutomata.on());
        assertTrue(meAutomata.coin(99));
        assertFalse(meAutomata.coin(-100));
        assertEquals(meAutomata.cancel(), 99);
    }

    /**
     * Комплексное тестирование всего класса как "чёрного ящика"
     */
    @org.junit.Test
    public void globalTest() {

        //Тестирование пакета как чёрного ящика:)))
        Automata meAutomata = new Automata();
        //После создания экземпляра автомат должен быть выключен. Проверяем
        assertEquals(meAutomata.getState(), Automata.STATES.OFF);
        //Внесение средств в автомат когда он выключен невозможно. Проверяем
        assertFalse(meAutomata.coin(100));
        assertEquals(meAutomata.getState(), Automata.STATES.OFF);
        assertEquals(0, meAutomata.cancel());
        assertEquals(meAutomata.getState(), Automata.STATES.OFF);
        //Включаем автомат. Проверяем включился или нет
        assertTrue(meAutomata.on());
        //После включения автомат должен сразу переходить в режим ожидания. Проверяем
        assertEquals(meAutomata.getState(), Automata.STATES.WAIT);
        //После включения автомата появляется возможность внесения средств,
        //что приводит к переводу автомата в режим приёма денег/ожидания заказа
        assertTrue(meAutomata.coin(10));
        assertEquals(meAutomata.getState(), Automata.STATES.ACCEPT);
        //Проверяем возможность возврата средств
        assertEquals(10, meAutomata.cancel());
        //После возврата средств автомат должен перейти в режим ожидания
        assertEquals(meAutomata.getState(), Automata.STATES.WAIT);
        //Повторно вносим средства
        assertTrue(meAutomata.coin(10));
        assertEquals(meAutomata.getState(), Automata.STATES.ACCEPT);
        //Пробуем сделать заказ заведомо несуществующего продукта
        assertFalse(meAutomata.choice(999));
        //Пробуем сделать заказ при недостаточности средств
        assertFalse(meAutomata.choice(1));
        //Вносим недостающую сумму
        assertTrue(meAutomata.coin(60));
        //Делаем заказ
        assertEquals(meAutomata.choice(1), true);
        //Проверяем состояние автомата
        assertEquals(meAutomata.getState(), Automata.STATES.COOK);
        //Проверяем получение сдачи и переходы автомата из одного состояния в другие
        assertTrue(meAutomata.coin(100));
        assertEquals(meAutomata.getState(), Automata.STATES.COOK);

        //Проверяем невозможность заказа до окончания приготовления предыдущего заказа
        assertFalse(meAutomata.choice(2));

        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(meAutomata.choice(2));
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertFalse(meAutomata.off());
        assertEquals(100, meAutomata.cancel());
        assertEquals(meAutomata.getState(), Automata.STATES.WAIT);
        assertTrue(meAutomata.off());
        assertEquals(meAutomata.getState(), Automata.STATES.OFF);

    }
}