import javax.swing.*;

    public class Automata {
        enum States {WAIT, OFF, ACCEPT, CHECK, COOK};
        private States state;
        private String[] menu = {"Tea","Milk","Espresso","Cappuccino","Cacao"};
        private int[] prices = {15,20,35,45,30};
        private int cash;

        public Automata() {
            cash = 0;
            state = States.OFF;
        }
        public int getCash() {

            return cash;
        }
        public States getState(){

            return state;
        }
        public String on() { // включение автомата;
            if(state == States.OFF) {
                state = States.WAIT;
            }
            return "Аппарат в готовности";
        }
        public String off() { //выключение автомата;
            if(state == States.WAIT) {
                state = States.OFF;
            }
            return "Аппарат выключен";
        }
        public String coin( int myCash) { //занесение денег на счёт пользователем;
            if (state == States.WAIT || state == States.ACCEPT) {
                state = States.ACCEPT;
            }
                if (myCash <= 0) {
                    return "Баланс" + " " + cash + "руб";
                }
                cash += myCash;
                cancel();
                return "Баланс" + " " + cash + "руб";
        }

        public String getMenu()  { // отображение меню с напитками и ценами для пользователя;
            String bar = "****** MENU ******\n";
            if(state == States.OFF) {
                return "Аппарат выключен";
            }
            for(int i = 0; i <= menu.length - 1; i++) {
                bar = bar + i +"."+ " " + menu[i] + " " + "-" + " " + prices[i] + " " + "рублей" + "\n";
            }
            return bar;
        }
        public String setState() { // - отображение текущего состояния для пользователя;

            return String.valueOf(state);
        }

        public String choice(int choice) { // выбор напитка пользователем;
            if(state == States.OFF) {
                return "Аппарат выключен";
            }
            if(choice < 0 || choice > menu.length) {
                return "Неверный выбор";
            }
            else {
                state = States.CHECK;
                if (check(choice)) {
                    state = States.ACCEPT;
                    return "Нет средств для покупки! Внесите - " + (prices[choice] - cash) + " " + "рублей";
                }
                else {
                    cash = cash - prices[choice];
                    cook(choice);
                    return "Ваша сдача - " + cash + " " + "рублей" + "\n" + "Заказ принят, ожидайте!";
                }
            }
        }
        public boolean check(int choise) { // проверка наличия необходимой суммы;
            if (prices[choise] > cash) {
                return true;
            } else
                return false;
        }

        public void cancel() { // отмена сеанса обслуживания пользователем;
            if(state == States.ACCEPT || state == States.CHECK)
                state = States.WAIT;
        }
        private void cook(int choise) { // имитация процесса приготовления напитка;
            if(state == States.CHECK) {
                state = States.COOK;
            }
        }
        public String finish() { // завершение обслуживания пользователя.
            if(state == States.COOK) {
                state = States.WAIT;
                return "Напиток готов!";
            }
            else
                cancel();
                return "Продолжить обслуживание?";
        }
    }
    class Auto {
    public static void main(String[] args) throws InterruptedException {
        Automata play = new Automata();
        System.out.println("Программа кофемашины готова принять ваш заказ!");
        System.out.println(play.on());
        System.out.println(play.setState());
        System.out.println(play.coin(75));
        System.out.println(play.setState());
        System.out.println(play.getMenu());
        System.out.println(play.choice(3));
        System.out.println(play.setState());
        System.out.println(play.finish());
        System.out.println(play.setState());
        System.out.println(play.off());
        System.out.println(play.setState());
    }
}