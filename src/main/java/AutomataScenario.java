public class AutomataScenario
{
    private static double coins[];
    private static int drinksNum[];
    private static Automata.UserCommand actions[];
    private static int errCurCount;
    private static void setCoins(double[] coinsArr) {
        coins = coinsArr;
    }
    private static void setDrinksNum(int[] drinksNumArr) {
        drinksNum = drinksNumArr;
    }
    private static void setActions(Automata.UserCommand[] actionsArr) {
        actions = actionsArr;
    }
    private static void setParamCheck(double coinsArr[], int drinksNumArr[], Automata.UserCommand actionsArr[]) {
        setCoins(coinsArr);
        setDrinksNum(drinksNumArr);
        setActions(actionsArr);
    }
    private static boolean coinNegative() {
        int count=0;
        for(int i=0; i < coins.length; i++)
            if (coins[i] < 0) {
                if (++count == 1) AutomataLoger.mesErrLine(++errCurCount + ". ");
                if (count != 1) AutomataLoger.mesErrLine(", ");
                AutomataLoger.mesErrLine(i+1 + "-я");
            }
        if (count > 0) {
            AutomataLoger.mesErr(" вносимая сумма должна быть больше нуля.");
            return false;
        }
        return true;
    }
    private static boolean drinkNotFound() {
        int count=0;
        for(int i=0; i < drinksNum.length; i++)
            if (drinksNum[i] == -1) {
                if (++count == 1) AutomataLoger.mesErrLine(++errCurCount + ". ");
                if (count != 1) AutomataLoger.mesErrLine(", ");
                AutomataLoger.mesErrLine(i+1 + "-й");;
            }
        if (count > 0) {
            AutomataLoger.mesErr(" напиток в меню не найден.");
            return false;
        }
        return true;
    }
    private static boolean coinStart() {
        if (actions[0] != Automata.UserCommand.COIN) {
            AutomataLoger.mesErr(++errCurCount + ". " + "Первой должна быть комманда <COIN>.");
            return false;
        }
        return true;
    }
    private static boolean coinCount() {
        int coinCount=0;
        for(int i=0; i < actions.length; i++) if (actions[i] == Automata.UserCommand.COIN) coinCount++;
        if (coinCount != coins.length) {
            AutomataLoger.mesErr(++errCurCount + ". " + "Количество комманд <COIN> должно быть равно количеству coins.");
            return false;
        }
        return true;
    }
    private static boolean drinkCount() {
        int drinkCount=0;
        for(int i=0; i < actions.length; i++) if (actions[i] == Automata.UserCommand.DRINK) drinkCount++;
        if (drinkCount == 0 & drinksNum[0] == -2) return true;
        if (drinkCount != drinksNum.length) {
            AutomataLoger.mesErr(++errCurCount + ". " + "Количество комманд <DRINK> должно быть равно количеству drinks.");
            return false;
        }
        if (drinkCount > 0 & drinksNum[0] == -2) {
            AutomataLoger.mesErr(++errCurCount + ". " + "Ни один напиток не указан.");
            return false;
        }
        return true;
    }
    private static boolean changeCoin() {
        for (int i=1; i < actions.length - 3; i++)
            if (actions[i] == Automata.UserCommand.CHANGE & actions[i+1] != Automata.UserCommand.COIN) {
                AutomataLoger.mesErr(++errCurCount + ". " + "Если комманда <CHANGE> не перед <EXIT>, то после нее должна быть комманда <COIN>.");
                return false;
            }
        return true;
    }
    private static boolean changeChange() {
        for (int i=1; i < actions.length - 2; i++)
            if (actions[i] == Automata.UserCommand.CHANGE & actions[i+1] == Automata.UserCommand.CHANGE) {
                AutomataLoger.mesErr(++errCurCount + ". " + "Комманда <CHANGE> не может повторяться более 1-го раза подряд.");
                return false;
            }
        return true;
    }
    private static boolean changeExit() {
        if (actions[actions.length-2] != Automata.UserCommand.CHANGE) {
            AutomataLoger.mesErr(++errCurCount + ". " + "Перед коммандой <EXIT> должна быть комманда <CHANGE>.");
            return false;
        }
        return true;
    }
    private static boolean exitCount() {
        for(int i=0; i < actions.length-1; i++)
            if (actions[i] == Automata.UserCommand.EXIT) {
                AutomataLoger.mesErr(++errCurCount + ". " + "Комманда <EXIT> должна быть одна и последней.");
                return false;
            }
        return true;
    }
    private static boolean exitEnd() {
        if (actions[actions.length-1] != Automata.UserCommand.EXIT) {
            AutomataLoger.mesErr(++errCurCount + ". " + "Последней должна быть комманда <EXIT>.");
            return false;
        }
        return true;
    }
    public static boolean trivial() {
        return (coins.length == 1 & coins[0] == 0 & drinksNum.length == 1 & drinksNum[0] == -2 & actions.length == 1 & actions[0] == Automata.UserCommand.EXIT);
    }
    public static boolean checking() {
        errCurCount=0;
        if (trivial()) return true;
        else return (coinNegative()&drinkNotFound()&coinStart()&coinCount()&drinkCount()&changeCoin()&changeChange()&changeExit()&exitCount()&exitEnd());
    }
    public static boolean check(double coinsArr[], int drinksNumArr[], Automata.UserCommand actionsArr[]) {
        setParamCheck(coinsArr, drinksNumArr, actionsArr);
        AutomataLoger.mesErrStart("Проверка сценария:");
        if (checking()) {
            AutomataLoger.mesErrEnd("Сценарий корректен.");
            return true;
        }
        else {
            AutomataLoger.mesErrEnd("Сценарий некорректен! Необходимо внести исправления!");
            return false;
        }
    }
}
