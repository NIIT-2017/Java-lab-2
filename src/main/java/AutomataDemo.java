import sun.rmi.runtime.Log;
import java.io.*;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Logger;


class Automata {

        private static int cash;
        private int price;
        private int change;

    public Automata(int cash, int price, int change) {
        this.cash = cash;
        this.price = price;
        this.change = change;
    }

    public static int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getChange() {
        return change;
    }

    public void setChange(int change) {
        this.change = change;
    }

    public Automata() {

        }


        //     public int change;
        public Properties getMenu() {
            return menu;
        }

        enum STATES {
            OFF,
            WAIT,//switch-on state
            ACCEPT,
            CHECK,
            COOK
        }

        private STATES state;
        private Properties menu;

        Automata(Properties menu) {
            STATES state = STATES.OFF;
        }

        public void on() {
            state = STATES.WAIT;
            AutomataDemo.logfile.println("Automata is switched on. Choose your drink.");
        }

        public int cancel(int moneyForReturn){
            state=STATES.WAIT;
            cash=moneyForReturn;
           // AutomataDemo.setLogfile("Take your money back");
           // AutomataDemo.setLogfile(setCash(cash));
            setCash(cash);
            return cash;
        }

        public void off() { state = STATES.OFF;
            AutomataDemo.logfile.println("Automata is switched off");
        }

        public int coin(int addMoney) {
            //передать деньги int coin(10)
            if(state == STATES.WAIT) {
                state = STATES.ACCEPT;
                cash+=addMoney;
                setCash(cash);
            }else {
                state = STATES.ACCEPT;
                cash+=addMoney;
                setCash(cash);
            }
//        cash=inputMoney;
           AutomataDemo.logfile.println("Please input money. Money accepted. You give: "+cash+" rubles");
            return cash;
        }

        public void setMenu(Properties menu) {
            this.menu = menu;
        }

        public STATES choice(int priceOfDrink) throws InterruptedException {
            /* call check cook finish */
            price=priceOfDrink;
          //  System.out.println("Please, choose your drink...");
         //   System.out.println("price is: "+price);
            if(cash>=price){
                check(cash,price);
                cook();
                finish();
                return state;
            }else
                check(cash,price);
            return state;
        }

        private void check(int cashInput, int priceOfDrink) throws InterruptedException {
            state=STATES.CHECK;
            cash=cashInput;
            price=priceOfDrink;
            if (cash == price) {
                state = STATES.COOK;
//                System.out.println("state: "+state);
            }
            else if(cash>price) {
                state = STATES.COOK;
                returnChange(cashInput, priceOfDrink);
                AutomataDemo.logfile.println("Take your change..."+returnChange(cashInput,priceOfDrink));

// System.out.println("state: "+state);
            }
            else if (cash < price) {
                state = STATES.WAIT;
                AutomataDemo.logfile.println("Not enough money. If you want to continue, add some coins...");
                coin(60);
                AutomataDemo.logfile.println("You give: "+String.valueOf((cash)));
                setCash(cash);
                if(cash>=price) {
                    cook();
                    returnChange(cash, priceOfDrink);
                }else{
                    AutomataDemo.logfile.println("Still not enough money.");
                    cancel(cash);
                }


            } else {
                state = STATES.WAIT;
                AutomataDemo.logfile.println("If you don't want to continue, press CANCEL");
                cancel(cash);
            }
        }

        private void cook() throws InterruptedException {//InterruptedException сигнализирует о том, что поток просят завершить его работу. При этом вас не просят немедленно завершить свою работу. Вас просят корректно завершить работу. На это может понадобится некоторое время
            AutomataDemo.logfile.println("Please wait 5 sec. Your drink is preparing...");

            Thread.sleep(5000);
            state=STATES.WAIT;
        }
        public int returnChange(int cashInput, int priceOfDrink){
            state=STATES.CHECK;
            setCash(cash);
            cash=cashInput;
            price=priceOfDrink;
          //  setPrice(price);
            change=cash-price;
            setChange(change);
            AutomataDemo.logfile.println("Take your change: "+getChange());
            return getChange();
        }
        private void finish(){
            state=STATES.WAIT;
            //"Done."


        }

    }

     class AutomataDemo {
    //сценарий с описанием работы

        public static PrintStream logfile;

         static {
             try {

                 logfile = new PrintStream(new File("log.txt"));
             } catch (FileNotFoundException e) {
                 e.printStackTrace();
             }
         }

         public AutomataDemo(PrintStream logfile) throws FileNotFoundException {
            this.logfile=logfile;
        }




        public static void main(String args[]) throws InterruptedException, FileNotFoundException {

//            PrintStream logfile = new PrintStream(new File("log.txt"));

            File file = new File("src/main/resources/menu.properties");
            Properties properties = new Properties();
            System.setOut(logfile);
            Automata nescafe = new Automata(properties);
            nescafe.on();//включение
            //logfile.println("Automata is switched on. Choose your drink.");
            int value = 0;
            try {

                FileInputStream fileInput = new FileInputStream(file);
                properties.load(fileInput);

                Enumeration names = properties.keys();
                while (names.hasMoreElements()) {
                    String key = (String) names.nextElement();
                    value = Integer.parseInt(properties.getProperty(key));
                   // logfile.println(key + ":" + value);
                }
                String latte = properties.getProperty("latte");
                String capuccino = properties.getProperty("capuccino");
                String coffeBlack = properties.getProperty("coffeBlack");
                String espresso = properties.getProperty("espresso");

                logfile.println("MENU: " + "latte: " + latte +
                        ", capuccino: " + capuccino +
                        " ,coffeBlack: " + coffeBlack +
                        " ,espresso: " + espresso);
                fileInput.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            Properties menu = nescafe.getMenu();
            String ourChoice;
            ourChoice = (properties.getProperty("latte"));
            logfile.println("Price of your drink is: "  + ourChoice);
            nescafe.coin(30);
            nescafe.choice(Integer.parseInt((ourChoice)));

            nescafe.off();
        }

//        public static void setLogfile() {
//        }
    }

