public class AutomateDemo {
    private static Automate automate;
    public static void main(String[] args) {

        automate = createAutomate();
        automateON();
        automatePrintState();
        automatePrintMenu();
        automateCoin(50);
        automatePrintState();
        automateChoice(2);

        automateCoin(10);
        automateChoice(2);
        automatePrintMenu();
        automateCoin(40);
        automateCancel();

        automatePrintMenu();
        automateCoin(25);
        automateChoice(1);
        automatePrintState();

        automateOFF();
    }
    private static Automate createAutomate(){
        String[] menu = {"Кофе эспрессо","Кофе американо","Кофе с молоком","Кофе капучино","Кофе латте","Горячий шоколад","Чай"};
        int[] prices = {25,40,35,40,40,35,20};
        Automate automate = Automate.getAutomate(menu, prices);
        if(automate == null)
            System.out.println("Создание нового автомата вызвало ошибку.\n");
        else
            System.out.println("Новый автомат создан успешно.\n");

        return automate;
    }
    private static void automateON(){
        boolean result = automate.on();
        if (result)
            System.out.println("Автомат готов принять заказ.\n");
        else
            System.out.println("Автомат включить не удалось.\n");
    }
    private static void automateOFF(){
        boolean result = automate.off();
        if (result)
            System.out.println("Автомат успешно выключен.\n");
        else
            System.out.println("Автомат выключить не удалось.\n");
    }
    private static void automatePrintState(){
        String result = automate.printState();
        if (result == null)
            System.out.println("Состояние автомата неизвестно.\n");
        else
            System.out.println("Состояние автомата: " + result + "\n");
    }
    private static void automatePrintMenu(){
        String[] result = automate.printMenu();
        if (result != null){
            System.out.println("Внесите деньги и выберите номер напитка:");
            for (String line:result
            ) {
                System.out.println(line);
            }
            System.out.println();
        }
        else
            System.out.println("Меню сейчас недоступно.\n");

    }
    private static void automateCoin(int money){
        int result = automate.coin(money);
        if (result > 0)
            System.out.println("Зачисление денег прошло успешно.\nВаш баланс: " + result +" руб.\n");
        else
            System.out.println("Зачисление денег вызвало ошибку.\n");
    }
    private static void automateChoice(int number){
        Integer result = automate.choice(number);
        if (result == null)
            System.out.println("Выбор напитка недоступен.\n");
        else if (result < 0){
            int deficit = ~result +1;
            System.out.println("Для выбора этого напитка не хватает " + deficit + " руб.");
            System.out.println("Внесите, пожалуйста, недостающую сумму.\n");
        }
        else {
            System.out.println("Ваш напиток готов.");
            if (result > 0)
                System.out.println("Ваша сдача: " + result + " руб.");
            System.out.println("\nАвтомат готов принять заказ.\n");
        }

    }
    private static void automateCancel(){
        int result = automate.cancel();
        if (result < 0)
            System.out.println("Сеанс обслуживания сейчас не может быть отменён.\n");
        else{
            System.out.println("Сеанс обслуживания пользователя отменён.");
            if (result > 0)
                System.out.println("Возьмите ваши деньги: " + result + " руб.");
            System.out.println("\nАвтомат готов принять заказ.\n");
        }
    }
}
