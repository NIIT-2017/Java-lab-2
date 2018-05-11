public class AutomataLoger
{
    private static boolean testMode, quickMode;
    private static String log;
    final static String ln = ("\n");
    private static void setTestMode(boolean tMode) {
        testMode = tMode;
    }
    private static void setQuickMode(boolean qMode) {
        quickMode = qMode;
    }
    private static void setLog(String lg) {
        log = lg;
    }
    public static void setTestQuickModes(boolean testMode, boolean quickMode) {
        setTestMode(testMode);
        setQuickMode(quickMode);
        setLog("");
    }
    public static String getLog() {
        return log;
    }
    private static void delay(int quantity) {//искусственная задержка
        if (!quickMode) {
            try {
                Thread.sleep(100 * quantity);
            }
            catch (InterruptedException e) {
            }
        }
    }
    private static void oneDelay() {//искусственная задержка
        delay(5);
    }
    private static void halfDelay() {//искусственная задержка
        delay(3);
    }
    private static void concat(String message) {
        log += message;
    }
    private static void show(String message) {
        concat(message);
        if (!testMode) print(message);
    }
    private static void showln(String message) {
        show(message);
        oneln();
    }
    private static void showTwoln(String message) {
        show(message);
        twoln();
    }
    private static void oneln() {
        show(ln);
    }
    private static void twoln() {
        someln(2);
    }
    private static void someln(int count) {
        for(int i=0; i < count; i++) oneln();
    }
    private static String nln(int n) {
        String nln = ("");
        for(int i=0; i < n; i++) nln += ln;
        return nln;
    }
    public static void statOn() {
        show(nln(1) + "----------------" + nln(2));
        showTwoln("Автомат включен.");
    }
    public static void statStart() {
        showTwoln("Обслуживание начато.");
    }
    public static void statOff() {
        showln("Автомат выключен.");
        show(nln(1) + "----------------" + nln(1));
    }
    public static void status(Automata.State state, Automata.UserCommand userCom, double cash) {//отображение текущего состояния для пользователя
        showTwoln("Состояние " + state + ". Действие " + userCom + ". Сумма " + String.format("%(.2f", cash) + ".");
        oneDelay();
    }
    public static void menu() {//отображение меню с напитками и ценами для пользователя
        for(int i=0; i < AutomataMenu.getMenuLength(); i++)
            show(i+1 + " - " + AutomataMenu.getDrink(i) + " - " + String.format("%(.2f", AutomataMenu.getPrice(i)) + "$.  ");
        twoln();
        oneDelay();
    }
    public static void selCoin() {
        showln("Сумма:");
        oneDelay();
    }
    public static void nextLine(double val) {
        concat(Double.toString(val));
        oneln();
    }
    public static void coin(double coin) {
        showTwoln(String.format("%(.2f",coin));
        oneDelay();
    }
    public static void selDrink() {
        showln("Номер напитка:");
        oneDelay();
    }
    public static void nextLine(int val) {
        concat(Integer.toString(val));
        oneln();
    }
    public static void num(int num) {
        showTwoln(Integer.toString(num));
        oneDelay();
    }
    public static void change(double change) {
        showTwoln("Сдача равна: " + String.format("%(.2f", change));
        oneDelay();
    }
    public static void exit(boolean workStat) {
        if(workStat == false) {
            showTwoln("Обслуживание завершено.");
            oneDelay();
        }
    }
    public static void select(Automata.State state) {
        switch(state) {
            case WAIT:
                showln("1 - Внести сумму, 2 - Выход.");
                break;
            case ACCEPT:
                showln("1 - Выбрать напиток, 2 - Добавить сумму, 3 - Выдать сдачу.");
                break;
        }
        showln("Номер:");
        oneDelay();
    }
    public static void checkFalse() {
        showTwoln("Недостаточно средств.");
        oneDelay();
    }
    public static void checkTrue(double drinksPrice) {
        showTwoln("Сумма к списанию: " + String.format("%(.2f", drinksPrice));
        oneDelay();
    }
    public static void cook(int i) {
        showln("Идет приготовление напитка: " + AutomataMenu.getDrink(i));
        for(int l=0; l < 27; l++) {
            show(".");
            halfDelay();
        }
        twoln();
        oneDelay();
    }
    public static void cookFinish(int i) {
        showTwoln("Приготовление напитка " + AutomataMenu.getDrink(i) + " завершено.");
        oneDelay();
    }
    public static void mesErrStart(String message) {
        show(nln(1) + "----------------" + nln(2));
        showln(message);
        halfDelay();
    }
    public static void mesErrLine(String message) {
        show(message);
    }
    public static void mesErr(String message) {
        showln(message);
        halfDelay();
    }
    public static void mesErrEnd(String message) {
        showln(message);
        show(nln(1) + "----------------" + nln(1));
        oneDelay();
    }
    public static void print(String string) {
        System.out.print(string);
    }
}
