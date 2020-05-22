import java.sql.Date;

public class AutomataDemo {
    private enum States {OFF, AWAIT, COOK, ACCEPT, CHECK};
    private States state;
    private int cash = 0;
    private final String[] drinks = {"espresso", "cappuccino", "mochaccino", "tea", "water"};
    private final int[] prices = {25, 30, 35, 25, 20};
    private long timer = 0;

    public String on() { //- включение автомата;
        if (state != States.COOK) {
            state = States.AWAIT;
        }
        return "Аппарат включен";
    }

    public String off() { //- выключение автомата;
        if (state == States.AWAIT && cash == 0){
            state = States.OFF;
            return "Аппарат выключен";
        }
        else if (state == States.AWAIT && cash > 0){
            return "Перед выключением заберите остатки средств или выберите напиток!";
        }
        else{
            return "Дождитесь окончания операции";
        }
    }

    public String coin(int money) { // - занесение денег на счёт пользователем;
        if (state == States.OFF){
            return "Аппарат выключен";
        }
        state = States.ACCEPT;
        if (money <= 0){
            return "Купюра не распознана, повторите попытку";
        }
        cash = cash + money;
        cancel();
        return "На счету "+cash+" рублей.";
    }

    public void cancel(){
        state = States.AWAIT;
    }

    public int getCoin(){
        return cash;
    }

    public String moneyBack(){ //- возврат денег
        if (state == States.OFF){
            return "Аппарат выключен";
        }
        if (cash==0){
            return "Нет средств";
        }
        else {
            String text = "Возьмите вашу сдачу - " + cash;
            cash = 0;
            return text;
        }
    }

    public String getMenu() { // - отображение меню с напитками и ценами для пользователя;
        if (state == States.OFF){
            return "Аппарат выключен";
        }
        String menu ="------< Menu >------\n";
        for (int i =0; i< drinks.length; i++)
        {
            int k = i+1;
            menu=menu+"| "+k+" | "+drinks[i]+" - "+prices[i]+"\n";
        }
        menu+="--------------------";
        return menu;
    }

    public String getState() { // - отображение текущего состояния для пользователя;
        return String.valueOf(state);
    }

    public String choice(int choice) { // - выбор напитка пользователем;
        if (state == States.OFF){
            return "Аппарат выключен";
        }
        if (choice <=0 || choice > drinks.length){
            return "Такого напитка нет, повторите попытку.";
        }
        else{
            state = States.CHECK;
            if (!check(choice)){
                state = States.ACCEPT;
                return "Недостаточно средств, внесите еще " + (prices[choice-1] - cash) + " рублей.";
            }
            else{
                cash = cash - prices[choice-1];
                cook(choice);
                return "Выбор принят, готовим ваш "+ drinks[choice-1];
            }
        }
    }

    public boolean check (int choice) { // - проверка наличия необходимой суммы;
        if (prices[choice-1] > cash){
            return false;
        }
        else{
            return true;
        }
    }

    private void cook (int choice) { // - имитация процесса приготовления напитка;
        state = States.COOK;
        timer = System.currentTimeMillis();
    }

    public String finish () { // - завершение обслуживания пользователя.
        if (state == States.OFF){
            return "Аппарат выключен";
        }
        if ((System.currentTimeMillis()-timer) > 5000){
            state = States.AWAIT;
            return "Ваш напиток готов, благодарим за покупку!";
        }
        else{
            return "Напиток готовится, подождите еще несколько секунд.";
        }

    }

}
