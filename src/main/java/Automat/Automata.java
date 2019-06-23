package Automat;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public
class Automata {
    private int cash;
    private ArrayList<String>menu;
    private ArrayList<Integer>prices;
    private STATES state;
    private String yourChoice;

    public
    Automata ( String fileName ) {
        this.cash = 0;
        this.state = STATES.OFF;
        this.menu = new ArrayList<String>();
        this.prices = new ArrayList<Integer>();
        this.yourChoice = null;

        String s; // читаем файл построчно; в s записываем считанную строку
        BufferedReader fileReader = null;
        try {
            fileReader = new BufferedReader(new FileReader(fileName));
            while((s = fileReader.readLine()) != null){
                String [] liquidAndPrice = s.split(":", 2);
                menu.add(liquidAndPrice[0]);
                int price = Integer.parseInt(liquidAndPrice[1]);
                prices.add(price);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
конструктор
on() - включение автомата;
off() - выключение автомата;
coin() - занесение денег на счёт пользователем;
getMenu() - отображение меню с напитками и ценами для пользователя;
getState() - отображение текущего состояния для пользователя;
choice() - выбор напитка пользователем;
check() - проверка наличия необходимой суммы;
cancel() - отмена сеанса обслуживания пользователем;
cook() - имитация процесса приготовления напитка;
finish() - завершение обслуживания пользователя.
     */
    void on(){
        if(state != STATES.OFF){
            return;
        }
        state = STATES.WAIT;
        getState();
    }
    void off(){
        if(state == STATES.OFF){
            return;
        }
        state = STATES.OFF;
        getState();
    }
    //сеттеры и геттеры для кэша и выбора напитка
    public
    void setCash ( int cash ) {
        this.cash += cash;
    }

    public
    int getCash () {
        return cash;
    }

    public
    void setYourChoice ( int choice ) {
        this.yourChoice = menu.get(choice - 1);
    }

    public
    String getYourChoice () {
        return yourChoice;
    }

    public
    STATES getState(){
        return state;
    }
    //функции автомата
    public ArrayList getMenu(){
        ArrayList<String>fullMenu = new ArrayList<String>();
        for (int i = 0; i < menu.size(); i++) {
            fullMenu.add(menu.get(i) + " price: " + prices.get(i));
        }
        return fullMenu;
    }

    public void coin(int insertCoins){
        if(getState() != STATES.ACCEPT && getState() != STATES.WAIT){
            return;
        }
        setCash(insertCoins);
        state = STATES.ACCEPT;
    }

    public void choice(int numberOfDrink){
        if(check(numberOfDrink - 1)) {
            setCash(-prices.get(numberOfDrink - 1));
            getState();
            cook();
        }
        cancel();
    }

    private boolean check(int numberOfDrink){
        if(cash < prices.get(numberOfDrink)){
            return false;
        }
        state = STATES.CHECK;
        return true;
    }

    void cancel(){
        if(state != STATES.ACCEPT){
            return;
        }
        state = STATES.WAIT;
    }

    void cook(){
        state = STATES.COOK;
        finish();
    }

    void finish(){
        state = STATES.WAIT;
    }

}