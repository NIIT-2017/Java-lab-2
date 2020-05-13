public class Main {
    public static void main(String[] args) {
        //menu loading
        Automata demo = new Automata("menu.json");
        System.out.println("state:" + demo.getState());
        //switch on automata
        demo.on();
        //print menu
        System.out.println("menu:");
        for(int i = 0; i< demo.getMenu().size();i++) {
            System.out.println(i + ". " + demo.getMenu().get(i) + " - " + demo.getPrices().get(i) + "rub.");
        }
        //print cash
        System.out.println("cash: " + demo.getCash());
        //print state
        System.out.println("state:" + demo.getState());
        //give money
        demo.coin(10);
        System.out.println("Всего внесено: " + demo.getCash());
        demo.coin(10);
        System.out.println("Всего несено: " + demo.getCash());
        demo.coin(5);
        System.out.println("Всего несено: " + demo.getCash());
        //cancel by user
        demo.cancel();
        System.out.println("отмена!");
        System.out.println("Состояние автомата:" + demo.getState());
        System.out.println("Возврат: " + demo.getChange());
        System.out.println("Всего внесено: " + demo.getCash());
        //give money
        demo.coin(10);
        System.out.println("Всего внесено: " + demo.getCash());
        //make a choice. Price is bigger than cash
        demo.choice(3);
        System.out.println(demo.getMenu().get(3) + " выбран");
        System.out.println("Внесено недостаточно, возврат: " + demo.getChange());
        System.out.println("Состояние автомата:" + demo.getState());
        //give money
        demo.coin(50);
        System.out.println("Всего внесено: " + demo.getCash());
        //make a choice. Cash is bigger than price
        demo.choice(3);
        System.out.println(demo.getMenu().get(3) + " выбран");
        System.out.println(demo.getMenu().get(3) + " готов. Осторожно, горячо!");
        System.out.println("Состояние автомата:" + demo.getState());
        System.out.println("Ваша сдача: " + demo.getChange());
        System.out.println("Всего внесено: " + demo.getCash());
        //Switch off automata
        demo.off();
        System.out.println("Состояние автомата:" + demo.getState());
    }
}
