import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Automata {
    private int cash;
    private int change;
    private States state;
    private ArrayList<Long> prices = new ArrayList<>();
    private ArrayList<String> drinks = new ArrayList<>();

    private static void parseDrinksObject(JSONObject drink, ArrayList<String> drinks, ArrayList<Long> prices) {
        JSONObject drinkObject = (JSONObject)drink.get("drink");
        String name = drinkObject.get("name").toString();
        drinks.add(name);
        long price = (Long)drinkObject.get("price");
        prices.add(price);
    }

    public Automata(String filename) throws URISyntaxException {
        this.state = States.OFF;
        this.cash = 0;
        URL resource = Main.class.getResource(filename);
        File file = Paths.get(resource.toURI()).toFile();
        JSONParser jsonParser = new JSONParser();
        try (FileReader fr = new FileReader(file)) {
            Object obj = jsonParser.parse(fr);
            JSONArray drinksList = (JSONArray) obj;
            for (Object o:drinksList) {
                parseDrinksObject((JSONObject) o, drinks, prices);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    // MAIN METHODS

    public void on() {
        if (state == States.OFF) state = States.WAIT;
    }

    public void off() {
        if (state == States.WAIT) state = States.OFF;
    }

    public int coin (int cash) {
        if (cash > 0 && (state == States.WAIT || state == States.ACCEPT)) {
            state = States.ACCEPT;
            this.cash += cash;
        }
        return getCash();
    }

    public String choice(int i) {
        if (check(i) >= 0 && state == States.CHECK) {
            state = States.COOK;
            cash -= getPrice(i);
            cook();
            if (cash == 0) finish();
            else if (cash > 0) returnChange();
            return "Take your " + drinks.get(i);
        } else {
            state = States.ACCEPT;
            return "Add enough money to buy a drink";
        }
    }

    public int check(int i) {
        if (state == States.ACCEPT) state = States.CHECK;
        return (int)(getCash() - getPrice(i));
    }

    public void cook() {
        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void finish() {
        state = States.WAIT;
        cash = 0;
    }

    public int cancel() {
        if (cash > 0 && (state == States.ACCEPT || state == States.CHECK)) {
            int cashBack = cash;
            finish();
            return cashBack;
        }
        return 0;
    }

    public void returnChange() {
        change = cash;
        if (state == States.COOK) finish();
    }

    // GETTERS

    public States getState() {
        return state;
    }

    public int getCash() {
        return cash;
    }

    public int getChange() {
        int c = change;
        change = 0;
        return c;
    }

    public String getMenu(int i) {
        return drinks.get(i) + " - " + prices.get(i) + " RUB";
    }

    public long getPrice(int i) {
        return prices.get(i);
    }

    public String getDrink(int i) {
        return drinks.get(i);
    }

    public int getMenuLength() {
        return drinks.size();
    }
}
