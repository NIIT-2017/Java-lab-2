package automata;

import javafx.util.Pair;
import java.io.*;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by kortez on 01/04/18.
 */
public class Automata {
    enum States {OFF, WAIT,ACCEPT,CHECK,COOK}
    private States state;
    private double cash;
    private Enumeration key;
    private Properties menu;
    private double money;
    private Pair<String,Double> selectedDrink;

    public double getCash() {
        return cash;
    }

    public String getState() {

        return state.toString();
    }

    public Automata(String path) {
        this.state = States.OFF;
        cash = 0.0f;
        money = 0.0f;
        File drinks = new File(path);
        try {
            FileInputStream inputStream = new FileInputStream(drinks);
            menu = new Properties();
            menu.load(inputStream);
            key = menu.keys();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("не найден файл с напитками");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //вывод меню
    public void getMenu(){
        while (key.hasMoreElements()){
            String drink = key.nextElement().toString();
            System.out.println(drink+" "+menu.getProperty(drink));
        }
    }
    //выбор напитка
    public void choise(String drink){
        if (state ==States.ACCEPT) {
            selectedDrink = new Pair<String, Double>(drink, Double.parseDouble(menu.getProperty(drink)));
            //проверить достаточно ли денег
            if (cash != selectedDrink.getValue()) {
                //нехватает денег
            } else {
                if (cash - selectedDrink.getValue() > money) {
                    //нехватает сдачи
                } else {
                    //готовим напиток
                    //переводим автомат в состояние готовки
                    cook();
                }
            }
        }
    }
    //олтмена заказа и возврат денег
    public void cancel(){
        if (state==States.CHECK){

        }
    }
    void cook(){
        if (state==States.CHECK){
            //вход в состояние готовки только из состояния check
            state=States.COOK;
        }
    }
    //метод переводит автомат в состояние on
    public void on(){
        //включение возможено только из off
        if (state == States.OFF) state = States.WAIT;
    }
    //метод переводит автомат в состояние off
    public void off(){
        //выключение только из состояния wait
        if (state == States.WAIT) state = States.OFF;
    }
    //метод внесения денег на счет в автомате
    public void coin(double cash){
        //занесение денег только из состояния wait или довнесение в состоянии accept
        if (state == States.ACCEPT) this.cash+=cash;
        else if (state == States.WAIT) {
            this.cash+=cash;
            state = States.ACCEPT;
        }
    }

}
