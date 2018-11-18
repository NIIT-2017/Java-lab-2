import org.junit.Test;

import java.util.ArrayList;

public class TestAutomata {

    @Test
    public void main() throws InterruptedException {
        TestAutomata();
    }

    void TestAutomata() throws InterruptedException {
        ArrayList<String[]> menu = new ArrayList<String[]>();

        String[] str1 = new String[2];
        str1[0] = "Кофе";
        str1[1] = "120";
        menu.add(str1);

        String[] str2 = new String[2];
        str2[0] = "Чай зеленый";
        str2[1] = "80";
        menu.add(str2);

        String[] str3 = new String[2];
        str3[0] = "Чай черный";
        str3[1] = "60";
        menu.add(str3);

        String[] str4 = new String[2];
        str4[0] = "Капучино";
        str4[1] = "150";
        menu.add(str4);

        String[] str5 = new String[2];
        str5[0] = "Горячее молоко";
        str5[1] = "110";
        menu.add(str5);

        Automata A1 = new Automata(menu);

        A1.printState();
        A1.ON();
        A1.printState();
        A1.printMenu();
        A1.printState();
        A1.choice(2);
        A1.printState();
        A1.coin(50);
        A1.printState();
        A1.coin(100);
        A1.cook();
        A1.printState();
        A1.OFF();
        A1.printState();
    }

}