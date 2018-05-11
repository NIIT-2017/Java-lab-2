public class AutomataDemo
{
    final static String[][][] scenarios = {
        {
            {"-0.50,-1.00,-1.50",
             "Cofe Espresso,Coffee America,Coffee Glases,Coffee Capucino",
             "DRINK,DRINK,COIN,DRINK,EXIT,EXIT,DRINK,CHANGE,CHANGE,COIN,CHANGE"},
            {"1.00", "", "COIN,CHANGE"},
            {"2.00", "", "COIN,DRINK,CHANGE,EXIT"},
            {"2.00", "Coffee A", "COIN,DRINK,CHANGE,EXIT"}
        },
        {
            {"", "", "EXIT"},
            {"1.00", "", "COIN,CHANGE,EXIT"},
            {"2.00", "Coffee Espresso", "COIN,DRINK,CHANGE,EXIT"},
            {"0.50,1.00,1.50",
             "Coffee Espresso,Coffee Americano,Coffee Cappuccino",
             "COIN,DRINK,COIN,DRINK,COIN,DRINK,CHANGE,EXIT"},
            {"1.30,0.50,1.00,1.50,1.00,2.50,0.50",
             "Coffee Cappuccino,Coffee Espresso,Coffee Americano,Coffee Glasses",
             "COIN,CHANGE,COIN,DRINK,CHANGE,COIN,COIN,DRINK,CHANGE,COIN,DRINK,COIN,COIN,DRINK,CHANGE,EXIT"}
        },
    };
    public static void autoManual() {
        AutomataBuild.manual();
    }
    public static void autoRandom() {
        AutomataBuild.random();
    }
    public static void autoCommand(String[] commands) {
        AutomataBuild.command(commands);
    }
    public static void autoCommandQuick(String[] commands) {
        AutomataBuild.commandQuick(commands);
    }
    public static void autoCommandTest(String[] commands) {
        AutomataBuild.commandTest(commands);
    }
    public static void autoManuals(int n) {
        for(int i=0; i < n; i++) autoManual();
    }
    public static void autoRandoms(int n) {
        for(int i=0; i < n; i++) autoRandom();
    }
    public static void autoCommands() {
        for(int i=0; i < scenarios.length; i++)
            for(int j=0; j < scenarios[i].length; j++)
                autoCommand(scenarios[i][j]);
    }
    public static void autoCommandsQuick() {
        for(int i=0; i < scenarios.length; i++)
            for(int j=0; j < scenarios[i].length; j++)
                autoCommandQuick(scenarios[i][j]);
    }
    public static String scenariosLog(String[] commands) {
        autoCommandTest(commands);
        return AutomataLoger.getLog();
    }
    public static void main(String[] args) {
        //autoManuals(3);
        //autoRandoms(5);
        //autoCommands();
        autoCommandsQuick();
    }
}
