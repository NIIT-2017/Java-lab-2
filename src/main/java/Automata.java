import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Automata {
    private int cash = 0; //для хранения текущей суммы
    private List<String> menu = new ArrayList<String>();   //строки с названиями напитков (может подгружаться из файла);
    private List<Integer> prices = new ArrayList<Integer>(); //цены напитков (соответствует массиву menu);
    enum STATES {
        OFF, WAIT, ACCEPT, CHECK, COOK
    }
    private STATES state = STATES.OFF; //текущее состояние автомата;

    public STATES getStates() {
        return state;
    }

    public Automata () { // конструктор
        URL resource= Automata.class.getResource("/menu.txt"); //url клас про путь, находит файл в самом проекте
        try {
            File file = Paths.get(resource.toURI()).toFile();
            FileReader fr = new FileReader(file); //умеет считывать
            BufferedReader reader = new BufferedReader(fr); //тоже для считывания, но реализация как считывается тут
            String line=null;
            while ((line=reader.readLine()) != null) {
                String[] split = line.split("\\s+"); //разделить по пробелам на 2 элемента
                menu.add(split[0]);   //cчитывание
                prices.add(Integer.valueOf(split[1])); //преобразование стринга в интежер
            }
        } catch (URISyntaxException | FileNotFoundException e) {
            e.printStackTrace(); //нашли файл и проверили адекватность
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void on() { // включение автомата;
        if (this.state == STATES.OFF) {
            this.state = STATES.WAIT;
        }
    }

    public void off() { //выключение автомата
        if (this.state == STATES.WAIT) {
            this.state = STATES.OFF;
        }
    }
    public void coin(int sum) { //занесение денег на счёт пользователем;
        if (this.state == STATES.WAIT) {
            this.state = STATES.ACCEPT;
        }
            this.cash = this.cash + sum;
    }

    public int getCash() {
        return cash;
    }

    public void getMenu() { // - отображение меню с напитками и ценами для пользователя;
        if (this.state == STATES.WAIT) {
            for (int i = 0; i < menu.size(); i++) {
                System.out.println((i+1) + " " + menu.get(i) + " " + prices.get(i));
            }
        }
    }

    public ArrayList<String> getMenu2() {
        return (ArrayList<String>) menu;
    }
    public ArrayList<Integer> getPrices() {
        return (ArrayList<Integer>) prices;
    }

    public void getState() { //отображение текущего состояния для пользователя;
        if (this.state != STATES.OFF) {
            System.out.println("State is "+this.state);
        }
    }

    public void choice(int number) { //выбор напитка пользователем;
        if(this.state == STATES.ACCEPT) {
            if (check(number)) {
                cook(number);
            }
            else {
                System.out.println("you do not have enough funds, deposit another amount");
            }
        }
    }

   private boolean check(int number) { //- проверка наличия необходимой суммы;
        if (this.state== STATES.ACCEPT) {
            this.state = STATES.CHECK;
            if (prices.get(number-1) == this.cash) {
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }

    public void cancel() { //отмена сеанса обслуживания пользователем;
        if ((this.state == STATES.ACCEPT)||(this.state == STATES.CHECK)) {
            this.state = STATES.WAIT;
            this.cash = 0;
        }
    }

    private void cook(int number) { //- имитация процесса приготовления напитка;
        if (this.state == STATES.CHECK) {
            this.state = STATES.COOK;
            System.out.println("cooking " + menu.get(number - 1));
            try {
                Thread.sleep(5000); //не нужен объект для класса поток Thread
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finish();
        }
    }

    private void finish() { //- завершение обслуживания пользователя.
        if (this.state == STATES.COOK) {
            this.state = STATES.WAIT;
            this.cash = 0;
        }
    }
}

class demoAutomata {
    public static void main(String[] args) {
        Automata automata = new Automata();
        System.out.println("State is " + automata.getStates());
        automata.on();
        System.out.println("State is " + automata.getStates());
        automata.getMenu();
        automata.coin(5);
        automata.coin(25);
        System.out.println("State is " + automata.getStates());
        automata.choice(3);
        System.out.println("State is " + automata.getStates());
        automata.coin(5);
        automata.choice(3);
        System.out.println("State is " + automata.getStates());
    }
}