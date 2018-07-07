import java.util.Arrays;

public class Automata {
    int cash;
    String choice;

    String[] menu = {"Вода", "Черный чай", "Молоко", "Американо"
            , "Эспрессо", "Моккаччино", "Каппучино", "Двойной шоколад"};

    int[] prices = {15, 25,25,30,30,35,35,40};

    enum STATES {
        OFF, WAIT, ACCEPT, CHECK, COOK
    }

    STATES states = STATES.OFF;

    public void on() {
        System.out.println("Автомат включен");
        states = STATES.WAIT;
        getState();
    }

    public void off() {
        System.out.println("Автомат выключен");
        states = STATES.OFF;
        getState();
    }

    public void coin(int cash) {
        states = STATES.ACCEPT;
        getState();
        this.cash += cash;
        System.out.println("Текущий баланс = "+ this.cash);
    }

    public void getMenu() {
        //System.out.println(Arrays.toString(menu));
        for(int i = 0; i<menu.length; i++) {
            System.out.print(menu[i]+"-"+prices[i]+"  ");
        }
        System.out.println("");
    }

    public void getState() {
        System.out.println(states);
    }


    public void choice(String choice) {
        this.choice = choice;
        switch (choice) {
            case "Вода":
                if(check()) {
                    states = STATES.CHECK;
                    getState();
                    this.choice = menu[0];
                    break;
                } else System.out.println("Не хватает");
            case "Черный чай":
                if(check()) {
                    states = STATES.CHECK;
                    getState();
                    this.choice = menu[1];
                    break;
                }else System.out.println("Не хватает");
            case "Молоко":
                if(check()) {
                    states = STATES.CHECK;
                    getState();
                    this.choice = menu[2];
                    break;
                }else System.out.println("Не хватает");
            case "Американо":
                if(check()) {
                    states = STATES.CHECK;
                    getState();
                    this.choice = menu[3];
                    break;
                }else System.out.println("Не хватает");
            case "Эспрессо":
                if(check()) {
                    states = STATES.CHECK;
                    getState();
                    this.choice = menu[4];
                    break;
                }else System.out.println("Не хватает");
            case "Моккаччино":
                if(check()) {
                    states = STATES.CHECK;
                    getState();
                    this.choice = menu[5];
                    break;
                }else System.out.println("Не хватает");
            case "Каппучино":
                if(check()) {
                    states = STATES.CHECK;
                    getState();
                    this.choice = menu[6];
                    break;
                }else System.out.println("Не хватает");
            case "Двойной шоколад":
                if(check()) {
                    states = STATES.CHECK;
                    getState();
                    this.choice = menu[7];
                    break;
                }else System.out.println("Не хватает");
        }

    }

    private boolean check() {
        if(cash < 15) {
            return false;
        } else if(cash >= 15 && choice.equals(menu[0])) {
            return true;
        } else if(cash >= 25 && (choice.equals(menu[1]) || choice.equals(menu[2]))) {
            return true;
        } else if(cash >= 30 && (choice.equals(menu[3]) || choice.equals(menu[4]))) {
            return true;
        } else if(cash >= 35 && (choice.equals(menu[5]) || choice.equals(menu[6]))) {
            return true;
        } else if(cash >= 40 && choice.equals(menu[7])) {
            return true;
        } else return false;
    }

    public void cancel() {
        states = STATES.WAIT;
        System.out.println("Ваша сдача "+ cash + " руб.");
        cash = 0;
        getState();
    }

    public void cook() {
        if(states == STATES.CHECK) {
                states = STATES.COOK;
                getState();
                System.out.println("Вы выбрали " + choice + ". Ожидайте приготовления.");
                finish();
        }
    }

    private void finish() {
        states = STATES.WAIT;
        getState();
        System.out.println("Ваш "+choice+" готов. Напиток горячий, не обожгитесь");
        for(int i = 0; i<menu.length; i++) {
            if(choice.equals(menu[i])){
                System.out.println("Ваша сдача "+(cash-prices[i])+" руб."); break;
            }
        }
        System.out.println("Спасибо за покупку.");


    }






























}
