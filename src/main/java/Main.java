import java.net.URISyntaxException;
public class Main
{
    public static void main(String[] args) throws URISyntaxException
    {
        Automata demo = new Automata("menu.json");
        System.out.println("state:" + demo.getState());
        demo.on();
        System.out.println("menu:");
        for(int i = 0; i< demo.getMenu().size();i++)
        {
            System.out.println(i + ". " + demo.getMenu().get(i) + " - " + demo.getPrices().get(i) + "rub.");
        }
        System.out.println("cash: " + demo.getCash());
        System.out.println("state:" + demo.getState());
        demo.coin(10);
        System.out.println("Всего внесено: " + demo.getCash());
        demo.coin(10);
        System.out.println("Всего несено: " + demo.getCash());
        demo.coin(5);
        System.out.println("Всего несено: " + demo.getCash());
        demo.cancel();
        System.out.println("отмена!");
        System.out.println("Состояние автомата:" + demo.getState());
        System.out.println("Возврат: " + demo.getChange());
        System.out.println("Всего внесено: " + demo.getCash());
        demo.coin(10);
        System.out.println("Всего внесено: " + demo.getCash());
        demo.choice(3);
        System.out.println(demo.getMenu().get(3) + " выбран");
        System.out.println("Внесено недостаточно, возврат: " + demo.getChange());
        System.out.println("Состояние автомата:" + demo.getState());
        demo.coin(50);
        System.out.println("Всего внесено: " + demo.getCash());
        demo.choice(3);
        System.out.println(demo.getMenu().get(3) + " выбран");
        System.out.println(demo.getMenu().get(3) + " готов. Осторожно, горячо!");
        System.out.println("Состояние автомата:" + demo.getState());
        System.out.println("Ваша сдача: " + demo.getChange());
        System.out.println("Всего внесено: " + demo.getCash());
        demo.off();
        System.out.println("Состояние автомата:" + demo.getState());
    }
}