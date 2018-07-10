import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

class Automata {

    enum States {
        OFF, WAIT, ACCEPT, CHECK, COOK
    }

    int cash;
    //private String[] menu;
    //private int[] prices;
    States state;

    ArrayList<String> menu2;
    ArrayList<Integer> prices2;
/*
    Automata() {
        this.cash = 0;
        this.menu = new String[]{"black coffee", "coffee with milk", "latte macchiato", "tea"};
        this.prices = new int[]{20, 30, 40, 15};
        this.state = States.OFF;
    }
*/
    Automata() {
        this.cash = 0;
        this.state = States.OFF;
        this.menu2 = new ArrayList<String>();
        this.prices2 = new ArrayList<Integer>();
        importJSON();
    }

    private void importJSON () {


        JSONParser parser = new JSONParser();
        //URL file = System.class.getResource("/goods");
        try
        {
            Object obj=parser.parse(new FileReader("./src/main/resources/goods"));
            JSONArray goods =(JSONArray)obj;
            Iterator goodIterator=goods.iterator();
            while(goodIterator.hasNext()) {
                JSONObject good=(JSONObject)goodIterator.next();
                String name=good.get("name").toString();
                menu2.add(name);
                int price=Integer.parseInt(good.get("price").toString());
                prices2.add(price);
            }
        }
        catch(FileNotFoundException ex){}
        catch(IOException ex) {}
        catch (ParseException ex) {}
    }


    public void on() {
        if (this.state == States.OFF) {
            this.state = States.WAIT;
        }
    }


    public boolean off() {

        if (this.state == States.WAIT) {
            this.cash = 0;
            this.state = States.OFF;
            return true;
        } else {
            System.out.println("Не могу выключить");
            return false;
        }
    }


    public void coin(int money) {
        if ((this.state != States.OFF) & (this.state == States.WAIT | this.state == States.ACCEPT)) {
            this.cash += money;
            this.state = States.ACCEPT;
        }
    }


    public String getMenu() {
        String a = "";
        if (this.state != States.OFF) {
            for (int i = 0; i < menu2.size(); i++) {
                a += menu2.get(i) + "=" + prices2.get(i) + '\n';
            }
            return a;
        }
        return a;
    }


    public String getState() {
        return state.toString();
    }


    public boolean choice(int pos) {
        if(this.state != States.OFF) {
            if (this.state == States.ACCEPT) {
                if (check(pos)) {
                    this.state = States.COOK;
                    //getChange();
                    cook();
                    return true;
                } else {
                    this.state = States.ACCEPT;
                    return false;
                }
            } /*else {
                this.state = States.WAIT;
                //return false;
            }*/
        } else {
            System.out.println ("Автомат выключен");
            return false;
        }

        return false;
    }


    public void cancel() {
        if (this.state == States.CHECK) {
            state = States.ACCEPT;
        } else if (this.state == States.ACCEPT) {
            getChange();
            this.state = States.WAIT;
        }
    }


    private boolean check(int a) {
        this.state = States.CHECK;
        if (this.cash >= this.prices2.get(a)) {
            this.cash -= this.prices2.get(a);
            System.out.println ("Денег хватает");
            return true;
        } else {
            cancel();
            System.out.println ("Нехватает денег");
            return false;
        }
    }


    private boolean cook() {
        if (this.state == States.COOK) {
            System.out.println("Cooking");
            System.out.println("Your change is " + getChange());
            finish();
            return true;
        }
        System.out.println ("Нельзя приготовить");
        return false;
    }


    private void finish() {
        if (this.state == States.COOK) {
            this.state = States.WAIT;
        }
        else System.out.println ("Нельзя finish");
    }


    public int getChange() {
        int change = this.cash;
        this.cash = 0;
        return change;
    }


    public void printAll() {
        System.out.println("Cash =" + cash);
        System.out.println("State =" + getState());
    }

}


public class AutomataDemo {

    public static void main(String args[]) {

        Automata nescafe = new Automata();


        nescafe.on();
        System.out.println(nescafe.getMenu());

        nescafe.coin(5);
        nescafe.coin(5);
        nescafe.coin(10);
        nescafe.coin(20);
        nescafe.choice(3);
        nescafe.off();

        nescafe.printAll();
    }

}