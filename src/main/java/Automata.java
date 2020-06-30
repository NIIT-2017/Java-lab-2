import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Automata {
    ArrayList<String> menu = new ArrayList<String>();
    ArrayList<Integer> prices = new ArrayList<Integer>();
    private int cash;
    private int anInt;
    private States state;
    enum States {
        OFF, WAIT, ACCEPT, CHECK, COOK
    }

    Automata(){
        try{
            URL menuRec= Automata.class.getResource("/menu.txt");
            URL pricesRec= Automata.class.getResource("/prices.txt");
            File menuFile = Paths.get(menuRec.toURI()).toFile();
            File pricesFile = Paths.get(pricesRec.toURI()).toFile();
            FileReader mfr = new FileReader(menuFile);
            FileReader pfr = new FileReader(pricesFile);
            BufferedReader mreader = new BufferedReader(mfr);
            BufferedReader preader = new BufferedReader(pfr);
            String mLine=null;
            String pLine=null;
            while ((mLine=mreader.readLine()) != null && (pLine=preader.readLine()) != null) {
                menu.add(mLine);
                prices.add(Integer.valueOf(pLine));
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace(); }

        state=States.OFF;
    }

    public ArrayList<String> getMenu(){
        ArrayList<String> menuAndPrices = new ArrayList<String>();
        if (state==States.WAIT || state==States.ACCEPT)
            for (int i = 0; i < menu.size(); i++) {
                menuAndPrices.add(menu.get(i) + "   " + prices.get(i) + " coins.");
            }
        return menuAndPrices;
    }

    public States on(){
        if (state == States.OFF ){
            state = States.WAIT;
        }
        return state;
    }

    public States off(){
        if (state == States.WAIT ) {
            state = States.OFF;
        }
        return state;
    }

    public int coin(int coins){
        if (state == States.WAIT || state == States.ACCEPT){
            state = States.ACCEPT;
            cash += coins;
        }
        return cash;
    }

    public States getState(){
        return state;
    }

    public void choice(int choiceNumb){
        if (state == States.ACCEPT && check(choiceNumb)){
            cash -= prices.get(choiceNumb - 1);
            anInt = cash;
            cook();
        } else {
            cancel();
        }
    }

    public boolean check(int choiceNumb){
        if (cash >= prices.get(choiceNumb - 1)){
            state = States.CHECK;
            return true;
        }
        return false;
    }

    public int cancel(){
        state = States.WAIT;
        cash = 0;
        anInt = cash;
        return anInt;
    }

    private void cook(){
        state = States.COOK;
        finish();
    }

    public int getAnInt(){
        return anInt;
    }

    private void finish(){
        if (state == States.COOK){
            state = States.WAIT;
            getAnInt();
        }
    }
}