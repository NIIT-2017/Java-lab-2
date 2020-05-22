import org.json.JSONException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, JSONException {

        Automata a = new Automata();
        System.out.println("----------------------------------Кейс 1-----------------------------------");
        a.on();
        System.out.println("Покупатель включил автомат, состояние = "+ a.getState());
        a.getMenu();
        System.out.println("Покупатель перешел в раздел меню, состояние = "+ a.getState());
        a.coin(100);
        System.out.println(String.format("Покупатель внёс средства: дебет счета автомата = %.2f; cостояние = %s.", a.getCash(), a.getState()));
        a.choice("Чай с облепихой");
        System.out.println("Покупатель выбрал напиток 'Чай и облепихой', стоимость напитка = 100.00");
        System.out.println("Идёт приготовление напитка ... state последовательно изменяется ACCEPT -> COOK -> WAIT");
        System.out.printf("Напиток готов. Дебет счета автомата = %.2f; состояние = %s\n", a.getCash(), a.getState());
        a.off();
        System.out.println("Покупатель выключает автомат, состояние = " + a.getState());
        System.out.println();


        System.out.println("----------------------------------Кейс 2-----------------------------------");
        a.on();
        System.out.println("Покупатель включил автомат, состояние = "+ a.getState());
        a.getMenu();
        System.out.println("Покупатель перешел в раздел меню, состояние = "+ a.getState());
        a.coin(90);
        System.out.println(String.format("Покупатель внёс средства: дебет счета автомата = %.2f; cостояние = %s.", a.getCash(), a.getState()));
        a.choice("Горячий шоколад");
        System.out.println("Покупатель выбрал напиток 'Горячий шоколад', стоимость напитка = 100.00");
        System.out.println("Покупатель внёс недостаточно средств, состояние = " + a.getState());
        a.coin(10);
        System.out.println(String.format("Покупатель внёс средства: дебет счета автомата = %.2f; cостояние = %s.", a.getCash(), a.getState()));
        a.choice("Горячий шоколад");
        System.out.println("Покупатель выбрал напиток 'Горячий шоколад', стоимость напитка = 100.00");
        System.out.println("Идёт приготовление напитка ... state последовательно изменяется ACCEPT -> COOK -> WAIT");
        System.out.printf("Напиток готов. Дебет счета автомата = %.2f; состояние = %s\n", a.getCash(), a.getState());
        a.cancel();
        System.out.println("Покупатель выходит из меню, состояние = "+ a.getState());
        a.off();
        System.out.println("Покупатель выключает автомат, состояние = " + a.getState());
        System.out.println();

        System.out.println("----------------------------------Кейс 3-----------------------------------");
        a.on();
        System.out.println("Покупатель включил автомат, состояние = "+ a.getState());
        a.getMenu();
        System.out.println("Покупатель перешел в раздел меню, состояние = "+ a.getState());
        a.coin(120);
        System.out.println(String.format("Покупатель внёс средства: дебет счета автомата = %.2f; cостояние = %s.", a.getCash(), a.getState()));
        a.choice("Американо на молоке");
        System.out.println("Покупатель выбрал напиток 'Американо на молоке', стоимость напитка = 100.00");
        System.out.println("Идёт приготовление напитка ... state последовательно изменяется ACCEPT -> COOK -> ACCEPT");
        System.out.printf("Напиток готов. Дебет счета автомата = %.2f; состояние = %s\n", a.getCash(), a.getState());
        System.out.println("Покупатель выбирает пункт меню 'Завершить обслуживание'");
        System.out.println("Покупатель получает сдачу " + a.getCash());
        a.cancel();
        System.out.printf("Дебет счета автомата = %.2f; состояние = %s\n", a.getCash(), a.getState());
        a.off();
        System.out.println("Покупатель выключает автомат, состояние = " + a.getState());
        System.out.println();

        System.out.println("----------------------------------Кейс 4-----------------------------------");
        a.on();
        System.out.println("Покупатель включил автомат, состояние = "+ a.getState());
        a.getMenu();
        System.out.println("Покупатель перешел в раздел меню, состояние = "+ a.getState());
        a.coin(150);
        System.out.println(String.format("Покупатель внёс средства: дебет счета автомата = %.2f; cостояние = %s.", a.getCash(), a.getState()));
        a.choice("Чай с шиповником");
        System.out.println("Покупатель выбрал напиток 'Чай с шиповником', стоимость напитка = 100.00");
        System.out.println("Идёт приготовление напитка ... state последовательно изменяется ACCEPT -> COOK -> ACCEPT");
        System.out.printf("Дебет счета автомата = %.2f; состояние = %s\n", a.getCash(), a.getState());
        a.coin(50);
        System.out.println(String.format("Покупатель пополняет счет автомата: дебет = %.2f; cостояние = %s.", a.getCash(), a.getState()));
        a.choice("Американо");
        System.out.println("Покупатель выбрал напиток 'Американо', стоимость напитка = 100.00");
        System.out.println("Идёт приготовление напитка ... state последовательно изменяется ACCEPT -> COOK -> WAIT");
        System.out.printf("Напиток готов. Дебет счета автомата = %.2f; состояние = %s\n", a.getCash(), a.getState());
        a.off();
        System.out.println("Покупатель выключает автомат, состояние = " + a.getState());
        System.out.println();

        System.out.println("----------------------------------Кейс 5-----------------------------------");
        a.on();
        System.out.println("Покупатель включил автомат, состояние = "+ a.getState());
        a.getMenu();
        System.out.println("Покупатель перешел в раздел меню, состояние = "+ a.getState());
        a.coin(100);
        System.out.println(String.format("Покупатель внёс средства: дебет счета автомата = %.2f; cостояние = %s.", a.getCash(), a.getState()));
        System.out.println("Покупатель выбирает пункт меню 'Завершить обслуживание'");
        System.out.println("Возврат средств на сумму " + a.getCash());
        a.cancel();
        System.out.printf("Дебет счета автомата = %.2f; состояние = %s\n", a.getCash(), a.getState());
        a.off();
        System.out.println();

        System.out.println("----------------------------------Кейс 6-----------------------------------");
        a.on();
        System.out.println("Покупатель включил автомат, состояние = "+ a.getState());
        a.getMenu();
        System.out.println("Покупатель перешел в раздел меню, состояние = "+ a.getState());
        a.coin(100);
        System.out.println(String.format("Покупатель внёс средства: дебет счета автомата = %.2f; cостояние = %s.", a.getCash(), a.getState()));
        System.out.println("Покупатель выключает автомат");
        System.out.println("Возврат средств на сумму " + a.getCash());
        a.off();
        System.out.printf("Дебет счета автомата = %.2f; состояние = %s\n", a.getCash(), a.getState());
        a.off();
        System.out.println();

        System.out.println("----------------------------------Кейс 7-----------------------------------");
        a.on();
        System.out.println("Покупатель включил автомат, состояние = "+ a.getState());
        a.getMenu();
        System.out.println("Покупатель перешел в раздел меню, состояние = "+ a.getState());
        System.out.println("Покупатель выбирает пункт меню 'Завершить обслуживание'");
        a.cancel();
        System.out.println("Покупатель выходит из раздела меню, состояние = " + a.getState());
    }
}
