import java.io.*;

public class AutomataDemo{
    public static void main(String [] args){

        Automata coffee=new Automata();
        coffee.on();
        String[]menu=coffee.printMenu();
        for (int i=0;i<menu.length;i++) System.out.println(menu[i]);
        coffee.coin(5);
        coffee.coin(5);
        System.out.println(coffee.printState());
        coffee.coin(10);
        coffee.coin(10);
        for (int i=0;i<menu.length;i++) System.out.println(menu[i]);
        System.out.println(coffee.printState());
        coffee.choise(2);
        System.out.println(coffee.printState());
        coffee.off();
        System.out.println(coffee.printState());
        coffee.on();
        coffee.coin(10);
        coffee.coin(10);
        coffee.choise(3);
        coffee.off();

    }
}

enum STATES{OFF,WAIT,ACCEPT,CHECK,COOK}

class Automata{
    private int cash;
    private String[] menu;
    private int[] price;
    private STATES state;


    Automata(){
        state=STATES.OFF;
        cash=0;

        File f=new File("menu.txt").getAbsoluteFile();
        FileInputStream fileMenu= null;
        try {
            fileMenu = new FileInputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(fileMenu));
        try {
            menu=reader.readLine().split(",");
        } catch (IOException e) {
            e.printStackTrace();
        }
        price=new int[menu.length];
        int i=0;

        try {
            for (String string:reader.readLine().split(",")){
                price[i++]=Integer.parseInt(string);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public int getCash(){
        return cash;
    }

    public STATES getState(){
        return state;
    }

    public void on(){
        if (state==STATES.OFF){
            state=STATES.WAIT;
        }
    }

    public void off(){
        if(state==STATES.WAIT){
            state=STATES.OFF;
        }
    }

    public void coin(int money){
        if (state==STATES.WAIT) state=STATES.ACCEPT;
        if (state==STATES.ACCEPT){
            cash+=money;
            System.out.println("cash: " + cash);
        }
    }

    public int cancel(){
        if (state==STATES.ACCEPT || state==STATES.CHECK){
            int money=cash;
            cash=0;
            state=STATES.WAIT;
            return money;
        }
        else return 0;
    }

    public String[] printMenu(){
        String[] menuPrint=new String[menu.length];
        for (int i=0;i<menuPrint.length;i++)
            menuPrint[i]=(i+1)+":"+menu[i]+"-"+price[i];

        return menuPrint;

    }

    public String printState(){
        String status=new String(String.valueOf(state));
        return status;
    }

    public int choise(int number){
        if (state==STATES.ACCEPT){
            state=STATES.CHECK;
            if (cash>=price[number-1]){
                cook(number-1);
                cash-=price[number-1];
                return cash;
            }
        }
        return 0;
    }

    private void cook(int number){
        state=STATES.COOK;
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finish();
    }

    private void finish(){
        state=STATES.WAIT;
    }


}