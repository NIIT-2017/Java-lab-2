import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class Automata {
    enum STATES {                           //перечисление состояний автомата
        OFF, WAIT, ACCEPT, CHECK, COOK
    }

    private STATES state;                   //текущее состояние автомата
    private int cash;                       //количество внесенных денег
    private int cashback;                   //количество сдачи
    private int userDrink;                  //напиток выбранный пользователем
    private String[] menu;                  //массив строк названий напитков
    private int[] prices;                   //массив цен напитков

    public Automata() {                     //конструктор автомата и создание меню из файла
        this.state = STATES.OFF;
        this.cash = 0;
        this.cashback = 0;
        this.userDrink = -1;
        try {
            File file = new File("src/main/resources/menu.properties");
            FileInputStream fileInput = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fileInput);
            fileInput.close();

            Enumeration enuKeys = properties.keys();
            this.menu = new String[properties.size()];
            this.prices = new int[properties.size()];
            int id = 0;
            while (enuKeys.hasMoreElements()) {
                this.menu[id] = (String) enuKeys.nextElement();
                this.prices[id] = Integer.parseInt(properties.getProperty(menu[id]));
                id++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getCash() {                         //количество денег внесенных в автомат
        return cash;
    }

    public STATES getState() {                     //получение текущего состояния автомата
        return state;
    }

    private int getPrice(int id) {                  //получение цены определенного напитка
        return prices[id - 1];
    }

    public String getDrink(int id) {               //получение названия определенного напитка
        return menu[id - 1];
    }

    public void on() {                              //включение автомата
        if (state == STATES.OFF)
            state = STATES.WAIT;
        printMenu();
    }

    public void off() {                             //выключение автомата
        if (state == STATES.WAIT)
            state = STATES.OFF;
    }

    public void coin(int inputMoney) {              //занесение денег на счет пользователем
        if ((state == STATES.WAIT) || (state == STATES.ACCEPT)) {
            state = STATES.ACCEPT;
            cash += inputMoney;
        }
    }

    public String[] printMenu() {                   //отображение меню с напитками и ценами для пользователя
        String[] menuView = new String[menu.length + 1];
        menuView[0] = " Menu:";
        for (int i = 0; i < menu.length; i++) {
            menuView[i + 1] = (i + 1) + ". " + menu[i] + " - " + prices[i] + " rub.";
        }
        return menuView;
    }

    public String printState() {                    //отображение текущего состояния для пользователя
        String currentState = "Automata current state: " + state;
        return currentState;
    }

    public void choice(int drinkNumber) {           //выбор напитка пользователем
        if (state == STATES.ACCEPT && drinkNumber <= menu.length) {
            state = STATES.CHECK;
            userDrink = drinkNumber;
            if (check(userDrink)) {
                cook(userDrink);
            } else {
                state = STATES.ACCEPT;
            }
        } else return;
    }

    private boolean check(int userDrink) {           //проверка наличия необходимой суммы
        return (cash >= getPrice(userDrink));
    }

    public int cancel() {
        if ((state == STATES.ACCEPT) || (state == STATES.CHECK)) {
            state = STATES.WAIT;
            cashback = cash;
            cash = 0;
            return cashback;
        } else return -1;
    }

    private void cook(int userDrink) {               //имитация процесса приготовления напитка
        if (state == STATES.CHECK) {
            state = STATES.COOK;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                ;
            }
        }
        finish();
    }

    private int finish() {
        if (state == STATES.COOK) {
            state = STATES.WAIT;
            cashback = cash - getPrice(userDrink);
            cash = 0;
            return cashback;
        } else return -1;
    }
}

