package automata;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class AutomataTest implements UserAutomata {

    public static ArrayList<Goods> myGoods = new ArrayList();

    public void getGoods(Goods goods) {
        myGoods.add(goods);
        System.out.println("\tПеречень приобретённых продуктов обновлён!\nПолучено: " + goods);
    }

    /**
     * Тестирование функции включения автомата
     */
    @org.junit.Test
    public void onTest() {
        Automata meAutomata = new Automata(this);
        assertEquals(meAutomata.getState(), Automata.STATES.OFF);
        assertTrue(meAutomata.on());
        assertEquals(meAutomata.getState(), Automata.STATES.WAIT);
    }

    /**
     * Тестирование функции выключения автомата
     */
    @org.junit.Test
    public void offTest() {
        Automata meAutomata = new Automata(this);
        assertEquals(meAutomata.getState(), Automata.STATES.OFF);
        assertTrue(meAutomata.on());
        assertEquals(meAutomata.getState(), Automata.STATES.WAIT);
        assertTrue(meAutomata.coin(100));
        assertFalse(meAutomata.off());
        assertNotEquals(Automata.STATES.OFF, meAutomata.getState());
        assertEquals(100, meAutomata.cancel());
        meAutomata.off();
        assertEquals(meAutomata.getState(), Automata.STATES.OFF);
    }

    /**
     * Тестирование приёма и выполнения заказа
     */
    @org.junit.Test
    public void choiceTest() {
        myGoods.clear();

        Automata meAutomata = new Automata(this);
        assertTrue(meAutomata.on());
        assertTrue(meAutomata.coin(1000));
        assertTrue(meAutomata.choice(1));
        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(1, myGoods.size());
        assertTrue(myGoods.get(0).getName().equals("Чёрный кофе") && myGoods.get(0).getPrice() == 30);
        assertTrue(meAutomata.choice(2));
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(2, myGoods.size());
        assertTrue(myGoods.get(1).getName().equals("Капучино") && myGoods.get(1).getPrice() == 40);
    }


    /**
     * Тестирование внесения средств, возврата средств и получения сдачи
     */
    @org.junit.Test
    public void coinAndCancel() {
        Automata meAutomata = new Automata(this);
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
        Automata meAutomata = new Automata(this);
        //После создания экземпляра автомат должен быть выключен. Проверяем
        assertEquals(Automata.STATES.OFF, meAutomata.getState());
        //Внесение средств в автомат когда он выключен невозможно. Проверяем
        assertFalse(meAutomata.coin(100));
        assertEquals(Automata.STATES.OFF, meAutomata.getState());
        assertEquals(0, meAutomata.cancel());
        assertEquals(Automata.STATES.OFF, meAutomata.getState());
        //Включаем автомат. Проверяем включился или нет
        assertTrue(meAutomata.on());
        //После включения автомат должен сразу переходить в режим ожидания. Проверяем
        assertEquals(Automata.STATES.WAIT, meAutomata.getState());
        //После включения автомата появляется возможность внесения средств,
        //что приводит к переводу автомата в режим приёма денег/ожидания заказа
        assertTrue(meAutomata.coin(10));
        assertEquals(Automata.STATES.ACCEPT, meAutomata.getState());
        //Проверяем возможность возврата средств
        assertEquals(10, meAutomata.cancel());
        //После возврата средств автомат должен перейти в режим ожидания
        assertEquals(Automata.STATES.WAIT, meAutomata.getState());
        //Повторно вносим средства
        assertTrue(meAutomata.coin(10));
        assertEquals(Automata.STATES.ACCEPT, meAutomata.getState());
        //Пробуем сделать заказ заведомо несуществующего продукта
        assertFalse(meAutomata.choice(999));
        assertEquals(0, myGoods.size());
        //Пробуем сделать заказ при недостаточности средств
        assertFalse(meAutomata.choice(1));
        assertEquals(0, myGoods.size());
        //Вносим недостающую сумму
        assertTrue(meAutomata.coin(60));
        //Делаем заказ
        assertTrue(meAutomata.choice(1));
        assertEquals(0, myGoods.size());
        //Проверяем состояние автомата
        assertEquals(Automata.STATES.COOK, meAutomata.getState());
        //Проверяем получение сдачи и переходы автомата из одного состояния в другие
        assertTrue(meAutomata.coin(100));
        assertEquals(Automata.STATES.COOK, meAutomata.getState());

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
        assertEquals(Automata.STATES.WAIT, meAutomata.getState());
        assertTrue(meAutomata.off());
        assertEquals(Automata.STATES.OFF, meAutomata.getState());
        System.out.println("\tИтого приобретены: " + myGoods);
    }
}