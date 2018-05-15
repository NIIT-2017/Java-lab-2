import java.io.*;

import java.util.ArrayList;

public class Automata {

    private int cash;  // для хранения текущей суммы
    private ArrayList<String> menu;  // названия напитков
    private ArrayList<Integer> prices;  // цены напитков
    private STATES state;  // текущее состояние автомата
    private int userChoice; // выбор пользователя


    enum STATES{  //состояния автомата
        OFF, //выключено
        WAIT, //ожидание
        ACCEPT, //приём денег
        CHECK, //проверка наличности
        COOK //приготовление
    }


    Automata() {
        userChoice = 0;
        cash = 0; //для хранения текущей суммы
        menu = new ArrayList<String>(); // названия напитков
        prices = new ArrayList<Integer>(); //цены напитков
        try {
            BufferedReader br = new BufferedReader(new FileReader("menu.txt"));

                for (String s : br.readLine().split(",")) {
                    menu.add(s);
                }
                for (String s : br.readLine().split(",")) {
                    prices.add(Integer.parseInt(s));
                }


        } catch (FileNotFoundException exc) {
            System.out.println("Файл не найден: " + exc);

        } catch (IOException exc) {
            System.out.println("Ошибка ввода/вывода: " + exc);
        }
        state = STATES.OFF;
    }

    public void on() {  // включение автомата
        state = STATES.WAIT;
        System.out.println();
        this.printMenu();  // отображение меню (с напитками и ценами)
    }

    public void off() {  // выключение автомата
        state = STATES.OFF;
    }

    public int coin(int cash) {  // занесение денег на счёт пользователем
        state = STATES.ACCEPT;
        this.cash += cash;
        return this.cash;
    }

    private void printMenu() {  // отображение меню (с напитками и ценами) для пользователя
        int len = menu.size();
        for (int i = 0; i < len; i++)
            System.out.println(menu.get(i) + " - " + prices.get(i));
        System.out.println();
    }

    public String PrintState() {  // отображение текущего состояния для пользователя
        System.out.println(state.name());
        return state.toString();
    }

    public void choice(int userCh) {  // выбор напитка пользователем
        userChoice = userCh;
        check();
    }

    private void check() {  //проверка наличия необходимой суммы
        state = STATES.CHECK;
        if (cash > prices.get(userChoice-1)) {
            System.out.println("Вы внесли " + cash + " руб. Стоимость напитка: " + prices.get(userChoice-1) + " руб.");
            cash -= prices.get(userChoice-1);
            System.out.println("Ваша сдача: " + cash + " руб. Заберите сдачу!");
            System.out.println();
            cash = 0;
            cook();
        } else {
            System.out.println("Вы внесли " + cash + " руб. Стоимость напитка: " + prices.get(userChoice-1) + " руб.");
            System.out.println("Внесите недостающую сумму: " + (prices.get(userChoice-1)-cash) + " руб.");
            System.out.println();
            state = STATES.ACCEPT;
        }
    }

    public void cancel() {  // отмена сеанса обслуживания пользователем
        System.out.println("Вы отменили заказ.");
        System.out.println("Вы внесли " + cash + " руб. Заберите внесённые средства!");
        System.out.println();
        cash = 0;
        state = STATES.WAIT;
    }

    private void cook() {  // имитация процесса приготовления напитка
        state = STATES.COOK;
        System.out.println("Пожалуйста, подождите. Ваш напиток готовится... ");
        try {
            for(int k = 0; k < 5; k++) {
                for (int i = 0; i < 40; i++) {
                    System.out.print("|");
                    Thread.sleep(100);
                }
                System.out.print("\r");
                }
            System.out.println("\r                                              ");
        } catch (InterruptedException exp) {
            System.err.println(exp.getMessage());
        }
        finish();
    }

    private void finish() {  // завершение обслуживания пользователя
        System.out.println("Напиток готов. Пожалуйста, заберите напиток!");
        System.out.println("Будьте осторожны! Напиток горячий!");
        state = STATES.WAIT;
    }

}