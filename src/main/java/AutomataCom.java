public class AutomataCom
{
    private static double coins[];
    private static int drinksNum[];
    private static Automata.UserCommand actions[];
    private static int coinsCur, drinksNumCur, actionsCur;
    private static void setCoins(double[] coinsArr) {
        coins = coinsArr;
    }
    private static void setDrinksNum(int[] drinksNumArr) {
        drinksNum = drinksNumArr;
    }
    private static void setActions(Automata.UserCommand[] actionsArr) {
        actions = actionsArr;
    }
    public static double getCoin() {
        return coins[coinsCur++];
    }
    public static int getDrinksNum() {
        return drinksNum[drinksNumCur++];
    }
    public static Automata.UserCommand getAction() {
        return actions[actionsCur++];
    }
    private static String[] split(String str) {
        return str.split(",");
    }
    private static double parsCheck(String strVal) {
        if (strVal.equals("")) return 0;
        else return Double.parseDouble(strVal);
    }
    private static double[] coinsArr(String[] strArr) {
        double[] coinsArr = new double[strArr.length];
        for(int i=0; i < coinsArr.length; i++) coinsArr[i] = parsCheck(strArr[i]);
        return coinsArr;
    }
    private static int[] drinksNumArr(String[] strArr) {
        int[] drinksNumArr = new int[strArr.length];
        for(int i=0; i < drinksNumArr.length; i++) drinksNumArr[i] = AutomataMenu.getDrinksNum(strArr[i]);
        return drinksNumArr;
    }
    private static Automata.UserCommand[] actionsArr(String[] strArr) {
        Automata.UserCommand[] actionsArr = new Automata.UserCommand[strArr.length];
        for(int i=0; i < actionsArr.length; i++) actionsArr[i] = Automata.UserCommand.valueOf(strArr[i]);
        return actionsArr;
    }
    private static void setCommands(double coinsArr[], int drinksNumArr[], Automata.UserCommand actionsArr[]) {
        coinsCur = 0;
        drinksNumCur = 0;
        actionsCur = 0;
        setCoins(coinsArr);
        setDrinksNum(drinksNumArr);
        setActions(actionsArr);
    }
    public static boolean setCheck(String[] commands) {
        setCommands(coinsArr(split(commands[0])), drinksNumArr(split(commands[1])), actionsArr(split(commands[2])));
        if (AutomataScenario.check(coins, drinksNum, actions)) return true;
        else return false;
    }
}
