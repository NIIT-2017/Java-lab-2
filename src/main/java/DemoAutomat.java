import java.util.*;

class Automat {

    private static String choice;


    enum States {
        ON, OFF, PRINTMENU, CHOICE, CANCEL, COOK, WAIT
    }

    public static int Cash = 0;
    public static int DrinkSelection = 0;
    public static int res;
    public static int EnCook = 0;
    public static int CookFinal = 0;
    public static Deque<String> Result = new ArrayDeque<String>();
    public static int FlagPower = 0;
    public static Map<String,Integer> menu = new HashMap<String, Integer>(){
        {
            put("milk", 37);
            put("water", 50);
            put("coffee", 83);
            put("juce", 20);
            put("beer", 130);
        }
    };

   Automat(){
       Cash = 0;
       DrinkSelection = 0;
       res=0;
       EnCook = 0;
       CookFinal = 0;
       FlagPower = 0;
   }

    public static void coin(int cash) {
        Cash = Cash+cash;

    }

    public static String choice(Map<String,Integer> choice)
    {
        Object[] keys = choice.keySet().toArray();
        Object key = keys[new Random().nextInt(keys.length)];
        return (String) key;
    }
    public static int check(int p){
        int k = p-Cash;
        if (k <= 0) Result.offer("Отличный выбор");
        else Result.offer("Недостаточно денег, внесите еще");
        return k;
    }

    public static ArrayList<String> printMenu(Map<String,Integer> menu) {
        ArrayList<String> s = new ArrayList<String>();
        for (Map.Entry<String,Integer> entry: menu.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            s.add(key+ " "+value);
            Result.offer(String.format("%s : %s", key, value));
        }
return s;
    }


    public static void printState(String result){
        Result.offer(result);
    }

    public static void automatStates(States state) {

        switch (state) {
            case ON:
                if (FlagPower == 0) {
                    FlagPower = 1;
                    printState("Автомат включен");
                    automatStates(States.WAIT);
                } else
                    printState("Автомат уже включен");
                break;
            case OFF:
                FlagPower = 0;
                res = 0;
                printState("Автомат выключен");
                break;
            case PRINTMENU:
                if (FlagPower == 1) {
                    ArrayList<String> s = printMenu(menu);
                    printState("Выберите напиток");
                    automatStates(States.CHOICE);
                } else printState("Автомат выключен");
                break;
            case CHOICE:
                if (FlagPower == 1) {
                    choice = choice(menu);
                    int price = menu.get(choice);
                    DrinkSelection = 1;
                    printState("Вы выбрали: " + choice + " за " + price);
                    res = check(price);
                    if (res <= 0) {
                        EnCook = 1;
                        } else automatStates(States.WAIT);
                } else printState("Автомат выключен");
                break;
            case CANCEL:
                if (FlagPower == 1) {
                    printState("Ваша сдача: " + Cash);
                    DrinkSelection = 0;
                    automatStates(States.WAIT);
                }
                else printState("Автомат выключен");
                break;
            case COOK:
                if ( FlagPower == 1) {
                    if ((EnCook == 1) && (DrinkSelection == 1)) {
                        printState("Приготовление напитка");
                        printState("Напиток " + choice + " готов. Возьмите сдачу: " + res);
                        CookFinal = 1;
                        automatStates(States.WAIT);
                    } else printState("Вы не выбрали напиток или забыли внести деньги");
                }
                else printState("Автомат выключен");
                break;
            case WAIT:
                if (FlagPower == 1) {
                    if (DrinkSelection == 0)
                        Cash = 0;
                    if (CookFinal == 1) {
                        DrinkSelection = 0;
                        CookFinal = 0;
                        Cash = 0;
                    }
                    EnCook = 0;
                    printState("Режим ожидания");
                }
                else printState("Автомат выключен");
                break;

        }

    }
}

public class DemoAutomat{
     static String [] s= {"ON", "200", "PRINTMENU", "COOK","10", "120","PRINTMENU", "COOK", "OFF"};
    static String k="";
    public static void main(String [] args){
        Automat a = new Automat();


            for (int i=0; i<s.length;i++) {
                k = s[i];
                int myInt = checkString(k) ? 1 : 0;
                if (myInt == 1) {
                    a.coin(Integer.parseInt(k));
                    System.out.println("На счету: " + a.Cash + ". При необходимости внесите еще.");
                } else {
                    Automat.States readline = Automat.States.valueOf(k);
                    Automat.automatStates(readline);
                    while(Automat.Result.size() != 0)   {
                        System.out.println(Automat.Result.pop());
                    }
                }
            }

    }

    public static boolean checkString(String string) {
        try {
            Integer.parseInt(string);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
