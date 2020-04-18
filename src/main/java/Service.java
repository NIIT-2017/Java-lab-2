import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Map;

public class Service {
    public static void main(String[] args) {
        Automata automata = new Automata();
        automata.work();
    }
}

class Automata {
    private Connect connect; //a link module
    private float cash;
    private Map<String, Object> menu;
    private STATES state;

    private String order;

    public Automata() {
        connect = new Connect();
        Init();
    }
    public Automata(String[] params) {
       connect = new Connect(params);
        Init();
    }
    public Automata(Object input, Object output) {
        connect = new Connect(input, output);
        Init();
    }
    //Initialization of fields
    private void Init(){
        cash = 0f;
        menu = Stuff.getMenu();
        setState(STATES.OFF);
        order = "";
    }

    private void on() {
        setState(STATES.WAIT);
    }

    private void off() {
        setState(STATES.OFF);
        cash = 0f;
        order = "";
    }

    private void coin(float money) {
        if (money <= 0) {
            connect.write("Invalid value of money!");
            return;
        }
        cash += money;
        if (state == STATES.WAIT)
            setState(STATES.ACCEPT);
        connect.write("cash : " + cash);
    }

    private void getMenu() {
        connect.write("Menu:");
        for (Map.Entry<String, Object> entry : menu.entrySet()) {
            connect.write(entry.getKey() + " : " + entry.getValue());
        }
    }

    private void getState() {
        connect.write("The Automate is " + state + ". Cash : " + cash);
    }

    private void choice(String order) {
        if (menu.containsKey(order)) {
            this.order = order;
            setState(STATES.CHECK);
        } else
            connect.write("Invalid order : " + order);
    }

    private void check() {
        if (findOut())
            connect.write("Cash is enough, you can cook the beverage");
    }

    //Check, is there cash enough for the order
    private boolean findOut() {
        if (cash < (Integer)menu.get(order)) {
            connect.write("Cash = " + cash);
            connect.write("Price of " + order + " = " + menu.get(order));
            connect.write("You have to add " + ((Integer)menu.get(order) - cash));
            return false;
        }
        return true;
    }

    private void cancel() {
        setState(STATES.WAIT);
        order = "";
    }

    private void cook() {
        if (findOut()) {
            cash -= (Integer)menu.get(order);
            setState(STATES.COOK);
            connect.write("cooking...");
            connect.write("Please, your " + order);
            finish();
        }
    }

    private void finish() {
        setState(STATES.WAIT);
        order = "";
    }

    //Run work
    public void work(){
        while(command(connect.read()));
    }

    //Initial parsing of the command
    private boolean command(String command){
        if (!command.isEmpty())
            return execute(command.split("\\s+"));
        else {
            connect.write("You must write a command");
            return true;
        }
    }

    //Execute a command, if type of the command and extra params are valid
    //Return false if the command "exit" is given
    private boolean execute(String[] params){
        COMMANDS cmnd = COMMANDS.EMPTY;
        boolean continuation = true;
        for (COMMANDS command : COMMANDS.values()){
            if (params[0].equalsIgnoreCase(command.toString()))
                cmnd = command;
        }
        switch(cmnd){
            case EXIT -> continuation = false;
            case ON -> {
                if (state == STATES.OFF)
                    on();
            }
            case OFF -> {
                if (state == STATES.WAIT)
                    off();
            }
            case COIN -> {
                if (state == STATES.WAIT || state == STATES.ACCEPT) {
                    if (params.length >= 2) {
                        try {
                            coin(Float.parseFloat(params[1]));
                        } catch (NumberFormatException ex) {
                            connect.write("Invalid format of money :" + params[1]);
                        }
                    } else
                        connect.write("You must give money if you want to do \"COIN\"");
                }
            }
            case GETMENU -> {
                if (state != STATES.OFF)
                    getMenu();
            }
            case GETSTATE -> {
                if (state != STATES.OFF)
                    getState();
            }
            case CHOICE -> {
                if (state == STATES.ACCEPT){
                    if (params.length >= 2)
                        choice(params[1]);
                    else
                        connect.write("You must write name of beverage to do \"CHOICE\"");
                }
            }
            case CHECK -> {
                if (state == STATES.CHECK)
                    check();
            }
            case CANCEL -> {
                if (state != STATES.OFF && state != STATES.WAIT)
                    cancel();
            }
            case COOK -> {
                if (state == STATES.CHECK)
                    cook();
            }
            default -> connect.write("Unknown command \"" + params[0] + "\"");
        }
        return continuation;
    }

    //Change the automate state and give a describe of the state
    private void setState(STATES state){
        switch (state) {
            case OFF -> {
                this.state = STATES.OFF;
                connect.write("The automate is disable. Possible actions : ON");
            }
            case WAIT -> {
                this.state = STATES.WAIT;
                connect.write("The automate is waiting for your command. Possible action : OFF, COIN, GETMENU, GETSTATE");
            }
            case ACCEPT -> {
                this.state = STATES.ACCEPT;
                connect.write("You money is accepted. Possible actions : COIN, CHOICE, CANCEL, GETMENU, GETSTATE");
            }
            case CHECK -> {
                this.state = STATES.CHECK;
                connect.write("You made a choice. Possible actions : CANCEL, CHECK, COOK, GETMENU, GETSTATE");
            }
            case COOK -> {
                this.state = STATES.COOK;
                connect.write("The cooking has started. Possible actions : just relax and wait");
            }
            default -> connect.write("Unknown state!");
        }
    }
}

//types of command
enum COMMANDS {
    EMPTY, EXIT, ON, OFF, COIN, GETMENU, GETSTATE, CHOICE, CHECK, CANCEL, COOK
 }

 //types of automate state
 enum STATES {
    OFF, WAIT, ACCEPT, CHECK, COOK
 }

