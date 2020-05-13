import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Automata {
    enum STATES {OFF, WAIT, ACCEPT, CHECK, COOK}
    private int cash;
    private int change;
    private ArrayList<String> menu = new ArrayList<>();
    private ArrayList<Integer> prices = new ArrayList<>();
    private STATES state;
    public Automata(String fileMenu){
        cash = 0;
        state = STATES.OFF;
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileMenu).getFile());
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(file));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray drinks = (JSONArray) jsonObject.get("menu");
            for(int i = 0 ; i< drinks.size() ;i++){
                JSONObject object = (JSONObject) drinks.get(i);
                String name = (String) object.get("name");
                menu.add(name);
                int price = Integer.valueOf((String)object.get("price"));
                prices.add(price);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<String> getMenu(){
        return menu;
    }
    public ArrayList<Integer> getPrices(){
        return prices;
    }
    public int getCash(){
        return cash;
    }
    public int getChange(){
        return change;
    }
    public STATES getState(){
        return state;
    }
    public void on(){
        if(state.equals(STATES.OFF))
            state = STATES.WAIT;
    }
    public void coin(int value){
        if(state.equals(STATES.WAIT)){
            change=0;
            cash+=value;
            state=STATES.ACCEPT;
        }
        else if(state.equals(STATES.ACCEPT)){
            cash+=value;
        }
    }
    public void choice(int numberOfButton){
        int price=0;
        if(state.equals(STATES.ACCEPT)){
            price = prices.get(numberOfButton);
            state = STATES.CHECK;
        }
        check(price);
    }
    private void check(int price){
        if(state.equals(STATES.CHECK)){
            if (cash<price)
                cancel();
            else {
                change=cash-price;
                cash=0;
                cook();
            }
        }
    }
    public void cancel (){
        if(state.equals(STATES.ACCEPT) || state.equals(STATES.CHECK)){
            change = cash;
            cash = 0;
            state = STATES.WAIT;
        }
    }
    private void cook (){
        if(state.equals(STATES.CHECK)) {
            state = STATES.COOK;
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finish();
        }
    }
    private void finish(){
        if(state.equals(STATES.COOK)) {
            state = STATES.WAIT;
        }
    }
    public void off(){
        if(state.equals(STATES.WAIT)) {
            state = STATES.OFF;
        }
    }
}
