package automata;

import java.io.*;
import java.util.*;

/**
 * Created by kortez on 01/04/18.
 */
public class Automata{
    public double getMoney() {
        return money;
    }

    //Members
    enum States {OFF, WAIT,ACCEPT,CHECK,COOK}
    private boolean menuVisible;
    private States state;
    private double cash;
    private double money;
    private int countDrinks;//доступное колличество напитков
    private int indexSelectedDrink;
    private Hashtable<Integer,String[]> drinks;
//Methods
    public double getCash() {
        return cash;
    }

    public String getState() {
        return state.toString();
    }

    //метод переводит автомат в состояние on
    public void on(){
        //включение возможено только из off
        if (state == States.OFF) state = States.WAIT;
        work();
    }

    //метод переводит автомат в состояние off
    public void off(){
        //выключение только из состояния wait
        if (state == States.WAIT) state = States.OFF;
        work();
    }

    public Automata(String path,String spliter) {
        this.state = States.OFF;
        countDrinks=0;
        cash = 0.0f;
        money = 0.0f;
        drinks = new Hashtable<Integer, String[]>();
        indexSelectedDrink=0;
        File file = new File(path);
        readMenuFromFile(file,spliter);
    }

    //вывод меню
    private void getMenu(){
        System.out.println("№ название цена");
        for (int i = 1; i <= countDrinks; i++) {
            String[] drink = drinks.get(i);
            System.out.println(i+" "+drink[0]+" "+drink[1]);
        }
    }

    //выбор напитка
    public void choicedrink(int drink){
        indexSelectedDrink = drink;
        state=States.CHECK;
        menuVisible=false;
        work();
    }

    void pay( double price){
        if (state==States.CHECK){
            //вход в состояние готовки только из состояния check
            cash-=price;
            money+=price;
            state=States.COOK;
        }
        work();
    }

    //метод внесения денег на счет в автомате
    public void coin(double cash){
        //занесение денег только из состояния check
        if (state == States.CHECK) this.cash+=cash;
        work();
    }

    //прочитать меню из файла
    public void readMenuFromFile(File file, String spliter){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            int i=1;
            while((line = reader.readLine())!=null){
                drinks.put(i,line.split(spliter));
                i++;
                countDrinks+=1;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //напиток приготовлен
    private void complite() {
        state=States.WAIT;
    }
    //возвращает деньги со счета
    public double returnMoney() {
        double result=cash;
        cash=0.0f;
        return result;
    }

    //цыкл работы автомата
    private void work() {
        switch (state){
            //waiting
            case WAIT:  waitAction();   break;
            //checking
            case CHECK: checkMoney();   break;
            //cooling drink
            case COOK:  cookDrink();    break;
            default:    break;
        }
    }
    private void checkMoney(){
        //проверить хватает ли на счете денег
        double price =Double.parseDouble(drinks.get(indexSelectedDrink)[1]);
        if (price <= cash) pay(price);
    }

    private void cookDrink(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        complite();
    }

    private void waitAction(){
        if (!menuVisible) {
            getMenu();
            menuVisible=true;
        }
    }
}
