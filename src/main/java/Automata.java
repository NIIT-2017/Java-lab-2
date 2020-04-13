import org.apache.commons.io.FileUtils;
import org.json.JSONObject;


import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

public class Automata {

    enum States {
        OFF, WAIT, ACCEPT, CHECK, COOK
    }

    private int cash;
    private ArrayList<String> menu = new ArrayList<>();
    private ArrayList<Integer> prises = new ArrayList<>();
    private States state;

    public Automata(String menu) {
        cash = 0;
        state = States.OFF;
        try {
            URL resource = getClass().getResource(menu);
            File file = Paths.get(resource.toURI()).toFile();
            String content = FileUtils.readFileToString(file, "utf-8");
            JSONObject menu1 = new JSONObject(content);
            Iterator<String> keys = menu1.keys();
            int i = 0;
            while(keys.hasNext()) {
                this.menu.add(Integer.toString(i)+" - "+keys.next());
                i++;
            }
            Iterator<String> values = menu1.keys();
            while(values.hasNext()) {
                this.prises.add(menu1.getInt(values.next()));
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String on() {
        if (state == States.OFF) {
            state = States.WAIT;
        }
        return state.toString();
    }
    public String off() {
        if (state == States.WAIT) {
            state = States.OFF;
        }
        return state.toString();
    }

    public String coin(int coin) {
        if ((state == States.WAIT || state == States.ACCEPT || state == States.CHECK)&& coin>0) {
            state = States.ACCEPT;
            cash = cash + coin;
            return Integer.toString(cash);
        } else return "Операция невозможна!";
    }

    public ArrayList<String>getMenu(){
        if (state == States.WAIT || state == States.ACCEPT) {
            return menu;
        }
        else{
            ArrayList<String> error = new ArrayList<>();
            error.add("Операция невозможна!");
            return error;
        }
    }

    public String getState() {
        return state.toString();
    }
    private boolean check(int price){
        if (cash>= price)
            return true;
        else
            return false;
    }
    private String cook(){
        try {
            Thread.sleep(6000); // реализована адержка времени в 6 секунд
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        state = States.COOK;
        return state.toString();
    }
    public String finish(String surrender){
        if (state == States.COOK){
            if(surrender=="YES"&& cash%5 == 0){
                state = States.WAIT;
                int money = cash;
                cash = 0;
                return Integer.toString(money);
            }
            else {
                state = States.WAIT;
                cash = 0;
                return "Благодарим за покупку!";
            }
        }
        else return "Операция невозможна!";
    }
    public String cancel(){
        if (state == States.ACCEPT || state == States.COOK){
            state = States.WAIT;
            return state.toString();
        }
        else return "Операция невозможна!";
    }
    public int checkcash (){
        if (state!=States.OFF){
            return cash;
        }
        else return 0;
    }
    public String choise(int number){
        if (state == States.ACCEPT){
            if (menu.contains(menu.get(number))){
                state = States.CHECK;
                if(check(prises.get(number))){
                    cash=cash-prises.get(number);
                    cook();
                    return Integer.toString(cash);
                }
                else{
                    return "Недостаточно средств на счете!";
                }
            }
            else{
                return "Выбранного вами напитка не существует";
            }
        }
        else return "Операция невозможна!";
    }
}