public class AutomataDemo {
    private static void printChoiceRes(int num) {
        switch (num) {
            case 1:
                System.out.println("Drink has prepared");
                break;
            case 2:
                System.out.println("Not enough money");
                break;
            case 3:
                System.out.println("Wrong choice");
                break;
            default:
                System.out.println("Wrong state");
                break;
        }
    }
    public static void main(String[] args) {
        System.out.println("After creating");
        Automata obj = new Automata("/menu.properties");
        System.out.println(obj.getStateStr());
        System.out.println(obj.getCashStr());
        System.out.println();

        String[] menu = obj.getMenuStr();
        for (int idx = 0; idx < menu.length; idx++)
            System.out.println(menu[idx]);
        System.out.println();

        System.out.println("On");
        obj.on();
        System.out.println(obj.getStateStr());
        System.out.println(obj.getCashStr());
        System.out.println();

        System.out.println("Choice without money");
        int[] res = obj.choice(1);
        System.out.println("Change: " + res[0] + " rub.");
        printChoiceRes(res[1]);
        System.out.println(obj.getStateStr());
        System.out.println(obj.getCashStr());
        System.out.println();

        System.out.println("Coin 20 rub.");
        obj.coin(20);
        System.out.println(obj.getStateStr());
        System.out.println(obj.getCashStr());
        System.out.println();

        System.out.println("Choice tea");
        int[] res2 = obj.choice(1);
        System.out.println("Change: " + res2[0] + " rub.");
        printChoiceRes(res2[1]);
        System.out.println(obj.getStateStr());
        System.out.println(obj.getCashStr());
        System.out.println();

        System.out.println("Coin 20 rub.");
        obj.coin(20);
        System.out.println(obj.getStateStr());
        System.out.println(obj.getCashStr());
        System.out.println();

        System.out.println("Choice espresso");
        int[] res3 = obj.choice(4);
        System.out.println("Change: " + res3[0] + " rub.");
        printChoiceRes(res3[1]);
        System.out.println(obj.getStateStr());
        System.out.println(obj.getCashStr());
        System.out.println();
    }
}
