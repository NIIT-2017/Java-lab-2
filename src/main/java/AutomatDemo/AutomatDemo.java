package AutomatDemo;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

class NoSuchDrinkException extends RuntimeException{
    }

enum States{OFF, WAIT, ACCEPT, CHECK, COOK}

public class AutomatDemo {
    private ArrayList<String> menu = new ArrayList();
    private ArrayList<Integer> price = new ArrayList();
    private Map<String,Integer> menuMap = new TreeMap();
    private int cash = 0;
    private States state = States.OFF;
    private int currentDrinkPrice = 0;

    public void on() throws InterruptedException {
        if (state == States.OFF) {
            state = States.WAIT;
        }
        else System.out.print("Автомат включен!");}

    public void off(){
        if (state == States.WAIT)
            state=States.OFF;
        else System.out.println("Подождите, выполняется операция!");
    }

    private void cashReset(){ // этот метод используется GUI-версией AutomatDemo
        cash=0;
        state=States.WAIT;}

    private boolean cashIncrease(int c) throws InterruptedException{
        boolean a = true;
            switch (c) {
                    case 0:
                        a = false;
                        break;
                    case 1:
                        cancel();
                        a = false;
                        //cash=0;
                        break;
                    case 5:
                        cash += 5;
                        state = States.ACCEPT;
                        break;
                    case 10:
                        cash += 10;
                        state = States.ACCEPT;
                        break;
                    case 50:
                        cash += 50;
                        state = States.ACCEPT;
                        break;
                    case 100:
                        cash += 100;
                        state = States.ACCEPT;
                        break;
                    default:
                        //a=false;
                        System.err.println("Неверное число");
            }
        //choice();
        return a;
    }

    public void consoleInputCoin(InputIntReader reader) throws InterruptedException {
        System.out.println("Положите деньги в автомат. Принимаются монеты по 5, 10, а также купюры по 10, 50 и 100 рублей");
        System.out.println("Для выбора напитка введите 0");
        System.out.println("Отмена - введите 1");
        if (state == States.WAIT||state == States.ACCEPT) {
            int c;
            while (true) {
                try {
                    c = reader.getInt();
                    if (!cashIncrease(c)){
                    return;
                    }
                }catch (NumberFormatException nfe){
                    System.err.println("Неверные символы!");
                }
                System.out.println("Ваш баланс "+cash+" руб.");
            }
            }
        else if (state == States.OFF)
            System.out.println("Включите автомат!");
        else System.out.println("Подождите, выполняется операция");
    }

    public void textFieldInputCoin(int c) throws InterruptedException{

        cashIncrease(c);
    }

    private void  setMenu() throws IOException{
        String menuStr;
        Integer priceNum;
        try (FileInputStream in = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/MENU.properties")) {
            Properties props = new Properties();
            props.load(in);
            Enumeration en = props.keys();
            while (en.hasMoreElements()) {
                menuStr = (String) en.nextElement();
                priceNum = Integer.parseInt((String) props.get(menuStr));
                menuMap.put(menuStr, priceNum);
                menu.add(menuStr);
                price.add(priceNum);
            }
        }
    }

    public void choice(InputIntReader reader) throws InterruptedException {
        if (state==States.ACCEPT){
            lbl: while (true){
                System.out.println("Сделайте выбор напитка. Введите 1 - "+menu.get(0)+", 2 - "+menu.get(1)+", 3 - "+menu.get(2)+", 4 - "+menu.get(3)+", 5 - "+menu.get(4));

            int a = reader.getInt();

                switch (a){
                case 1: currentDrinkPrice = price.get(0);
                    break lbl;
                case 2: currentDrinkPrice = price.get(1);
                    break lbl;
                case 3: currentDrinkPrice = price.get(2);
                    break lbl;
                case 4: currentDrinkPrice = price.get(3);
                    break lbl;
                case 5: currentDrinkPrice = price.get(4);
                    break lbl;
                default: try {
                    throw new NoSuchDrinkException();
                }catch (NoSuchDrinkException e){
                    System.err.println("Выбор сделан неверно");
                }
                }
            }
            //check();
        }
    }

    private void check() throws InterruptedException {
        if (state == States.ACCEPT) {
            if (currentDrinkPrice == cash)
            {state=States.CHECK;
            //cash=0;
            //cook();
            }
            else if (currentDrinkPrice<cash) {
                System.out.println("Заберите сдачу - " + (cash - currentDrinkPrice) + " руб.");
                state=States.CHECK;
               // cash=0;
                //cook();
            }
            else {System.out.println("Недостаточно средств! Внесите еще "+(currentDrinkPrice-cash)+" руб.");
            //consoleInputCoin();
            }
        }
    }

    private void cook() throws InterruptedException {
        if(state == States.CHECK){
        System.out.println("Подождите, идет приготовление напитка");
        Thread.currentThread().sleep(10000);
        System.out.println("Заберите свой напиток");
        finish();
        }
    }

    private void finish(){
        cash = 0;
        state = States.WAIT;
    }

    public String getState(){
        return state.toString();
    } // этот метод используется GUI-версией AutomatDemo

    public void setState(States state){
        this.state = state;
    }

    public int getCash(){
        return cash;
    }

    public int getCurrentDrinkPrice(){

        return currentDrinkPrice;
    }

    public ArrayList<String> getMenu(){
        return menu;
    } // этот метод используется GUI-версией AutomatDemo

    public ArrayList<Integer> getPrice(){
        return price;
    } // этот метод используется GUI-версией AutomatDemo

    public Map<String,Integer> getMenuMap(){return menuMap;}

    public void cancel() {
        if (state == States.ACCEPT || state == States.CHECK)
        state = States.WAIT;
    }



    public AutomatDemo(){
        try {
            setMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

public static void main(String[] args) throws IOException, InterruptedException {
    AutomatDemo ad = new AutomatDemo();
    System.out.println(ad.getMenuMap());
    ad.on();
    ad.consoleInputCoin(new InputIntReader(System.in));
    //ad.cancel();
    ad.choice(new InputIntReader(System.in));
   // ad.cancel();
    ad.check();
    ad.cook();
    System.out.println(ad.state);
    }
}