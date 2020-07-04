import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Automata {
    enum States {
        ON,
        OFF,
        WAIT,
        ACCEPT,
        CHECK,
        COOK}
    private States state;
    private float cash;
    private ArrayList<Integer> prices;
    private ArrayList<String> menu;

    public Automata() {
        this.state = States.OFF;
        this.cash = 0;
        this.menu = new ArrayList<String>();
        this.prices = new ArrayList<Integer>();

        try {
            File file = new File("C:\\Users\\240488\\IdeaProjects\\JavaLab2\\src\\main\\resources\\menu.txt");
            FileReader reader = new FileReader(file);
            BufferedReader breader = new BufferedReader(reader);
            String line = null;
            while ((line = breader.readLine()) != null) {
                String [] liquidAndPrice = line.split(":");
                menu.add(liquidAndPrice[0]);
                int price = Integer.parseInt(liquidAndPrice[1]);
                prices.add(price);
            }
            reader.close();
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    //включение автомата
    public States on() {
        if (state == States.OFF)
            state = States.WAIT;
        return state;
    }
    //выключение автомата
    public States off() {
        state = States.OFF;
        return state;
    }
    //занесение денег на счёт пользователем
    public float coin(int money) {
        if (state == States.WAIT || state == States.ACCEPT) {
            state = States.ACCEPT;
            cash += money;
        } else { cancel();}
        return cash;
    }

    //отображение текущего состояния для пользователя
    public States getState() {
        return state;
    }
    //отображение кол-ва денег
    public void setCash(float cash) {
        this.cash += cash;
    }
    //получение денег
    public float getCash() {
        return cash;
    }
   //получение меню
    public ArrayList getMenu(){
        ArrayList<String>fullMenu = new ArrayList<String>();
        for (int i = 0; i < menu.size(); i++) {
            fullMenu.add(menu.get(i) + " price: " + prices.get(i));
        }
        return fullMenu;
    }
    //выбор напитка пользователем
    public void choice(int drinkChoice){
        if(check(drinkChoice - 1)) {
            setCash(-prices.get(drinkChoice - 1));
            getState();
            cook();
        }
        cancel();
    }
    //проверка наличия необходимой суммы
    private boolean check(int numberOfDrink){
        if(cash < prices.get(numberOfDrink)){
            return false;
        }
        state = States.CHECK;
        return true;
    }
    //отмена сеанса обслуживания пользователем
    void cancel() {
        if (state != States.ACCEPT) {
            return;
        }
        state = States.WAIT;
    }
    //имитация процесса приготовления напитка
    void cook() {
        if (state == States.CHECK) {
            state = States.COOK;
            //аппарат готовит...
            int itIsWaiting = 3;
            for (int i = 0; i < itIsWaiting; i++) {
                try {
                    Thread.sleep(1000);
                    System.out.print(".");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            finish();
        }
    }
    //окончание процесса
    protected void finish() {
        if (state == States.COOK && cash == 0) {
            state = States.WAIT;
        }
    }
}
