public class Automata
{
    enum Regime {
        MANUAL, RANDOM, COMMAND
    }
    enum State {
        OFF, ON, WAIT, ACCEPT, CHECK, COOK
    }
    enum UserCommand {
        NULL, COIN, DRINK, CHANGE, EXIT
    }
    private Regime regime;
    private State state, nextState;
    private UserCommand userCom;
    private double cash;
    private boolean workStat, checkStat;
    private int drinksNum;
    Automata(Regime regime) {
        this(regime, State.OFF, UserCommand.NULL, 0);
    }
    private Automata(Regime regime, State state, UserCommand userCom, double cash) {
        this.regime = regime; this.state = state; this.userCom = userCom; this.cash = cash;
    }
    public void on() {
        if (state == State.OFF) {
            state = State.ON;
            AutomataLoger.statOn();
            AutomataLoger.status(state, userCom, cash);
        }
    }
    public void off() {
        if (state == State.ON) {
            state = State.OFF;
            AutomataLoger.status(state, userCom, cash);
            AutomataLoger.statOff();
        }
    }
    public void start() {
        if (state == State.ON) {
            workStat = true;
            state = State.WAIT;
            AutomataLoger.statStart();
            work();
        }
    }
    private void coin() {
        AutomataLoger.selCoin();
        double val=-1;
        switch(regime) {
            case MANUAL:
                while (val <= 0) val = AutomataScan.scanCoin();
                break;
            case RANDOM:
                val = AutomataRand.randCoin(AutomataRand.getIntMin(), AutomataRand.getIntMax());
                break;
            case COMMAND:
                val = AutomataCom.getCoin();
                break;
        }
        if (regime == Regime.MANUAL) AutomataLoger.nextLine(val);
        else AutomataLoger.coin(val);
        cash += val;
    }
    private void drink() {
        AutomataLoger.menu();
        AutomataLoger.selDrink();
        int val=-1;
        drinksNum=-1;
        switch(regime) {
            case MANUAL:
                while (val < 1 | val > AutomataMenu.getMenuLength()) val = AutomataScan.scanNum();
                val--;
                break;
            case RANDOM:
                val = AutomataRand.randNum(0, AutomataMenu.getMenuLength()-1);
                break;
            case COMMAND:
                val = AutomataCom.getDrinksNum();
                break;
        }
        if (regime == Regime.MANUAL) AutomataLoger.nextLine(val);
        else AutomataLoger.num(val + 1);
        drinksNum = val;
    }
    private void change() {
        double change = cash;
        cash = 0;
        AutomataLoger.change(change);
    }
    private void exit() {
        workStat = false;
        AutomataLoger.exit(workStat);
    }
    private void autoStateWait() {
        AutomataLoger.menu();
        AutomataLoger.select(state);
        int val=-1;
        UserCommand curCom = UserCommand.NULL;
        switch(regime) {
            case MANUAL:
                while (val < 1 | val > 2) val = AutomataScan.scanNum();
                break;
            case RANDOM:
                val = AutomataRand.randNum(1, 2);
                break;
            case COMMAND:
                curCom = AutomataCom.getAction();
                if (curCom == UserCommand.COIN) val = 1;
                if (curCom == UserCommand.EXIT) val = 2;
                break;
        }
        if (val == 1) curCom = UserCommand.COIN;
        if (val == 2) curCom = UserCommand.EXIT;
        if (regime == Regime.MANUAL) AutomataLoger.nextLine(val);
        else AutomataLoger.num(val);
        userCom = curCom;
    }
    private void autoStateAccept() {
        AutomataLoger.menu();
        AutomataLoger.select(state);
        UserCommand curCom = UserCommand.NULL;
        int val = -1;
        switch (regime) {
            case MANUAL:
                while (val < 1 | val > 3) val = AutomataScan.scanNum();
                break;
            case RANDOM:
                val = AutomataRand.randNum(1, 3);
                break;
            case COMMAND:
                curCom = AutomataCom.getAction();
                if (curCom == UserCommand.DRINK) val = 1;
                if (curCom == UserCommand.COIN) val = 2;
                if (curCom == UserCommand.CHANGE) val = 3;
                break;
        }
        if (val == 1) curCom = UserCommand.DRINK;
        if (val == 2) curCom = UserCommand.COIN;
        if (val == 3) curCom = UserCommand.CHANGE;
        if (regime == Regime.MANUAL) AutomataLoger.nextLine(val);
        else AutomataLoger.num(val);
        userCom = curCom;
    }
    private void autoStateCheck() {
        double drinksPrice = AutomataMenu.getPrice(drinksNum);
        if (cash >= drinksPrice) {
            AutomataLoger.checkTrue(drinksPrice);
            cash -= drinksPrice;
            checkStat = true;
        }
        else {
            AutomataLoger.checkFalse();
            checkStat = false;
        }
    }
    private void autoStateCook() {
        AutomataLoger.cook(drinksNum);
        AutomataLoger.cookFinish(drinksNum);
    }
    private void functionState () {
        if (state == State.WAIT | state == State.ACCEPT)
            userCom = UserCommand.NULL;
        AutomataLoger.status(state, userCom, cash);
        switch(state) {
            case WAIT:
                autoStateWait();
                break;
            case ACCEPT:
                autoStateAccept();
                break;
            case CHECK:
                autoStateCheck();
                break;
            case COOK:
                autoStateCook();
                break;
        }
    }
    private void functionMove () {
        if (state == State.WAIT | state == State.ACCEPT)
            AutomataLoger.status(state, userCom, cash);
        switch(state) {
            case WAIT:
                switch(userCom) {
                    case COIN:
                        coin();
                        nextState = State.ACCEPT;
                        break;
                    case EXIT:
                        exit();
                        nextState = State.ON;
                        break;
                }
                break;
            case ACCEPT:
                switch(userCom) {
                    case DRINK:
                        drink();
                        nextState = State.CHECK;
                        break;
                    case COIN:
                        coin();
                        nextState = State.ACCEPT;
                        break;
                    case CHANGE:
                        change();
                        nextState = State.WAIT;
                        break;
                }
                break;
            case CHECK:
                if (!checkStat)
                    nextState = State.ACCEPT;
                else
                    nextState = State.COOK;
                break;
            case COOK:
                nextState = State.ACCEPT;
                break;
        }
    }
    private void work() {
        while (workStat) {
            functionState();
            functionMove();
            state = nextState;
        }
    }
}
