import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Properties;

enum STATES{OFF, WAIT, ACCEPT, CHECK, COOK};

public class Automata {
    private int cash;
    private String[] menu;
    private int[] prices;
    private STATES state;

    Automata(String fileName) {
        cash = 0;
        state = STATES.OFF;
        try {
            URL url1 = System.class.getResource(fileName);
            File file = new File(url1.getFile());
            FileInputStream fileInput = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fileInput);
            fileInput.close();
            Enumeration enuKeys = properties.keys();

            final int propSize = properties.size();
            menu = new String[propSize];
            for (int i = 0; i < propSize; i++)
                menu[i] = "";
            prices = new int[propSize];

            int idx = 0;
            while (enuKeys.hasMoreElements()) {
                menu[idx] += (String)enuKeys.nextElement();
                prices[idx] = Integer.parseInt(properties.getProperty(menu[idx]));
                idx++;
            }
            sortMenu();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Automata() {
        cash = 0;
        state = STATES.OFF;
        menu = new String[] {"Tea", "Americano", "Espresso"};
        prices = new int[] {15, 25, 30};
        sortMenu();
    }

    private void sortMenu() {
        String[] menu2 = menu.clone();
        int[] prices2 = prices.clone();
        Arrays.sort(prices2);
        int[] prices4 = prices.clone();
        int[] idxArr = new int[menu.length];
        for (int i = 0; i < menu.length; i++) {
            for (int k = 0; k < menu.length; k++) {
                if (prices2[i] == prices4[k]) {
                    idxArr[i] = k;
                    prices4[k] = -1;
                    break;
                }
            }
        }
        for (int i = 0; i < menu.length; i++) {
            menu[i] = "";
            menu[i] += menu2[idxArr[i]];
        }
        prices = prices2.clone();
    }

    public int getCash() {return cash;}
    public STATES getState() {return state;}
    public void setCash(int cash) {this.cash = cash;}
    public void setState(STATES state) {this.state = state;}

    public String[] getMenuStr() {
        String[] strArr = new String[menu.length+1];
        strArr[0] = "Automata menu: ";
        for (int i = 0; i < menu.length; i++) {
            strArr[i+1] = (String)((i+1) + ". " + menu[i] + ", " + prices[i] + " rub.");
        }
        return strArr;
    }

    public String getStateStr() {
        return (String)("Automata state: " + state);
    }

    public String getCashStr() {
        return (String)("Available cash: " + cash + " rub.");
    }

    public void on() {
        if (state == STATES.OFF) {
            state = STATES.WAIT;
            cash = 0;
        }
    }

    public void off() {
        if (state == STATES.WAIT) {
            state = STATES.OFF;
            cash = 0;
        }
    }

    public void coin(int sum) {
        if ((state == STATES.WAIT) || (state == STATES.ACCEPT)) {
            state = STATES.ACCEPT;
            cash += sum;
        }
    }

    public int cancel() {
        if ((state == STATES.ACCEPT) || (state == STATES.CHECK)) {
            state = STATES.WAIT;
            int change = cash;
            cash = 0;
            return change;
        }
        return -1;
    }

    public int[] choice(int userChoiceIdx) {
        int[] arr = new int[] {0,0};
        userChoiceIdx--;
        if (state == STATES.ACCEPT) {
            state = STATES.CHECK;
            if ((userChoiceIdx >= 0) && (userChoiceIdx < menu.length)) {
                if (check(userChoiceIdx)) {
                    cook(userChoiceIdx);
                    arr[0] = finish(userChoiceIdx);
                    arr[1] = 1;
                }
                else {
                    arr[0] = cancel();
                    arr[1] = 2;
                }
            }
            else {
                arr[0] = cancel();
                arr[1] = 3;
            }
        }
        return arr;
    }

    private boolean check(int userChoiceIdx) {
        return (state == STATES.CHECK) && (cash >= prices[userChoiceIdx]);
    }

    private void cook(int userChoiceIdx) {
        if (state == STATES.CHECK) {
            state = STATES.COOK;
            try {Thread.sleep(10000);}
            catch(InterruptedException ex) {;}
        }
    }

    private int finish(int userChoiceIdx) {
        if (state == STATES.COOK) {
            state = STATES.WAIT;
            int change = cash - prices[userChoiceIdx];
            cash = 0;
            return change;
      }
      return -1;
    }
}
