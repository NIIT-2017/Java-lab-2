public class Main {
    public static void main(String[] args) throws InterruptedException {
        AutomataDemo am = new AutomataDemo();
        System.out.println("Вас приветствует программа имитации работы кофемашины");
        System.out.println(am.on());
        System.out.println(am.getMenu());
        System.out.println(am.choice(0));
        System.out.println(am.coin(10));
        System.out.println(am.choice(5));
        System.out.println(am.coin(50));
        System.out.println(am.choice(5));
        System.out.println(am.finish());
        Thread.sleep(5050);
        System.out.println(am.finish());
        System.out.println(am.off());
        System.out.println(am.moneyBack());
        System.out.println(am.off());
        System.out.println(am.getState());
    }
}
