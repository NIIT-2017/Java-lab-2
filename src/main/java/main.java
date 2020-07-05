import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;


class AutomataDemo{
    private int cash;  // для хранения текущей суммы;
    private STATES state; // текущее состояние автомата;
    private int price;

    private ArrayList<String> menu = new ArrayList<>();
    private ArrayList<Integer> prices = new ArrayList<Integer>();

    public enum STATES{
        OFF, WAIT, ACCEPT, CHECK, COOK;
    }

    AutomataDemo(){
        this.state = STATES.OFF;
        this.cash = 0;

        menu.add("coffe");
        menu.add("tea");
        menu.add("cappuchino");
        prices.add(25);
        prices.add(15);
        prices.add(20);
    }



    public void on(){                                   // включение автомата;
            if(state == STATES.OFF){
                state = STATES.WAIT;
                getMenu();
            }
        }
    public void off(){                                 //  выключение автомата;
            if(state == STATES.WAIT && cash == 0){
                state = STATES.OFF;
            }
    }
    public int coin(int money){                        //  занесение денег на счёт пользователем
        if(state == STATES.WAIT){
            state = STATES.ACCEPT;
            if(money> 0){
                cash = cash + money;
                state = STATES.WAIT;
            }
        }
        return cash;
    }

    public void getMenu(){                            // отображение меню с напитками и ценами для пользователя;
        try {
            URL resource=main.class.getResource("menu.txt");
            File file= Paths.get(resource.toURI()).toFile();
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch(URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public STATES getState(){
            return state;
    }

    public int choice(int num){
            price = prices.get(num);
            check(price);
        return price;
    }

    private void check(int price){
        if(state == STATES.WAIT){
            state = STATES.CHECK;
            if(cash < price){
                cancel();
                state = STATES.WAIT;
            }else{
                cash = cash - price;
                cook();
            }
        }
    }

    public void cancel(){                    // отмена сеанса обслуживания пользователем;
        if(state == STATES.ACCEPT || state == STATES.CHECK || state == STATES.WAIT){
            if(cash > 0){
                cash = 0;
                state = STATES.WAIT;
            }
        }
    }

    private void cook(){                    // имитация процесса приготовления напитка;
        if(state == STATES.CHECK){
            state = STATES.COOK;
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finish();
        }
    }
    private void finish() {                          // завершение обслуживания пользователя.
        if(state == STATES.COOK){
            state = STATES.WAIT;
            cash = 0;
        }
    }

}


public class main {
    public static void main(String[] args) {
        AutomataDemo a = new AutomataDemo();
        a.on();
        a.coin(100);
        a.coin(100);
        a.choice(1);
    }
}
