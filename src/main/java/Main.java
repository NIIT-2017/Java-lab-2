
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    private static void createMenuJSON() {
        JSONObject createdMenu = new JSONObject();
        JSONArray menu = new JSONArray();         //creating an ordered array "menu" of objects
        createdMenu.put("menu", menu);
        JSONObject espresso = new JSONObject();  //creating an JSONObject for each type of drink
        espresso.put("name", "espresso");        // kay - "name". value - type of drink. For each type of drink
        espresso.put("price", 30);               //kay - "price". value - price per drink. For each type of drink
        menu.put(espresso);
        JSONObject tea = new JSONObject();
        tea.put("name", "tea");
        tea.put("price", 10);
        menu.put(tea);
        JSONObject americano = new JSONObject();
        americano.put("name", "americano");
        americano.put("price", 40);
        menu.put(americano);
        JSONObject cappuccino = new JSONObject();
        cappuccino.put("name", "cappuccino");
        cappuccino.put("price", 50);
        menu.put(cappuccino);
        JSONObject latte = new JSONObject();
        latte.put("name", "latte");
        latte.put("price", 80);
        menu.put(latte);
        JSONObject cocoa = new JSONObject("{\"name\":\"cocoa\" ,\"price\":20 }"); //another way to create an JSONObject
        menu.put(cocoa);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/menu.json"))) {
            bw.write(createdMenu.toString());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        createMenuJSON();
        Automata automat = new Automata();
        //Show Initial state of the automat
        System.out.println("Initial state of the automat: " + automat.getState() + '\n');
        //Turning automat on
        System.out.println("--- Turning on ---");
        automat.on();
        System.out.println("State: " + automat.getState());
        System.out.println("Total deposit: " + automat.getCash());
        System.out.println("Change: " + automat.getChange());
        System.out.println("Information: " + automat.getInfo() + '\n');
        //Demonstration of the menu and prices for drinks
        System.out.println("--- Demonstration of the menu and prices for drinks ---");
        for (int i = 0; i < automat.getMenu().size(); i++) {
            System.out.print("button #" + i + " - " + automat.getMenu().get(i) + ":");
            System.out.println(automat.getPrice().get(i) + " rub");
        }
        System.out.println();
        //depositing 20 rubles into the automat
        System.out.println("--- + 20 rub to deposit ---");
        automat.coin(20);
        System.out.println("State: " + automat.getState());
        System.out.println("Total deposit: " + automat.getCash());
        System.out.println("Change: " + automat.getChange());
        System.out.println("Information: " + automat.getInfo() + '\n');
        //depositing another 10 rubles into the automat
        System.out.println("--- + 10 rub to deposit ---");
        automat.coin(10);
        System.out.println("State: " + automat.getState());
        System.out.println("Total deposit: " + automat.getCash());
        System.out.println("Change: " + automat.getChange());
        System.out.println("Information: " + automat.getInfo() + '\n');
        //attempt to buy cappuccino. Сappuccino price 50 rubles. Now in deposit 30 rubles
        System.out.println("--- try to  buy cappuccino ---");
        automat.choice(3);
        System.out.println("State: " + automat.getState());
        System.out.println("Total deposit: " + automat.getCash());
        System.out.println("Change: " + automat.getChange());
        System.out.println("Information: " + automat.getInfo() + '\n');
        //depositing another 60 rubles into the automat
        System.out.println("--- + 60 rub to deposit ---");
        automat.coin(60);
        System.out.println("State: " + automat.getState());
        System.out.println("Total deposit: " + automat.getCash());
        System.out.println("Change: " + automat.getChange());
        System.out.println("Information: " + automat.getInfo() + '\n');
        //closing a drink sale session and returning change.
        System.out.println("--- cancellation of a sales session ---");
        automat.cancel();
        System.out.println("State: " + automat.getState());
        System.out.println("Total deposit: " + automat.getCash());
        System.out.println("Change: " + automat.getChange());
        System.out.println("Information: " + automat.getInfo() + '\n');
        //depositing 70 rubles into the automat
        System.out.println("--- + 70 rub to deposit ---");
        automat.coin(70);
        System.out.println("State: " + automat.getState());
        System.out.println("Total deposit: " + automat.getCash());
        System.out.println("Change: " + automat.getChange());
        System.out.println("Information: " + automat.getInfo() + '\n');
        //another attempt to buy cappuccino. Сappuccino price 50 rubles. Now in deposit 70 rubles
        System.out.println("--- try to  buy cappuccino---");
        automat.choice(3);
        System.out.println("State: " + automat.getState());
        System.out.println("Total deposit: " + automat.getCash());
        System.out.println("Change: " + automat.getChange());
        System.out.println("Information: " + automat.getInfo() + '\n');
    }
}
