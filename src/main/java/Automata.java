
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class Automata {
    enum States {
        OFF, WAIT, ACCEPT, CHECK, COOK
    }

    private String info;    //various information
    private int change; //change for buyer
    private int cash;   //actual deposit, available in the automat
    private ArrayList<String> names = new ArrayList<>();    //drinks names
    private ArrayList<Integer> prices = new ArrayList<>();  //drinks prices
    private States state;   //state of automat

    Automata() {
        info = "no errors";
        cash = 0;
        change = 0;
        state = States.OFF;
        try {
            File f = new File("src/main/resources/menu.json");
            JSONParser parser = new JSONParser();
            FileReader fr = new FileReader(f);
            Object obj = parser.parse(fr);
            JSONObject js = (JSONObject) obj;
            JSONArray items = (JSONArray) js.get("menu");
            for (Object i : items) {
                JSONObject jobj = (JSONObject) i;
                String name = (String) jobj.get("name");
                names.add(name);
                long price_long = (long) jobj.get("price");
                int price = (int) price_long;
                prices.add(price);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public States getState() {
        return state;
    }

    public String getInfo() {
        return info;
    }

    public int getCash() {
        return cash;
    }

    public int getChange() {
        return change;
    }

    public ArrayList<String> getMenu() {
        return names;
    }

    public ArrayList<Integer> getPrice() {
        return prices;
    }

    public void on() {  //Turning automat on
        if (state == States.OFF) {
            state = States.WAIT;
            info = "no errors";
            cash = 0;
            change = 0;
        }
    }

    public void off() {  //Turning automat off
        if (state == States.WAIT)
            state = States.OFF;
    }

    public void coin(int money) {   //depositing money into the account of the user
        if (state == States.WAIT || state == States.ACCEPT) {
            info = "no errors";
            change = 0;
            state = States.ACCEPT;
            cash += money;
        }
    }

    public void choice(int button) {    //choice of a drink by the user
        if (state == States.ACCEPT) {
            if (button < prices.size()) {
                state = States.CHECK;
                check(prices.get(button));
            } else
                info = "the drink does not exist";
        } else if (state == States.WAIT)
            info = "give money first";
    }

    private void check(int price) {     //checking the required deposit on the account
        if (state == States.CHECK) {
            if (price == cash) {    //no change
                state = States.COOK;
                cook();
            } else if (price < cash) {  //change required
                change = cash - price;
                state = States.COOK;
                cook();
            } else {
                info = "not enough money, need to deposit more";
                state = States.ACCEPT;
            }
        }
    }

    private void cook() {    //imitation of the process of preparing a drink;
        if (state == States.COOK) {
            try {
                Thread.sleep(5000); //5 seconds delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            info = "take your drink and don’t forget the change";
            finish();
        }
    }

    private void finish() {     //completion of user service
        if (state == States.COOK) {
            cash = 0;
            state = States.WAIT;
        }
    }

    public void cancel() {  //user service session cancellation. Return of money contributed by the user
        if (state == States.ACCEPT || state == States.CHECK) {
            info = "don’t forget your money";
            change = cash;
            cash = 0;
            state = States.WAIT;
        }
    }
}
