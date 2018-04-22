package Automata;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Automata {
    private int cash;   //Внесённая сумма по текущему заказу
    private int globalCash; //Средства хранящиеся в автомате
    private STATES states;
    private MenuGoods meMenu;
    private Integer idGoods = null;

    //Перечисление предназначенное для отображения состояния автомата
    enum STATES {
        OFF, WAIT, ACCEPT, COOK
    }

    /**
     * Конструктор класса (меню создаётся из переданного файла, при условии что он имеет корректный формат)
     *
     * @param fileName полный путь до файла на основании которого будет создано меню
     */
    public Automata(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            meMenu = new MenuGoods(fileName);
        } else meMenu = new MenuGoods();

        this.states = STATES.OFF;
        this.cash = 0;
        this.globalCash = 0;
    }

    /**
     * Конструктор класса (меню создаётся из файла по умолчанию)
     */
    public Automata() {
        this.states = STATES.OFF;
        this.cash = 0;
        this.globalCash = 0;
        meMenu = new MenuGoods();
    }

    /**
     * Включение автомата
     *
     * @return успешность выполнения включения, true - включение выполнено, false - включение не выполнено
     */
    public boolean on() {
        if (states == STATES.OFF) {
            states = STATES.WAIT;
            System.out.print("You on automat!\n");
            return true;
        }
        System.out.print("Automat was ON!\n");
        return false;
    }

    /**
     * Выключение автомата
     *
     * @return успешность выполнения выключения, true - выключение выполнено, false - выключение не выполнено
     */
    public boolean off() {
        if (states == STATES.WAIT) {
            System.out.print("You off automat!\n");
            states = STATES.OFF;
            System.out.println("revenue: " + globalCash);
            return true;
        } else {
            System.out.print("Automat is run!!!!\n");
            return false;
        }
    }

    /**
     * Внесение средств на счёт
     *
     * @param cash количество вносимых средств
     * @return успешность выполнения внесения средств, true - выполнено, false - не выполнено
     */
    public boolean coin(int cash) {
        if (this.states == STATES.WAIT && cash > 0) {
            this.states = STATES.ACCEPT;
            this.cash += cash;
            return true;
        } else if ((this.states == STATES.COOK || this.states == STATES.ACCEPT) && cash > 0) {
            this.cash += cash;
            return true;
        } else {
            System.out.print("Automat is OFF!!!\n");
            return false;
        }
    }


    /**
     * Получение меню в виде строки
     * @return меню в формате String
     */
    public String getMenu() {
        return meMenu.toString();
    }

    /**
     * Получение состояния автомата
     * @return OFF - автомат выключен, WAIT - автомат в режиме ожидания, ACCEPT - автомат в режиме приёма денег, COOK - автомат выполняет приготовление/выдачу заказа
     */
    public STATES getState() {
        return states;
    }

    /**
     * Заказ выбранного товара (выбор производится из созданного меню)
     * @param idGoods id товара в соответствии с меню
     * @return true - заказ принят (переданный продукт есть в меню, количество внесённых средст достаточно), false - заказ не может быть выполнен(переданный продукт отсутствует в меню, количество внесённых средст не достаточно)
     */
    public boolean choice(int idGoods) {
        if (states == STATES.OFF) {
            System.out.print("Automat is OFF!!!\n");
            return false;
        } else if (states == STATES.COOK) {
            System.out.print("Automat is cook!!!\n");
            return false;
        } else if (idGoods <= meMenu.getSizeMenu() && idGoods >= 1) {
            this.idGoods = idGoods;
            boolean buf = check();
            return buf;
        } else {
            System.out.print("The object is not!!!\n");
            return false;
        }
    }


    /**
     * Проверка достаточности средств на выбранный продукт
     * @return true - достаточно, false - недостаточно
     */
    private boolean check() {
        if (this.cash < meMenu.getPrice(this.idGoods)) {
            states = STATES.ACCEPT;
            this.idGoods = null;
            System.out.print("Deposited funds are insufficient!!!\n");
            return false;
        } else {
            cash = cash - meMenu.getPrice(this.idGoods);
            this.globalCash += meMenu.getPrice(this.idGoods);

            //cook();

            //----Новое----
            //Запуск процесса приготовления
            Thread meThread = new cookAndFinishThread();
            states = STATES.COOK;
            meThread.start();
            //-------------

            return true;
        }

    }

    /**
     * Получение сдачи/возврат средств
     * @return int сдача/внесённые средства
     */
    public int cancel() {
        if (states == STATES.ACCEPT) {
            states = STATES.WAIT;
        }
        int cashBuf = cash;
        cash = 0;
        return cashBuf;
    }

    /**
     * Создание потока приготовления пищи
     */
    class cookAndFinishThread extends Thread{
        @Override
        public void run(){
            cook();
        }
        /**
         * Приготовление заказанного товра (имитация)
         */
        private void cook() {
            states = STATES.COOK;
            if(meMenu.getTimeCook(idGoods) > 5) {
                System.out.print("Wait, there is a preparation!!!\n");
            }
            try {
                TimeUnit.SECONDS.sleep(meMenu.getTimeCook(idGoods));

            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
            finish();
        }

        /**
         * Выдача товара
         */
        private void finish() {
            if (cash == 0) {
                states = STATES.WAIT;
            } else {
                states = STATES.ACCEPT;
            }
            System.out.print("You can pick up your order: " + meMenu.getName(idGoods) + "\n");
            idGoods = null;
        }
    }
}
