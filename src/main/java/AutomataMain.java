public class AutomataMain {
    public static void main(String[] args){
        Automata automata = new Automata();
        System.out.println("Автомат выключен");
        System.out.println("Состояние: " + automata.getState() + "\n");

        automata.on();
        System.out.println("Автомат включён");
        System.out.println("Состояние: " + automata.getState() + "\n");

        automata.off();
        System.out.println("Автомат выключен");
        System.out.println("Состояние: " + automata.getState() + "\n");

        automata.on();
        automata.coin(50);
        System.out.println("Внесено 50 руб.");
        System.out.println("Всего средств: " + automata.getCash() + " руб.");
        System.out.println("Состояние: " + automata.getState() + "\n");

        automata.coin(10);
        System.out.println("Внесено 10 руб.");
        System.out.println("Всего средств: " + automata.getCash() + " руб." + "\n");

        automata.cancel();
        System.out.println("Отмена операции.");
        System.out.println("Всего средств: " + automata.getCash() + " руб.");
        System.out.println("Состояние: " + automata.getState() + "\n");

        automata.coin(100);
        System.out.println("Внесено 100 руб.");
        System.out.println("Всего средств: " + automata.getCash() + " руб.");
        System.out.println("Состояние: " + automata.getState() + "\n");

        automata.choice(2);
        System.out.println("Выбран напиток №4: Латте");
        System.out.println("Всего средств: " + automata.getCash() + " руб.");
        System.out.println("Состояние: " + automata.getState() + "\n");

        automata.check();
        automata.cook();
        System.out.println("Напиток готовится...");
        System.out.println("Состояние: " + automata.getState());
        System.out.println("Сдача: " + automata.getCash() + " руб." + "\n");

        automata.finish();
        System.out.println("Состояние: " + automata.getState());
        System.out.println("Напиток готов");
        System.out.println("Всего средств: " + automata.getCash() + " руб." + "\n");
    }
}
