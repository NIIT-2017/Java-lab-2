import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class Automat {
     enum STATES {
        OFF, WAIT,ACCEPT, CHECK, COOK}
     private int cash;
     private ArrayList<String> menu = new ArrayList<String>();
     private int[] prices;
     private STATES state;

    Automat(){
    //download menu
    try{
        URL resource= Automat.class.getResource("/menu.txt");
        File file = Paths.get(resource.toURI()).toFile();
        FileReader fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr);
        String line=null;

        while ((line=reader.readLine()) != null) {
        menu.add(line);
        }
       }
    catch (URISyntaxException e) {
        e.printStackTrace(); }
    catch (FileNotFoundException e){
        e.printStackTrace(); }
    catch (java.io.IOException e){
        e.printStackTrace(); }

        prices = new int[]{30,35,45,40,45}; //array initialization
        state=STATES.OFF; //ready for work!
    }

    public int getCash(){
        return cash;
    }

    public STATES on(){
        if (state == STATES.OFF)
            state = STATES.WAIT;

       return state;
    }

     public STATES getState(){
        return state;
    }

    public ArrayList<String> getMenu(){
        ArrayList<String> theMenu = new ArrayList<String>();
        if (state==STATES.WAIT || state==STATES.ACCEPT)
           for (int i = 0; i < menu.size(); i++) {
               theMenu.add(menu.get(i) + "   " + prices[i] + " rub.");
           }

       return theMenu;
    }

    public int coin(int coins){
        if (state==STATES.WAIT || state==STATES.ACCEPT) {
            state = STATES.ACCEPT;
            cash=cash+coins;
        }

        return cash;
    }

    public boolean choice(int numberCoffee){

        if (state==STATES.ACCEPT) {
            state = STATES.CHECK;
            //System.out.println("Choose coffee from the menu."); //can to be, but we have args of this method

           if(check(prices[numberCoffee])) { //check money
               cook(numberCoffee);
               return true;}

           else //if cash<price
               cancel(true); //go to WAIT
        }

        return false;
    }
    protected boolean check(int priceCoffee){
        if (state==STATES.CHECK) {
            if((cash-priceCoffee)>=0){
            cash=cash-priceCoffee; //for surrender
            return true;
            }
        }

        return false;
    }

    protected STATES cook(int numberCoffee){
        if (state==STATES.CHECK) {
            state = STATES.COOK;
            //System.out.println("Making " + menu.get(numberCoffee) + " begins.");
            //System.out.println("Expect...");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            //System.out.println("Coffee tree for your coffee has already been planted.");
            //System.out.println("It remains to wait for the first grains...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            //System.out.println("This is a joke)))");
            finish(numberCoffee);
        }

        return state;
    }

    protected int finish(int numberCoffee){
        if (state==STATES.COOK) {
            state = STATES.WAIT;
            if(cash>0) //for surrender
                cash=0;//System.out.println("Take your remaining money: "+cash+" rub.");
        }

        return cash;
    }


    public int cancel(boolean leaving){
        if (state==STATES.ACCEPT || state==STATES.CHECK || state==STATES.WAIT) {
            state = STATES.WAIT;
            if((cash>0)&& !leaving) //for surrender in this method too, because user can not want coffee
                cash=0;//System.out.println("Take your remaining money: "+cash+" rub.");
        }

        return cash;
    }

    public STATES off(){
        if (state==STATES.WAIT)
            state = STATES.OFF;

        return state;
    }
}

    class Program {
    public static void main(String[] args) {
        Automat session=new Automat();
        session.on();
        System.out.println("This state is "+session.getState());
        for(String line:session.getMenu())
            System.out.println(line);
        System.out.println("Your score = "+session.coin(10)+" rub.");
        System.out.println("Your score = "+session.coin(30)+" rub.");

        if(!session.choice(1)) //Americano coffee
            System.out.println("There is not enough money in your account for this choice.You have "+session.getCash()+" rub.");

        System.out.println("Your score = "+session.coin(15)+" rub.");
        System.out.println("This state is "+session.getState());
        session.off();//The state does not match the method.The method will not made.

        if(!session.choice(2)) //Cappuccino
            System.out.println("There is not enough money in your account for this choice. You have "+session.getCash()+" rub.");
        System.out.println("Your score = "+session.coin(35)+" rub.");
        session.cancel(false);
        System.out.println("This state is "+session.getState());
        session.off();
    }
}