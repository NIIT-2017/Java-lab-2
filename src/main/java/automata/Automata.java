package automata;

import javafx.util.Pair;
import java.io.*;
import java.util.*;

/**
 * Created by kortez on 01/04/18.
 */
public class Automata {
    enum States {OFF, WAIT,ACCEPT,CHECK,COOK}
    private States state;
    private double cash;
    private double money;
    private int countDrinks;//доступное колличество напитков
    private Pair<String,Double> selectedDrink;
    private Hashtable<Integer,String[]> drinks;

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
    }

    //метод переводит автомат в состояние off
    public void off(){
        //выключение только из состояния wait
        if (state == States.WAIT) state = States.OFF;
    }

    public Automata(String path,String spliter) {
        this.state = States.OFF;
        countDrinks=0;
        cash = 0.0f;
        money = 0.0f;
        drinks = new Hashtable<Integer, String[]>();
        File file = new File(path);
        readMenuFromFile(file,spliter);
    }

    //вывод меню
    public void getMenu(){
        System.out.println("№ название цена");
        for (int i = 1; i <= countDrinks; i++) {
            String[] drink = drinks.get(i);
            System.out.println(i+" "+drink[0]+" "+drink[1]);
        }
    }

    //выбор напитка
    public void choise(String drink){
    }

    //отмена заказа и возврат денег
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

    //метод внесения денег на счет в автомате
    public void coin(double cash){
        //занесение денег только из состояния wait или довнесение в состоянии accept
        if (state == States.ACCEPT) this.cash+=cash;
        else if (state == States.WAIT) {
            this.cash+=cash;
            state = States.ACCEPT;
        }
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
}
