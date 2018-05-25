import java.io.*;
import java.util.ArrayList;

public class Main{
    public static void main(String[] args){
        Automata auto=new Automata();
        auto.on();
        auto.coin(20);
        auto.choice(2);
        auto.coin(50);
        auto.choice(2);
        System.out.println(auto.printMenu()[4][0]+auto.printMenu()[4][1]);
        auto.off();
    }
}

enum STATES{
    OFF,WAIT,ACCEPT,CHECK,COOK
}

class Automata{
    private int cash;
    private ArrayList<String> menu;
    private ArrayList<Integer> prices;
    private STATES state;
    private int choiceProduct;
    private int sumMoney;

    Automata() {
        choiceProduct=-1;
        cash=0;
        menu=new ArrayList<String>();
        prices=new ArrayList<Integer>();
        try {
            File f=new File("menu.txt").getAbsoluteFile();
            FileInputStream fileMenu=new FileInputStream(f);
            BufferedReader reader=new BufferedReader(new InputStreamReader(fileMenu));
            for(String s:reader.readLine().split(",")){
                menu.add(s);
            }
            for(String s:reader.readLine().split(",")){
                prices.add(Integer.parseInt(s));
            }
            //throw new FileNotFoundException();
        } catch (FileNotFoundException e){
            e.printStackTrace();

        } catch (IOException e){
            e.printStackTrace();
        }
        state=STATES.OFF;
    }

    public void on(){
        if(state!=STATES.OFF){
            //System.out.println("Automat is already turned on");
            return;
        }
            state=STATES.WAIT;
            //this.printMenu();
    }

    public void off(){
            state=STATES.OFF;
    }

    public int coin(int cash){
        if(state!=STATES.WAIT && state!=STATES.ACCEPT) {
            //System.out.println("Error! Automat was broken");
            return this.cash;
        }
        state = STATES.ACCEPT;
        this.cash += cash;
        return this.cash;
    }

    public String[][] printMenu(){
        int len=menu.size();
        String[][] arr=new String[len][2];
        for(int i=0;i<len;i++)
        {
            arr[i][0]=menu.get(i);
            arr[i][1]=prices.get(i).toString();
        }
        return arr;
    }

    public String PrintState(){
        //System.out.println(state.name());
        return state.toString();
    }

    public void choice(int ch){
        if(state!=STATES.ACCEPT){
            //System.out.println("Error! Automat was broken");
            return;
        }
        choiceProduct=ch;
        check();
    }

    private void check(){
        if(state!=STATES.ACCEPT){
           // System.out.println("Error! Automat was broken");
            return;
        }
        state=STATES.CHECK;
        if(cash>prices.get(choiceProduct)){
            cash-=prices.get(choiceProduct);
            cook();
        }
        else{
            //System.out.println("Not enough money!");
            state=STATES.ACCEPT;
        }
    }

    public int cancel(){
        //System.out.println("Take money back "+cash);
        int tempCash=cash;
        cash=0;
        state=STATES.WAIT;
        return tempCash;
    }

    private void cook(){
        if(state!=STATES.CHECK){
            //System.out.println("Error! Automat was broken");
            return;
        }
        state=STATES.COOK;
        //System.out.println("Your drink is cooking... ");
        try{
            Thread.sleep(3000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finish();
    }

    private int finish(){
        //System.out.println("Take your drink and the change - "+cash+" Good bye!");
        int tempCash=cash;
        cash=0;
        sumMoney+=prices.get(choiceProduct);
        state=STATES.WAIT;
        return tempCash;
    }

    public int getSumMoney(){
        return sumMoney;
    }




}